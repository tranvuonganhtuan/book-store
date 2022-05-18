<%-- 
    Document   : index
    Created on : Mar 7, 2022, 7:52:38 PM
    Author     : SE151464 Nguyen Hoang Huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <div class="col">

        <div class="text-center" style="margin-top: auto;margin-bottom: auto">
            <img src="${pageContext.request.contextPath}/images/book/<c:if test="${book.bookImage==null}">book.gif</c:if><c:if test="${book.bookImage!=null}">${book.bookImage}</c:if>" alt="">
            </div>

        </div>
        <div class="col">
            <div class="introduction" style="margin-left: 30px">
                <div class="mb-2">
                        <h1 class="text-center" style="font-weight: bold;color: black">${book.bookName}</h1>
            </div>

            <div class="mt-3 mb-2">
                <h5 class="mt-2 fw-bold">Category</h5>
                <h6>${book.categoryID.categoryName}</h6>
            </div>
            <div class="mt-3">
                <h5 class="mt-2 fw-bold">Author</h5>
                <h6>${book.authorID.authorName}</h6>
            </div>
            <div class="mt-3">
                <h5 class="mt-2 fw-bold">Publisher</h5>
                <h6>${book.publisherID.publisherName}</h6>
            </div>
            <div class="mt-3">
                <h5 class="mt-2 fw-bold">Quantity purchased: ${book.bought}</h5>
            </div>
            <div>
                <h4 class="mt-1 fw-bold">Description</h4>
                <p>${book.bookSummary}</p>
            </div>
            <c:if test="${book.quantity == 0}">Sold out</c:if>
                <div class="price mt-1 mb-3">
                    <h1 style="margin-left: 10px;color: red">$${book.price}</h1>
            </div>
            <form action="addToCart.do">
                <input type="number" name="bookID" value="${book.bookID}" hidden/>         

                <div class="input-group mb-3">
                    <input type="number" id="quantity" name="quantity" value="1" style="width: 70px;text-align: right;" min="1">
                    <button type="submit" class="btn btn-outline-success" style="width: 200px"  <c:if test="${book.quantity == 0}">disabled</c:if> <c:if test="${role == null}">disabled</c:if>>Add Cart</button>
                    </div>
                </form>
                <br/>
            <c:if test="${role == null}">Login first</c:if>
            </div>
        </div>
        <br>
        <br>
        <hr>
        <div class="seller">
            <div class="text-center">
                <img src="${pageContext.request.contextPath}/images/seller.gif" style="background-size: cover">
        </div>
        <hr>
        <br>
        <div class="row lisBook">

            <c:forEach var="book" items="${bookList}">
                <div class="col text-truncate"> 
                    <a href="${pageContext.request.contextPath}/detail/index.do?bookID=${book.bookID}" class="btn btn-primary" style="margin-top: 10px;">
                        <img src="${pageContext.request.contextPath}/images/book/<c:if test="${book.bookImage==null}">book.gif</c:if><c:if test="${book.bookImage!=null}">${book.bookImage}</c:if>" alt="" style="width: 100px;">
                        </a>
                            <h4 class="text-truncate">${book.bookName}</h4>
                    <h5>${book.price}</h5></a>
                </div> 
            </c:forEach>
        </div>

    </div>