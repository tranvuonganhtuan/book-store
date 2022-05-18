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
<h1 class="text-center">List of Author</h1>
<hr>     
<a class="btn btn-sm btn-outline-success" href="createAuthorForm.do"><i class="bi bi-plus-circle"></i> Create New</a>
<table  class="table table-striped">
    <thead>
        <tr>
            <th>Author ID</th>
            <th>Name</th>
            <th>Summary</th>
            <th>Operations</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="author" items="${authorList}">
            <tr>
                <td>${author.authorID}</td>
                <td>${author.authorName}</td>
                <td>${author.authorSummary}</td>
                <td></td>
                <td>
                    <a class="btn btn-sm btn-outline-success" href="${pageContext.request.contextPath}/manager/editAuthor.do?authorID=${author.authorID}"><i class="bi bi-pencil-square"></i> Edit</a> 
                    <!--<input type="button" value="Delete">-->
                    <button type="button" class="btn btn-sm btn-outline-danger" name="op" value="delete" onclick="fdelete('${author.authorID}', '${author.authorName}')" /><i class="bi bi-x-circle"></i> Delete </button>   
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<script>
    function fdelete(id, name) {
        if (confirm("Are You Sure You Want To Delete Author " + name)) {
            alert("Success");
            window.location.href = "<%=request.getContextPath()%>/manager/deleteAuthor.do?authorID=" + id;
        }
    }
</script>