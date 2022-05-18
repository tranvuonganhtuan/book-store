/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.UserInformation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import sessionbeans.UserInformationFacade;

/**
 *
 * @author SE151464 Nguyen Hoang Huy
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 10, // 10 KB
        maxFileSize = 1024 * 300, // 300 KB
        maxRequestSize = 1024 * 1024 // 1 MB 
)

public class LoginController extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String action = session.getAttribute("action").toString();
        String controller = session.getAttribute("controller").toString();
        String layouts = "main2";
        switch (action) {
            case "index":
                controller = "home";
                //session.setAttribute("controller", controller);
                layouts = "main";
                request.getRequestDispatcher("/home/index.do").forward(request, response);
                break;
            case "login":
                break;
            case "goin":
                layouts = goin(request, response);
                break;
            case "register":
                break;
            case "logout":
                logout(request, response);
                break;
            default:
                action = "error";
                session.setAttribute("action", action);
        }
        System.out.println(controller + " " + action);
        request.getRequestDispatcher("/WEB-INF/layouts/" + layouts + ".jsp").forward(request, response);
    }

    public String goin(HttpServletRequest request, HttpServletResponse response) {
        String layout = "main2";
        HttpSession session = request.getSession();
        session.setAttribute("role", null);

        try {
            String op = request.getParameter("op");
            if (op.equals("submit")) {
                String userName = request.getParameter("userName");
                String password = request.getParameter("password");

                UserInformation user = userInformationFacade.find(userName);
                System.out.println(userName + " " + password);
                System.out.println(user.getUserName() + " " + user.getUserPassword());
                if (user == null) {
                    session.setAttribute("action", "login");
                    session.setAttribute("controller", "login");
                    session.setAttribute("errorMessage", "User name not found!");
                } else if (password.equals(user.getUserPassword())) {
                    session.setAttribute("user", user);
                    session.setAttribute("action", "index");
                    session.setAttribute("controller", "home");
                    if (user.getUserRole() == null) {
                        user.setUserRole("Client");
                        userInformationFacade.edit(user);
                    }
                    if (user.getUserRole().equals("Admin")) {
                        session.setAttribute("role", "Admin");
                        layout = "main";
                    }
                    if (user.getUserRole().equals("Mod")) {
                        session.setAttribute("role", "Employer");
                        layout = "main";
                    }
                    if (user.getUserRole().equals("Client")) {
                        session.setAttribute("action", "index");
                        session.setAttribute("role", "Client");
                        layout = "main";
                        //request.getRequestDispatcher("/home/index.do").forward(request, response);
                    }
                } else {
                    session.setAttribute("action", "login");
                    session.setAttribute("controller", "login");
                    session.setAttribute("errorMessage", "Incorrect password.");
                }
            }
        } catch (Exception ex) {
            //  Chi hien lai view create va thong bao loi
            System.out.println(ex);
            session.setAttribute("action", "login");
            session.setAttribute("controller", "login");
            session.setAttribute("errorMessage", "Invalid data.");
        }
        return layout;
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("role", null);
        session.setAttribute("user", null);
        session.setAttribute("controller", "home");
        session.setAttribute("action", "index");
        session.setAttribute("errorMessage", "");
        request.getRequestDispatcher("/home/index.do").forward(request, response);
    }

    public void regist(HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//        try {
//            String name = request.getParameter("name");
//            String address = request.getParameter("address");
//            String userName = request.getParameter("userName");
//            String password = request.getParameter("password");
//            String re_password = request.getParameter("re-password");
////            String gender = request.getParameter("gender");
//            String gender = "Male";
//            String phoneNumber = request.getParameter("phoneNumber");
//            String email = request.getParameter("email");
//            System.out.println(name);
//            System.out.println(address);
//            System.out.println(userName);
//            System.out.println(password);
//            System.out.println(re_password);
//            System.out.println(phoneNumber);
//            System.out.println(email);
//            int age = Integer.parseInt(request.getParameter("age"));
//
//            System.out.println(age);
//            Part part = request.getPart("userImage");
//            String fileName = part.getSubmittedFileName();
//            String fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
//            String path = getServletContext().getRealPath("/" + "images/user") + File.separator + userName + ".jpg"; //fileExtension;
//            path = path.replace("\\build", "");
//
//            if (userInformationFacade.find(userName) != null) {
//                session.setAttribute("action", "register");
//                session.setAttribute("errorMessage", "User name already existed");
//            } else if (password.equals(re_password)) {
//                UserInformation user = new UserInformation(userName, password, email, address, gender, phoneNumber, name, age);
//                InputStream is = part.getInputStream();
//                boolean success = uploadFile(is, path);
//                if (success) {
//                    System.out.println("Upload to this: " + path);
//                } else {
//                    System.out.println("Error");
//                }
//                user.setUserRole("Client");
//                userInformationFacade.create(user);
//                session.setAttribute("action", "login");
//            } else {
//                session.setAttribute("action", "register");
//                session.setAttribute("errorMessage", "Incorrect re-password");
//            }
//        } catch (Exception ex) {
//            //Chi hien lai view create va thong bao loi
//            System.out.println("Lỗi rồi " + ex);
//            session.setAttribute("action", "register");
//            session.setAttribute("errorMessage", "Invalid data.");
//        }
        HttpSession session = request.getSession();
        try {

//            UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
// String email = ParamUtil.getString(uploadRequest, "fromemail");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String re_password = request.getParameter("re-password");
            String gender = request.getParameter("gender");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            System.out.println(name);
            System.out.println(address);
            System.out.println(userName);
            System.out.println(password);
            System.out.println(re_password);
            System.out.println(phoneNumber);
            System.out.println(email);
            int age = Integer.parseInt(request.getParameter("age"));
//            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
//            System.out.println(date);
            System.out.println(age);
            Part part = request.getPart("userImage");
            String fileName = part.getSubmittedFileName();
            String fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            String path = getServletContext().getRealPath("/" + "images/user") + File.separator + userName + ".jpg"; //fileExtension;
            path = path.replace("\\build", "");
            InputStream is = part.getInputStream();
            boolean success = uploadFile(is, path);
            if (success) {
                System.out.println("Upload to this: " + path);
            } else {
                System.out.println("Error");
            }
        } catch (Exception ex) {
            System.out.println("Lỗi rồi " + ex);
            session.setAttribute("action", "register");
            session.setAttribute("errorMessage", "Invalid data.");
        }
    }

    public boolean uploadFile(InputStream is, String path) {
        boolean test = false;
        try {
            byte[] byt = new byte[is.available()];
            is.read(byt);
            FileOutputStream fops = new FileOutputStream(path);
            fops.write(byt);
            fops.flush();
            fops.close();
            test = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
