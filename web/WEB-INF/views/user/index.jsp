<%-- 
    Document   : index
    Created on : Mar 21, 2022, 3:26:56 AM
    Author     : SE150010 Nguyen Sy Hoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section class="row" style="background: url(${pageContext.request.contextPath}/images/background.png); background-size: cover;">
    <div class="col mt-4">
        <form action="/BookStore/changeUserData" method="post" enctype="multipart/form-data">
            <!--<form action="changeUserData.do" method="post" enctype="multipart/form-data">-->
            <h2 class="text-center">User Information </h2>

            <div class="form-outline mb-4">
                <input type="text" value="${user.fullName}" id="name" placeholder="Full name" class="form-control form-control-lg" name="name" required />
            </div>
            
            <div class="form-outline mb-4">
                <input type="text" value="${user.userName}" id="userName" placeholder="User name" class="form-control form-control-lg" name="userName" minlength="6" readonly/>
            </div>

            <div class="form-outline mb-4">
                <input type="text" value="${user.userAddress}" id="form3Example8" placeholder="Address" class="form-control form-control-lg" name="address" required />
            </div>

            <div class="row">
                <div class="col-xl-6">
                    <div class="d-md-flex justify-content-start align-items-center mb-4 py-2">

                        <h6 class="mb-0 me-4">Gender: </h6>

                        <div class="form-check form-check-inline mb-0 me-4">
                            <input
                                class="form-check-input"
                                type="radio"
                                name="gender"
                                id="female"
                                value="Female"
                                <c:if test="${user.gender.equals('Female')}">checked</c:if>
                            />
                            <label class="form-check-label" for="female">Female</label>
                        </div>

                        <div class="form-check form-check-inline mb-0 me-4">
                            <input
                                class="form-check-input"
                                type="radio"
                                name="gender"
                                id="male"
                                value="Male"
                                <c:if test="${user.gender.equals('Male')}">checked</c:if>
                            />
                            <label class="form-check-label" for="maleGender">Male</label>
                        </div>
                    </div>
                </div>
                <div class="col-xl-6">
                    <div class="form-outline mb-4">
                        <input type="date" value="${user.dateOfBirth}" id="date" class="form-control form-control-lg" name="dateOfBirth" required />
                    </div>
                </div>
            </div>  


            <div class="form-outline mb-4">
                <input type="text" value="${user.phoneNumber}" id="num" name="phoneNumber" placeholder="Phone Number" class="form-control form-control-lg" required />
            </div>

            <div class="form-outline mb-4">
                <input name="email" value="${user.email}" type="email" pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$" class="form-control" placeholder="Email*" id="email" required="" style="height: 50px; font-size: 20px;">
            </div>

            <div class="form-outline mb-4">
                <img src="${pageContext.request.contextPath}/images/user/${user.userImage}">
                <input type="file" id="form3Example90" placeholder="Avata image" class="form-control form-control-lg" name="userImage"  accept="image/*"/>
            </div>

            <div class="card-footer text-muted">
                <button type="submit" name="op" value="Save" class="btn btn-primary"><i class="bi bi-save2"></i>Save</button>
                <a href="${pageContext.request.contextPath}/home/index.do" class="btn btn-danger" style="float: right;"><i class="bi bi-x-circle"></i> Cancel</a>
            </div>
        </form>
    </div>
</section>