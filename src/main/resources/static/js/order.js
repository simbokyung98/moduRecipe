'use strict';

function order(){
//    var id = $("#userId").val()

    if($('input:checkbox[id="pay_agree"]').is(":checked") == true){
        var materialKeys = getParameterByName("keys")

        $("#orderphone").val($("#emergency11").val() + "-" + $("#emergency12").val() + "-" + $("#emergency13").val())
        $("#orderaddress").val($("#sample4_postcode").val() + " " + $("#sample4_roadAddress").val() + "-" + $("#sample4_detailAddress").val())


        $("#order_form").attr("method", "post");
        $("#order_form").attr("action", "/material/orderProcess?keys=" + materialKeys);
        $("#order_form").submit();
    } else {
        alert("구매진행에대해 동의함을 체크해주세요.")
    }

}


function getParameterByName(name) {
name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
results = regex.exec(location.search);
return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}


