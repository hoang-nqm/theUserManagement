<%-- 
    Document   : promotionList
    Created on : Jun 8, 2021, 8:58:44 PM
    Author     : Minh Hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promotion List Page</title>
    </head>
    <body>
        <a href="getAll">Admin page</a>
        <h3>Promotion List</h3>
        <form action="updateRank" method="post">
            <table border="1">
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>User Name</th>
                        <th>Role ID</th>
                        <th>Phone</th>
                        <th>Photo</th>
                        <th>Rank</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.PROMOTION}" var="var">  
                        <c:set var="error" value="${requestScope.ERROR_CREATE}"/>
                        <tr>
                            <td><input value="${var.dto.userID}" name="userID" type="text" readonly required></td>
                            <td>${var.dto.userName}</td>
                            <td> <c:if test="${var.dto.roleID eq '2'}">
                                    USER
                                </c:if>
                            </td>
                            <td>${var.dto.phone}</td>
                            <td><img src="image/${var.dto.photo}" width="100" height="50"></td>

                            <td>
                                <input value="${var.rank}" name="rank" type="text">
                                </div><font color="red">${error.rankError}</font>
                                <input type="submit" value="Update Rank">
                            </td>

                            <td>

                                <a href="deletePromo?userID=${var.dto.userID}">Delete</a> 

                            </td>

                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </form>
        <h3>User can add promotion list</h3>
        <table border="1">
            <thead>
                <tr>
                    <th>User Name</th>
                    <th>Role ID</th>
                    <th>Phone</th>
                    <th>Photo</th>
                    <th>Status</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.LIST_USER}" var="o">
                    <c:if test="${o.promotionStatus eq 'Enable'&& o.roleID eq'2'}">
                        <tr>

                            <td>${o.userName}</td>
                            <td> <c:if test="${o.roleID eq '2'}">
                                    USER
                                </c:if>
                            </td>
                            <td>${o.phone}</td>
                            <td><img src="image/${o.photo}" width="100" height="50"></td>
                            <td>${o.status}</td>
                            <td>

                                <a href="addPromo?userID=${o.userID}">Add promotion list</a> 

                            </td>

                        </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
