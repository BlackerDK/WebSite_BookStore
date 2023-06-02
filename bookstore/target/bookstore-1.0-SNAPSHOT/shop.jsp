<%-- 
    Document   : shop
    Created on : May 23, 2023, 2:14:33 PM
    Author     : DUY KHANH
--%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <title>Website Bán Hàng</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    </head>
    <body>
        <header>
            <nav>
                <ul>
                    <li class="logo"><a href="ServletShopPage">BookStore</a></li>
                    <li><a href="ServletShopPage">Home</a></li>
                    <li><a href="#">
                            <c:set var="resultName" value="${sessionScope.USER.fullName}"/>
                            <c:if test="${not empty resultName}">
                                Welcome,${sessionScope.USER.fullName}
                            </c:if>
                            <c:if test="${empty resultName}">
                                Welcome,Guest
                            </c:if>   
                        </a></li>
                    <li><a href="login.jsp">Login</a></li>
                    <li>
                        <form action="Controller">
                            <div class="search-bar">
                                <input type="text" placeholder="Searching..." name="txtSearchValueProducts">
                                <button type="submit" name="btAction" value="Searching"><i class="fas fa-search"></i></button>
                            </div>
                        </form>
                    </li>
                    <li><a href="view.jsp"><i class="fas fa-shopping-cart icon-cart">Cart</i>${requestScope.COUNT}</a></li>
                </ul>
            </nav>
        </header>
        <div class="image-backround">
            <h1>Welcome to BookStore
            </h1>
        </div>
        <c:set var="result" value="${requestScope.BOOK}"/>
        <c:if test="${not empty result}">
            <section id="featured-products">
                <h2>Sản phẩm nổi bật</h2>
                <c:forEach var="dto" items="${result}" varStatus="counter">
                    <form action="Controller" method="POST">
                        <div class="product">
                            <div>                            
                                <img src="${dto.bookImage}">
                                <input type="hidden" name="txtImage" value="${dto.bookImage}"/>
                            </div>
                            <h3>${dto.bookName}                               
                                <input type="hidden" name="txtID" value="${dto.bookID}"/>
                            </h3>
                            <div class="rating">
                                <input type="radio" id="star5" name="rating" value="5">
                                <label for="star5"></label>
                                <input type="radio" id="star4" name="rating" value="4">
                                <label for="star4"></label>
                                <input type="radio" id="star3" name="rating" value="3">
                                <label for="star3"></label>
                                <input type="radio" id="star2" name="rating" value="2">
                                <label for="star2"></label>
                                <input type="radio" id="star1" name="rating" value="1">
                                <label for="star1"></label>
                            </div>
                            <p>Price: $${dto.bookPrice}</p>
                            <input class="btn" type="submit" value="Add Items" name="btAction"/>
                            <input type="hidden" name="txtQuantity" value="1"/>
                        </div>
                    </form>
                </c:forEach>
            </section>
        </c:if>
        <c:set var="resultS" value="${requestScope.SEARCH_RESULT_PRODUCT}"/>
        <c:if test="${not empty resultS}">
            <section id="featured-products">
                <h2>Sản phẩm nổi bật</h2>
                <c:forEach var="dto" items="${resultS}" varStatus="counter">
                    <form action="Controller" method="POST">
                        <div class="product">
                            <img src="${dto.bookImage}">
                            <input type="hidden" name="txtImage" value="${dto.bookImage}"/>
                            <h3>${dto.bookName}
                                <input type="hidden" name="txtID" value="${dto.bookID}"/>
                            </h3>
                            <div class="rating">
                                <input type="radio" id="star5" name="rating" value="5">
                                <label for="star5"></label>
                                <input type="radio" id="star4" name="rating" value="4">
                                <label for="star4"></label>
                                <input type="radio" id="star3" name="rating" value="3">
                                <label for="star3"></label>
                                <input type="radio" id="star2" name="rating" value="2">
                                <label for="star2"></label>
                                <input type="radio" id="star1" name="rating" value="1">
                                <label for="star1"></label>
                            </div>
                            <p>Price: $${dto.bookPrice}</p>
                            <input class="btn" type="submit" value="Add Items" name="btAction"/>
                            <input type="hidden" name="txtQuantity" value="1"/>
                        </div>
                    </form>>
                </c:forEach>
            </section>
        </c:if>
        <footer>
            <div class="container">
                <div class="footer-info footer-social">
                    <h3>Bookstore</h3>
                    <p>Địa chỉ: 123 Đường Sách, Thành phố, Quốc gia</p>
                    <p>Điện thoại: (012) 345-6789</p>
                    <p>Email: info@bookstore.com</p>
                    <h3>Kết nối với chúng tôi</h3>
                    <ul class="item">
                        <li><a href="#"><i class="fab fa-facebook-f"></i></a></li>
                        <li><a href="#"><i class="fab fa-twitter"></i></a></li>
                        <li><a href="#"><i class="fab fa-instagram"></i></a></li>
                    </ul>
                </div>
            </div>
        </footer>
    </body>
</html>
