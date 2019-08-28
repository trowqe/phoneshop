$(document).ready(function () {

    $("button").click(function (e) {

        e.preventDefault();
        var phoneId = this.id;
        var quantity = document.getElementById(this.id + 'q').value;

        var cartItem = {
            itemId: phoneId,
            itemQuantity: quantity
        };

        console.log(cartItem);


        $.ajax({
            type: 'POST',
            url: '${pageContext.request.contextPath}/ajaxCart',
            headers: {
                "Content-Type": "application/json"
            },
            data: JSON.stringify(cartItem),
            success: function (data) {
                console.log("post resp " + data.hasOwnProperty("totalSum"));
                console.log(data);
                var totalSum = data.totalSum;
                var totalItem = parseInt(data.totalItem);
                console.log(totalSum);
                console.log(totalItem);

                document.getElementById("totalQuantity").innerHTML = totalItem;
                document.getElementById("totalSum").innerHTML = totalSum;

            }, error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                console.log(textStatus);
                console.log(errorThrown);
                alert("wrong input. Number should be between 1 and 100");
            }
        });

    });
});