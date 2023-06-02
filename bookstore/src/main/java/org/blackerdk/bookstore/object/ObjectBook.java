/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.blackerdk.bookstore.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.blackerdk.bookstore.dao.BookDAO;
import org.blackerdk.bookstore.dto.Book;

/**
 *
 * @author DUY KHANH
 */
public class ObjectBook implements Serializable {

    private Map<String, Book> book;

    public Map<String, Book> getBook() {
        return book;
    }

    public boolean addBook(String id, int quantity) {
        boolean result = false;
        String name = "";
        int price = 0;
        String img = null;
        try {
            //Create DAO
            BookDAO dao = new BookDAO();
            //show book
            ArrayList<Book> books = dao.showBook();
            for (Book cartDTO : books) {
                // Gan gia tri cua bookName va bookPrice    
                if(cartDTO.getBookID().contains(id)) {
                    name = cartDTO.getBookName();
                    price = cartDTO.getBookPrice();
                    img=cartDTO.getBookImage();
                    
                }
            }
            if (id == null) {
                return result;
            }
            if (id.trim().isEmpty()) {
                return result;
            }
            if (quantity < 0) {
                return result;
            }
            if (this.book == null) {
                this.book = new HashMap<>();
            }
            if (this.book.containsKey(id)) {
                int currentQuantity = this.book.get(id).getBookQuantity();
                quantity = quantity + currentQuantity;
            }
            //Create new cartDTO
            Book cartDTO = new Book(id, name, quantity, price, img);
            this.book.put(id, cartDTO);//Add card vao
            result = true;
        } finally {
            return result;
        }
    }

    public boolean removeBook(String id, int quantity) {
        boolean result = false;
        String name = "";
        int price = 0;
        String img = null;
        try {
            //Create DAO
            BookDAO dao = new BookDAO();
            //show book
            ArrayList<Book> books = dao.showBook();
            for (Book cartDTO : books) {
                // Gan gia tri cua bookName va bookPrice    
                if (cartDTO.getBookID().contains(id)) {
                    name = cartDTO.getBookName();
                    price = cartDTO.getBookPrice();
                }
            }
            if (id == null) {
                return result;
            }
            if (id.trim().isEmpty()) {
                return result;
            }
            if (quantity < 0) {
                return result;
            }
            if (this.book == null) {
                return result; //Check book
            }
            int currentQuantity = this.book.get(id).getBookQuantity();//create currentQuantity
            if (currentQuantity >= quantity) {
                quantity = currentQuantity - quantity;
            }
            if (quantity == 0) {
                this.book.remove(id);
                if (this.book.isEmpty()) {
                    this.book = null;
                }
            } else {
                //Create new cartDTO
                Book cartDTO = new Book(id, name, quantity, price, img);
                this.book.get(id).setBookQuantity(quantity);
            }
            result = true;
        } finally {
            return result;
        }
    }

    public float totalMoney() {
        float total = 0;
        for (Map.Entry<String, Book> entry : book.entrySet()) {
            total += entry.getValue().getBookQuantity() * entry.getValue().getBookPrice();
        }
        return total;
    }
        public int totalCount(){
        int totalCount = 0;
        for (Map.Entry<String, Book> entry : book.entrySet()) {
            totalCount += entry.getValue().getBookQuantity();
        }
        return totalCount;
    }
    
}
