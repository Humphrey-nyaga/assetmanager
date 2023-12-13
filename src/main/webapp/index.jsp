<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" class="h-100">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>


        .form-group label {
            display: block;
            text-align: left;
            margin-bottom: 2px;
        }

        body {
            overflow-y: hidden;
            background-color: #f5f5f5;
        }

        .container {
            margin-top: -15px;
        }
    </style>
    <title>Login</title>
</head>
<body>
<c:choose>
    <c:when test='${sessionScope.loggedInId ne null}' >
        <c:redirect url="./home" />
    </c:when>
    <c:otherwise>
    <nav class="navbar navbar-light">
        <a class="navbar-brand" href="#">
            <img src="https://i.etsystatic.com/13930112/r/il/299b7a/4866387148/il_1140xN.4866387148_7oh3.jpg" width="35" height="35" class="d-inline-block align-top" alt="">
            <b>Asset Manager</b>
        </a>
    </nav>
<div class="container h-100">
    <div class="row h-100 justify-content-center align-items-center">
    <div>
    </div>
        <form id="loginForm" class=" form-group w-75 justify-content-center">
            <section class="py-5">
                <div class="container py-4">
                    <div class="row d-flex justify-content-center align-items-center">
                        <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                            <div class="card bg-dark text-white" style="border-radius: 1rem;">
                                <div class="card-body p-8 text-center">

                                    <div class="mb-3">
                                        <h2 class="fw-bold mb-2 text-uppercase">LOGIN</h2>

                                        <jsp:useBean id="loginForm" class="com.assetmanager.app.usebeans.UserBean">
                                            <jsp:setProperty name="loginForm" property="usernameLabel" value="Enter Unique Username"/>
                                                <jsp:setProperty name="loginForm" property="passwordLabel" value="Enter Unique Password"/>
                                        </jsp:useBean>
                                        <div class="form-group">
                                            <label class="mb-2" for="username">Username</label>
                                            <input name="username" id="username" class="form-control"
                                                   placeholder="<jsp:getProperty name="loginForm" property="usernameLabel"/>"/>
                                        </div>


                                        <div class="form-group">
                                            <label class="mb-2" for="password">Password</label>
                                            <input type="password" id="password" name="password" class="form-control"
                                                   placeholder="<jsp:getProperty name="loginForm" property="passwordLabel"/>" />
                                        </div>

                                        <p class="medium mb-2"><a class="text-white" href="#!">Forgot password?</a></p>
                                        <button id="loginButton" class="btn btn-primary btn-lg btn-outline-light px-4 mt-3 mb-3" type="submit">LOGIN
                                        </button>
                                        <div>
                                            <p class="mb-0">Don't have an account? <a href="./signup.jsp"
                                                                                      class="text-white fw-bold">Sign
                                                Up</a>
                                            </p>
                                        </div>

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
            document.getElementById('loginForm').addEventListener("submit", (e) => {
                e.preventDefault();
                const username = document.getElementById('username').value;
                const password = document.getElementById('password').value;

                const formData = new URLSearchParams();
                formData.append('username', username);
                formData.append('password', password);

                fetch('./login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: formData.toString(),
                 })
                //     .then(response => {
                //         const authToken = response.headers.get('Authorization');
                //
                //         if (authToken) {
                //             localStorage.setItem('authToken', authToken);
                //             console.log('Login successful! Token:', authToken);
                //         } else {
                //             console.error('No Authorization header found in the response.');
                //         }
                //     })
                    .then(response => response.json())
                    .then(data => {
                        const authToken = data.authToken;

                        if (authToken) {
                            localStorage.setItem('authToken', authToken);
                            console.log('Login successful!');
                            window.location.href = './home';

                        } else {
                            console.error('No authToken found in the response.');
                        }
                    })
                    .catch(error => console.error('Error:', error));
            });
        </script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
    </c:otherwise>
</c:choose>
</body>
</html>
