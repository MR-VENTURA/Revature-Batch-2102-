let addCatMenuOpen = false;
setup();

function setup() {
    getPitches().then(() => {
        checkLogin().then(() => {
            if (loggedUser.role.name === 'Employee') employeeSetup();
        });
    });
}

async function getPitches() {

    let url ='http://localhost:8081/StoryPitchMS/pitch';
    let response = await fetch(url);
    if (response.status === 200) {
        let cats = await response.json();
        populateCats(cats);
    }
}

function populateCats(cats) {
    let catSection = document.getElementById('catSection');
    catSection.innerHTML = '';

    if (cats.length > 0) {
        let table = document.createElement('table');
        table.id = 'catTable';

        table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>ETC</th>
                <th>Story Type</th>
                <th>Genre</th>
                <th>Tag Line</th>
                <th></th>
            </tr>
        `;

        for (let pitch of cats) {
            let tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${pitch.id}</td>
                <td>${pitch.title}</td>
                <td>${pitch.userID}</td>
                <td>${pitch.completionDate}</td>
                <td>${pitch.type}</td>
                <td>${pitch.genre}</td>
                <td>${pitch.tagline}</td>
            `;
            let td = document.createElement('td');
            let ul = document.createElement('ul');

            td.appendChild(ul);
            tr.appendChild(td);

            let approveBtn = document.createElement('button');
            approveBtn.type = 'button';
            approveBtn.id = pitch.title + '_' + pitch.id;
            approveBtn.textContent = 'Approve';
            //approveBtn.disabled = !loggedUser;


            let btnTd = document.createElement('td');
            btnTd.appendChild(approveBtn);
            tr.appendChild(btnTd);
            table.appendChild(tr);

            approveBtn.addEventListener('click', approvePitch);
            
                        
            //let rejectBtn = document.createElement('button');
            //rejectBtn.type = 'button';
            //rejectBtn.id = pitch.title + '_' + pitch.id;
            //rejectBtn.textContent = 'Approve';
            //rejectBtn.disabled = !loggedUser;


            //let btnTd = document.createElement('td');
            //btnTd.appendChild(rejectBtn);
            //td.appendChild(btnTd);
            //table.appendChild(tr);

            //rejectBtn.addEventListener('click', rejectPitch);
        }

        catSection.appendChild(table);
    } else {
        catSection.innerHTML = 'No cats are available.';
    }
}

async function approvePitch(){
  let btnId = event.target.id;
  let index = btnId.indexOf('_');
  let id = btnId.slice(index+1);
  let name = btnId.replace('_', '');

  let url = 'http://localhost:8081/StoryPitchMS/pitch/' + id;
  let response = await fetch(url, {method:'PUT'});
}

async function  rejectPitch(){

  }
  
