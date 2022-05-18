<%-- 
    Document   : index
    Created on : Mar 12, 2022, 10:37:09 PM
    Author     : SE150010 Nguyen Sy Hoan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    a{
        text-decoration: none;
        text-align: center;
        color: gray;
    }
     
    .card-body{
      padding: 7rem 1rem;
    }
    .author:hover{
      background-image: url(${pageContext.request.contextPath}/images/author.gif);
      background-size: cover;
    }
    .publisher:hover{
      background-image: url(${pageContext.request.contextPath}/images/publisher.gif);
      background-size: cover;
    }
    .category:hover{
      background-image: url(${pageContext.request.contextPath}/images/category.gif);
      background-size: cover;
    }
    .book:hover{
      background-image: url(${pageContext.request.contextPath}/images/bookmanage.gif);
      background-size: cover;
    }
    .voucher:hover{
      background-image: url(${pageContext.request.contextPath}/images/voucher.gif);
      background-size: cover;
    }
    .user:hover{
      background-image: url(${pageContext.request.contextPath}/images/user.gif);
      background-size: cover;
    }
</style>
           
                
<div class="container mt-3">
    
    <div class="row">
        <div class="col">
            <a href="${pageContext.request.contextPath}/manager/authormanage.do">
                <div class="d-flex justify-content-center mb-3 product container mt-3">
                    <div class="card author" style="width:300px;height: 300px;">
                        <div class="card-body">
                            <h4 class="card-title">Author Manage</h4>
                        </div>
                    </div>
                    <br>
                </div>
            </a>
        </div>
        <div class="col">
            <a href="${pageContext.request.contextPath}/manager/publishermanage.do">
                <div class="d-flex justify-content-center mb-3 product container mt-3">
                    <div class="card publisher" style="width:300px;height: 300px;">
                        <div class="card-body">
                            <h4 class="card-title">Publisher Manage</h4>
                        </div>
                    </div>
                    <br>
                </div>
            </a>
        </div>
        <div class="col">
            <a href="${pageContext.request.contextPath}/manager/categorymanage.do">
                <div class="d-flex justify-content-center mb-3 product container mt-3">
                    <div class="card category" style="width:300px;height: 300px;">
                        <div class="card-body">
                            <h4 class="card-title">Category Manage</h4>
                        </div>
                    </div>
                    <br>
                </div>
            </a>
        </div>
    </div>     
    <div class="row">
        <div class="col">
            <a href="${pageContext.request.contextPath}/manager/bookmanage.do">
                <div class="d-flex justify-content-center mb-3 product container mt-3">
                    <div class="card book" style="width:300px;height: 300px;">
                        <div class="card-body">
                            <h4 class="card-title">Book Manage</h4>
                        </div>
                    </div>
                    <br>
                </div>
            </a>
        </div>   
        <div class="col">
            <a href="${pageContext.request.contextPath}/manager/vouchermanage.do">
                <div class="d-flex justify-content-center mb-3 product container mt-3">
                    <div class="card voucher" style="width:300px;height: 300px;">
                        <div class="card-body">
                            <h4 class="card-title">Voucher Manage</h4>
                        </div>
                    </div>
                    <br>
                </div>
            </a>
        </div>  
        <div class="col" <c:if test = "${role!='Admin'}"> hidden </c:if>>
            <a href="${pageContext.request.contextPath}/manager/usermanage.do">
                <div class="d-flex justify-content-center mb-3 product container mt-3">
                    <div class="card user" style="width:300px;height: 300px;">
                        <div class="card-body">
                            <h4 class="card-title">User Manage</h4>
                        </div>
                    </div>
                    <br>
                </div>
            </a>
        </div>
        
    </div>

</div>