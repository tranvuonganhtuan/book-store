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
<h1 class="text-center">List of Category</h1>
<hr>     
<a class="btn btn-sm btn-outline-success" href="createCategoryForm.do"><i class="bi bi-plus-circle"></i> Create New</a>
<table  class="table table-striped">
    <thead>
        <tr>
            <th>Category ID</th>
            <th>Name</th>
            <th>Summary</th>
            <th>Operations</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="category" items="${categoryList}">
            <tr>
                <td>${category.categoryID}</td>
                <td>${category.categoryName}</td>
                <td>${category.categorySummary}</td>
                <td></td>
                <td>
                    <a class="btn btn-sm btn-outline-success" href="${pageContext.request.contextPath}/manager/editCategory.do?categoryID=${category.categoryID}"><i class="bi bi-pencil-square"></i> Edit</a> 
                    <button type="button" class="btn btn-sm btn-outline-danger" name="op" value="delete" onclick="fdelete('${category.categoryID}', '${category.categoryName}')" /><i class="bi bi-x-circle"></i> Delete </button>   
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<script>
    function fdelete(id, name) {
        if (confirm("Are You Sure You Want To Delete Category " + name)) {
            alert("Success");
            window.location.href = "<%=request.getContextPath()%>/manager/deleteCategory.do?categoryID=" + id;
        }
    }
</script>