<%-- 
    Document   : main
    Created on : Feb 12, 2022, 10:47:59 PM
    Author     : PHT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Books store</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>        
        <link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/pay.css" rel="stylesheet" type="text/css"/>
         <style>
            a{
                text-decoration: none;
            }
            .role:hover{
                background-image: url(${pageContext.request.contextPath}/images/role.gif);
                background-size: cover;
                
            }
            .role{
                background-image: url(${pageContext.request.contextPath}/images/roler.gif);
                background-size: cover;
            }
        </style>
    </head>
    <body>

        <div class="container-fluid">
            <!--navigator-->  
            <header>
                <div class="logo">
                    <a href="${pageContext.request.contextPath}/home/index.do"><img src="https://i.pinimg.com/originals/3c/7a/f3/3c7af3c03a1fc34f679d6cb8d1af703a.png" alt=""></a>
                    <h2>Book Store</h2>
                    <form action="<c:url value="/search/searchbar.do" />">
                        <input type="text" name="searchBar" placeholder="Search.." style="width: 500px; height: 45px; margin-top: 20px; margin-left: 20px;">
                    </form> 
                </div>
                <div <c:if test = "${role==null || role=='Client'}"> hidden </c:if>style="padding: 1rem 3rem">
                    <a class="btn btn-outline-info role" href="${pageContext.request.contextPath}/manager/index.do" role="button">${role}</a>
                </div>
                <!--<a class="btn btn-sm btn-outline-success" href="#><i class="bi bi-pencil-square"></i> </a>--> 
                <nav>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/home/index.do"><i class="bi bi-house-door-fill" style="font-size: 30px;"></i></a></li>
                        <c:if test="${role != null}"><li><a href="${pageContext.request.contextPath}/cart/index.do"><i class="bi bi-basket-fill"style="font-size: 30px;"></i></a></li></c:if>

                        <c:if test="${role == null}"> <li><a href="${pageContext.request.contextPath}/login/login.do" ><i class="bi bi-person-circle"style="font-size: 30px;"></i> Login</a></li></c:if>
                            <c:if test="${role != null}"> 
                            <li class="nav-item dropdown">
                                <a class="dropdown-toggle  rounded-circle" data-bs-toggle="dropdown" href="#"><i class="bi bi-person-circle rounded-circle"style="font-size: 30px;"></i></a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/index.do">User Information</a></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/changePassword.do">Change password</a></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user/viewPayments.do">View Payments</a></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/login/logout.do">Logout</a></li>
                                </ul>
                            </c:if>
                    </ul>
                </nav>
            </header>
            <div class="container mt-3">
                <h2>Menu</h2>
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Home</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#">Categories</a>
                        <ul class="dropdown-menu">
                            <c:forEach var="category" items="${categoryList}">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/search/search.do?category=${category.categoryID}">${category.categoryName}</a></li>
                                </c:forEach>
                        </ul>
                    </li>
                    
                </ul>
            </div>
            <br>
            <!--Ung dung-->            

            <!--body-->
            <div class="row view"> 
                <div class="col">
                    <jsp:include page="/WEB-INF/views/${controller}/${action}.jsp" />    
                </div>
            </div>
            <!--footer-->

            <hr>
            <footer class="text-center text-lg-start bg-black text-muted">
                <!-- Section: Social media -->
                <section
                    class="d-flex justify-content-center justify-content-lg-between p-4 border-bottom"
                    >

                    <div style="margin-right: auto;margin-left: auto;" class="link">
                        <a href="" class="me-4 text-reset">
                            <i class="bi bi-facebook"></i>
                        </a>
                        <a href="" class="me-4 text-reset">
                            <i class="bi bi-twitter"></i>
                        </a>
                        <a href="" class="me-4 text-reset">
                            <i class="bi bi-google"></i>
                        </a>
                        <a href="" class="me-4 text-reset">
                            <i class="bi bi-instagram"></i>
                        </a>
                        <a href="" class="me-4 text-reset">
                            <i class="bi bi-linkedin"></i>
                        </a>
                        <a href="" class="me-4 text-reset">
                            <i class="bi bi-github"></i>
                        </a>
                    </div>
                    <!-- Right -->
                </section>
                <!-- Section: Social media -->

                <!-- Section: Links  -->
                <section class="footer">
                    <div class="container text-center text-md-start mt-5">
                        <!-- Grid row -->
                        <div class="row mt-3">
                            <div class="col-md-3   mx-auto mb-4">

                                <a href="#" class="text-uppercase fw-bold mb-4">Home</a>
                            </div>
                            <!-- Grid column -->

                            <!-- Grid column -->
                            <div class="col-md-3  mx-auto mb-4">
                                <!-- Links -->

                                <a href="#" class="text-uppercase fw-bold mb-4">Category</a>
                            </div>

                            <div class="col-md-3   mx-auto mb-md-0 mb-4">
                                <!-- Links -->

                                <a href="#" class="text-uppercase fw-bold mb-4">Contact</a>
                            </div>
                            <!-- Grid column -->
                        </div>
                        <!-- Grid row -->
                    </div>
                </section>
                <!-- Section: Links  -->
                <hr style="color: white;">
                <!-- Copyright -->
                <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
                    © 2022 Copyright 2021-2022 by PRJ. All Rights Reserved.
                </div>
                <!-- Copyright -->
            </footer>
        </div>

    </body>
</html>



