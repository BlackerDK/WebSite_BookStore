/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.blackerdk.bookstore.config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author DUY KHANH
 */
//Quy ước ENDPOINT Điểm chạm đến API
//http://tên-miền/api => api chạm đến dây
@ApplicationPath("api") // api đến các này phục vụ
                        //phục vụ gì thì từng class ở trong source sẽ lo
                        // /api/books => trả về cuốn sách thô
public class BookApplication extends ResourceConfig{

    public BookApplication() {
        packages("org.blackerdk.bookstore.resource");
        //Những class v1-v2 sẽ phục vụ data JSON thô cho ai đó cần
    }
    
    
}
