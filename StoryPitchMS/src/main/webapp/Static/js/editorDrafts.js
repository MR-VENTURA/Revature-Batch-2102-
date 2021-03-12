getPitches();
checkLogin();

async function getPitches() {

    let url ='http://localhost:8081/StoryPitchMS/editor/draft';
    let response = await fetch(url);
    if (response.status === 200) {
        let pitches = await response.json();
        populatePitch(pitches);
    }
}

function populatePitch(pitches) {
    let catSection = document.getElementById('catSection');
    catSection.innerHTML = '';

    if (pitches.length > 0) {
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

        for (let pitch of pitches) {
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
            approveBtn.disabled = loggedUser.rank !== 4;
		//Currently only Senior Editor can approve
           
            let btnTd = document.createElement('td');
            btnTd.appendChild(approveBtn);
            tr.appendChild(btnTd);
            table.appendChild(tr);

            approveBtn.addEventListener('click', approvePitch);
            
            //Adding way to request info
            let infoBtn = document.createElement('button');
            infoBtn.type = 'button';
            infoBtn.id = pitch.title + '_' + pitch.id;
            infoBtn.textContent = 'Request Info';
      
            let btnInf = document.createElement('td');
            btnInf.appendChild(infoBtn);
            td.appendChild(btnInf);
            table.appendChild(tr);

            infoBtn.addEventListener('click', infoPitch);
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
  
  let url = 'http://localhost:8081/StoryPitchMS/editor/' + id;
  let response = await fetch(url);
  
  let pitch = await response.json();
  
  pitch.status = 7;
  
  let newResponse = await fetch(url,{method:'PUT',body:JSON.stringify(pitch)});
  if (newResponse.status === 200) {
        alert('Pitch Approved for Publication');
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
 What changes do you wish to request?
 The author can also be contacted at: 
     sample@email.com   
    </textarea>

    <button type="button" onclick="addInfo()" id="submit-info" >Submit</button>
  </form>
  `; //input actual email's dependent on author
    let submitInfoBtn = document.getElementById('submit-info');
    submitInfoBtn.onclick = addInfo;
 }
 
  async function addInfo(){


}