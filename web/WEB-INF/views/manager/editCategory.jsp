<%-- 
    Document   : editBook
    Created on : Mar 18, 2022, 3:32:07 PM
    Author     : SE150010 Nguyen Sy Hoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<section class="row" style="background: url(${pageContext.request.contextPath}/images/background.png); background-size: cover;">
    <div class="col mt-4">
        <form action="updateCategoryData.do" method="post">

            <h2 class="text-center">Edit Category </h2>
            <input type="number" value="${category.categoryID}" name="categoryID" hidden>
            <div class="cart-body">
                <div class="row">
                    <div class="form-group mt-3">
                        <label for="name">Name:</label>
                        <input type="text" value="${category.categoryName}" class="form-control" name="name" id="name" aria-describedly="nameHid" placeholder="Name" required>
                        <!--<small id="nameHid" class="form-text text-muted">Name is required</small>-->
                    </div> 
                </div>

                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="categorySummary">Description</label>
                            <textarea class="form-control" value="${category.categorySummary}" name="categorySummary" id="categorySummary" rows="3"></textarea>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card-footer text-muted">
                <button type="reset"  name="op" value="Reset" class="btn btn-success"><i class="bi bi-plus-circle"></i>Reset</button>
                <button type="submit" name="op" value="Save" class="btn btn-primary"><i class="bi bi-save2"></i> Save</button>
                <a href="${pageContext.request.contextPath}/manager/categorymanage.do" class="btn btn-danger" style="float: right;"><i class="bi bi-x-circle"></i> Cancel</a>
            </div>
        </form>
    </div>
</section>