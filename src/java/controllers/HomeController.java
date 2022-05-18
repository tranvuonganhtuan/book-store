/*
    Document   : HomeController
    Created on : Feb 12, 2022, 4:43:12 PM
    Author     : PHT
*/
package controllers;

import entities.Book;
import entities.Category;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionbeans.BookFacade;
import sessionbeans.CategoryFacade;

@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    @EJB
    private CategoryFacade categoryFacade;

    @EJB
    private BookFacade bookFacade;
    
    @Override
    public void init(){
        ServletContext context = this.getServletContext();
        context.setAttribute("root", context.getContextPath());
        
    }
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
        
        switch(action){
            case "index":
                index(request, response);
                break;
            default:
                action="error";
                session.setAttribute("action", action);
        }        
        request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
    }

    
    private void index(HttpServletRequest request, HttpServletResponse response) {
       
        int pageSize = 6;//Kich thuoc trang                        
        HttpSession session = request.getSession();

        //Xac dinh so thu tu cua trang hien tai
        Integer page = (Integer)session.getAttribute("page");
        if (page == null) {
            page = 1;
        }

        //Xac dinh tong so trang
        Integer totalPage = null;//(Integer)session.getAttribute("totalPage");
        if (totalPage == null) {
           int count = bookFacade.count();//Dem so luong records
           totalPage = (int) Math.ceil((double) count / pageSize);//Tinh tong so trang
        }

        String op = request.getParameter("op");
        if (op == null) {
            op = "FirstPage";
        }
        switch (op) {
            case "FirstPage":
                page = 1;
                break;
            case "PreviousPage":
                if (page > 1) {
                    page--;
                }
                break;
            case "NextPage":
                if (page < totalPage) {
                    page++;
                }
                break;
            case "LastPage":
                page = totalPage;
                break;
            case "GotoPage":
                page = Integer.parseInt(request.getParameter("gotoPage"));
                if(page <= 0){
                    page = 1;
                }else if(page > totalPage){
                    page = totalPage;
                }
                break;            
        }

        //Lay trang du lieu duoc yeu cau
        int n1 = (page - 1) * pageSize;//Vi tri mau tin dau trang
        int n2 = n1 + pageSize - 1;//Vi tri mau tin cuoi trang
        List<Book> bookList = bookFacade.findRange(new int[]{n1, n2});//Doc mot trang
        List<Category> categoryList = categoryFacade.findAll();
        //Luu thong tin vao session va request
        session.setAttribute("page", page);
        session.setAttribute("totalPage", totalPage);
        session.setAttribute("bookList", bookList);
        session.setAttribute("categoryList", categoryList);
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
