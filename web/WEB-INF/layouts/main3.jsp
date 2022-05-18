<%-- 
    Document   : main3
    Created on : Mar 2, 2022, 3:51:12 PM
    Author     : SE151464 Nguyen Hoang Huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <title>Add Product</title>
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
        <main class="container">
            <header class="row text-center">
                <nav class="navbar navbar-expand-sm bg-light navbar-light sticky-top">
                    <div class="container-fluid navigator">
                        <div class="collapse navbar-collapse" id="navbar">
                            <ul class="navbar-nav me-auto">
                                <li class="nav-item">
                                    <h4><a class="nav-link"  href="${pageContext.request.contextPath}/admin/create.do">Add Employee</a></h4>
                                </li>
                                <li class="nav-item">
                                    <h4><a class="nav-link"  href="${pageContext.request.contextPath}/admin/index.do">Manager Employees</a></h4>
                                </li>
                                <li class="nav-item">
                                    <h4><a class="nav-link"  href="${pageContext.request.contextPath}/admin/client.do">Manager Clients</a></h4>
                                </li>
                            </ul>

                            <h5><a class="nav-link" href="${pageContext.request.contextPath}/login/login.do"><i class="bi bi-box-arrow-in-right"></i> Log Out</a></h5>
                        </div>  
                    </div>
                </nav>
            </header>
            <jsp:include page="/WEB-INF/views/${controller}/${action}.jsp" />    

            <footer class="row"><p class="text-center">Copyright 2021-2022 by PRJ. All Rights Reserved.</p></footer>
        </main>

        <!-- Optional JavaScript; choose one of the two! -->
        <!-- hien hinh khi chon file -->
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
        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

        <!-- Option 2: Separate Popper and Bootstrap JS -->

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    </body>
</html>