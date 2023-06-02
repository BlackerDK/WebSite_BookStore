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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.blackerdk.bookstore.dao.RegistrationDAO;
import org.blackerdk.bookstore.dto.ErrorDTO;
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
@WebServlet(name = "ServletRegisterAccount", urlPatterns = {"/ServletRegisterAccount"})
public class ServletRegisterAccount extends HttpServlet {
    private final String LOGIN_PAGE="login.jsp";
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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullname");
        String phone = request.getParameter("txtPhone");
        String mail = request.getParameter("txtMail");
        String address = request.getParameter("txtAddress");

        ErrorDTO errors = new ErrorDTO();
        boolean result = false;
        String url = LOGIN_PAGE;
        try {
            if (username.trim().length() < 6
                    || username.trim().length() > 20) {
                result = true;
                errors.setUsernameLengthError("Username is required input from 6-20");
            }
            if (password.trim().length() < 6
                    || password.trim().length() > 20) {
                result = true;
                errors.setPasswordLengthError("Password is required input from 6-30");
            } else if (!confirm.trim().equals(password.trim())) {
                result = true;
                errors.setConfirmNotMatch("Confirm is not Match");
            }
            if (fullName.trim().length() < 6
                    || fullName.trim().length() > 30) {
                result = true;
                errors.setFullNameLengthError("Full name is required input from 6-50");
            }
            if (phone.trim().length() < 10 || phone.trim().length() > 11) {
                result = true;
                errors.setPhoneLengthError("Phone is required 10 number");
            }
            if (address.trim().length() < 5 || address.trim().length() > 50) {
                result = true;
                errors.setAddressLengthError("Address is required from 5-50 chars");
            }
            if (result) {
                request.setAttribute("CREATE_ERROR", errors);
            } else {
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, fullName, false, phone, mail, address);
                boolean newUser = dao.createUser(dto);
                if (newUser == true) {
                    url = LOGIN_PAGE;
                }
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("RegisterAccount_SQL_" + msg);
            if (msg.contains("duplicate")) {
                errors.setUsernameDuplicate(username + "is Duplicated");
                request.setAttribute("CREATE_ERROR", errors);
            }
        } catch (NamingException ex) {
            log("RegisterAccount_Naming_" + ex.getMessage());
        } finally {
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
