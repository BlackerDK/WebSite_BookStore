/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.blackerdk.bookstore.resource.v1;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.blackerdk.bookstore.dto.Book;

/**
 *
 * @author DUY KHANH
 */
@Path("v1/books") // api/v1/books đến class này
public class BookResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Book getAbook(){
    //gọi DAO
    return new Book("0","name", 0, 0, "image"); //object OOP   
    }
}
