/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Author;
import entities.Book;
import entities.Category;
import entities.Publisher;
import entities.UserInformation;
import entities.Voucher;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import sessionbeans.AuthorFacade;
import sessionbeans.BookFacade;
import sessionbeans.CategoryFacade;
import sessionbeans.PublisherFacade;
import sessionbeans.UserInformationFacade;
import sessionbeans.VoucherFacade;

/**
 *
 * @author SE150010 Nguyen Sy Hoan
 */
@WebServlet(name = "ManagerController", urlPatterns = {"/manager"})
public class ManagerController extends HttpServlet {

    @EJB
    private VoucherFacade voucherFacade;

    @EJB
    private PublisherFacade publisherFacade;

    @EJB
    private CategoryFacade categoryFacade;

    @EJB
    private AuthorFacade authorFacade;

    @EJB
    private BookFacade bookFacade;

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
//                index(request, response);
                break;

            case "usermanage":
                usermanage(request, response);
                break;

            case "updateUser":
                updateUser(request, response);
                usermanage(request, response);
                action = "usermanage";
                session.setAttribute("action", action);
                break;
            
            case "deleteUser":
                deleteUser(request, response);
                usermanage(request, response);
                action = "usermanage";
                session.setAttribute("action", action);
                break;

            case "bookmanage":
                bookmanage(request, response);
                action = "bookmanage";
                session.setAttribute("action", action);
                break;

            case "createBookForm":
                createBookForm(request, response);
//                action = "bookmanage";
//                session.setAttribute("action", action);
                break;

            case "createNewBook":
//                createNewBook(request, response);
//                action = "bookmanage";
//                session.setAttribute("action", action);
                break;
            
            case "editBook":
                editBook(request, response);
//                action = "bookmanage";
//                session.setAttribute("action", action);
                break;
            
            case "deleteBook":
                deleteBook(request, response);
                bookmanage(request, response);
                action = "bookmanage";
                session.setAttribute("action", action);
                break;    
                
            case "authormanage":
                authormanage(request, response);
                action = "authormanage";
                session.setAttribute("action", action);
                break;
            
            case "createAuthorForm":
//                createAuthorForm(request, response);
//                action = "authormanage";
//                session.setAttribute("action", action);
                break;

            case "createNewAuthor":
                createNewAuthor(request, response);
                authormanage(request, response);
                action = "authormanage";
                session.setAttribute("action", action);
                break;
            
            case "editAuthor":
                editAuthor(request, response);
//                action = "authormanage";
//                session.setAttribute("action", action);
                break;
            
            case "updateAuthorData":
                updateAuthorData(request, response);
                authormanage(request, response);
                action = "authormanage";
                session.setAttribute("action", action);
                break;
                
            case "deleteAuthor":
                deleteAuthor(request, response);
                authormanage(request, response);
                action = "authormanage";
                session.setAttribute("action", action);
                break;
                
            case "publishermanage":
                publishermanage(request, response);
                action = "publishermanage";
                session.setAttribute("action", action);
                break;
            
            case "createPublisherForm":
//                createPublisherForm(request, response);
//                action = "publishermanage";
//                session.setAttribute("action", action);
                break;

            case "createNewPublisher":
                createNewPublisher(request, response);
                publishermanage(request, response);
                action = "publishermanage";
                session.setAttribute("action", action);
                break;
            
            case "editPublisher":
                editPublisher(request, response);
//                action = "publishermanage";
//                session.setAttribute("action", action);
                break;
            
            case "updatePublisherData":
                updatePublisherData(request, response);
                publishermanage(request, response);
                action = "publishermanage";
                session.setAttribute("action", action);
                break;  
                
            case "deletePublisher":
                deletePublisher(request, response);
                publishermanage(request, response);
                action = "publishermanage";
                session.setAttribute("action", action);
                break;
                
            case "categorymanage":
                categorymanage(request, response);
                action = "categorymanage";
                session.setAttribute("action", action);
                break;
                
            case "createCategoryForm":
//                createCategoryForm(request, response);
//                action = "categorymanage";
//                session.setAttribute("action", action);
                break;

            case "createNewCategory":
                createNewCategory(request, response);
                categorymanage(request, response);
                action = "categorymanage";
                session.setAttribute("action", action);
                break;
            
            case "editCategory":
                editCategory(request, response);
//                action = "categorymanage";
//                session.setAttribute("action", action);
                break;
            
            case "updateCategoryData":
                updateCategoryData(request, response);
                categorymanage(request, response);
                action = "categorymanage";
                session.setAttribute("action", action);
                break;
            
            case "deleteCategory":
                deleteCategory(request, response);
                categorymanage(request, response);
                action = "categorymanage";
                session.setAttribute("action", action);
                break;    
                
            case "vouchermanage":
                vouchermanage(request, response);
                action = "vouchermanage";
                session.setAttribute("action", action);
                break;
                
            case "createVoucherForm":
//                createVoucherForm(request, response);
//                action = "vouchermanage";
//                session.setAttribute("action", action);
                break;

            case "createNewVoucher":
                createNewVoucher(request, response);
                vouchermanage(request, response);
                action = "vouchermanage";
                session.setAttribute("action", action);
                break;
            
            case "editVoucher":
                editVoucher(request, response);
//                action = "vouchermanage";
//                session.setAttribute("action", action);
                break;
            
            case "updateVoucherData":
                updateVoucherData(request, response);
                vouchermanage(request, response);
                action = "vouchermanage";
                session.setAttribute("action", action);
                break;    
                
            case "deleteVoucher":
                deleteVoucher(request, response);
                vouchermanage(request, response);
                action = "vouchermanage";
                session.setAttribute("action", action);
                break;   
                
                
            default:
                action = "error";
                session.setAttribute("action", action);
        }
        request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
    }

    private void usermanage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<UserInformation> userList = userInformationFacade.findAll();
        session.setAttribute("userList", userList);
    }

    private void bookmanage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<Book> bookList = bookFacade.findAll();
        session.setAttribute("bookList", bookList);
    }
    
    private void authormanage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<Author> authorList = authorFacade.findAll();
        session.setAttribute("authorList", authorList);
    }
    
    private void createNewAuthor(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String authorName = request.getParameter("name");
        String summary = request.getParameter("authorSummary");
        if (summary.length() == 0) {
            summary = "No Information";
        }
        System.out.println(authorName);
        System.out.println(summary);
        Author author = new Author(authorName, summary);
        authorFacade.create(author);
    }
    
    private void editAuthor(HttpServletRequest request, HttpServletResponse response) {
        int authorID = Integer.parseInt(request.getParameter("authorID"));
        Author author = authorFacade.find(authorID);
        HttpSession session = request.getSession();
        session.setAttribute("author", author);
    }
    
    private void updateAuthorData(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int authorID = Integer.parseInt(request.getParameter("authorID"));
        String authorName = request.getParameter("name");
        String summary = request.getParameter("authorSummary");
        if (summary.length() == 0) {
            summary = "No Information";
        }
        System.out.println(authorName);
        System.out.println(summary);
        Author author = authorFacade.find(authorID);
        author.setAuthorName(authorName);
        author.setAuthorSummary(summary);
        authorFacade.edit(author);
    }
    
    private void deleteAuthor(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int authorID = Integer.parseInt(request.getParameter("authorID"));
        Author author = authorFacade.find(authorID);
        authorFacade.remove(author);
    }

    
    private void publishermanage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<Publisher> publisherList = publisherFacade.findAll();
        session.setAttribute("publisherList", publisherList);
    }
    
    private void createNewPublisher(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String publisherName = request.getParameter("name");
        String summary = request.getParameter("publisherSummary");
        if (summary.length() == 0) {
            summary = "No Information";
        }
        Publisher publisher = new Publisher(publisherName, summary);
        publisherFacade.create(publisher);
    }
    
    private void editPublisher(HttpServletRequest request, HttpServletResponse response) {
        int publisherID = Integer.parseInt(request.getParameter("publisherID"));
        Publisher publisher = publisherFacade.find(publisherID);
        HttpSession session = request.getSession();
        session.setAttribute("publisher", publisher);
    }
    
    private void updatePublisherData(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int publisherID = Integer.parseInt(request.getParameter("publisherID"));
        String publisherName = request.getParameter("name");
        String summary = request.getParameter("publisherSummary");
        if (summary.length() == 0) {
            summary = "No Information";
        }
        System.out.println(publisherName);
        System.out.println(summary);
        Publisher publisher = publisherFacade.find(publisherID);
        publisher.setPublisherName(publisherName);
        publisher.setPublisherSummary(summary);
        publisherFacade.edit(publisher);
    }
    
    private void deletePublisher(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int publisherID = Integer.parseInt(request.getParameter("publisherID"));
        Publisher publisher = publisherFacade.find(publisherID);
        publisherFacade.remove(publisher);
    }
    
    private void categorymanage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<Category> categoryList = categoryFacade.findAll();
        session.setAttribute("categoryList", categoryList);
    }
    
    private void createNewCategory(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String categoryName = request.getParameter("name");
        String summary = request.getParameter("categorySummary");
        if (summary.length() == 0) {
            summary = "No Information";
        }
        Category category = new Category(categoryName, summary);
        categoryFacade.create(category);
    }
    
    private void editCategory(HttpServletRequest request, HttpServletResponse response) {
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        Category category = categoryFacade.find(categoryID);
        HttpSession session = request.getSession();
        session.setAttribute("category", category);
    }
    
    private void updateCategoryData(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        String categoryName = request.getParameter("name");
        String summary = request.getParameter("categorySummary");
        if (summary.length() == 0) {
            summary = "No Information";
        }
        System.out.println(categoryName);
        System.out.println(summary);
        Category category = categoryFacade.find(categoryID);
        category.setCategoryName(categoryName);
        category.setCategorySummary(summary);
        categoryFacade.edit(category);
    }
    
    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        Category category = categoryFacade.find(categoryID);        
        categoryFacade.remove(category);
    }
    
    private void vouchermanage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<Voucher> voucherList = voucherFacade.findAll();
        session.setAttribute("voucherList", voucherList);
    }
    
    private void createNewVoucher(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String voucherName = request.getParameter("name");
            int voucherValue = Integer.parseInt(request.getParameter("voucherValue"));
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDate"));
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endDate"));
            String summary = request.getParameter("voucherSummary");
            if (summary.length() == 0) {
                summary = "No Information";
            }
            System.out.println(voucherName);
            System.out.println(voucherValue);
            System.out.println(startDate);
            System.out.println(endDate);
            System.out.println(summary);
            Voucher voucher = new Voucher(voucherName, summary, voucherValue, startDate, endDate);
            voucherFacade.create(voucher);
        } catch (ParseException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void editVoucher(HttpServletRequest request, HttpServletResponse response) {
        int voucherID = Integer.parseInt(request.getParameter("voucherID"));
        Voucher voucher = voucherFacade.find(voucherID);
        HttpSession session = request.getSession();
        session.setAttribute("voucher", voucher);
    }
    
    private void updateVoucherData(HttpServletRequest request, HttpServletResponse response)  {
        try {
            HttpSession session = request.getSession();
            System.out.println("PreID");
            int voucherID = Integer.parseInt(request.getParameter("voucherID"));
            System.out.println("PreName");
            String voucherName = request.getParameter("name");
            System.out.println(voucherID);
            System.out.println(voucherName);
            int voucherValue = Integer.parseInt(request.getParameter("voucherValue"));
            
            System.out.println(voucherValue);
//            System.out.println(voucherID);
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDate"));
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endDate"));
            String summary = request.getParameter("voucherSummary");
            if (summary.length() == 0) {
                summary = "No Information";
            }
            Voucher voucher = voucherFacade.find(voucherID);
            voucher.setVoucherName(voucherName);
            voucher.setVoucherSummary(summary);
            voucher.setStartDate(startDate);
            voucher.setEndDate(endDate);
            voucher.setVoucherValue(voucherValue);
            
            voucherFacade.edit(voucher);
        } catch (ParseException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void deleteVoucher(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int voucherID = Integer.parseInt(request.getParameter("voucherID"));
        Voucher voucher = voucherFacade.find(voucherID);        
        voucherFacade.remove(voucher);
    }
    
    private void updateUser(HttpServletRequest request, HttpServletResponse response) {
        String op = request.getParameter("op");
        System.out.println(op);
        if (op.equals("Update")) {
            try {
                String userName = request.getParameter("userName");
                String role = request.getParameter("editRole");
                System.out.println(userName + " " + role);
                UserInformation user = userInformationFacade.find(userName);
                user.setUserRole(role);
                userInformationFacade.edit(user);
                usermanage(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
                //Chi hien lai view create va thong bao loi
                System.out.println(ex);
                request.setAttribute("action", "usermanage");
                request.setAttribute("errorMessage", "Invalid data.");
            }
        }

    }
    
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String userName = request.getParameter("userName");
        UserInformation user = userInformationFacade.find(userName);        
        userInformationFacade.remove(user);
    }

    private void createBookForm(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<Category> categoryList = categoryFacade.findAll();
        session.setAttribute("categoryList", categoryList);
        List<Author> authorList = authorFacade.findAll();
        session.setAttribute("authorList", authorList);
        List<Publisher> publisherList = publisherFacade.findAll();
        session.setAttribute("publisherList", publisherList);
    }

    private String randomAlphabetic(int n) {
        String ans = "";
        Random generator = new Random();
        for (int i = 0; i < n; i++) {
            int value = generator.nextInt(26);
            ans += (char) (value + 'a');
        }
        return ans;
    }

    private void editBook(HttpServletRequest request, HttpServletResponse response) {
        int bookID = Integer.parseInt(request.getParameter("bookID"));
        Book book = bookFacade.find(bookID);
        HttpSession session = request.getSession();
        session.setAttribute("book", book);
        List<Category> categoryList = categoryFacade.findAll();
        session.setAttribute("categoryList", categoryList);
        List<Author> authorList = authorFacade.findAll();
        session.setAttribute("authorList", authorList);
        List<Publisher> publisherList = publisherFacade.findAll();
        session.setAttribute("publisherList", publisherList);
    }
    
    private void deleteBook(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int bookID = Integer.parseInt(request.getParameter("bookID"));
        Book book = bookFacade.find(bookID);        
        bookFacade.remove(book);
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
