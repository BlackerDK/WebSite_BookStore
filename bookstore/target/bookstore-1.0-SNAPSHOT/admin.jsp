<%-- 
    Document   : admin
    Created on : May 23, 2023, 1:26:17 PM
    Author     : DUY KHANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AD page</title>
        <link rel="stylesheet" href="css/admincss.css"/>
    </head>
    <body>
        <div class="Page">
            <h1>ADMIN PAGE</h1>
            <div>
                <font>
                Welcome,${sessionScope.USER.fullName}
                </font>
            </div>
            <a href="login.jsp" class="logout-link">Click To Logout</a>
             <a href="ServletUpdateBookPage" class="logout-link">Update Data Book</a>
        </div>
        <div class="search-employees">
            <form action="Controller">
                <input type="text" name="txtSearchValue" 
                       placeholder="Search Value" 
                       value="${param.txtSearchValue}" />
                <button type="submit" name="btAction" value="Search">Search</button>
            </form>
        </div>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">                
                <table class="search-table">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>User ID</th>
                            <th>Pass word</th>
                            <th>Full name</th>
                            <th>Phone</th>
                            <th>Email</th>
                            <th>Address</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus = "counter">
                        <form action="Controller">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" 
                                           value="${dto.username}"/>
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" 
                                           value="${dto.passwords}"/>
                                </td>
                                <td> 
                                    ${dto.fullName}
                                </td>
                                <td>
                                    <input type="text" name="txtPhone" 
                                           value="${dto.phone}"/>
                                </td>
                                <td>
                                    <input type="text" name="txtEmail" 
                                           value="${dto.email}"/>
                                </td>
                                <td>
                                    <input type="text" name="txtAddress" 
                                           value="${dto.address}"/>                                       
                                </td>                                       
                                <td class="delete">
                                    <c:url var = "deleteLink" value = "Controller">
                                        <c:param name="btAction" value="Delete"/>
                                        <c:param name="primaryKey" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}"> Delete </a> 
                                </td>
                                <td>
                                    <input type="submit" value="Update" name ="btAction" />
                                    <input type="hidden" name ="lastSearchValue" value="${searchValue}" />
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty result}">
            <h2>
                No matches!!!
            </h2>
        </c:if>
    </c:if>
</body>
</html>
