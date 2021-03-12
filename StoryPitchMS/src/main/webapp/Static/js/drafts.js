getPitches();

async function getPitches() {

    let url ='http://localhost:8081/StoryPitchMS/author/draft';
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
            approveBtn.textContent = 'Approved!!!!';
           
            let btnTd = document.createElement('td');
            btnTd.appendChild(approveBtn);
            tr.appendChild(btnTd);
            table.appendChild(tr);

            
        }

        catSection.appendChild(table);
    } else {
        catSection.innerHTML = 'No pitches have been approved.';
    }
}

    //let submitInfoBtn = document.getElementById('draftBtn');
    //submitInfoBtn.onclick = uploadDraft();

//Upload Draft will probably run into errors because of CORS
function uploadDraft(){
	const draftForm = document.getElementById("draftForm");
	const draftFile = document.getElementById("draft");
	
	draftForm.addEventListener("submit", e => {
		e.preventDefault();
		
		const endpoint = '"http://localhost:8081/StoryPitchMS/src/main/webapp/Static/draftUploads/';
		const formData = new FormData();
		
		console.log(draftFile.files);
		
		formData.append("draftFile", draftFile.files[0]);
		
		fetch(endpoint, {
			method: "POST",
			body: formData
		}).catch(console.error);
	});
}


  

  
   