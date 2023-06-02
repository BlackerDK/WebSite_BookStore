<%-- 
    Document   : updateBook
    Created on : May 23, 2023, 2:04:21 PM
    Author     : DUY KHANH
--%>

<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Update</title>
        <link rel="stylesheet" href="css/admincss.css"/>
    </head>
    <body>
        <h1 class="Page">BOOK UPDATE</h1>
        <c:set var="result" value="${requestScope.BOOKS}"/>
        <c:if test="${not empty result}">       
            <table class="search-table">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID Book</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                </thead> 
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus = "counter">
                    <form action="Controller" method="POST">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.bookID}
                                <input type="hidden" name="txtID" value="${dto.bookID}" />
                            </td>
                            <td>
                                <input type="text" name="txtName" value="${dto.bookName}" />

                            </td>
                            <td>
                                <input type="text" name="txtQuantity" value="${dto.bookQuantity}"/>
                            </td>
                            <td>
                                <input type="text" name="txtPrice" value="${dto.bookPrice}" />
                            </td>
                            <td>
                                <input type="submit" value="UPDATE" name="btAction"/>
                            </td>
                            <td class="delete">
                                <c:url var = "deleteBook" value = "Controller">
                                    <c:param name="btAction" value="DeleteBook"/>
                                    <c:param name="primaryKey" value="${dto.bookName}"/>
                                </c:url>
                                <a href="${deleteBook}"> Delete </a> 
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <a class="logout-link" href="login.jsp">Click To Logout</a>
    <a class="logout-link" href="admin.jsp">Click To Comeback ADMIN</a>
</body>
</html>
