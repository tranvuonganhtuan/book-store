<%-- 
    Document   : user
    Created on : Mar 12, 2022, 10:41:58 PM
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
<h1 class="text-center">List of Users</h1>
<hr>     
<table class="table table-striped table-hover table-bordered">
    <thead>
        <tr  style="text-align: center">
            <th>User Id</th>
            <th>Avatar</th>
            <th>Name</th>
            <th>User Name</th>
            <th>Address</th>
            <th>Email</th>
            <th>Date Of Birth</th>
            <th>Phone</th>
            <th>Operations</th>
        </tr>
    </thead>
    <tbody>

        <c:forEach var="user" items="${userList}" varStatus="loop">
            <tr style="text-align: center">
                <td>${loop.index + 1}</td>
                <td><img src="${pageContext.request.contextPath}/images/user/<c:if test="${user.userImage==null}">avatar.png</c:if><c:if test="${user.userImage!=null}">${user.userImage}</c:if>" style="width: 100px;height: 100px;"></td>
                <td>${user.fullName}</td>
                <td>${user.userName}</td>
                <td>${user.userAddress}</td>
                <td>${user.email}</td>
                <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${user.dateOfBirth}" /></td>
                <td>${user.phoneNumber}</td>
                <td>
                    <form id="userRoleEditor" method="post" action="${pageContext.request.contextPath}/manager/updateUser.do">
                        <input name="userName" value="${user.userName}" hidden />
                        <select <c:if test="${user.userRole=='Admin'}">disabled</c:if> name="editRole" class="form-select form-select-sm" aria-label="Default select example" style="text-align-last:center;">
                            <option value="Admin" <c:if test="${user.userRole=='Admin'}">selected</c:if> <c:if test="${user.userRole!='Admin'}">disabled selected</c:if>>Administrator</option>
                            <option value="Mod" <c:if test="${user.userRole=='Mod'}">selected</c:if>>Employer</option>
                            <option value="Client" <c:if test="${user.userRole=='Client'}">selected</c:if>>Client</option>
                        </select> 
                        <!--<a class="btn btn-outline-success"  href="#" onclick="myFunction()"><i class="bi bi-pencil-square"></i>Update</a>-->
                        <!--<input class="btn btn-outline-success" valu="Update" type="submit"><i class="bi bi-pencil-square"></i>-->
                        <button type="submit" class="btn btn-sm btn-outline-success" name="op" value="Update"><i class="bi bi-check-circle"></i>Update</button>
                        <!--<button type="submit" class="btn btn-sm btn-outline-danger" name="op" value="Delete"><i class="bi bi-x-circle-fill"></i>Delete</button>-->
                        <button type="button" class="btn btn-sm btn-outline-danger" name="op" value="delete" onclick="fdelete('${user.userName}')" /><i class="bi bi-x-circle"></i> Delete </button>   
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<script>
    function fdelete(name) {
        if (confirm("Are You Sure You Want To Delete User " + name)) {
            alert("Success");
            window.location.href = "<%=request.getContextPath()%>/manager/deleteUser.do?userName=" + name;
        }
    }
</script>