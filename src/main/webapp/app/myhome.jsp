<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
          integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.css" />
    <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.js"></script>
    <script>

        async function getAssigneeAssets() {
            const authToken = localStorage.getItem('authToken');

            let url = 'http://localhost:8080/assetmanager/api/v1/asset/assignee/3'

            try {
                let response = await fetch(url, {
                    method: 'GET',
                    headers: {
                        'Authorization': 'Bearer '.concat(authToken),
                        'Content-Type': 'application/json'
                    }
                });

                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }

                let data = await response.json();
                populateAssigneeAssetsTable(data);
            } catch (error) {
                console.error('Error:', error.message);
            }
        }

        function populateAssigneeAssetsTable(data) {
            let table = document.getElementById('assigneeAssetsTable');
            data.forEach(assetData => {
                let row = table.insertRow();
                let serialNumber = row.insertCell(0);
                serialNumber.innerHTML = assetData.serialNumber;
                let name = row.insertCell(1);
                name.innerHTML = assetData.name;
            });
            $('#dataTable').DataTable();

        }

        async function getAssigneeAssetsStats() {
            const authToken = localStorage.getItem('authToken');

            let url = 'http://localhost:8080/assetmanager/api/v1/asset/statistics/3'

            try {
                let response = await fetch(url, {
                    method: 'GET',
                    headers: {
                        'Authorization': 'Bearer '.concat(authToken),
                        'Content-Type': 'application/json'
                    }
                });

                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }

                let data = await response.json();
                populateAssetsByCategory(data);
                fillTotalAssetsCount(data);
                console.log(data)
            } catch (error) {
                console.error('Error:', error.message);
            }
        }

        function populateAssetsByCategory(data) {
            const listContainer = document.getElementById('assignedAssetsByCategory');

            for (const category in data.assetsByCategory) {
                const listItem = document.createElement('li');
                listItem.classList.add('list-group-item');
                listItem.style.display = 'flex';


                const categoryNameElement = document.createElement('div');
                categoryNameElement.classList.add('col-6');
                categoryNameElement.innerText = category + ":";

                const categoryValueElement = document.createElement('div');
                categoryValueElement.classList.add('col-6');
                categoryValueElement.innerText = data.assetsByCategory[category];

                listItem.appendChild(categoryNameElement);
                listItem.appendChild(categoryValueElement);

                listContainer.appendChild(listItem);
            }
        }


        function fillTotalAssetsCount(data) {
            const totalAssetsElement = document.getElementById('totalAssetsToAssigneeCount');
            if (totalAssetsElement) {
                totalAssetsElement.textContent = data.totalAssets;
            }
        }

        document.addEventListener('DOMContentLoaded', function () {
            getAssigneeAssets();
            getAssigneeAssetsStats();
        });

    </script>

</head>
<body>
<div class="container-fluid" style="padding-left: 0;">
    <div class="row">
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
        <div class="col-md-9 ml-4 mt-2">
            <div class="border border-light rounded">
                <div class="row m-2 ">
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-body" style="text-align: center">
                                <h6 class="card-header bg-primary text-center">Asset By Category </h6>
                                    <ul class="list-group list-group-flush" id="assignedAssetsByCategory" style="display:flex">

                                    </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-body" style="text-align: center">
                                <h6 class="card-header bg-primary text-center">Assets Assigned</h6>
                                <h6 class="card-subtitle mb-2 text-muted"></h6>
                                <p class="card-text" id="totalAssetsToAssigneeCount" style="margin: 0 auto"></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="mt-3">
                <div class="row">
                    <div>
                        <table id="dataTable"
                               class=" display nowrap table table-bordered border-4 table-striped table-responsive-sm "
                               style="width:100%;margin: 0 auto">
                            <thead class="table-success">
                            <tr>
                                <th colspan="2" style="text-align: center">Assigned Assets</th>
                            </tr>
                            <tr>
                                <th scope="col">Serial Number</th>
                                <th scope="col">Name of Asset</th>

                            </tr>
                            </thead>
                            <tbody id="assigneeAssetsTable">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</div>
<script>
    $(document).ready( function () {
} );</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous">
</script>
</body>
</html>