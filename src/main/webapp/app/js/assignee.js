async function getAssigneeAssets() {
    //const authToken = localStorage.getItem('authToken');

    let url = 'http://localhost:8080/assetmanager/api/v1/asset/assignee/3'

    try {
        let response = await fetch(url, {
            method: 'GET',
            headers: {
              //  'Authorization': 'Bearer '.concat(authToken),
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
    let asset = document.getElementById('assigneeAssetsTable');
    data.forEach( asset => {
        let row = table.insertRow();
        let serialNumber = row.insertCell(0);
        serialNumber.innerHTML = data.serialNumber;
        let name = row.insertCell(1);
        name.innerHTML = data.name;
    });
}
    // function loadTableData(items) {
    //     const table = document.getElementById("testBody");
    //     items.forEach( item => {
    //         let row = table.insertRow();
    //         let date = row.insertCell(0);
    //         date.innerHTML = item.date;
    //         let name = row.insertCell(1);
    //         name.innerHTML = item.name;
    //     });
    // }
    //
