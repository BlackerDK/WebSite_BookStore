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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import org.blackerdk.bookstore.dto.Book;
import org.blackerdk.bookstore.object.ObjectBook;
import org.blackerdk.bookstore.util.DBUtil;

/**
 *
 * @author DUY KHANH
 */
//Có vài loại interface
// 1 )Maker Interface :Loại interface mà ko y/c class implements , phải có code
// interface ko có hàm abstract- báo hiệu cho JVM biết cần phải biết
// 2 )Function Interface : Loại interface mà chỉ only 1 hàm abtracts
//Ko nhiều hơn 
//Function Interface chơi vs LAMBDA expecssion
//Comparable /Comparator
//3.Những interface còn lại là implement và viết code cho tất cả các hàm

public class BookDAO implements Serializable{
public ArrayList<Book> showBook() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Book> dto = new ArrayList<>();
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "SELECT IDBook, NameBook, Quantity, Price, Image AS ImageSource\n"
                        + "FROM BookTable;";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    dto.add(new Book(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return dto;
    }



   private List<Book> booksList;//Khai báo cái list
    public List<Book> getBooksList() {
        return booksList;
    }


    public ArrayList<Book> searchProducts(String keyword) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;//Do dạng tham số động
        ResultSet rs = null;
        ArrayList<Book> dto = new ArrayList<>();
        try {
            //1 . Connect Database
            con = DBUtil.makeConnection();
            //2 . Create SQL Statement String
            if (con != null) { // Trong username là khóa chính
                String sql = "Select * "
                        + "From BookTable "
                        + "Where NameBook Like ?";
                //3 . Create Statement to SET SQL
                stm = con.prepareStatement(sql);//Nạp  này vào Obj
                stm.setString(1, "%" + keyword + "%");
                //4 . Execute Query
                rs = stm.executeQuery();
                //5 . Process
                while (rs.next()) {
                    dto.add(new Book(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
                    //Get field/ collum
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
        return dto;
    }

    public void ShowBill(ObjectBook cartObj, String user) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        LocalDate date = LocalDate.now();
        String curDate = date.toString();
        try {
            //1 connect DB
            con = DBUtil.makeConnection();
            if (con != null) {
                //2-Viet sql
                Map<String, Book> myMap = cartObj.getBook();
                String sql = "Insert into Orders(userName,createDate) Values(?,?)";
                //3 vbiet statement
                stm = con.prepareStatement(sql);
                stm.setString(1, user);
                stm.setString(2, curDate);
                stm.executeUpdate();
                String sql2 = "Select top 1 orderID from [Orders] "
                        + "order by orderID desc";
                PreparedStatement stm2 = con.prepareStatement(sql2);
                rs = stm2.executeQuery();
                if (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    for (Map.Entry<String, Book> entry : myMap.entrySet()) {
                        float total = entry.getValue().getBookQuantity() * entry.getValue().getBookPrice();
                        String sql3 = "Insert into Details(orderID,IDBook,Name,Quantity,Price,Total) VALUES(?,?,?,?,?,?)";
                        PreparedStatement stm3 = con.prepareStatement(sql3);
                        stm3.setInt(1, orderID);
                        stm3.setString(2, entry.getValue().getBookID());
                        stm3.setString(3, entry.getValue().getBookName());
                        stm3.setInt(4, entry.getValue().getBookQuantity());
                        stm3.setInt(5, entry.getValue().getBookPrice());
                        stm3.setFloat(6, total);
                        stm3.executeUpdate();
                    }
                    String sql4 = "Update BookTable set "
                            + "Quantity =Quantity - ? "
                            + "where IDBook = ?";
                    PreparedStatement stm4 = con.prepareStatement(sql4);
                    for (Map.Entry<String, Book> entry : myMap.entrySet()) {
                        stm4.setInt(1, entry.getValue().getBookQuantity());
                        stm4.setString(2, entry.getValue().getBookID());
                        stm4.executeUpdate();
                    }
                }
            }
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

    public boolean updateBook(String bookID, String bookName, int quantity, int price)
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
                String sql = "Update  BookTable SET NameBook = ? , Quantity = ? , Price =?\n"
                        + "where IDBook =?";
                //Điều kiện của hidden username
                //3 . Create Statement to SET SQL
                stm = con.prepareStatement(sql);//Nạp  này vào Obj                
                stm.setString(1, bookName);
                stm.setInt(2, quantity);
                stm.setInt(3, price);
                stm.setString(4, bookID);
                //4 . Execute Query
                int row = stm.executeUpdate();
                if (row > 0) {
                    result = true;
                }
                //5 . Process
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

    public boolean deleteBook(String primaryKey)
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
                String sql = "DELETE from BookTable "
                        + "WHERE IDBook = ?";
                //Điều kiện của hidden username
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
}
