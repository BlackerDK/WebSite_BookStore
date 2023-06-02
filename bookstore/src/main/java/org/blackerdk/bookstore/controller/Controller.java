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
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DUY KHANH
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {
    private final String LOGIN_PAGE = "login.jsp";
    private final String LOGIN_CONTROLLER = "ServletLogin";
    private final String SEARCH_LASTNAME_CONTROLLER="ServletSearchName";
    private final String DELETE_ACCOUNT="ServletDeleteAccount";
    private final String UPDATE_ACCOUNT="ServletUpdateAccount";
    private final String ADD_CART="ServletAddBook";
    private final String REMOVE_CART="ServletRemoveBook";
    private final String VIEW_PAGE="view.jsp";
    private final String REGISTER_PAGE="ServletRegisterAccount";
    private final String UPDATE_BOOKSHOP="ServletUpdateBook";
    private final String DELETE_BOOK="ServletDeleteBook";
    private final String SEARCH_PRODUCT="ServletSearchProducts";
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
        response.setContentType("text/html;charset=UTF-8");String button = request.getParameter("btAction");//Which button does user click! 
        String url = LOGIN_PAGE;
        try {
            if(button == null) {
                //do not thing
            } else if (button.equals("Login")) {
                url=LOGIN_CONTROLLER;
            } else if (button.equals("Search")) {
                url=SEARCH_LASTNAME_CONTROLLER;
            }else if (button.equals("Delete")) {
                url=DELETE_ACCOUNT;
            }else if (button.equals("Update")) {
                url=UPDATE_ACCOUNT;
            }else if (button.equals("Add Items")){
                url=ADD_CART;
            }else if (button.equals("Remove Items")) {
                url=REMOVE_CART;
            }else if(button.equals("Cart")){
               url=VIEW_PAGE;
            }else if (button.equals("Register")) {
                url=REGISTER_PAGE;
            }else if (button.equals("UPDATE")) {
                url=UPDATE_BOOKSHOP;      
            }else if (button.equals("DeleteBook")) {
                url=DELETE_BOOK;
            }else if (button.equals("Searching")) {
                url=SEARCH_PRODUCT;
            }
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
