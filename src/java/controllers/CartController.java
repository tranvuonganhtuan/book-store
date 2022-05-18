/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Book;
import entities.Cart;
import entities.OrderItem;
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
import sessionbeans.BookFacade;
import sessionbeans.CartFacade;
import sessionbeans.OrderItemFacade;
import sessionbeans.UserInformationFacade;

/**
 *
 * @author SE151464 Nguyen Hoang Huy
 */
@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @EJB
    private BookFacade bookFacade;

    @EJB
    private UserInformationFacade userInformationFacade;

    @EJB
    private CartFacade cartFacade;

    @EJB
    private OrderItemFacade orderItemFacade;
    
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
                
            case "addToCart":
                addToCart(request, response);
                index(request, response);
                session.setAttribute("action", "index");
//                System.out.println("kjahwkjhdkwjahd");
                break;
                
            case "change":
                change(request, response);
                index(request, response);
                session.setAttribute("action", "index");
                break;
                
            case "deleteOrder":
                deleteOrder(request, response);
                index(request, response);
                session.setAttribute("action", "index");
                break;
                
            
            default:
                action="error";
                request.setAttribute("action", action);
        }        
        request.getRequestDispatcher("/WEB-INF/layouts/main.jsp").forward(request, response);
    }
    
    Cart getCart(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserInformation tmpuser = (UserInformation) session.getAttribute("user");
        UserInformation user = userInformationFacade.find(tmpuser.getUserName());
        Cart cart = new Cart();
        cart.setUserName(user);
        cart.setCartStatus(1);
        List <Cart> list = cartFacade.findAll();
        List <Cart> cartList = new ArrayList<Cart>();
        if (cartList == null) {
            cartList = new ArrayList<Cart>();
        }
        for (Cart tmp: list) {
            if (tmp.getUserName().getUserName().equals(user.getUserName())) {
                cartList.add(tmp);
            }
        }
        System.out.println(cart);
        System.out.println(cartList.size());
        if (cartList.size() != 0 && cartList.get(cartList.size() - 1).getCartStatus() == 1) {
            System.out.println("kajhwdkajwdhkajdhkwajdhk");
            cart = cartList.get(cartList.size() - 1);
            System.out.println(cart.getCartID());
            System.out.println(cart.getOrderItemList().size());
        } else {
            cartFacade.create(cart);
            user = userInformationFacade.find(user.getUserName());
            cartList = user.getCartList();
            cart = cartList.get(cartList.size() - 1);
        }
        List<OrderItem> orderList = new ArrayList<OrderItem>();
        List<OrderItem> fOrderList = orderItemFacade.findAll();
        for (OrderItem order: fOrderList) {
            if (order.getCartID().getCartID() == cart.getCartID()) {
                orderList.add(order);
            }
        }
        cart.setOrderItemList(orderList);
        return cart;
    }
    
    private void index(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = getCart(request, response);
        session.setAttribute("cart", cart);
    }
    
    private void addToCart(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int bookID = Integer.parseInt(request.getParameter("bookID"));
        Cart cart = getCart(request, response);
        Book book = bookFacade.find(bookID);
        OrderItem order = new OrderItem();
        order.setBookID(book);
        order.setCartID(cart);
        order.setQuantity(quantity);
        List <OrderItem> orderList = cart.getOrderItemList();
        boolean flag = false;
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getBookID().getBookID() == order.getBookID().getBookID()) {
                order = orderList.get(i);
                order.setQuantity(order.getQuantity() + quantity);
                orderItemFacade.edit(order);
                flag = true;
            }
        }
        if (!flag) {
            orderItemFacade.create(order);
        }
    }
    
    private void change(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String op = request.getParameter("op");
        int value = 0;
        switch (op) {
            case "decrement":
                value = -1;
                break;
                
            case "increment":
                value = 1;
                break;
        }
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        OrderItem order = orderItemFacade.find(orderID);
        order.setQuantity(order.getQuantity() + value);
        if (order.getQuantity() == 0) {
            orderItemFacade.remove(order);
        } else {
            orderItemFacade.edit(order);
        }
    }
    
    
    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        OrderItem order = orderItemFacade.find(orderID);
        orderItemFacade.remove(order);
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
