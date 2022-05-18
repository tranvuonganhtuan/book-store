<%-- 
    Document   : index
    Created on : Mar 1, 2022, 11:45:48 AM
    Author     : SE151464 Nguyen Hoang Huy
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<style>
    #hero{
        display: table;
        width: 100%;
        height: 50vh;
        background: url(${pageContext.request.contextPath}/images/bookcart.png) top center;
        background-size: cover;
    }
</style>
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

    }
    tbody {
        background-color: white;

    }


    form #decrease {
        margin-right: -4px;
        border-radius: 8px 0 0 8px;
    }

    form #increase {
        margin-left: -4px;
        border-radius: 0 8px 8px 0;
    }

    form #input-wrap {
        margin: 0px;
        padding: 0px;
    }

    input#number {
        text-align: center;
        border: none;
        /*border-top: 1px solid #ddd;*/
        border-bottom: 0px solid #ddd;
        margin: 0px;
        width: 40px;
        height: 40px;
    }

    input[type=number]::-webkit-inner-spin-button,
    input[type=number]::-webkit-outer-spin-button {
        -webkit-appearance: none;
        margin: 0;
    }

</style>
<hr/>
<section id="hero">
    <div class="hero-container">
        <div class="hero-logo">
            <h1 class="font-weight-bold">Cart Products</h1>
        </div>
    </div>
</section>
<!---End of Hero Section-->
<!--Cart Section-->
<section class="mt-5">
    <div class="container">
        <div class="cart">
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
                            <th scope="col"class="text-white">Options</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${cart.orderItemList}">
                            <tr>
<!--                                <td>
                                    <h6 class="mt-4">${order.bookID.bookName}</h6>
                                </td>-->
                                <td>
                                    <div class="main"><!--W=145 H=98-->
                                        <div class="text-center">
                                            <img src="${pageContext.request.contextPath}/images/book/<c:if test="${order.bookID.bookImage==null}">book.gif</c:if><c:if test="${order.bookID.bookImage!=null}">${order.bookID.bookImage}</c:if>" class="rounded" alt="book_image" id="product">
                                            </div>
                                            <div class="des text-truncate">
                                                    <p>${order.bookID.bookName}</p>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <h6 class="mt-4">$${order.bookID.price}</h6>
                                </td>
                                <td class="cart-product-quantity">
                                    <form action="change.do">
                                        <input name="orderID" value="${order.orderID}" hidden/>
                                        <button class="btn btn-outline-success" type="submit" name="op" value="decrement"><i class="bi bi-caret-down-fill"></i></button>

                                        <input type="number" value="${order.quantity}" id="number" name="quantity" max="${order.bookID.quantity}}" value="1" readonly >

                                        <button class="btn btn-outline-success" type="submit" name="op" value="increment"><i class="bi bi-caret-up-fill"></i></button>
                                    </form>
                                </td>
                                <td>
                                    <h6 class="mt-4">${order.bookID.discount}%</h6>
                                </td>
                                <td>
                                    <h6 class="mt-4">$${order.total}</h6>
                                </td>
                                <td>
                                    <div class="mt-3">
                                        <!--<a class="btn btn-outline-success" href="#" ><i class="bi bi-cash"> Buy Now</i></a>--> 
                                        <!--<a class="btn btn-outline-danger" href="#" ><i class="bi bi-x-circle"></i> Delete</a>-->
                                        <button type="button" class="btn btn-sm btn-outline-danger" name="op" value="delete" onclick="fdelete('${order.orderID}', '${order.bookID.bookName}')" /><i class="bi bi-x-circle"></i> Delete </button>
                                    </div>
                                </td>
                            </tr>
                            <!----->
                        </tbody>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</section>
<div class="col-lg-4 offset-lg-4">
    <div class="checkout">
        <ul>
            <li class="subtotal">subtotal
                <span>$${cart.total}</span>
            </li>
            <li class="cart-total">Total
                <span>$${cart.total}</span></li>
        </ul>
        <a href="${pageContext.request.contextPath}/payment/index.do?cartID=${cart.cartID}"class="proceed-btn" style="text-decoration: none">Proceed to Checkout</a>
    </div>
</div>
<hr/>          

<script>
    function fdelete(id, name) {
        if (confirm("Are You Sure You Want To Delete Book " + name)) {
            alert("Success");
            window.location.href = "<%=request.getContextPath()%>/cart/deleteOrder.do?orderID=" + id;
        }
    }
</script>