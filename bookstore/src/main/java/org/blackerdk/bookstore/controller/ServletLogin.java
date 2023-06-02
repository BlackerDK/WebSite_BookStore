/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.blackerdk.bookstore.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.blackerdk.bookstore.dao.RegistrationDAO;
import org.blackerdk.bookstore.dto.RegistrationDTO;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DUY KHANH
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {
    private final String ADMIN_PAGE = "admin.jsp";
    private final String LOGIN_PAGE = "login.jsp";
    private final String SHOPPING_CART = "ServletShopPage";
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
       
            String url ="";
        try {
            String username = request.getParameter("txtUserName");
            String password = request.getParameter("txtPassword");
            //Call DAO --> New DAO Obj và call method of DAO 
            RegistrationDAO dao = new RegistrationDAO();
            //call method
            RegistrationDTO result = dao.checkLogin(username, password);//Dao trả cho controller
            if (result != null) {
                if (result.isRole() == true) {
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", result);
                    url = ADMIN_PAGE;
                } else{
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", result);
                    url = SHOPPING_CART;
                }
            }
            if (result==null){
                String test="ENTER USER NAME OR PASSWORD IS FAILS";
                request.setAttribute("MSG",test);
                url=LOGIN_PAGE;
            }
        } catch (SQLException ex) {
            String msg =ex.getMessage();
            log("LoginSevrlet_SQL_"+msg);
        } catch (NamingException ex) {
            log("RegisterAccount_Naming_" +ex.getMessage());
        } finally {
//            //Khi có cookies thì nên sài repson vì ...
//            response.sendRedirect(url);//Respone obj send về phía brower
//            //Dòng này sẽ hiển thị đường truyền
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

        }
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
