document.addEventListener('DOMContentLoaded', function() {
    const birthDateInput = document.getElementById('birthDate');
    const deathDateInput = document.getElementById('deathDate');
    const heightInput = document.getElementById('height');

    birthDateInput.addEventListener('focus', function() {
        birthDateInput.type = 'date';
    });

    birthDateInput.addEventListener('blur', function() {
        if (!birthDateInput.value) {
            birthDateInput.type = 'text';
            birthDateInput.placeholder = 'Birth Date...';
        }
    });

    deathDateInput.addEventListener('focus', function() {
        deathDateInput.type = 'date';
    });

    deathDateInput.addEventListener('blur', function() {
        if (!deathDateInput.value) {
            deathDateInput.type = 'text';
            deathDateInput.placeholder = 'Death Date...';
        }
    });

    heightInput.addEventListener('focus', function () {
        heightInput.type = 'number';
    });

    heightInput.addEventListener('blur', function () {
        if (!heightInput.value || heightInput.value === 0) {
            heightInput.type = 'text';
            heightInput.placeholder = 'Height...';
        }
    })

    // Remove the focus
    birthDateInput.type = 'text';
    deathDateInput.type = 'text';
    heightInput.type = 'text';
});
