<%-- 
    Document   : search
    Created on : Mar 12, 2022, 5:48:30 PM
    Author     : SE150010 Nguyen Sy Hoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container mt-3">
    <div class="row">
        <c:forEach var="book" items="${searchList}" >
            <div class="col">
                <div class="d-flex justify-content-center mb-3 product container mt-3">
                    <div class="card" style="width:300px;">
                        <img src="${pageContext.request.contextPath}/images/book/<c:if test="${book.bookImage==null}">book.gif</c:if><c:if test="${book.bookImage!=null}">${book.bookImage}</c:if>" class="img-circle" alt="${book.bookName}" style="width:100%;height: 25vh">
                        <div class="card-body text-center">
                            <h4 class="card-title text-truncate" data-bs-toggle="tooltip" data-bs-placement="top" title="${book.bookName}">${book.bookName}</h4>
                            <p class="card-text"><fmt:formatNumber value="${book.price}" type="currency"/></p>    
                            <div class="progress">
                                <div class="progress-bar" style="width:${book.quantity}%">${book.quantity}</div>
                            </div>
                            <a href="${pageContext.request.contextPath}/detail/index.do?bookID=${book.bookID}" class="btn btn-primary" style="margin-top: 10px;">See Profile</a>
                        </div>
                    </div>
                    <br>
                </div>
            </div>
        </c:forEach>
    </div>

</div>

<!--phan trang cuoi-->
<div class="row">
    <div class="col" style="text-align: right;">
        <br/>
        <form action="<c:url value="/search/search.do" />">
            <input type="text" name="category" value="${category}" hidden/>
            <button type="submit" class="btn btn-sm btn-info" name="op" value="FirstPage" title="First Page" <c:if test="${page==1}">disabled</c:if>><i class="bi bi-chevron-bar-left"></i></button>
            <button type="submit" class="btn btn-sm btn-info" name="op" value="PreviousPage" title="Previous Page" <c:if test="${page==1}">disabled</c:if>><i class="bi bi-chevron-left"></i></button>
            <button type="submit" class="btn btn-sm btn-info" name="op" value="NextPage" title="Next Page" <c:if test="${page==totalPage}">disabled</c:if>><i class="bi bi-chevron-right"></i></button>
            <button type="submit" class="btn btn-sm btn-info" name="op" value="LastPage" title="Last Page" <c:if test="${page==totalPage}">disabled</c:if>><i class="bi bi-chevron-bar-right"></i></button>
            <input type="text" name="gotoPage" value="${page}" class="btn btn-sm btn-outline-default" style="text-align: right;width: 32px;" title="Enter page number"/>
            <button type="submit" class="btn btn-sm btn-info" name="op" value="GotoPage" title="Goto Page"><i class="bi bi-arrow-up-right-circle"></i></button>
        </form>
        Page ${page}/${totalPage}
    </div>
</div>