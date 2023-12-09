function showNotification() {
    const notification = document.getElementById('successNotification');
    notification.style.display = 'block';

    setTimeout(() => {
        notification.style.display = 'none';
    }, 3000);
}