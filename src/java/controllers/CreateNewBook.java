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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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

/**
 *
 * @author SE150010 Nguyen Sy Hoan
 */
@WebServlet(name = "CreateNewBook", urlPatterns = {"/createNewBook"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 10, // 10 KB
        maxFileSize = 1024 * 300, // 300 KB
        maxRequestSize = 1024 * 1024 // 1 MB 
)
public class CreateNewBook extends HttpServlet {

    @EJB
    private PublisherFacade publisherFacade;

    @EJB
    private CategoryFacade categoryFacade;

    @EJB
    private BookFacade bookFacade;

    @EJB
    private AuthorFacade authorFacade;

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
        createNewBook(request, response);
        request.getRequestDispatcher("/WEB-INF/layouts/" + "main" + ".jsp").forward(request, response);
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

    private void bookmanage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<Book> bookList = bookFacade.findAll();
        session.setAttribute("bookList", bookList);
    }

    private void createNewBook(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String op = request.getParameter("op");
        switch (op) {
            case "Save":
                String name = request.getParameter("name");
                int authorID = Integer.parseInt(request.getParameter("chooseAuthorID"));
                int publisherID = Integer.parseInt(request.getParameter("choosePublisherID"));
                int publisherYear = Integer.parseInt(request.getParameter("publishingYear"));
                int categoryID = Integer.parseInt(request.getParameter("chooseCategoryID"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                BigDecimal price = new BigDecimal(request.getParameter("price"));
                int discount = Integer.parseInt(request.getParameter("discount"));
                String summary = request.getParameter("bookSummary");
                if (summary.length() == 0) {
                    summary = "No Information";
                }
                System.out.println(name);
                System.out.println(authorID);
                System.out.println(publisherID);
                System.out.println(publisherYear);
                System.out.println(categoryID);
                System.out.println(quantity);
                System.out.println(price);
                System.out.println(discount);
                System.out.println(summary.length());
                Part part = request.getPart("bookImage");
                String path = null;
                String bookImage = null;
                String fileName = part.getSubmittedFileName();
                System.out.println(fileName.length());
                if (fileName.length() != 0) {
                    String fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                    bookImage = randomAlphabetic(10) + fileExtension;
                    path = getServletContext().getRealPath("/" + "images/book") + File.separator + bookImage;
                    path = path.replace("\\build", "");
                }
                Book book = new Book(name, publisherYear, quantity, price);
                Author author = authorFacade.find(authorID);
                if (authorID == 0) {
                    author = new Author(0, "No Information");
                }
                Category category = categoryFacade.find(categoryID);
                if (categoryID == 0) {
                    category = new Category(0, "No Information");
                }
                Publisher publisher = publisherFacade.find(publisherID);
                if (publisherID == 0) {
                    publisher = new Publisher(0, "No Information");
                }
                System.out.println(author.getAuthorName());
                System.out.println(category.getCategoryName());
                System.out.println(publisher.getPublisherName());
                book.setAuthorID(author);
                book.setCategoryID(category);
                book.setPublisherID(publisher);
                book.setDiscount(discount);
                book.setBookSummary(summary);
                if (bookImage != null) {
                    book.setBookImage(bookImage);
                    InputStream is = part.getInputStream();
                    boolean success = uploadFile(is, path);
                    if (success) {
                        System.out.println("Upload to this: " + path);
                    } else {
                        System.out.println("Error");
                    }
                }
                book.setBought(0);
                bookFacade.create(book);
                bookmanage(request, response);
                session.setAttribute("action", "bookmanage");
                break;
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
