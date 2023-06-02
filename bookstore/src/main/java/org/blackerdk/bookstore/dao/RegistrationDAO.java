/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.blackerdk.bookstore.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import org.blackerdk.bookstore.dto.RegistrationDTO;
import org.blackerdk.bookstore.util.DBUtil;

/**
 *
 * @author DUY KHANH
 */
public class RegistrationDAO implements Serializable {
    //Mục tiêu -->Access DataBase

    public RegistrationDTO checkLogin(String username, String passwords)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;//Do dạng tham số động
        ResultSet rs = null;
        RegistrationDTO result = null;
        try {
            //1 . Connect Database
            con = DBUtil.makeConnection();
            //2 . Create SQL Statement String
            if (con != null) { // Trong username là khóa chính
                String sql = "select fullname,isAdmin from Users where userID =? and password =?";
                //3 . Create Statement to SET SQL
                stm = con.prepareStatement(sql);//Nạp  này vào Obj
                stm.setString(1, username);// Thiết lập các  giá trị tham số vào 
                stm.setString(2, passwords); // các câu truy vấn này
                //4 . Execute Query
                rs = stm.executeQuery();
                //5 . Process
                if (rs.next()) {
                    String fullName = rs.getString("fullname");
                    boolean role = rs.getBoolean("isAdmin");
                    result = new RegistrationDTO(username, passwords, fullName, role, null, null, null);
                }
            }//End if connection is process
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    private List<RegistrationDTO> accounts;//Khai báo cái list

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }
    
    public void searchLastname(String keyword) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;//Do dạng tham số động
        ResultSet rs = null;
        try {
            //1 . Connect Database
            con = DBUtil.makeConnection();
            //2 . Create SQL Statement String
            if (con != null) { // Trong username là khóa chính
                String sql = "Select * "
                        + "From Users "
                        + "Where fullname Like ?";
                //3 . Create Statement to SET SQL
                stm = con.prepareStatement(sql);//Nạp  này vào Obj
                stm.setString(1, "%" + keyword + "%");
                //4 . Execute Query
                rs = stm.executeQuery();
                //5 . Process
                while (rs.next()) {
                    //Get field/ collum
                    String username = rs.getString(1);
                    String password = rs.getString(2);
                    String Fullname = rs.getString(3);
                    boolean role = rs.getBoolean(4);
                    String phone = rs.getString(5);
                    String email = rs.getString(6);
                    String address = rs.getString(7);
                    //Create DTO instance
                    RegistrationDTO dto = new RegistrationDTO(username, password, Fullname, role, phone, email, address);
                    //add to account List
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }//Check account-->Sẽ khởi tạo giá trị cho List khi giá trị account = null
                    this.accounts.add(dto);
                }//end rs has more than one record
            }//End if connection is existed
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            
        }
    }
    
    public boolean DeleteAcount(String primaryKey) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;//Do dạng tham số động
        ResultSet rs = null;
        boolean result = false;
        try {
            //1 . Connect Database
            con = DBUtil.makeConnection();
            //2 . Create SQL Statement String
            if (con != null) { // Trong username là khóa chính
                String sql = "Delete From Users "
                        + "Where userID = ?";
                //3 . Create Statement to SET SQL
                stm = con.prepareStatement(sql);//Nạp  này vào Obj
                stm.setString(1, primaryKey);
                //4 . Execute Query
                int row = stm.executeUpdate();
                if (row > 0) {
                    result = true;
                }
                //5 . Process
            }//End if connection is process
        } finally {
            
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
            
        }
        return result;
    }

    public boolean updateAccount(String username, String password, String phone, String email, String address)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;//Do dạng tham số động
        ResultSet rs = null;
        boolean result = false;
        try {
            //1 . Connect Database
            con = DBUtil.makeConnection();
            //2 . Create SQL Statement String
            if (con != null) { // Trong username là khóa chính
                String sql = "UPDATE Users "
                        + "SET password = ? , phone = ? , email =?, address= ? "
                        //Hai phương thức cần update  
                        + "WHERE userID = ?";
                //Điều kiện của hidden username
                //3 . Create Statement to SET SQL
                stm = con.prepareStatement(sql);//Nạp  này vào Obj                
                stm.setString(1, password);
                stm.setString(2, phone);
                stm.setString(3, email);
                stm.setString(4, address);
                stm.setString(5, username);
                //4 . Execute Query
                int row = stm.executeUpdate();
                if (row > 0) {
                    result = true;
                }
                //5 . Process
            }//End if connection is process
        } finally {
            
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean createUser(RegistrationDTO dto) throws SQLException, NamingException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1-Connect DB
            con = DBUtil.makeConnection();
            if (con != null) {
                //2-viet cau lenh SQL
                String sql = "insert into "
                        + "dbo.[Users] (userID, [password], fullname, isAdmin, phone, email, [address]) "
                        + "values(?,?,?,?,?,?,?)";
                //3-Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPasswords());
                stm.setString(3, dto.getFullName());
                stm.setBoolean(4, dto.isRole());
                stm.setString(5, dto.getPhone());
                stm.setString(6, dto.getEmail());
                stm.setString(7, dto.getAddress());
                //4-Excute statement
                rs = stm.executeQuery();
                //5-Result process
                int row = stm.executeUpdate();
                if (row > 0) {
                    result = true;
                }
            }            
        } finally {
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                con.close();
            }
            if (stm != null) {
                con.close();
            }
        }
        return result;
    }

}
