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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.css" />
    <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.js"></script>
    <style>
        <jsp:include page="../styles/style.css">
        <jsp:param name="pageBackground" value="#f5f5f5"/>
        </jsp:include>
    </style>
    <script>

        async function getAllAssets() {
            const authToken = localStorage.getItem('authToken');
            let url = 'http://localhost:8080/assetmanager/api/v1/asset';

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
                buildHtmlTable('#dataTable', data);
            } catch (error) {
                console.error('Error:', error.message);
            }
        }

        getAllAssets();

        $(document).ready(function () {
            $('#dataTable').DataTable();
        });

    </script>
</head>
<body>
<table id="dataTable" class="display nowrap table table-bordered border-4 table-striped table-responsive-sm" style="width:100%">
    <!-- Thead and Tbody will be added dynamically -->
</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous">
</script>
</body>
</html>
