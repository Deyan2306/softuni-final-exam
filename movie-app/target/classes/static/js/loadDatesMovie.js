document.addEventListener('DOMContentLoaded', function() {
    const releaseDate = document.getElementById('releaseDate');

    releaseDate.addEventListener('focus', function () {
        releaseDate.type = 'date';
    });

    releaseDate.addEventListener('blur', function() {
        if (!releaseDate.value) {
            releaseDate.type = 'text';
            releaseDate.placeholder = 'Release datea...';
        }
    });

    // Remove the focus
    releaseDate.type = 'text';

});