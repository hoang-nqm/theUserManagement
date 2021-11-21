<%-- 
    Document   : insert
    Created on : Jun 6, 2021, 2:19:49 PM
    Author     : Minh Hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Page</title>
    </head>
    <body>
      
        <h5>INSERT USER</h5>
        <c:set var="user"  value="${requestScope.USER}"/>
        <c:set var="error" value="${requestScope.ERROR_CREATE}"/>
        <form action="insert" method="POST" enctype="multipart/form-data" >
            User ID: <input type="text" name="txtUserId" value="${user.userID}"/>  <font color="red">${error.userIDError}</font> <br>
            Full Name: <input type="text" name="txtFullName" value="${user.userName}"/> <font color="red">${error.fullNameError}</font><br>
            Password: <input type="password" name="txtPassword" value="${user.password}"/> <font color="red">${error.passwordError}</font><br>
            Confirm: <input type="password" name="txtConfirm" value="${requestScope.confirm}"/> <font color="red">${error.confirmError}</font><br>
            Phone: <input type="text" name="txtPhone" value="${user.phone}"/><font color="red">${error.phoneError}</font><br>
            Photo: <input type="file" name="file" /> <br>
            <div class="container-login100-form-btn m-t-17">
                <button class="login100-form-btn">
                Insert
                </button>
            </div>
        </form>
    </body>
</html>

