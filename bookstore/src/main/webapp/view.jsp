<%-- 
    Document   : view
    Created on : May 23, 2023, 1:55:10 PM
    Author     : DUY KHANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>View Cart</title>
        <link href="css/viewcss.css" rel="stylesheet" />
    </head>
    <body class="bd-ckeck">
        <div class="form-container">
            <div class="form-header">
                <h1 class="logo">YOUR CART INCLUDE:</h1>
                <c:if test="${sessionScope!=null}">
                    <c:set var="cart" value="${sessionScope.ADD.book}"/>
                    <c:if test="${not empty cart}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>Image</th>
                                    <th>Name Book</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="book" items="${cart}" varStatus="counter">
                                    <c:set var="showBook" value="${book.value}"/>
                                <form action="Controller" method="POST">
                                    <tr>
                                        <td>
                                            <img src="${showBook.getBookImage()}" width="168px" height="250px">
                                        </td>
                                        <td>${showBook.getBookName()}</td>
                                        <td>$${showBook.getBookPrice()}</td>
                                        <td>${showBook.getBookQuantity()}</td>
                                        <td>
                                            <input type="checkbox" name="checkBook" value="${book.key}" />
                                        </td>
                                    </tr>
                                </c:forEach>
                                <td colspan="5">
                                    <input class="submit" type="submit" value="Remove Items" name="btAction" />
                                </td>
                            </form>
                            <h3>
                                <a class="logout-link" href="ServletShopPage">Click To Add More Book</a> <br>
                            </h3>
                            </tbody>
                        </table>
                        <a href="ServletShowBill" class="logout-link">Checkout</a>
                    </c:if>
                </c:if>
                <c:if test="${empty cart}">
                    <h3>
                        No book on Your Cart
                        <a href="ServletShopPage" class="logout-link">Click To Add More Book</a>
                    </h3>
                </c:if>
                  
            </div>
        </div>
    </body>
</html>