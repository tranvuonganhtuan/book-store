<%-- 
    Document   : booklist
    Created on : Mar 12, 2022, 10:42:51 PM
    Author     : SE150010 Nguyen Sy Hoan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--Alert Bootstrap 5-->
<c:if test="${not empty errorMessage}">
<div class="alert alert-danger alert-dismissible fade show" role="alert">
    ${errorMessage}
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
</c:if>
<c:if test="${not empty sameId}">

    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        ${sameId}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>
<c:if test="${not empty success}">

    <div class="alert alert-success alert-dismissible fade show" role="alert">
        ${success}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>
<hr>
<h1 class="text-center">List of Products</h1>
<hr>     
<a class="btn btn-sm btn-outline-success" href="createBookForm.do"><i class="bi bi-plus-circle"></i> Create New</a>
<table  class="table table-striped">
    <thead>
        <tr>
            <th>Book ID</th>
            <th>Image</th>
            <th>Name</th>
            <th>Author</th>
            <th>Publisher</th>
            <th>Category</th>
            <th>Publishing Year</th>
            <th>Bought</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Discount</th>
            <th>Summary</th>
            <th>Operations</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="book" items="${bookList}">
            <tr>
                <td>${book.bookID}</td>
                <td><img src="${pageContext.request.contextPath}/images/book/<c:if test="${book.bookImage==null}">book.gif</c:if><c:if test="${book.bookImage!=null}">${book.bookImage}</c:if>" style="width: 100px;height: 100px;"></td>
                <td>${book.bookName}</td>
                <td>${book.authorID.authorName}</td>
                <td>${book.publisherID.publisherName}</td>
                <td>${book.categoryID.categoryName}</td>
                <td>${book.publishingYear}</td>
                <td>${book.bought}</td>
                <td>${book.quantity}</td>
                <td><fmt:formatNumber value="${book.price}" pattern="$ #" /></td>
                <td><fmt:formatNumber value="${book.discount}" type="percent" /></td>
                <td>${book.bookSummary}</td>
                <td></td>
                <td>
                    <a class="btn btn-sm btn-outline-success" href="${pageContext.request.contextPath}/manager/editBook.do?bookID=${book.bookID}"><i class="bi bi-pencil-square"></i> Edit</a> 
                    <button type="button" class="btn btn-sm btn-outline-danger" name="op" value="delete" onclick="fdelete('${book.bookID}', '${book.bookName}')" /><i class="bi bi-x-circle"></i> Delete </button>   
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<script>
    function fdelete(id, name) {
        if (confirm("Are You Sure You Want To Delete Book " + name)) {
            alert("Success");
            window.location.href = "<%=request.getContextPath()%>/manager/deleteBook.do?bookID=" + id;
        }
    }
</script>