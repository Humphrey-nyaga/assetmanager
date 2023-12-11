<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <style>
        <jsp:include page="../styles/style.css">
        <jsp:param name="pageBackground" value="#f5f5f5"/>
        </jsp:include>
    </style>
    <script>
        async function getAssigneeNameAndID() {
            let url = 'http://localhost:8080/assetmanager/api/v1/assignee/assignee-name-and-id';

            try {
                let response = await fetch(url, { method: 'GET' });

                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }

                let data = await response.json();
                populateAssigneeDropdown(data);
            } catch (error) {
                console.error('Error:', error.message);
            }
        }

        async function getAllAssetsNameAndSerialNo() {
            let url = 'http://localhost:8080/assetmanager/api/v1/asset/assets-name-and-serial-no';

            try {
                let response = await fetch(url, { method: 'GET' });

                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }

                let data = await response.json();
                populateAssetDropdown(data);
            } catch (error) {
                console.error('Error:', error.message);
            }
        }

        function populateAssigneeDropdown(data) {
            let selectAssignee = document.getElementById('staffId');
            data.forEach(assignee => {
                let option = document.createElement('option');
                option.value = assignee.id;
                option.text = assignee.fullName;
                selectAssignee.appendChild(option);
            });
        }

        function populateAssetDropdown(data) {
            let selectAsset = document.getElementById('assetSerialID');
            data.forEach(asset => {
                let option = document.createElement('option');
                option.value = asset.serialNumber;
                option.text = asset.name;
                selectAsset.appendChild(option);
            });
        }

        document.addEventListener('DOMContentLoaded', function () {
            getAssigneeNameAndID();
            getAllAssetsNameAndSerialNo();
        });
    </script>
    <title>Dashboard</title>
</head>
<body>
<jsp:useBean id="headerMenu" class="com.assetmanager.app.view.toolbars.Header"/>
<jsp:setProperty name="headerMenu" property="activeUrl" value='${requestScope.activeUrl}'/>
${headerMenu.menu}
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-10 p-2 ml-2">
            <div class=" bg-white asset-container mx-auto" style="">
                <form method="POST" action="./assignAsset">
                <div class=" data-form border border-1 p-3 rounded">
                <h4 class="text-center mb-0 mt-0">Assign Asset</h4>
                <div class="mt-2 row">
                    <div class="col-md-4">
                        <label for="staffId" class="form-label">Assignee</label>
                        <select class="form-select form-select-sm" id="staffId" name="staffId" required="">
                        </select>
                    </div>


                    <div class="col-md-4">
                        <label for="assetSerialID" class="form-label">Asset</label>
                        <select class="form-select form-select-sm" id="assetSerialID" name="assetSerialID">
                        </select></div>
                    <div class="col-md-4">
                        <label for="assignaction" class="form-label">Action</label>
                        <select class="form-select form-select-sm" id="assignaction" name="assignaction">
                            <option value="ASSIGN">Assign</option>
                            <option value="UNASSIGN">Unassign</option>
                        </select></div>
                </div>
                <div class=" mt-2 col-md-4">
                    <label for="dateRequested" class="form-label">Date</label>
                    <span style="color:black;">*</span> <input type="date" required=""
                                                               class="form-control form-control-sm" id="dateRequested"
                                                               name="dateRequested">
                </div>
                <div class="gap-2 p-2 d-flex justify-content-center">
                    <button class="btn btn-lg btn-primary" type="submit">Assign/Unassign</button>
                </div>
                </form>
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