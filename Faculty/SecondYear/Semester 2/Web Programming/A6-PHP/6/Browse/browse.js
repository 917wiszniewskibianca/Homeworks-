var pagenumber = 0;

function buttonClick() {
    var category = $("#category").val();
    var type = $("#type").val();
    var price = $("#price").val();
    var hotel = $("#hotel").val();
    var pg = $("#pageNumber").val();

    $.ajax({
        url: "browse-room.php",
        type: "GET",
        data: { category:category,  type:type, price:price, hotel:hotel,pg:pg},
        success:function (result) {
            $('#tbodyTable').html(result);
        }
    })
}

function nextPage(){
    pagenumber += 1;
    document.getElementById("pageNumber").value = pagenumber;
}

function prevPage(){
    if(pagenumber > 0)
        pagenumber -=1;
    document.getElementById("pageNumber").value = pagenumber;
}

$(document).ready(function () {

    document.getElementById("pageNumber").value = pagenumber;
    $('#submitButton').click(buttonClick);
    $('#submitButton').trigger('click');
    $('#prevButton').click(function (){prevPage();     $('#submitButton').trigger('click');});
    $('#nextButton').click(function (){nextPage();     $('#submitButton').trigger('click');});


});


