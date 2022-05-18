/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Payment;
import entities.UserInformation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionbeans.PaymentFacade;
import sessionbeans.UserInformationFacade;

/**
 *
 * @author SE150010 Nguyen Sy Hoan
 */
@WebServlet(name = "UserController", urlPatterns = {"/user"})
public class UserController extends HttpServlet {

    @EJB
    private PaymentFacade paymentFacade;

    @EJB
    private UserInformationFacade userInformationFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String action = session.getAttribute("action").toString();

        switch (action) {
            case "index":
                index(request, response);
                break;
            
            case "changePassword":
                
                break;
                
            case "changeProcess":
                changeProcess(request, response);
                session.setAttribute("action", "changePassword");
                break;
                
            case "viewPayments":
                viewPayments(request, response);
                break;
             
            case "paymentDetail":
                paymentDetail(request, response);
                break;
                
            default:
                action = "error";
                request.setAttribute("action", action);
        }
        request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
    }
    
    private void index(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserInformation user = (UserInformation) session.getAttribute("user");
        session.removeAttribute("user");
        UserInformation nUser = userInformationFacade.find(user.getUserName());
        session.setAttribute("user", nUser);
    }

    private void changeProcess(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserInformation user = (UserInformation) session.getAttribute("user");
        session.removeAttribute("user");
        UserInformation nUser = userInformationFacade.find(user.getUserName());
        String oldPassword = request.getParameter("old-password");
        String newPassword = request.getParameter("new-password");
        String rePassword = request.getParameter("re-password");
        System.out.println(oldPassword);
        System.out.println(newPassword);
        System.out.println(rePassword);
        if (!newPassword.equals(nUser.getUserPassword())) {
            session.setAttribute("errorMessage", "Incorrect old-password");
        } else
        if (newPassword.equals(oldPassword)) {
            System.out.println("new and old");
            session.setAttribute("errorMessage", "New password must different from old password.");
        } else
        if (newPassword.equals(rePassword)) {
            System.out.println("new and reNew");
            nUser.setUserPassword(newPassword);
            userInformationFacade.edit(nUser);
            session.setAttribute("user", nUser);
            session.setAttribute("errorMessage", "");
        } else {
            System.out.println("different re");
            session.setAttribute("errorMessage", "Incorrect re-password");
        }
    }
    
    private void viewPayments(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserInformation user = (UserInformation) session.getAttribute("user");
        List<Payment> list = paymentFacade.findAll();
        List<Payment> paymentList = new ArrayList<>();
        for (Payment payment: list) {
//            System.out.println(user.getUserName());
//            System.out.println(payment.getCartID().getUserName());
            if (payment.getCartID().getUserName().getUserName().equals(user.getUserName())) {
                paymentList.add(payment);
            }
        }
        session.setAttribute("paymentList", paymentList);
    }
    
    private void paymentDetail(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int paymentID = Integer.parseInt(request.getParameter("paymentID"));
        Payment payment = paymentFacade.find(paymentID);
        session.setAttribute("payment", payment);
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
