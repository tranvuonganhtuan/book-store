package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entities.UserInformation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author SE150010 Nguyen Sy Hoan
 */
@WebServlet(urlPatterns = {"/regist"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 10, // 10 KB
        maxFileSize = 1024 * 300, // 300 KB
        maxRequestSize = 1024 * 1024 // 1 MB 
)
public class Regist extends HttpServlet {

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
        regist(request, response);
        String layouts = "main2";
        request.getRequestDispatcher("/WEB-INF/layouts/" + layouts + ".jsp").forward(request, response);
    }

    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String re_password = request.getParameter("re-password");
            String gender = request.getParameter("gender");
//            String gender = "Male";
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            System.out.println(name);
            System.out.println(address);
            System.out.println(userName);
            System.out.println(password);
            System.out.println(re_password);
            System.out.println(phoneNumber);
            System.out.println(email);
            Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dateOfBirth"));
            Part part = request.getPart("userImage");
            String path = null;
            String userImage = null;
            if (part != null) {
                String fileName = part.getSubmittedFileName();
                String fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                userImage = userName + fileExtension;
                path = getServletContext().getRealPath("/" + "images/user") + File.separator + userImage;
                path = path.replace("\\build", "");
            }
            if (userInformationFacade.find(userName) != null) {
                session.setAttribute("action", "register");
                session.setAttribute("errorMessage", "User name already existed");
            } else if (password.equals(re_password)) {
                UserInformation user = new UserInformation(userName, password, email, address, gender, phoneNumber, name, dateOfBirth);
                if (part != null) {
                    user.setUserImage(userImage);
                    InputStream is = part.getInputStream();
                    boolean success = uploadFile(is, path);
                    if (success) {
                        System.out.println("Upload to this: " + path);
                    } else {
                        System.out.println("Error");
                    }
                }
                user.setUserRole("Client");
                userInformationFacade.create(user);
                session.setAttribute("action", "login");
            } else {
                session.setAttribute("action", "register");
                session.setAttribute("errorMessage", "Incorrect re-password");
            }
        } catch (Exception ex) {
            //Chi hien lai view create va thong bao loi
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
