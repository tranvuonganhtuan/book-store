<%-- 
    Document   : index
    Created on : Mar 21, 2022, 6:26:43 PM
    Author     : SE150010 Nguyen Sy Hoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<title>Payment</title>
<style>
    .outer {
        overflow-y: auto;
        height: 300px;
    }

    .outer {
        width: 100%;
        -layout: fixed;
    }

    .outer th {
        text-align: left;
        top: 0;
        position: sticky;

    }.outer {
        overflow-y: auto;
        height: 300px;
    }

    .outer {
        width: 100%;
        -layout: fixed;
    }

    .outer th {
        text-align: left;
        top: 0;
        position: sticky;

    }
    tbody {
        background-color: white;

    }
</style>
<div class="container mt-4 p-0">

    <div class="row px-md-4 px-2 pt-4" style="background-image: url(${pageContext.request.contextPath}/images/pay.png);
         background-size: cover;">
        <form action="paymentCreate.do">
            <div class="col-sm-8 ">

                <p class="pb-2 fw-bold">Infor</p>
                <div class="card mb-2" style="padding: 10px;">
                    <a href="${pageContext.request.contextPath}/cart/index.do"><i class="btn btn-outline-primary bi bi-arrow-left"> Return</i></a> 
                    <div class="row mt-2">
                        <div>
                            <label class="labels mt-3 mb-2 ml-2">Name</label>
                            <input type="text" name="name" class="form-control" value="${name==null?'':name}" required>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-12">
                            <label class="labels mb-2">Mobile Number</label>
                            <input type="text" name="phoneNumber" class="form-control" placeholder="Enter phone number" value="${phoneNumber==null?'':phoneNumber}" required>
                        </div>

                        <div class="row mt-3">
                            <div class="col-md-12">
                                <label class="labels mb-2">Address</label>
                                <input type="text" name="address" class="form-control mb-3" value="${address==null?'':address}" required>
                            </div>
                        </div>
                            
                        <div class="row mt-3">
                            <div class="col-md-12">
                                <label class="labels mb-2">Note</label>
                                <input type="text" name="note" class="form-control mb-3" value="${note==null?'':note}">
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="col-sm-4 payment-summary">
                <p class="fw-bold pt-lg-0 pt-4 pb-2">Payment Summary</p>
                <div class="card px-md-3 px-2 pt-4">
                    <!-- Them Ten o day -->
                    <div class="d-flex justify-content-between b-bottom"> 
                        <label for="voucherID">Voucher</label>
                        <select class="form-control" name="voucherID" id="voucherID">
                            <option value="0"></option>
                            <c:forEach var="voucher" items="${voucherList}">
                                <option value="${voucher.voucherID}" <c:if test="${voucher.voucherID == selectedVoucher}">selected</c:if>>${voucher.voucherName} - ${voucher.voucherValue}%</option>
                            </c:forEach>
                        </select>
                        <button class="btn btn-outline-success" type="submit" name="op" value="applyVoucher">Apply</button>
                    </div>
                    <div class="d-flex flex-column b-bottom">
                        <div class="d-flex justify-content-between py-3"> <small class="text-muted">Order Summary</small>
                            <p>$${paymentTotal==null?cart.total:paymentTotal}</p>
                        </div>
                        <div class="d-flex justify-content-between pb-3"> <small class="text-muted">Additional Service</small>
                            <p>$10</p>
                        </div>
                        <div class="d-flex justify-content-between"> <small class="text-muted">Total Amount</small>
                            <p>$${paymentTotal==null?cart.total+10:paymentTotal+10}</p>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-outline-success mt-2 mb-2" name="op" value="createPayment"> <i class="bi bi-paypal"></i> Pay </button> 
                </div>
            </div>


            <div class="col-sm-12 delivery px-md-3 px-1">
                <p class="pt-4 fw-bold pb-3 ps-2">Product</p>
                <div class="container-fluid col">
                    <div class="row">

                        <div class="col-sm-12">

                            <div class="card-body">
                                <div class="table-responsive outer">

                                    <table class="table" style="text-align: center">
                                        <thead class="table-dark">
                                            <tr>
                                                <!--<th scope="col"class="text-white">Name</th>-->
                                                <th scope="col"class="text-white">Image</th>
                                                <th scope="col"class="text-white">Price</th>
                                                <th scope="col"class="text-white">Quantity</th>
                                                <th scope="col"class="text-white">Discount</th>
                                                <th scope="col"class="text-white">Total</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="order" items="${cart.orderItemList}">
                                                <tr>
                                                    <td>
                                                        <div class="main"><!--W=145 H=98-->
                                                            <div class="text-center">
                                                                <img src="${pageContext.request.contextPath}/images/book/<c:if test="${order.bookID.bookImage==null}">book.gif</c:if><c:if test="${order.bookID.bookImage!=null}">${order.bookID.bookImage}</c:if>" class="rounded" alt="book_image" id="product">
                                                                </div>
                                                                <div class="des">
                                                                        <p>${order.bookID.bookSummary}</p>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <h6 class="mt-4">${order.bookID.price}</h6>
                                                    </td>
                                                    <td class="cart-product-quantity">
                                                        ${order.quantity}
                                                    </td>
                                                    <td>
                                                        <h6 class="mt-4">${order.bookID.discount}%</h6>
                                                    </td>
                                                    <td>
                                                        <h6 class="mt-4">${order.total}</h6>
                                                    </td>
                                                </tr>
                                                <!----->
                                            </tbody>
                                        </c:forEach>
                                    </table>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>        
        </form>
    </div>
</div>
