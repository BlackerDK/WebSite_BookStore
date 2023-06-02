<%-- 
    Document   : login
    Created on : May 23, 2023, 7:12:12 AM
    Author     : DUY KHANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP LOGIN</title>'
        <link rel="stylesheet" href="css/login.css"/>
    </head>
    <body>
        <h2>Khanh Book Store LOGIN-REGISTER</h2>
        <div class="container" id="container">
            <div class="form-container sign-up-container">
                <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
                <form action="ServletRegisterAccount" method="POST">
                    <h1>Sign-up</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>create new account to shopping book store</span>
                    <input type="text" name="txtUsername" value="" size="20" required="" placeholder="User name"/><br>
                    <c:if test="${not empty errors.usernameLengthError}">
                        <font color="red">
                        ${errors.usernameLengthError}
                        </font><br>
                    </c:if>
                    <c:if test="${not empty errors.usernameDuplicate}">
                        <font color="red">
                        ${errors.usernameDuplicate}
                        </font><br>                   
                    </c:if>    
                    <input type="password" name="txtPassword" value="" size="30" required="" placeholder="Password"/><br>
                    <c:if test="${not empty errors.passwordLengthError}">
                        <font color="red">
                        ${errors.passwordLengthError}
                        </font><br>
                    </c:if>
                    <input type="password" name="txtConfirm" value="" size="30" required="" placeholder="Confirm"/><br>
                    <c:if test="${not empty errors.confirmNotMatch}">
                        <font color="red">
                        ${errors.confirmNotMatch}
                        </font><br>
                    </c:if>
                    <input type="text" name="txtFullname" value="" size="30" required="" placeholder="Full name"/><br>
                    <c:if test="${not empty errors.fullNameLengthError}">
                        <font color="red">
                        ${errors.fullNameLengthError}
                        </font><br>
                    </c:if>
                    <input type="text" name="txtPhone" value="" size="11" required="" placeholder="Phone"/><br>
                    <c:if test="${not empty errors.phoneLengthError}">
                        <font color="red">
                        ${errors.phoneLengthError}
                        </font><br>
                    </c:if>
                    <input type="email" name="txtMail" value="" required="" placeholder="Email"/><br>
                    <input type="text" name="txtAddress" value="" size="50" required="" placeholder="Address"/><br>
                    <c:if test="${not empty errors.addressLengthError}">
                        <font color="red">
                        ${errors.addressLengthError}
                        </font><br>
                    </c:if>
                    <button type="submit" value="Register" name="btAction">Sign Up</button>
                </form>
            </div>
            <div class="form-container sign-in-container">
                <form action="Controller" method="POST">
                    <c:set var="Msg" value="${requestScope.MSG}"/>
                    <h1>Sign in</h1>
                    <div class="social-container">
                        <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                        <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                        <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                    <span>or use your account</span>
                    <input type="text" placeholder="User name" name="txtUserName" required=""/>
                    <input type="password" placeholder="Password" name="txtPassword" required=""/>
                    <button type="submit" value="Login" name="btAction">Sign In</button>
                    <div class="errors">${Msg}</div>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>Welcome Back!</h1>
                        <p>To keep connected with us please login with your personal info</p>
                        <button class="ghost" id="signIn">Sign In</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Hello, Friend!</h1>
                        <p>Enter your personal details and start journey with us</p>
                        <button class="ghost" id="signUp">Sign Up</button>
                    </div>
                </div>
            </div>
        </div>
        <footer>
            <p>
                Welcome to our shop! Here, we provide a wide range of high-quality products that are sure to meet even the most discerning customer's needs. 
                You'll have the opportunity to experience the very best products at the most reasonable prices. We wish you a pleasant shopping experience at our store!
            </p>
        </footer>
        <script>
            const signUpButton = document.getElementById('signUp');
            const signInButton = document.getElementById('signIn');
            const container = document.getElementById('container');
            signUpButton.addEventListener('click', () => {
                container.classList.add("right-panel-active");
            });
            signInButton.addEventListener('click', () => {
                container.classList.remove("right-panel-active");
            });
        </script>
    </body>
</html>
