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
    <%@ page import="com.assetmanager.app.model.entity.vehicle.VehicleType" %>
    <%@ page import="com.assetmanager.app.model.entity.vehicle.EngineType" %>
    <%@ page import="com.assetmanager.app.model.entity.vehicle.Transmission" %>

    <style>
        <jsp:include page="../styles/style.css">
        <jsp:param name="pageBackground" value="#f5f5f5"/>
        </jsp:include>
    </style>

    <title>Update Vehicle</title>
</head>
<body>

<jsp:useBean id="headerMenu" class="com.assetmanager.app.view.toolbars.Header"/>
<jsp:setProperty name="headerMenu" property="activeUrl" value='${requestScope.activeUrl}'/>
${headerMenu.menu}

<c:set var="vehicle" value="${requestScope.vehicle}"/>

<div class="container">
    <div class="row">
        <div class="row justify-content-center">
            <div class="col-md-10 p-2 ml-2">
                <div class=" bg-white asset-container mx-auto" style="">
                    <form id="vehicleUpdateForm">
                        <div class=" data-form border border-1 p-3 rounded">
                            <h4 class="text-center mb-2 mt-0">Update Vehicle</h4>
                            <div class="row">
                                <input type="hidden" required="" class="form-control form-control-sm" id="id" name="id" value="${vehicle.id}">
                                <div class=" col-md-4">
                                    <label for="name" class="form-label">Name</label>
                                    <span style="color:black;">*</span>
                                    <input type="text" required="" class="form-control form-control-sm" id="name"
                                           name="name" value="${vehicle.name}">
                                </div>
                                <div class=" col-md-4">
                                    <label for="description" class="form-label">Description</label>
                                    <textarea rows="3" class="form-control form-control-sm" id="description"
                                              name="description">
                                        ${vehicle.description}
                                    </textarea></div>
                                <div class=" col-md-4">
                                    <label for="dateAcquired" class="form-label">Date Acquired</label>
                                    <span style="color:black;">*</span> <input type="date" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="dateAcquired" name="dateAcquired"
                                                                               value="${vehicle.dateAcquired}">
                                </div>
                                <div class=" col-md-4">
                                    <label for="purchaseValue" class="form-label">Purchase Value($)</label>
                                    <span style="color:black;">*</span> <input type="number" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="purchaseValue" name="purchaseValue"
                                                                               value="${vehicle.purchaseValue}">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <label for="vehicleType" class="form-label">Vehicle Type</label>
                                    <select class="form-select form-select-sm" id="vehicleType" name="vehicleType">
                                        <c:forEach var="vehicleType" items="${VehicleType.values()}">
                                            <option value="${vehicleType}" ${vehicle.vehicleType eq vehicleType ? 'selected' : ''}>
                                                    ${vehicleType.getName()}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class=" col-md-4">
                                    <label for="cc" class="form-label">CC </label>
                                    <span style="color:black;">*</span> <input type="number" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="cc" name="cc" value="${vehicle.cc}">
                                </div>
                                <div class="col-md-4">
                                    <label for="engineType" class="form-label">Engine Type</label>
                                    <select class="form-select form-select-sm" id="engineType" name="engineType">
                                        <c:forEach var="engineType" items="${EngineType.values()}">
                                            <option value="${engineType}" ${vehicle.engineType eq engineType ? 'selected' : ''}>
                                                    ${engineType.getName()}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class=" col-md-4">
                                    <label for="model" class="form-label">Vehicle Model</label>
                                    <span style="color:black;">*</span> <input type="text" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="model" name="model"
                                                                               value="${vehicle.model}">
                                </div>
                            </div>
                            <div class="row">
                                <div class=" col-md-4">
                                    <label for="year" class="form-label">Year Manufactured</label>
                                    <span style="color:black;">*</span> <input type="text" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="year" name="year"
                                                                               value="${vehicle.year}">
                                </div>
                                <div class=" col-md-4">
                                    <label for="manufacturer" class="form-label">Manufacturer</label>
                                    <span style="color:black;">*</span> <input type="text" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="manufacturer" name="manufacturer"
                                                                               value="${vehicle.manufacturer}">
                                </div>
                                <div class=" col-md-4">
                                    <label for="color" class="form-label">Color</label>
                                    <span style="color:black;">*</span> <input type="text" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="color" name="color"
                                                                               value="${vehicle.color}">
                                </div>
                                <div class="col-md-4">
                                    <label for="transmission" class="form-label">Transmission</label>
                                    <select class="form-select form-select-sm" id="transmission" name="transmission">
                                        <c:forEach var="transmission" items="${Transmission.values()}">
                                            <option value="${transmission}" ${vehicle.transmission eq transmission ? 'selected' : ''}>
                                                    ${transmission.getName()}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class=" col-md-4">
                                    <label for="numberPlate" class="form-label">Number Plate</label>
                                    <span style="color:black;">*</span> <input type="text" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="numberPlate" name="numberPlate"
                                                                               value="${vehicle.numberPlate}">
                                </div>
                            </div>
                            <div class="row">
                                <div class=" col-md-4">
                                    <label for="tyreNumber" class="form-label">Tyre Number</label>
                                    <span style="color:black;">*</span> <input type="number" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="tyreNumber" name="tyreNumber"
                                                                               value="${vehicle.tyreNumber}">
                                </div>
                            </div>
                            <div class="gap-2 p-2 d-flex justify-content-center">
                                <button class="btn btn-lg btn-primary" type="submit">Update Vehicle</button>
                            </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        let url = 'http://localhost:8080/assetmanager/api/v1/vehicle/'
        const authToken = localStorage.getItem('authToken');

        const form = document.getElementById('vehicleUpdateForm');
        form.addEventListener('submit', function (event) {
            event.preventDefault();
            const formData = new FormData(form);
            const vehicleUpdateJson = {};
            formData.forEach(function (value, key) {
                vehicleUpdateJson[key] = value;
            });

            console.log(vehicleUpdateJson)

            fetch(url, {
                method: 'PUT',
                headers: {
                    'Authorization': 'Bearer '.concat(authToken),
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(vehicleUpdateJson),

            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Operation Failed');
                    }
                    return response.json();
                })
                .then(data => {
                    console.log(data);
                    window.location.href = './machinery';
                })
                .catch(error => {
                    console.error('There was a problem with the fetch operation:', error);
                });
        });
    });
</script>

</body>
</html>
