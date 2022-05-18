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
        <form action="/BookStore/updateBookData" method="post" enctype="multipart/form-data">
            <input type="number" value="${book.bookID}" name="bookID" hidden>
            <h2 class="text-center">Edit Book Information </h2>

            <div class="cart-body">

                <div class="row">
                    <div class="col-7">

                        <div class="form-group mt-3">
                            <label for="name">Name:</label>
                            <input type="text" value="${book.bookName}" class="form-control" name="name" id="name" aria-describedly="nameHid" placeholder="Name" required>
                            <!--<small id="nameHid" class="form-text text-muted">Name is required</small>-->
                        </div> 
                        
                        <div class="form-group mt-3">
                            <label for="authorID">Author</label>
                            <select class="form-control" name="chooseAuthorID" id="authorID">
                                <!--<option value="0">No Information</option>-->
                                <c:forEach var="author" items="${authorList}">
                                    <option value="${author.authorID}" <c:if test="${author.authorID == book.authorID.authorID}">selected</c:if>>${author.authorName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="form-group mt-3">
                            <label for="publisherID">Publisher</label>
                            <select class="form-control" name="choosePublisherID" id="publisherID">
                                <!--<option value="0">No Information</option>-->
                                <c:forEach var="publisher" items="${publisherList}">
                                    <option value="${publisher.publisherID}" <c:if test="${publisher.publisherID == book.publisherID.publisherID}">selected</c:if>>${publisher.publisherName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="input-group form-group mt-3">
                            <span class="input-group-text">Publishing Year</span>
                            <input type="number" value="${book.publishingYear}" name="publishingYear" id="publishingYear" class="form-control" placeholder="Publishing Year" min="0"  required>
                        </div>
                        
                        <div class="form-group mt-3">
                            <label for="categoryID">Category</label>
                            <select class="form-control" name="chooseCategoryID" id="publisherID">
                                <!--<option value="0">No Information</option>-->
                                <c:forEach var="category" items="${categoryList}">
                                    <option value="${category.categoryID}" <c:if test="${category.categoryID == book.categoryID.categoryID}">selected</c:if>>${category.categoryName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="form-group mt-3">
                            <label for="quantity">Quantity:</label>
                            <input type="number" value="${book.quantity}" class="form-control" name="quantity" id="quantity" aria-describedly="quantityHid" placeholder="Quantity" min="0" max="1000000" required>
                            <!--<small id="quantityHid" class="form-text text-muted">Quantity is required</small>-->
                        </div> 

                        <div class="input-group form-group mt-3">
                            <span class="input-group-text">Unit Price</span>
                            <input type="number" value="${book.price}" name="price" class="form-control" placeholder="Unit Price" min="0" required>
                            <span class="input-group-text">$</span>
                        </div>
                        
                        <div class="input-group form-group mt-3">
                            <span class="input-group-text">Discount</span>
                            <input type="number" value="${book.discount}" name="discount" class="form-control" value="0"  min="0" max="100" placeholder="Discount">
                            <span class="input-group-text">%</span>
                        </div>

                    </div>

                    <div class="col-5 text-center">

                        <div class="image-preview" id="imagePreview" style="margin-right: auto; margin-left: auto;">
                            <img src="" alt="Image Preview" class="image-preview__image" style="max-height: 300px; width: 100%;">
                            <span class="image-preview__default-text">Image Preview</span>
                        </div>
                        <input type="file" class="form-control mt-2" aria-describedly="bookImage" name="bookImage" id="productImage">
                        <!--<small id="productImage" class="form-text text-muted mt-2">Product image is required</small>-->

                    </div>
                </div>

                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="bookSummary">Description</label>
                            <textarea class="form-control" value="${book.bookSummary}" name="bookSummary" id="bookSummary" rows="3"></textarea>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card-footer text-muted">
                <button type="reset"  name="op" value="Reset" class="btn btn-success"><i class="bi bi-plus-circle"></i>Reset</button>
                <button type="submit" name="op" value="Save" class="btn btn-primary"><i class="bi bi-save2"></i>Save</button>
                <a href="${pageContext.request.contextPath}/manager/bookmanage.do" class="btn btn-danger" style="float: right;"><i class="bi bi-x-circle"></i> Cancel</a>
            </div>
        </form>
    </div>
</section>