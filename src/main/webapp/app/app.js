async function getAssigneeNameAndID() {
    let url = `http://localhost/api/v1/assignee/assignee-name-and-id`;

    let response = await fetch(url, {
        method: 'GET',
    });
    console.log(response.status)
}