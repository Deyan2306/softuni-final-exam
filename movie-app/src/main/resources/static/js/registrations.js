function checkFields() {
    let username = document.getElementById('username').value;
    let email = document.getElementById('email').value;
    let password = document.getElementById('password').value;
    let confirmPassword = document.getElementById('confirmPassword').value

    const submitButton = document.getElementById('submitButton');

    if (username !== '' && email !== '' && password !== '' && confirmPassword !== '') {
        submitButton.disabled = false;
    }
}

function checkFieldsLogin() {
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    const submitButton = document.getElementById('loginButton');

    if (username !== '' && password !== '') {
        submitButton.disabled = false;
    }
}

function checkFieldsAddActor() {
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const birthDate = document.getElementById('birthDate').value;

    const addActorButton = document.getElementById('addActorButton');

    if (firstName !== '' && lastName !== '' && birthDate !== '') {
        addActorButton.disabled = false;
    }
}

function checkFieldsAddStudio() {
    const name = document.getElementById('name').value;
    const establishedAt = document.getElementById('establishedAt').value;

    const addStudioButton = document.getElementById('addStudioButton');

    if (name !== '' && establishedAt !== '') {
        addStudioButton.disabled = false;
    }
}

function checkFieldsAddADirector() {
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const birthDate = document.getElementById('birthDate').value;

    const addActorButton = document.getElementById('addDirectorButton');

    if (firstName !== '' && lastName !== '' && birthDate !== '') {
        addActorButton.disabled = false;
    }
}

function checkFieldsAddMovie() {

    const movieTitle = document.getElementById('title').value;
    const movieSummary = document.getElementById('summary').value;
    const movieReleaseDate = document.getElementById('releaseDate').value;
    const movieLength = document.getElementById('length').value;

    const addMovieButton = document.getElementById('addMovieButton');

    if (movieTitle !== '' && movieSummary !== '' && movieReleaseDate !== '' && movieLength !== 0) {
        addMovieButton.disabled = false;
    }
}