<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><!DOCTYPE html>
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
        async function getAssigneeRequests() {
            const authToken = localStorage.getItem('authToken');
            const id = localStorage.getItem('id');

            let url = 'http://localhost:8080/assetmanager/api/v1/request/assignee/'.concat(id)
/*
*
  {
    "id": 17,
    "staffId": "EMP91-20231212",
    "fullName": "",
    "assetRequestSerialNumber": "ASR58-3522023",
    "assetName": "Dell PowerEdge R750xs",
    "description": "Dell PowerEdge R750xs Rack Server",
    "dateRequested": "2023-12-12",
    "quantity": 2,
    "requestStatus": "PENDING",
    "category": null,
    "assigneeId": 3
    *
  },*/

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
                let assetRequestSerialNumber = row.insertCell(0);
                assetRequestSerialNumber.innerHTML = assetData.assetRequestSerialNumber;
                let dateRequested = row.insertCell(1);
                dateRequested.innerHTML = assetData.dateRequested;
                let assetName = row.insertCell(2);
                assetName.innerHTML = assetData.assetName;
                let quantity = row.insertCell(3);
                quantity.innerHTML = assetData.quantity;
                let requestStatus = row.insertCell(4);
                requestStatus.innerHTML = assetData.requestStatus;


            });
            $('#dataTable').DataTable();

        }
        async function getAssigneeRequestsStats() {
            const authToken = localStorage.getItem('authToken');
            const id = localStorage.getItem('id');
            let url = 'http://localhost:8080/assetmanager/api/v1/request/statistics/'.concat(id)

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
                fillApprovedRequests(data);
                fillCompletedRequests(data);
                fillRejectedRequests(data);
                fillPendingRequests(data);
                console.log(data)
            } catch (error) {
                console.error('Error:', error.message);
            }
        }

        function fillPendingRequests(data) {
            const pendingRequests = document.getElementById('pendingRequests');
            if (pendingRequests) {
                pendingRequests.textContent = data && data.PENDING ? data.PENDING : 0;
            }
        }

        function fillCompletedRequests(data) {
            const completedRequests = document.getElementById('completedRequests');
            if (completedRequests) {
                completedRequests.textContent = data && data.COMPLETED ? data.COMPLETED : 0;
            }
        }

        function fillRejectedRequests(data) {
            const rejectedRequests = document.getElementById('rejectedRequests');
            if (rejectedRequests) {
                rejectedRequests.textContent = data && data.REJECTED ? data.REJECTED : 0;
            }
        }

        function fillApprovedRequests(data) {
            const approvedRequests = document.getElementById('approvedRequests');
            if (approvedRequests) {
                approvedRequests.textContent = data && data.APPROVED ? data.APPROVED : 0;
            }
        }

        document.addEventListener('DOMContentLoaded', function () {
            getAssigneeRequests();
            getAssigneeRequestsStats();
        });
//GET


    </script>
</head>
<body>
<div class="container-fluid" style="padding-left: 0;">
    <div class="row">
        <%@include file="./sidebar.jsp" %>
        <div class="col-md-9 ml-4 mt-2">
            <div class="border border-light rounded">
                <div class="row m-1 ">
                    <div class="col-md-4 mt-1 mb-1">
                        <div class="card text-white bg-success">
                            <div class="card-body" style="text-align: center">
                                <h6 class="card-subtitle mb-2">Completed</h6>
                                <p class="card-text" id="completedRequests">0</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mt-1 mb-1">
                        <div class="card text-white bg-primary">
                            <div class="card-body" style="text-align: center">
                                <h6 class="card-subtitle mb-2">Pending</h6>
                                <p class="card-text" id="pendingRequests">0</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mt-1 mb-1">
                        <div class="card text-white bg-danger">
                            <div class="card-body" style="text-align: center">
                                <h6 class="card-subtitle mb-2">Rejected</h6>
                                <p class="card-text" id="rejectedRequests">0</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card text-white bg-success">
                            <div class="card-body" style="text-align: center">
                                <h6 class="card-subtitle mb-2">Approved</h6>
                                <p class="card-text" style="margin: 0 auto" id="approvedRequests">0</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="mt-2">
                <div class="row">
                    <div>
                        <table id="dataTable"
                               class=" display nowrap table table-bordered border-4 table-striped table-responsive-sm "
                               style="width:100%">
                            <thead class="table-success">
                            <tr>
                                <th colspan="5" style="text-align: center">Requested Assets</th>
                            </tr>
                            <tr>
                                <th scope="col">Serial No</th>
                                <th scope="col">Date</th>
                                <th scope="col">Asset</th>
                                <th scope="col">Asset Quantity</th>
                                <th scope="col">Status</th>
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

</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous">
</script>
</body>
</html>