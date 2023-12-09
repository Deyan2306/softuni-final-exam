// JavaScript for filtering actors
$(document).ready(function(){
    $('#searchInput').on('input', function(){
        var searchText = $(this).val().toLowerCase();
        var $list = $('#actorsList');
        $list.children().detach().sort(function(a, b) {
            var aText = $(a).text().toLowerCase();
            var bText = $(b).text().toLowerCase();
            return (aText.includes(searchText) && !bText.includes(searchText)) ? -1 : (bText.includes(searchText) && !aText.includes(searchText)) ? 1 : 0;
        }).appendTo($list);
    });
});