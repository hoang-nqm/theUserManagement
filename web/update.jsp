<%-- 
    Document   : update
    Created on : Jun 7, 2021, 7:40:22 PM
    Author     : Minh Hoang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>User <b>Information</b></h2>
                        </div>
                        <a href="getAll">Admin page</a>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <c:set var="error" value="${requestScope.ERROR_CREATE}"/>
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="updateAD" method="post" enctype="multipart/form-data">
                            <div class="modal-header">						
                                <h4 class="modal-title">User Information</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">
                                <c:set var="user" value="${requestScope.USER}"></c:set>
                                    <div class="form-group">
                                        <label>Email</label>
                                        <input value="${user.userID}" name="email" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>User Name</label>
                                    <input value="${user.userName}" name="name" type="text" class="form-control" required>
                                </div><font color="red">${error.fullNameError}</font>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input value="${user.password}" name="password" type="text" class="form-control" required>
                                </div><font color="red">${error.passwordError}</font>
                                <div class="form-group">
                                    <label>Photo</label>
                                    <input value="${user.photo}" name="photo" type="text" readonly required>
                                    <img src="images/${user.photo}"/>
                                    <input type="file" name="file" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Phone</label>
                                    <input value="${user.phone}" name="phone" type="text" class="form-control" required>
                                </div><font color="red">${error.phoneError}</font>
                                <label>Status (Active or Inactive)</label>
                                <input value="${user.status}" name="status" type="text" class="form-control" required> 
                            </div><font color="red">${error.statusError}</font>
                    </div>
                    <div class="container-login100-form-btn m-t-17">
                        <button class="login100-form-btn">
                            Update
                        </button>
                    </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
