getEditors();

async function getEditors() {

    let url ='http://localhost:8081/StoryPitchMS/editor';
    let response = await fetch(url);
    if (response.status === 200) {
        let user = await response.json();
        populateEditors(user);
    }
}

function populateEditors(user) {
    let editorsSection = document.getElementById('editorsSection');
    editorsSection.innerHTML = '';

    if (user.length > 0) {
        let table = document.createElement('table');
        table.id = 'editorsTable';

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>First Name</th>
           		<th>Last Name</th>
                <th>Username</th>
                <th>Email</th>
                <th>Genre ID</th>
                <th>Rank ID</th>
                <th></th>
            </tr>
        `;

        for (let ed of user) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${ed.id}</td>
                <td>${ed.firstname}</td>
         		<td>${ed.lastname}</td>
                <td>${ed.username}</td>
                <td>${ed.email}</td>
                <td>${ed.genre}</td>
                <td>${ed.rank}</td>
            `;
            let td = document.createElement('td');
            let ul = document.createElement('ul');

            td.appendChild(ul);
            tr.appendChild(td);
            
            
            table.appendChild(tr);
        }

        editorsSection.appendChild(table);
    } else {
        editorsSection.innerHTML = 'No editors, please contact IT to fix.';
    }
}