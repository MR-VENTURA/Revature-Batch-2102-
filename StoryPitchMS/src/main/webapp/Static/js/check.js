checkLogin();

async function checkLogin() {
    let url = 'http://localhost:8081/StoryPitchMS/user';
    let response = await fetch(url);
    if (response.status === 200) loggedUser = await response.json();
}