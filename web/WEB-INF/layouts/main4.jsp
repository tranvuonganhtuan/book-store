<%-- 
    Document   : main4
    Created on : Mar 2, 2022, 9:32:32 PM
    Author     : SE151464 Nguyen Hoang Huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Books store</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>        
        <link href="${pageContext.request.contextPath}/css/site.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>
        <style>
            .image-preview{
                width: 306px;
                min-height: 300px;
                border: 2px solid #dddddd;
                margin-top: 15px;

                display: flex;
                align-items: center;
                justify-content: center;
                font-weight: bold;
                color: #cccccc;
            }

            .image-preview__image{
                display: none;
                width: 100%;
            }
        </style>
    </head>
    <body>

        <div class="container-fluid">
            <!--navigator-->  
            <nav class="navbar navbar-expand-sm bg-light navbar-light sticky-top">
                <div class="container-fluid navigator">
                    <div class="collapse navbar-collapse" id="navbar">
                        <ul class="navbar-nav me-auto">
                            <li class="nav-item">
                                <h3><a class="nav-link"  href="${pageContext.request.contextPath}/employer/create.do" style="font-weight: bold;">Add Product</a></h3>
                            </li>
                            <li class="nav-item">
                                <h3><a class="nav-link"  href="${pageContext.request.contextPath}/employer/index.do" style="font-weight: bold;">List of Products</a></h3>
                            </li>
                        </ul>
                        <h4 style="float: right;"><a class="nav-link" href="${pageContext.request.contextPath}/login/login.do"><i class="bi bi-box-arrow-in-right"></i>Log Out</a></h4>
                    </div>  
                </div>
            </nav>

            <jsp:include page="/WEB-INF/views/${controller}/${action}.jsp" />   


            <div class="row" style="text-align: center">
                <div class="col footer">
                    Copyright 2021-2022 by PRJ. All Rights Reserved.
                </div>
            </div>
        </div>
            <script>
            const inpFile = document.getElementById("productImage");
            const previewContainer = document.getElementById("imagePreview");
            const previewImage = previewContainer.querySelector(".image-preview__image");
            const previewDefaultText = previewContainer.querySelector(".image-preview__default-text");

            inpFile.addEventListener("change", function () {
                const file = this.files[0];
                if (file) {
                    const reader = new FileReader();
                    previewDefaultText.style.display = "none";
                    previewImage.style.display = "block";
                    reader.addEventListener("load", function () {
                        previewImage.setAttribute("src", this.result);
                    });
                    reader.readAsDataURL(file);
                } else {
                    previewDefaultText.style.display = null;
                    previewImage.style.display = null;
                    previewImage.setAttribute("src", "");
                }
            });
        </script>
    </body>
</html>