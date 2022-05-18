<%-- 
    Document   : register
    Created on : Feb 28, 2022, 10:07:24 PM
    Author     : SE151464 Nguyen Hoang Huy
--%>
<title>Register</title>
<div class="col-md-6">
    <div class="card-body p-md-4 text-black">
        <h3 class="mb-5 text-uppercase">Registration form</h3>

        <form action="/BookStore/regist" method="post" enctype="multipart/form-data">
        <!--<form action="regist.do" method="post">-->
            <div class="form-outline mb-4">
                <input type="text" id="name" placeholder="Full name" class="form-control form-control-lg" name="name" required />
            </div>

            <div class="form-outline mb-4">
                <input type="text" id="form3Example8" placeholder="Address" class="form-control form-control-lg" name="address" required />
            </div>

            <div class="form-outline mb-4">
                <input type="text" id="userName" placeholder="User name" class="form-control form-control-lg" name="userName" minlength="6" required />
            </div>

            <div class="form-outline mb-4">
                <input type="password" id="password" placeholder="Password" class="form-control form-control-lg" name="password" minlength="6" required />
            </div>

            <div class="form-outline mb-4">
                <input type="password" id="re-password" placeholder="Re-Password" class="form-control form-control-lg" name="re-password" minlength="6" required />
            </div>

            <div class="row">
                <div class="col-xl-6">
                    <div class="d-md-flex justify-content-start align-items-center mb-4 py-2">

                        <h6 class="mb-0 me-4">Gender: </h6>

                        <div class="form-check form-check-inline mb-0 me-4">
                            <input
                                class="form-check-input"
                                type="radio"
                                name="gender"
                                id="female"
                                value="Female"
                                />
                            <label class="form-check-label" for="female">Female</label>
                        </div>

                        <div class="form-check form-check-inline mb-0 me-4">
                            <input
                                class="form-check-input"
                                type="radio"
                                name="gender"
                                id="male"
                                value="Male"
                                />
                            <label class="form-check-label" for="maleGender">Male</label>
                        </div>
                    </div>
                </div>
                <div class="col-xl-6">
                    <div class="form-outline mb-4">
                        <input type="date" id="date" class="form-control form-control-lg" name="dateOfBirth" required />
                    </div>
                </div>
            </div>  
            

            <div class="form-outline mb-4">
                <input type="text" id="num" name="phoneNumber" placeholder="Phone Number" class="form-control form-control-lg" required />
            </div>

            <div class="form-outline mb-4">
                <input name="email" type="email" pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}$" class="form-control" placeholder="Email*" id="email" required="" style="height: 50px; font-size: 20px;">
            </div>

            <div class="form-outline mb-4">
                <input type="file" id="form3Example90" placeholder="Avata image" class="form-control form-control-lg" name="userImage"  accept="image/*"/>
            </div>

            <div class="d-flex justify-content-end pt-3">
                <a href="index.do" class="btn btn-light btn-lg">Home Page</a>
                <a href="register.do" class="btn btn-light btn-lg">Reset all</a>
                <!--<button type="button" class="btn btn-warning btn-lg ms-2">Submit form</button>-->
                <input type="submit" class="btn btn-warning btn-lg ms-2" value="Submit form">
            </div>

            <p class="text-center text-muted mt-5 mb-0">Have already an account? <a href="login.do" class="fw-bold text-body"><u>Login here</u></a></p>
            <p style="color: red; font-weight: bold">${errorMessage}</p>
        </form>
    </div>
</div>
