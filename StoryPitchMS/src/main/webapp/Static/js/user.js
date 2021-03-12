loggedUser = null;
login();

async function login() {
    let url = 'http://localhost:8081/StoryPitchMS/user/login?';
    url += 'user=' + document.getElementById('user').value + '&';
    url += 'pass=' + document.getElementById('pass').value;
    let response = await fetch(url, {method: 'POST'});
   
      switch (response.status) {
        case 200: 
            loggedUser = await response.json();
            break;
        case 400: 
            alert('Incorrect password, try again.');
            document.getElementById('pass').value = '';
            break;
        case 404:
            alert('That user does not exist.');
            document.getElementById('user').value = '';
            document.getElementById('pass').value = '';
            break;
        default: 
            alert('Something went wrong.');
            break;
    }
    }