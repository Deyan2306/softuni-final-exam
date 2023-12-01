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