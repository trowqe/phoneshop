$(document).ready(function () {

    $("button").click(function (e) {
        e.preventDefault();
        console.log('delete phone from cart');
        var url = document.getElementById("deleteButton").getAttribute("form");
        var id=document.getElementById("deleteButton").getAttribute("name");git

        $.ajax({
            type: 'DELETE',
            url: url + id,
            success: function () {
                console.log('ok');
                location.reload();

            }, error: function (data) {
                console.log(data.responseText);
            }
        });
    });
});