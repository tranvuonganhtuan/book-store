/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Book;
import entities.Category;
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
import sessionbeans.BookFacade;
import sessionbeans.CategoryFacade;

/**
 *
 * @author SE150010 Nguyen Sy Hoan
 */
@WebServlet(name = "SearchController", urlPatterns = {"/search"})
public class SearchController extends HttpServlet {

    @EJB
    private CategoryFacade categoryFacade;

    @EJB
    private BookFacade bookFacade;

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
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = session.getAttribute("action").toString();
        switch (action) {
            case "search":
                search(request, response);
                break;
            case "searchbar":
                searchbar(request, response);
                break;
            default:
                action = "error";
                session.setAttribute("action", action);
        }
        request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
    }

    private void search(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<Book> bookList = bookFacade.findAll();//Doc mot trang
        List<Book> searchList = new ArrayList<>();
        Integer categoryID = Integer.parseInt((String) request.getParameter("category"));
        session.setAttribute("category", categoryID);
        for (Book book : bookList) {
            if (book.getCategoryID().getCategoryID() == categoryID) {
                searchList.add(book);
            }
        }
        int pageSize = 6;//Kich thuoc trang                        

        //Xac dinh so thu tu cua trang hien tai
        Integer page = (Integer) session.getAttribute("page");
        if (page == null) {
            page = 1;
        }

        //Xac dinh tong so trang
        Integer totalPage = null;//(Integer)session.getAttribute("totalPage");
        if (totalPage == null) {
            int count = searchList.size();//Dem so luong records
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
                if (page <= 0) {
                    page = 1;
                } else if (page > totalPage) {
                    page = totalPage;
                }
                break;
        }
        int n1 = (page - 1) * pageSize;//Vi tri mau tin dau trang
        int n2 = n1 + pageSize - 1;//Vi tri mau tin cuoi trang
        //Doc mot trang
        List<Book> search = new ArrayList<>();
        for (int i = n1; i <= Math.min(n2, (int) searchList.size() - 1); i++) {
            search.add(searchList.get(i));
        }

        //Luu thong tin vao session va request
        session.setAttribute("page", page);
        session.setAttribute("totalPage", totalPage);
        session.setAttribute("searchList", search);
    }

    private void KMPpreprocess(String P, int[] prefix) {
        prefix[0] = 0;
        int m = P.length();
        int j = 0;
        int i = 1;
        while (i < m) {
            if (P.charAt(i) == P.charAt(j)) {
                j++;
                prefix[i] = j;
                i++;
            } else {
                if (j != 0) {
                    j = prefix[j - 1];
                } else {
                    prefix[i] = 0;
                    i++;
                }
            }
        }
    }

    private static boolean KMPsearch(String T, String P, int[] prefix) {
        int n = T.length();
        int m = P.length();
        int i = 0, j = 0;
        while (i < n) {
            if (T.charAt(i) == P.charAt(j)) {
                j++;
                i++;
            }
            if (j == m) {
                j = prefix[j - 1];
                return true;
            } else if (i < n && T.charAt(i) != P.charAt(j)) {
                if (j != 0) {
                    j = prefix[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }
        return false;
    }

    private void searchbar(HttpServletRequest request, HttpServletResponse response) {
        List<Book> bookList = bookFacade.findAll();//Doc mot trang
        List<Book> searchList = new ArrayList<>();
        HttpSession session = request.getSession();
        String tmp = request.getParameter("searchBar");
        session.setAttribute("searchBar", tmp);
        String name = tmp.toUpperCase();
        int[] prefix = new int[name.length()];
        KMPpreprocess(name, prefix);

        for (Book book : bookList) {
            String temp = new String(book.getBookName());
            temp = temp.toUpperCase();
            if (KMPsearch(temp, name, prefix)) {
                searchList.add(book);
            }
        }
        int pageSize = 6;//Kich thuoc trang                        

        //Xac dinh so thu tu cua trang hien tai
        Integer page = (Integer) session.getAttribute("page");
        if (page
                == null) {
            page = 1;
        }

        //Xac dinh tong so trang
        Integer totalPage = null;
        if (totalPage
                == null) {
            int count = searchList.size();//Dem so luong records
            totalPage = (int) Math.ceil((double) count / pageSize);//Tinh tong so trang
        }

        String op = request.getParameter("op");
        if (op
                == null) {
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
                if (page <= 0) {
                    page = 1;
                } else if (page > totalPage) {
                    page = totalPage;
                }
                break;
        }

        List<Book> search = new ArrayList<>();
        //Lay trang du lieu duoc yeu cau
        if (!searchList.isEmpty()) {
            int n1 = (page - 1) * pageSize;//Vi tri mau tin dau trang
            int n2 = n1 + pageSize - 1;//Vi tri mau tin cuoi trang

            for (int i = n1; i <= Math.min(n2, (int) searchList.size() - 1); i++) {
                search.add(searchList.get(i));
            }
        }
        //Luu thong tin vao session va request
        session.setAttribute("page", page);
        session.setAttribute("totalPage", totalPage);
        session.setAttribute("searchList", search);
        if (searchList.isEmpty()) {
            tmp = "No result for " + tmp;
        } else {
            tmp = "Result for " + tmp;
        }
        session.setAttribute("searchBarMessage", tmp);
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
