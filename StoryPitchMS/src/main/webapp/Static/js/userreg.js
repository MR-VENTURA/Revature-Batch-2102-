addUser();

async function addUser() {
    let user = {};
    user.id = 0;
    user.username = document.getElementById('user').value;
    user.password = document.getElementById('pass').value;
    user.firstname = document.getElementById('fname').value;
    user.firstname = document.getElementById('lname').value;
    user.email = document.getElementById('email').value;

    let url = 'http://localhost:8081/StoryPitchMS/user/';
    let response = await fetch(url, {method:'POST', body:JSON.stringify(user)});
    if (response.status === 200) {
        alert('Added User successfully.');
    } else {
        alert('Something went wrong.');
    }
}


