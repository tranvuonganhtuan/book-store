<%-- 
    Document   : index
    Created on : Feb 28, 2022, 2:30:45 PM
    Author     : SE151464 Nguyen Hoang Huy
--%>
<title>Login</title>
<div class="col-md-6">
    <div class="card-body p-md-4 text-black">
        <a href="index.do" style="color: black"><h1><i class="bi bi-house-fill"></i></h1></a>
        <h4 style="text-align: center;">Sign into your account</h4>
        <form action="goin.do">

            <div class="form-outline mb-4">
                <input type="text" id="userName" placeholder="User Name" class="form-control form-control-lg" name="userName" minlength="6" required />
            </div>

            <div class="form-outline mb-4">
                <input type="password" id="password" placeholder="Password" class="form-control form-control-lg" name="password" minlength="6" required />
            </div>

            <div class="d-grid gap-2">
                <button type="submit" name="op" value="submit" class="btn btn-warning btn-lg ms-2">Submit</button>
            </div>
            <p class="text-center text-muted mt-4">Don't have an account? <a href="register.do" class="fw-bold text-body"><u>Register here</u></a></p>
            <p style="color: red; font-weight: bold">${errorMessage}</p>

        </form>
    </div>
</div>