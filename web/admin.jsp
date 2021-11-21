<%-- 
    Document   : admin
    Created on : Jun 3, 2021, 9:17:23 PM
    Author     : Minh Hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        Hi ${sessionScope.LOGIN_USER.userName}
        <a href="logout">Logout</a>
        <a href="insertView">Insert new User</a>
        <a href="promotionView">View promotion list</a>
        <form action="search" method="POST">
            Search by Name: <input type="text" name="txtName" value="${param.txtName}"> 
            <div class="container-login100-form-btn m-t-17">
                <button class="login100-form-btn">
                    Search
                </button>
            </div>
        </form>
        <a href="getAll">ALL</a>
        <c:forEach items="${requestScope.LIST_ROLE}" var="role">
            <a href="role?roleID=${role.roleID}">${role.roleName}</a>
        </c:forEach>

        <table border="1">
            <thead>
                <tr>
                    <th>User Name</th>
                    <th>Role ID</th>
                    <th>Phone</th>
                    <th>Photo</th>
                    <th>Status</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.LIST_USER}" var="o">

                    <tr>

                        <td>${o.userName}</td>
                        <td> <c:if test="${o.roleID eq '2'}">
                                USER
                            </c:if>
                            <c:if test="${o.roleID eq '1'}">
                                ADMIN
                            </c:if></td>
                        <td>${o.phone}</td>
                        <td><img src="image/${o.photo}" width="100" height="50"></td>
                        <td>${o.status}</td>
                        <td>
                            <c:if test="${o.roleID eq '2' }">
                                <a href="getUser?userID=${o.userID}">Update</a> 
                            </c:if>
                        </td>

                        <td>
                            <c:if test="${o.roleID eq '2'and o.status eq 'Active'}">
                                <a href="delete?userID=${o.userID}">Delete</a> 
                            </c:if>
                        </td>

                    </tr>

                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
