
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-3" style="width: 25%;">

    <div class="d-flex flex-column flex-shrink-0 bg-light"
         style="width: 225px; height: 100vh;padding-top: 1.25rem">
        <a class="navbar-brand p-0" style="margin-left: 1px; padding-left: 1px; " href="./home">
            <img src="https://i.etsystatic.com/13930112/r/il/299b7a/4866387148/il_1140xN.4866387148_7oh3.jpg"
                 width="35" height="35" class="d-inline-block align-top" alt="">
            <b style="color: #070707; margin-left: 5px">Asset Manager</b>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <hr>
        <ul class="nav nav-pills flex-column mb-auto" style="text-align:center">
            <li class="nav-item">
                <a class="nav-link px-2 fs-5 link-dark" href="./myHome">
                    Home
                </a>
            </li>
            <li>
                <a class="nav-link px-2 fs-5 link-dark" href="./myRequests">
                    Requests
                </a>
            </li>
        </ul>
        <hr>
        <div class="dropdown">
            <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1"
               data-bs-toggle="dropdown" aria-expanded="false">
                <img src="https://icons.veryicon.com/png/o/miscellaneous/two-color-icon-library/user-286.png"
                     alt="mdo" width="32" height="32" class="rounded-circle">
            </a>
            <ul class="dropdown-menu dropdown-menu-end profile-dropdown-menu text-small"
                aria-labelledby="dropdownUser1">
                <li><a class="dropdown-item" href="#">Settings</a></li>
                <li><a class="dropdown-item" href="./app/profile.jsp">Profile</a></li>
                <li>
                    <hr class="dropdown-divider">
                </li>
                <li><a class="dropdown-item" href="./logout">Sign out</a></li>
            </ul>
        </div>
    </div>
</div>
