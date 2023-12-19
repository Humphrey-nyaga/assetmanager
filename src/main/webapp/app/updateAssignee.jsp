<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="assetRequestBean" class="com.assetmanager.app.bean.AssetRequestBean" scope="request"/>
<!doctype html>
<html lang=en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <%@ page import="com.assetmanager.app.model.entity.AssigneeType" %>

    <style>
        <jsp:include page="../styles/style.css">
        <jsp:param name="pageBackground" value="#f5f5f5"/>
        </jsp:include>
    </style>

    <title>Dashboard</title>
</head>
<body>

<jsp:useBean id="headerMenu" class="com.assetmanager.app.view.toolbars.Header"/>
<jsp:setProperty name="headerMenu" property="activeUrl" value='${requestScope.activeUrl}'/>
${headerMenu.menu}

<c:set var="assignee" value="${requestScope.assignee}"/>

<div class="container">
    <div class="row">
        <div class="row justify-content-center">
            <div class="col-md-10 p-2 ml-2">
                <div class="bg-white asset-container mx-auto" style="">
                    <form id="assigneeUpdateForm">
                        <div class="data-form border border-1 p-3 rounded">
                            <h4 class="text-center mb-0 mt-0">Update Assignee</h4>
                                <div class="row">
                                    <input type="hidden" id="id" name="id" value="${assignee.id}">
                                    <div class=" col-md-4 mt-2">
                                            <label for="employeeType" class="form-label">Employee Type</label>
                                            <select class="form-select form-select-sm" name="employeeType" id="employeeType">
                                                <c:forEach var="employeeType" items="${AssigneeType.values()}">
                                                    <option value="${employeeType}" ${assignee.employeeType eq employeeType ? 'selected' : ''}>
                                                            ${employeeType.getName()}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class=" col-md-4 mt-2">
                                            <label for="firstName" class="form-label">First Name</label>
                                            <input type="text" class="form-control form-control-sm" id="firstName"
                                                   name="firstName" value="${assignee.firstName}">
                                        </div>
                                    <div class=" col-md-4 mt-2">
                                            <label for="lastName" class="form-label">Last Name</label>
                                            <input type="text" class="form-control form-control-sm" id="lastName"
                                                   name="lastName" value="${assignee.lastName}">
                                        </div>
                                    <div class=" col-md-4 mt-2">
                                            <label for="email" class="form-label">Email</label>
                                            <input type="text" class="form-control form-control-sm" id="email"
                                                   name="email" value="${assignee.email}">
                                        </div>
                                    <div class=" col-md-4 mt-2">
                                        <label for="email" class="form-label">Email</label>
                                        <input type="text" class="form-control form-control-sm" id="staffNumber"
                                               name="staffNumber" value="${assignee.staffNumber}">
                                    </div>
                                    </div>
                                    <div class="row">
                                        <div class=" col-md-4 mt-2">
                                            <label for="dateOfBirth" class="form-label">Date of Birth</label>
                                            <span style="color:black;">*</span> <input type="date" required=""
                                                                                       class="form-control form-control-sm"
                                                                                       id="dateOfBirth"
                                                                                       name="dateOfBirth"
                                                                                       value="${assignee.dateOfBirth}">
                                        </div>
                                        <div class=" col-md-4 mt-2">
                                            <label for="identificationNumber" class="form-label">ID Number</label>
                                            <input type="text" class="form-control form-control-sm"
                                                   id="identificationNumber"
                                                   name="identificationNumber" value="${assignee.identificationNumber}">
                                        </div>
                                    </div>
                                    <div class="gap-2 p-2 d-flex justify-content-center">
                                        <button class="btn btn-lg btn-primary" type="submit">Update Assignee</button>
                                    </div>
                                </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        let url = 'http://localhost:8080/assetmanager/api/v1/assignee/'
        const authToken = localStorage.getItem('authToken');

        const form = document.getElementById('assigneeUpdateForm');
        form.addEventListener('submit', function (event) {
            event.preventDefault();
            const formData = new FormData(form);
            const jsonData = {};
            formData.forEach(function (value, key) {
                jsonData[key] = value;
            });

            console.log(jsonData)

            fetch(url, {
                method: 'PUT',
                headers: {
                    'Authorization': 'Bearer '.concat(authToken),
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(jsonData),

            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Update Failed');
                    }
                    return response.json();
                })
                .then(data => {
                    console.log(data);
                    window.location.href = './assignee';
                })
                .catch(error => {
                    console.error('There was a problem with the fetch operation:', error);
                });
        });
    });
</script>

</body>
</html>
