function setCookie(name, value, days) {
    const expires = new Date();
    expires.setTime(expires.getTime() + days * 24 * 60 * 60 * 1000);
    document.cookie = name + '=' + value + ';expires=' + expires.toUTCString();
}

function getCookie(name) {
    const cookies = document.cookie.split(';');
    for (let i = 0; i < cookies.length; i++) {
        const cookie = cookies[i].trim().split('=');
        if (cookie[0] === name) {
            return cookie[1];
        }
    }
    return null;
}

const acceptCookiesButton = document.getElementById('acceptCookiesButton');
const cookieConsentBanner = document.getElementById('cookieConsentBanner');

acceptCookiesButton.addEventListener('click', function() {
    setCookie('useCookies', 'true', 365); // Set cookie 'useCookies' for 365 days
    cookieConsentBanner.style.display = 'none'; // Hide the banner after accepting cookies
});

// Check if 'useCookies' cookie exists
if (getCookie('useCookies')) {
    cookieConsentBanner.style.display = 'none'; // Hide the banner if the cookie exists
}

const loginButton = document.getElementById('loginButton');
const registerButton = document.getElementById('registerButton');
const buttonContainer = document.getElementById('buttonContainer');

acceptCookiesButton.addEventListener('click', function() {
    setCookie('useCookies', 'true', 365);
    cookieConsentBanner.style.display = 'none';
    // Show the buttons
    buttonContainer.style.display = 'block';
    // Enable login and register buttons
    loginButton.removeAttribute('disabled');
    registerButton.removeAttribute('disabled');
});

// Check if 'useCookies' cookie exists
if (getCookie('useCookies')) {
    cookieConsentBanner.style.display = 'none';
    // Show the buttons
    buttonContainer.style.display = 'block';
    // Enable login and register buttons
    loginButton.removeAttribute('disabled');
    registerButton.removeAttribute('disabled');
}