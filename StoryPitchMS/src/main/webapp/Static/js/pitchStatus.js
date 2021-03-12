getPitches();
getInfo();
getReject();
getHolds();

async function getPitches() {

    let url ='http://localhost:8081/StoryPitchMS/author';
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
                <th>Description</th>
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
                <td>${pitch.description}</td>
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
            
                        
            let rejectBtn = document.createElement('button');
            rejectBtn.type = 'button';
            rejectBtn.id = pitch.title + '_' + pitch.id;
            rejectBtn.textContent = 'Reject';
            //rejectBtn.disabled = !loggedUser;


            let btnRe = document.createElement('td');
            btnRe.appendChild(rejectBtn);
            td.appendChild(btnRe);
            table.appendChild(tr);

            rejectBtn.addEventListener('click', rejectPitch);
        }

        catSection.appendChild(table);
    } else {
        catSection.innerHTML = 'No pitches have been approved.';
    }
}

async function approvePitch(){
  let btnId = event.target.id;
  let index = btnId.indexOf('_');
  let id = btnId.slice(index+1);
  let name = btnId.replace('_', '');
  
  let url = 'http://localhost:8081/StoryPitchMS/author/' + id;
  let response = await fetch(url);
  
  let pitch = await response.json();
  
  pitch.status = 5;
  
  let newResponse = await fetch(url,{method:'PUT',body:JSON.stringify(pitch)});
  if (newResponse.status === 200) {
        alert('Pitch Approved.');
  } else {
        alert('Something went wrong.');
    }

  }
  
async function  rejectPitch(){
	let btnId = event.target.id;
  let index = btnId.indexOf('_');
  let id = btnId.slice(index+1);
  let name = btnId.replace('_', '');
  
    let url = 'http://localhost:8081/StoryPitchMS/pitch/reject';
  	    let response = await fetch(url, {method:'POST', body:JSON.stringify(reject)});
    if (response.status === 200) {
        alert('Reject successfully.');
    } else {
        alert('Something went wrong.');
    }
  }
  
  async function getInfo() {

    let url ='http://localhost:8081/StoryPitchMS/author/info';
    let response = await fetch(url);
    if (response.status === 200) {
        let info = await response.json();
        console.log("response is 200");
        populateInfo(info);
    }
}
  
async function populateInfo(info) {
	//Maybe ${info.request.pitchID}
    let infoSection = document.getElementById('infoSection');
    infoSection.innerHTML =   

  `
    Request for more info for pitch ${info.pitchID}:  
	${info.request}
   <form id='infoTable'> 
   </textarea>
    <textarea id="infoRes" name="infoResponse" rows="4" cols="50">
      *For Recipient Only* Please input the requested information here.
    </textarea>

    <button type="button" onclick="addInfo()" id="submit-info" >Submit</button>
  </form>
  `;
     let submitInfoBtn = document.getElementById('submit-info');
    submitInfoBtn.onclick = addInfo;
}

async function addInfo(){
 	//let info = {};
 	//info.id = 0;
 	//info.pitchID;
 	//info.response = document.getElementById('infoRes').value;
 	
 	let url = 'http://localhost:8081/StoryPitchMS/author/info'
 	let oldresponse = await fetch(url);
 	let info = await oldresponse.json();
 	
 		let response = await fetch(url, {method:'PUT', body:JSON.stringify(info)});
 	   if (response.status === 200) {
        alert('Response successful.');
    } else {
        alert('Something went wrong.');
    }
}

 async function getReject() {

    let url ='http://localhost:8081/StoryPitchMS/author/reject';
    let response = await fetch(url);
    if (response.status === 200) {
        let reject = await response.json();
        console.log("response is 200");
        populateReject(reject);
    } else {
    populateNoReject();
}
}

async function populateReject(reject) {
	let rejectSection = document.getElementById('rejectSection');
   	rejectSection.innerHTML = 
   	` Pitch of ID  ${reject.pitchID} has been rejected. 
    The reason is: ${reject.reason}  `;
    }

async function populateNoReject() {
	let rejectSection = document.getElementById('rejectSection');
   	rejectSection.innerHTML = 'No rejections';
}

async function getHolds() {

    let url ='http://localhost:8081/StoryPitchMS/author/hold';
    let response = await fetch(url);
    if (response.status === 200) {
        let cats = await response.json();
        populateHolds(cats);
    }
 }

function populateHolds(cats) {
    let holdSection = document.getElementById('holdSection');
    holdSection.innerHTML = '';

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
                <th>Description</th>
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
                <td>${pitch.description}</td>
            `;
            let td = document.createElement('td');
            let ul = document.createElement('ul');

            td.appendChild(ul);
            tr.appendChild(td);

            let approveBtn = document.createElement('button');
            approveBtn.type = 'button';
            approveBtn.id = pitch.title + '_' + pitch.id;
            approveBtn.textContent = 'Submit';
            approveBtn.disabled = 120 > 100;
            //make disabled button dependent on points of pitch.type + user.points < 100


            let btnTd = document.createElement('td');
            btnTd.appendChild(approveBtn);
            tr.appendChild(btnTd);
            table.appendChild(tr);

            //approveBtn.addEventListener('click', approvePitch);            
        }

        holdSection.appendChild(table);
    } else {
        holdSection.innerHTML = 'No pitches are on hold.';
    }
}



  