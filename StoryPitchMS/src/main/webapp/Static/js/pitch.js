var pID;
var priority = false;
setup();

function setup() {
    getPitches().then(() => {
        checkLogin().then(() => {
            if (loggedUser.rank === 4) seniorSetup();
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
            //approveBtn.disabled = priority;

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
            
           
            let infoBtn = document.createElement('button');
            infoBtn.type = 'button';
            infoBtn.id = pitch.title + '_' + pitch.id;
            infoBtn.textContent = 'Request Info';
            //infoBtn.disabled = !loggedUser;


            let btnInf = document.createElement('td');
            btnInf.appendChild(infoBtn);
            td.appendChild(btnInf);
            table.appendChild(tr);

            infoBtn.addEventListener('click', infoPitch);
            
          	pID = pitch.id;
            
            let pSD = parseInt(pitch.submissionDate);
            if (pSD < 1900-01-01) {
            	priority = true;
            	console.log("Priority is " + priority);
            	approveBtn.disabled = false;

            	let priBtn = document.createElement('button');
            	priBtn.type = 'button';
            	priBtn.id = pitch.title + '_' + pitch.id;
            	priBtn.textContent = 'Priority';
           
            	let btnPri = document.createElement('td');
            	btnPri.appendChild(priBtn);
            	td.appendChild(btnPri);
            	table.appendChild(tr);
            }   
            if (priority == true) {
            	approveBtn.disabled = false;
            }     
        }
        catSection.appendChild(table);
    } else {
        catSection.innerHTML = 'No pitches are available.';
    }
}

async function approvePitch(){
  let btnId = event.target.id;
  let index = btnId.indexOf('_');
  let id = btnId.slice(index+1);
  let name = btnId.replace('_', '');

  let url = 'http://localhost:8081/StoryPitchMS/pitch/' + id;
  let response = await fetch(url);
  
  let pitch = await response.json();
  
  pitch.assistantApprove = true;
  //pitch.title = document.getElementById('eTitle').value;
  //pitch.completionDate = document.getElementById('eETC').value;
  //pitch.tagline = document.getElementById('eTag').value;
  
  let newResponse = await fetch(url,{method:'PUT',body:JSON.stringify(pitch)});
  if (newResponse.status === 200) {
        alert('Pitch Approved.');
  } else {
        alert('Something went wrong.');
    }
}

async function  infoPitch(){
	let btnId = event.target.id;
  let index = btnId.indexOf('_');
  let id = btnId.slice(index+1);
  let name = btnId.replace('_', '');
  
  let headSection = document.getElementById('head');
    headSection.innerHTML = `<p>'Request More Information Below'</p>`;
  
  catSection.innerHTML =  
  `<form>
    <!-- Request for pitch_id -->
    <textarea id="infoReq" name="infoRequest" rows="4" cols="50">
      *For Editor Only* What more information is needed?
    </textarea>

    <button type="button" onclick="addInfo()" id="submit-info" >Submit</button>
  </form>
  `;
    let submitInfoBtn = document.getElementById('submit-info');
    submitInfoBtn.onclick = addInfo;
 }
 
 async function addInfo(){
	let info = {};
 	info.id = 0;
 	info.pitchID = pID;
 	info.request = document.getElementById('infoReq').value;
 	info.response = null;
 	
 	let url = 'http://localhost:8081/StoryPitchMS/pitch/info'
 		let response = await fetch(url, {method:'POST', body:JSON.stringify(info)});
 	   if (response.status === 200) {
        alert('Response successful.');
    } else {
        alert('Something went wrong.');
    }
}
  
  async function  rejectPitch(){
	let btnId = event.target.id;
  let index = btnId.indexOf('_');
  let idP = btnId.slice(index+1);
  let name = btnId.replace('_', '');
  
  x = pID;
  console.log("got" + x);
  
  catSection.innerHTML =  `<form>
  	 <textarea id="reject" name="reason" rows="4" cols="50">
      The reason that this pitch was rejected.
    </textarea>

    <button type="button" onclick="addReject()" id="submit-reject-form" >Submit</button>
  </form>
  `;
    let submitRejectBtn = document.getElementById('submit-reject-form');
    submitRejectBtn.onclick = addReject;
  }
  
  async function addReject(){
  	let reject = {};
  	reject.id = 0;
  	reject.pitchID = pID;
  	reject.reason = document.getElementById('reject').value;
  	
  	console.log(pID + "this is" + reject.pitchid);
  	
  	let url = 'http://localhost:8081/StoryPitchMS/pitch/reject';
  	    let response = await fetch(url, {method:'POST', body:JSON.stringify(reject)});
    if (response.status === 200) {
        alert('Reject successfully.');
    } else {
        alert('Something went wrong.');
    }
  }
  


function seniorSetup() {
console.log("senior setup");
    let catsTable = document.getElementById('catTable');
    for (let tr of catsTable.childNodes) {
        if (tr.nodeName === 'TR') {
            let td = document.createElement('td');
            if (tr != catsTable.childNodes.item(0)) {
                let editBtn = document.createElement('button');
                editBtn.id = 'edit_' + tr.childNodes.item(1).textContent;
                editBtn.type = 'button';
                editBtn.textContent = 'Edit';
                editBtn.onclick = editPitch;
                td.appendChild(editBtn);
            }
            tr.appendChild(td);
        }
    }
}

function editPitch() {
    let editBtn = event.target;
    let editId = event.target.id;
    let editTd = editBtn.parentElement;
    let editTr = editTd.parentElement;

    let nodes = editTr.childNodes;

    editTr.innerHTML = `
        <td>${nodes.item(1).innerHTML}</td>
        <td><input id = "eTitle" type = "text" value = ${nodes.item(3).innerHTML}></td>
        <td>${nodes.item(5).innerHTML}</td>
        <td><input id = "eETC" type = "text" value = ${nodes.item(7).innerHTML}></td>
        <td>${nodes.item(9).innerHTML}</td>
        <td>${nodes.item(11).innerHTML}</td>
        <td><input id = "eTag" type = "text" value = ${nodes.item(13).innerHTML}></td>
        <td>${nodes.item(15).innerHTML}</td>
        <td><button disabled = 'true'>Reject</button>
        <button id = ${editId}>Approve</button></td>
        `;
    editBtn = document.getElementById(editId);
    editBtn.addEventListener('click', approvePitch);

}
