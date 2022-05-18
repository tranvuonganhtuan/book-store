<%-- 
    Document   : changePassword
    Created on : Mar 21, 2022, 4:06:14 AM
    Author     : SE150010 Nguyen Sy Hoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<title>Change Password</title>
<div class="col-md-6">
    <div class="card-body p-md-4 text-black">

        <form action="changeProcess.do">
        <!--<form action="regist.do" method="post">-->
            <div class="form-outline mb-4">
                <input type="password" id="old-password" placeholder="Old Password" class="form-control form-control-lg" name="old-password" minlength="6" required />
            </div>
        
            <div class="form-outline mb-4">
                <input type="password" id="new-password" placeholder="New Password" class="form-control form-control-lg" name="new-password" minlength="6" required />
            </div>

            <div class="form-outline mb-4">
                <input type="password" id="re-password" placeholder="Re-Password" class="form-control form-control-lg" name="re-password" minlength="6" required />
            </div>

            <div class="d-flex justify-content-end pt-3">
                <a href="index.do" class="btn btn-light btn-lg">Home Page</a>
                <input type="submit" class="btn btn-warning btn-lg ms-2" value="Submit form">
            </div>
            <p style="color: red; font-weight: bold">${errorMessage}</p>
        </form>
    </div>
</div>
