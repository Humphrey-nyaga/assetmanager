<!DOCTYPE html>
<html lang="en" class="h-100">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        .gradient-custom {
            background: white;
        }

        .login-form-container {
            height: 100vh;
        }

        .form-group label {
            display: block;
            text-align: left;
            margin-bottom: 2px;
        }

        body {
            overflow-y: hidden;
            background-color: #f5f5f5;

        }
        .container{
            margin-top: -15px;
        }
    </style>
    <title>Register</title>
</head>
<body>
<nav class="navbar navbar-light">
    <a class="navbar-brand" href="#">
        <img src="https://i.etsystatic.com/13930112/r/il/299b7a/4866387148/il_1140xN.4866387148_7oh3.jpg" width="35" height="35" class="d-inline-block align-top" alt="">
        <b>Asset Manager</b>
    </a>
</nav>
<div class="container h-80">
    <div class="row h-100 justify-content-center align-items-center">
            <form id="signUpForm" class=" form-group w-75 justify-content-center">
                <section class="gradient-custom py-5 ">
                    <div class="container py-4">
                        <div class="row d-flex justify-content-center align-items-center">
                            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                                <div class="card bg-dark text-white" style="border-radius: 1rem;">
                                    <div class="card-body p-8 text-center">

                                        <div class="mb-3">
                                            <h2 class="fw-bold mb-2 text-uppercase">SIGN UP</h2>
                                            <div class="form-group">
                                                <label class="mb-2" for="email">Email</label>
                                                <input name="email" id="email" class="form-control"
                                                       placeholder="Enter Email"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="mb-2" for="username">Username</label>
                                                <input name="username" id="username" class="form-control"
                                                       placeholder="Enter username"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="mb-2" for="password">Password</label>
                                                <input type="password" id="password" name="password"
                                                       class="form-control" placeholder="Enter Password"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="mb-2" for="confirmPassword">Password</label>
                                                <input type="password" id="confirmPassword" name="confirmPassword"
                                                       class="form-control" placeholder="Confirm Password"/>
                                            </div>
                                            <button class="btn btn-primary btn-lg btn-outline-light px-4 mt-4"
                                                    type="submit">SIGN UP
                                            </button>
                                            <p class="medium mb-0 text-secondary text-white">Already
                                                have account? <a href="./index.jsp"> Sign In </a></p>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </form>
    </div>
</div>
<script>
    document.getElementById('signUpForm').addEventListener("submit", (e) => {
        e.preventDefault();
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        const email = document.getElementById('email').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        const formData = {
            username: username,
            password: password,
            email:email,
            confirmPassword:confirmPassword,
        };

        fetch('./api/v1/auth/register', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
                Accept: "application/json",
            },
            body: JSON.stringify(formData),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                } else {

                }
            })
            .catch(error => console.error('Error:', error));
    });
</script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</body>
</html>