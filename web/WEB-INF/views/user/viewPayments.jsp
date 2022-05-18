<%-- 
    Document   : viewPayments
    Created on : Mar 22, 2022, 1:16:34 AM
    Author     : SE150010 Nguyen Sy Hoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row mt-3">
    <div class="col table-responsive">
        <table class="table">
            <thead class="table-dark">
                <tr class="text-center">
                    <th scope="col"class="text-white">#</th>
                    <th scope="col"class="text-white">Date</th>
                    <th scope="col"class="text-white">Price</th>
                    <th scope="col"class="text-white">Payment Status</th>
                    <th scope="col"class="text-white">Operation</th>
                </tr>
            </thead>
            <tbody class="text-center">
                <c:forEach var="payment" items="${paymentList}" varStatus="loop">
                <tr>
                    <td>



                        <h5>${loop.count}</h5>



                    </td>
                    <td>
                        <fmt:formatDate value="${payment.createDate}"></fmt:formatDate>
                    </td>

                    <td>


                        ${payment.total}

                    </td>
                    <td>
                        <h6>${payment.paymentStatus}</h6>
                    </td>
                    <td>
                        <div>
                            <a class="btn btn-outline-info" href="${pageContext.request.contextPath}/user/paymentDetail.do?paymentID=${payment.paymentID}" ><i class="bi bi-info-square"></i> View</a> 
                        </div>
                    </td>
                </tr>
                </c:forEach>
                
            </tbody>
        </table>
    </div>
</div>