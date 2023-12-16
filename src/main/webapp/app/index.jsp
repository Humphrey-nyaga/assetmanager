<%--
  User: hum
  Date: 11/9/23
  Time: 12:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.assetmanager.app.model.entity.UserRole" %>
<!doctype html>
<html lang=en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.7/css/jquery.dataTables.css" />
    <script src="https://cdn.datatables.net/1.13.7/js/jquery.dataTables.js"></script>
    <style>
        <jsp:include page="../styles/style.css">
        <jsp:param name="pageBackground" value="#f5f5f5"/>
        </jsp:include>
        <jsp:include page="modals.jsp"/>

    </style>

    <script>
        $(document).ready( function () {
            $('#dataTable').DataTable();
        } );

        $(document).ready(function(){
            $("#myInput").on("keyup", function() {
                var value = $(this).val().toLowerCase();
                $("#myTable tr").filter(function() {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
        });

        $(document).ready(function () {
            $(document).on("click", ".btn-success", function () {
                console.log("Update button clicked!");

                var tds = $(this).closest("tr").find("td:not(:last-child)");
                $.each(tds, function (i, v) {
                    var inputValue = $(v).text();
                    var inputField = $($(".data-form input")[i]);

                    console.log("Setting value for input field:", inputField.attr("name"), "with value:", inputValue);

                    inputField.val(inputValue);
                });
            });
        });


        function confirmDelete(deleteUrl) {
            $('#deleteConfirmationModal').modal('show');

            $("#deleteConfirmationModal").data('delete-url', deleteUrl);
        }

        function proceedWithDelete() {
            const deleteUrl = $("#deleteConfirmationModal").data('delete-url');
            $('#deleteConfirmationModal').modal('hide');
            console.log("Delete url" + deleteUrl);

            fetch(deleteUrl, {method: 'DELETE'})
                .then(response => {
                    if (response.status === 204) {
                        location.reload();
                    } else {
                        console.error('Error:', response.statusText);
                    }
                })
                .catch(error => console.error('Error:', error));
        }
        $(document).ready(function() {
            $('#myTabs').tab();
        });
    </script>

    <title></title>
</head>
<body>
<jsp:useBean id="headerMenu" class="com.assetmanager.app.view.toolbars.Header"/>
<jsp:setProperty name="headerMenu" property="activeUrl" value='${requestScope.activeUrl}'/>
<%--<c:when test='${sessionScope.role eq UserRole.ADMIN}'>--%>
    ${headerMenu.menu}
<%--    <h5> Welcome <c:out value="${sessionScope.username}"/></h5>--%>

    ${requestScope.content}

    <div class="modal fade" id="deleteConfirmationModal" tabindex="-1" role="dialog"
         aria-labelledby="deleteConfirmationModal" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteConfirmationModalLabel">Confirm Delete</h5>
                    <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Are you sure you want to delete the Item? <br>
                    This action Cannot Be Reversed.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-danger" onclick=" proceedWithDelete() ">Delete</button>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous">
        let table = new DataTable('#dataTable');
    </script>
<%--</c:when>--%>
</body>
</html>