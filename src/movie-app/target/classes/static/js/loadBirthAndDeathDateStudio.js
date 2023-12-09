document.addEventListener('DOMContentLoaded', function() {
    const establishedAt = document.getElementById('establishedAt');

    establishedAt.addEventListener('focus', function () {
        establishedAt.type = 'date';
    });

    establishedAt.addEventListener('blur', function() {
        if (!establishedAt.value) {
            establishedAt.type = 'text';
            establishedAt.placeholder = 'Established at...';
        }
    });

    // Remove the focus
    establishedAt.type = 'text';

});