document.addEventListener('DOMContentLoaded', function() {
    const startDate = document.getElementById('startDate');
    const endDate = document.getElementById('endDate');

    startDate.addEventListener('focus', function() {
        startDate.type = 'date';
    });

    startDate.addEventListener('blur', function() {
        if (!startDate.value) {
            startDate.type = 'text';
            startDate.placeholder = 'Start Date...';
        }
    });

    endDate.addEventListener('focus', function() {
        endDate.type = 'date';
    });

    endDate.addEventListener('blur', function() {
        if (!endDate.value) {
            endDate.type = 'text';
            endDate.placeholder = 'End Date...';
        }
    });

    // Remove the focus
    startDate.type = 'text';
    endDate.type = 'text';
});
