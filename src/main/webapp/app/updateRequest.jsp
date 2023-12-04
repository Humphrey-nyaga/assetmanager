<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="assetRequestBean" class="com.assetmanager.app.bean.AssetRequestBean" scope="request" />
<!doctype html>
<html lang=en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
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

<c:set var="assetRequest" value="${requestScope.assetRequest}" />

<div class="container">
    <div class="row">
        <div class="row justify-content-center">
            <div class="col-md-10 p-2 ml-2">
                <div class="bg-white asset-container mx-auto" style="">
                    <form method="POST" action="./updateRequest">
                        <div class="data-form border border-1 p-3 rounded">
                            <h4 class="text-center mb-0 mt-0">Edit Asset Request</h4>
                            <div class="row">
                                    <input type="hidden" id="assetRequestId" name="assetRequestId" value="${assetRequest.id}">
                                <div class="col-md-4">
                                    <label for="staffId" class="form-label">Staff ID</label>
                                    <input type="text" class="form-control form-control-sm" id="staffId" name="staffId"
                                           value="${assetRequest.staffId}">
                                </div>
                                <div class="col-md-4">
                                    <label for="assetName" class="form-label">Asset Name</label>
                                    <input type="text" class="form-control form-control-sm" id="assetName"
                                           name="assetName" value="${assetRequest.assetName}">
                                </div>
                                <div class="col-md-4">
                                    <label for="description" class="form-label">Description</label>
                                    <input type="text" class="form-control form-control-sm" id="description"
                                           name="description" value="${assetRequest.description}">
                                </div>
                                <div class="col-md-4">
                                    <label for="dateRequested" class="form-label">Request Date</label>
                                    <input type="date" class="form-control form-control-sm" id="dateRequested"
                                           name="dateRequested" value="${assetRequest.dateRequested}">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <label for="quantity" class="form-label">Quantity</label>
                                    <input type="text" class="form-control form-control-sm" id="quantity"
                                           name="quantity" value="${assetRequest.quantity}">
                                </div>
                                <div class="col-md-4">
                                    <label for="requestStatus" class="form-label">Request Status</label>
                                    <select class="form-select form-select-sm" id="requestStatus" name="requestStatus">
                                        <option value="COMPLETED" ${assetRequest.requestStatus eq 'COMPLETED' ? 'selected' : ''}>
                                            Completed
                                        </option>
                                        <option value="PENDING" ${assetRequest.requestStatus eq 'PENDING' ? 'selected' : ''}>
                                            Pending
                                        </option>
                                        <option value="REJECTED" ${assetRequest.requestStatus eq 'REJECTED' ? 'selected' : ''}>
                                            Rejected
                                        </option>
                                        <option value="APPROVED" ${assetRequest.requestStatus eq 'APPROVED' ? 'selected' : ''}>
                                            Approved
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="gap-2 p-2 d-flex justify-content-center">
                                <button class="btn btn-lg btn-primary" type="submit">Update Asset Request</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous">

</script>
</body>
</html>
