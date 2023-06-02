
<%-- 
    Document   : bill
    Created on : May 23, 2023, 2:08:40 PM
    Author     : DUY KHANH
--%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bill</title>
        <link rel="stylesheet" href="css/billcss.css"/>
    </head>
    <body>
        <div class="form-container">
            <h1 class="logo">Shop Book Bill</h1>
            <c:set var="cart" value="${sessionScope.ADD.book}" />
            <c:if test="${not empty cart}">
                <form action="ServletShowBill">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>NO.</th>
                                <th>Name Book</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="book" items="${cart.entrySet()}" varStatus="counter">
                                <c:set var="key" value="${book.value}"/>
                                <c:set var="total" value="${key.getBookQuantity()*key.getBookPrice()}"/>
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${key.getBookName()}</td>
                                    <td>${key.getBookQuantity()}</td>
                                    <td>${key.getBookPrice()}</td>
                                    <td>${total}.000.000 VND</td>
                                </tr>
                            </c:forEach>
                        <br>
                        <tr>
                            <td>
                                <c:set var="date" value="${requestScope.DATE}"/>
                                Date: ${date}
                            </td>
                            <td colspan="3">
                                Total
                            </td>
                            <td>
                                <c:set var="Sum" value="${requestScope.TOTAL}"/>
                                ${Sum}00.000 VND
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
                <h2 class="thank-you">Thank you</h2>
            </c:if>
            <c:if test="${empty cart}">
                <div>
                    Do not has Book
                </div>
                <a href="ServletShopPage">Click To Add More Book</a>
            </c:if>
            <a href="login.jsp" class="logout-link">Click To Logout</a>
            <a href="ServletShopPage"class="logout-link">Click to Welcome Shop-Page</a>
        </div>
    </body>
</html>