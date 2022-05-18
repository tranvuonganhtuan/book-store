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
        <form action="updateVoucherData.do" method="post">

            <h2 class="text-center">Edit Voucher </h2>
            <input type="number" value="${voucher.voucherID}" name="voucherID" hidden>
            <div class="cart-body">
                <div class="row">
                    <div class="form-group mt-3">
                        <label for="name">Name:</label>
                        <input type="text" value="${voucher.voucherName}" class="form-control" name="name" id="name" aria-describedly="nameHid" placeholder="Name" required>
                        <!--<small id="nameHid" class="form-text text-muted">Name is required</small>-->
                    </div> 
                </div>
                
                <div class="row">
                    <div class="form-group mt-3">
                        <label for="name">Value:</label>
                        <input type="number" value="${voucher.voucherValue}" class="form-control" name="voucherValue" id="voucherValue" aria-describedly="nameHid" placeholder="Voucher Value" min="0" max="100" required>
                        <!--<small id="nameHid" class="form-text text-muted">Name is required</small>-->
                    </div> 
                </div>
                <div class="row">
                    <div class="col-xl-6">
                        <div class="form-outline mb-4">
                            <input type="date" value="${voucher.startDate}" id="startDate" class="form-control form-control-lg" name="startDate" required />
                        </div>
                    </div>
                    <div class="col-xl-6">
                        <div class="form-outline mb-4">
                            <input type="date" value="${voucher.endDate}" id="endDate" class="form-control form-control-lg" name="endDate" required />
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="form-group">
                            <label for="voucherSummary">Description</label>
                            <textarea class="form-control" name="voucherSummary" id="voucherSummary" rows="3"></textarea>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card-footer text-muted">
                <button type="reset"  name="op" value="New" class="btn btn-success"><i class="bi bi-plus-circle"></i> New</button>
                <button type="submit" name="op" value="Save" class="btn btn-primary"><i class="bi bi-save2"></i> Save</button>
                <a href="${pageContext.request.contextPath}/manager/vouchermanage.do" class="btn btn-danger" style="float: right;"><i class="bi bi-x-circle"></i> Cancel</a>
            </div>
        </form>
    </div>
</section>