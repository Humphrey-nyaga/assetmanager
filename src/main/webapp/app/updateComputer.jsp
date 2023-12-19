<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang=en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <%@ page import="com.assetmanager.app.model.entity.computer.ComputerType" %>
    <%@ page import="com.assetmanager.app.model.entity.Machinery.Condition" %>

    <style>
        <jsp:include page="../styles/style.css">
        <jsp:param name="pageBackground" value="#f5f5f5"/>
        </jsp:include>
    </style>

    <title>Update Computer</title>
</head>
<body>

<jsp:useBean id="headerMenu" class="com.assetmanager.app.view.toolbars.Header"/>
<jsp:setProperty name="headerMenu" property="activeUrl" value='${requestScope.activeUrl}'/>
${headerMenu.menu}

<c:set var="computer" value="${requestScope.computer}"/>

<div class="container">
    <div class="row">
        <div class="row justify-content-center">
            <div class="col-md-10 p-2 ml-2">
                <div class=" bg-white asset-container mx-auto" style="">
                    <form id="computerEditForm">
                        <div class=" data-form border border-1 p-3 rounded">
                            <h4 class="text-center mb-0 mt-0">Update Computer</h4>
                            <div class="row">
                                <input type="hidden" required="" class="form-control form-control-sm" id="id" name="id" value="${computer.id}">
                                <div class=" col-md-4">
                                    <label for="name" class="form-label">Name</label>
                                    <span style="color:black;">*</span>
                                    <input type="text" required=""
                                           class="form-control form-control-sm"
                                           id="name" name="name" value="${computer.name}">
                                </div>
                                <div class=" col-md-4">
                                    <label for="description" class="form-label">Description</label>
                                    <textarea rows="3" class="form-control form-control-sm" id="description"
                                              name="description">
                                        ${computer.description}
                                    </textarea></div>
                                <div class=" col-md-4">
                                    <label for="dateAcquired" class="form-label">Date Acquired</label>
                                    <span style="color:black;">*</span> <input type="date" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="dateAcquired" name="dateAcquired"
                                                                               value="${computer.dateAcquired}">
                                </div>
                                <div class=" col-md-4">
                                    <label for="purchaseValue" class="form-label">Purchase Value($)</label>
                                    <span style="color:black;">*</span> <input type="number" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="purchaseValue" name="purchaseValue"
                                                                               value="${computer.purchaseValue}">
                                </div>
                            </div>
                            <div class="row">
                                <div class=" col-md-4">
                                    <label for="ramInGB" class="form-label">RAM(GB)</label>
                                    <span style="color:black;">*</span> <input type="number" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="ramInGB" name="ramInGB"
                                                                               value="${computer.ramInGB}">
                                </div>
                                <div class=" col-md-4">
                                    <label for="screenSize" class="form-label">Screen Size</label>
                                    <span style="color:black;">*</span> <input type="text" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="screenSize" name="screenSize"
                                                                               value="${computer.screenSize}">
                                </div>
                                <div class=" col-md-4">
                                    <label for="manufacturer" class="form-label">Manufacturer</label>
                                    <span style="color:black;">*</span> <input type="text" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="manufacturer" name="manufacturer"
                                                                               value="${computer.manufacturer}">
                                </div>
                                <div class=" col-md-4">
                                    <label for="model" class="form-label">Model</label>
                                    <span style="color:black;">*</span> <input type="text" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="model" name="model"
                                                                               value="${computer.model}">
                                </div>
                            </div>
                            <div class="row">
                                <div class=" col-md-4">
                                    <label for="storageSizeInGB" class="form-label">Storage(GB)</label>
                                    <span style="color:black;">*</span> <input type="number" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="storageSizeInGB"
                                                                               name="storageSizeInGB"
                                                                               value="${computer.storageSizeInGB}">
                                </div>
                                <div class="col-md-4">
                                    <label for="currentCondition" class="form-label">Condition</label>
                                    <select class="form-select form-select-sm" id="currentCondition"
                                            name="currentCondition">
                                        <c:forEach var="currentCondition" items="${Condition.values()}">
                                            <option value="${currentCondition}" ${computer.currentCondition eq currentCondition ? 'selected' : ''}>
                                                    ${currentCondition.getName()}
                                            </option>
                                        </c:forEach>
                                    </select></div>
                                <div class=" col-md-4">
                                    <label for="processor" class="form-label">Processor</label>
                                    <span style="color:black;">*</span> <input type="text" required=""
                                                                               class="form-control form-control-sm"
                                                                               id="processor" name="processor"
                                                                               value="${computer.processor}">
                                </div>
                                <div class="col-md-4">
                                    <label for="computerType" class="form-label">Type</label>
                                    <select class="form-select form-select-sm" id="computerType" name="computerType">
                                        <c:forEach var="computerType" items="${ComputerType.values()}">
                                            <option value="${computerType}" ${computer.computerType eq computerType ? 'selected' : ''}>
                                                    ${computerType.getName()}
                                            </option>
                                        </c:forEach>
                                    </select></div>
                            </div>
                            <div class="gap-2 p-2 d-flex justify-content-center">
                                <button class="btn btn-lg btn-primary" type="submit">Update Computer</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<<script>
    document.addEventListener('DOMContentLoaded', function () {
        let url = 'http://localhost:8080/assetmanager/api/v1/computer/'
        const authToken = localStorage.getItem('authToken');

        const form = document.getElementById('computerEditForm');
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
                    window.location.href = './computer';
                })
                .catch(error => {
                    console.error('There was a problem with the fetch operation:', error);
                });
        });
    });
</script>
</body>
</html>
