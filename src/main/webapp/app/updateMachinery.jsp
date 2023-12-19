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
    <%@ page import="com.assetmanager.app.model.entity.Machinery.Condition" %>
    <%@ page import="com.assetmanager.app.model.entity.Machinery.OperationalStatus" %>
    <%@ page import="com.assetmanager.app.model.entity.MaintenanceFrequency" %>



    <style>
        <jsp:include page="../styles/style.css">
        <jsp:param name="pageBackground" value="#f5f5f5"/>
        </jsp:include>
    </style>

    <title>Update Machinery</title>
</head>
<body>

<jsp:useBean id="headerMenu" class="com.assetmanager.app.view.toolbars.Header"/>
<jsp:setProperty name="headerMenu" property="activeUrl" value='${requestScope.activeUrl}'/>
${headerMenu.menu}

<c:set var="machinery" value="${requestScope.machinery}"/>

<div class="container">
    <div class="row">
        <div class="row justify-content-center">
            <div class="col-md-10 p-2 ml-2">
                <div class=" bg-white asset-container mx-auto" style="">
                    <form id="machineryUpdateForm" method="POST">
                        <div class=" data-form border border-1 p-3 rounded">
                            <h4 class="text-center mb-0 mt-0"> Update Machinery</h4>
                            <div class="row">
                                <input type="hidden" id="id" name="id" value="${machinery.id}">

                                <div class=" col-md-4">
                                    <label for="name" class="form-label">Name</label>
                                    <span style="color:black;">*</span>
                                    <input type="text" required="" class="form-control form-control-sm" id="name"
                                           name="name" value="${machinery.name}">
                                </div>
                                <div class="col-md-4">
                                    <label for="description" class="form-label">Description</label>
                                    <textarea rows="3" class="form-control form-control-sm" id="description" name="description">
                                        ${machinery.description}
                                    </textarea>
                                </div>
                                <div class=" col-md-4">
                                    <label for="dateAcquired" class="form-label">Date Acquired</label>
                                    <span style="color:black;">*</span>
                                    <input type="date" required=""
                                           class="form-control form-control-sm"
                                           id="dateAcquired" name="dateAcquired" value="${machinery.dateAcquired}" >
                                </div>
                                <div class=" col-md-4">
                                    <label for="purchaseValue" class="form-label">Purchase Value($)</label>
                                    <span style="color:black;">*</span> <input type="number" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="purchaseValue" name="purchaseValue" value="${machinery.purchaseValue}">
                                </div>
                            </div>
                            <div class="row">
                                <div class=" col-md-4">
                                    <label for="yearOfProduction" class="form-label">Year Produced</label>
                                    <span style="color:black;">*</span>
                                    <input type="text" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="yearOfProduction"
                                                                               name="yearOfProduction" value="${machinery.yearOfProduction}">
                                </div>
                                <div class=" col-md-4">
                                    <label for="model" class="form-label">Model</label>
                                    <span style="color:black;">*</span> <input type="text" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="model" name="model" value="${machinery.model}">
                                </div>
                                <div class=" col-md-4">
                                    <label for="manufacturer" class="form-label">Manufacturer</label>
                                    <span style="color:black;">*</span> <input type="text" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="manufacturer" name="manufacturer" value="${machinery.manufacturer}">
                                </div>
                                <div class=" col-md-4">
                                    <label for="maxLoadCapacity" class="form-label">Max Load(Tonnes)</label>
                                    <span style="color:black;">*</span> <input type="number" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="maxLoadCapacity"
                                                                               name="maxLoadCapacity" value="${machinery.maxLoadCapacity}">
                                </div>
                            </div>
                            <div class="row">
                                <div class=" col-md-4">
                                    <label for="power" class="form-label">Power(Kilowatt (kW))</label>
                                    <span style="color:black;">*</span> <input type="number" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="power" name="power" value="${machinery.power}">
                                </div>
                                <div class=" col-md-4">
                                    <label for="expectedLifespan" class="form-label">Expected Lifespan(Years)</label>
                                    <span style="color:black;">*</span> <input type="number" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="expectedLifespan"
                                                                               name="expectedLifespan" value="${machinery.expectedLifespan}">
                                </div>
                                <div class="col-md-4">
                                    <label for="currentCondition" class="form-label">Condition</label>
                                    <select class="form-select form-select-sm" id="currentCondition"
                                            name="currentCondition">
                                        <c:forEach var="currentCondition" items="${Condition.values()}">
                                            <option value="${currentCondition}" ${machinery.currentCondition eq currentCondition ? 'selected' : ''}>
                                                    ${currentCondition.getName()}
                                            </option>
                                        </c:forEach>
                                    </select>

                                </div>
                                <div class="col-md-4">
                                    <label for="operationalStatus" class="form-label">Operation Status</label>
                                    <select class="form-select form-select-sm" id="operationalStatus"
                                            name="operationalStatus">
                                        <c:forEach var="operationalStatus" items="${OperationalStatus.values()}">
                                            <option value="${operationalStatus}" ${machinery.operationalStatus eq operationalStatus ? 'selected' : ''}>
                                                    ${operationalStatus.getName()}
                                            </option>
                                        </c:forEach>
                                    </select></div>
                                <div class="col-md-4">
                                    <label for="maintenanceFrequency" class="form-label">Maintenance Frequency</label>
                                    <select class="form-select form-select-sm" id="maintenanceFrequency"
                                            name="maintenanceFrequency">
                                        <c:forEach var="maintenanceFrequency" items="${MaintenanceFrequency.values()}">
                                            <option value="${maintenanceFrequency}" ${machinery.maintenanceFrequency eq maintenanceFrequency ? 'selected' : ''}>
                                                    ${maintenanceFrequency.getName()}
                                            </option>
                                        </c:forEach>
                                    </select></div>
                            </div>
                            <div class="gap-2 p-2 d-flex justify-content-center">
                                <button class="btn btn-lg btn-primary" type="submit">Update Machinery</button>
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
        let url = 'http://localhost:8080/assetmanager/api/v1/machinery/'
        const authToken = localStorage.getItem('authToken');

        const form = document.getElementById('machineryUpdateForm');
        form.addEventListener('submit', function (event) {
            event.preventDefault();
            const formData = new FormData(form);
            const machineryJsonData = {};
            formData.forEach(function (value, key) {
                machineryJsonData[key] = value;
            });

            console.log(machineryJsonData)

            fetch(url, {
                method: 'PUT',
                headers: {
                    'Authorization': 'Bearer '.concat(authToken),
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(machineryJsonData),

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
