'use strict';
$(document).ready(function(){
    setTotalPrice()
 });


function setTotalPrice() {
    var obj_length = document.getElementsByName("buy").length;
    var totalPrice = 0;

    for (var i=0; i<obj_length; i++) {
      if (document.getElementsByName("buy")[i].checked == true) {
          totalPrice += Number(document.getElementsByName("buy")[i].value)
      }
    }

    $("#hiddenTotalPrice").val(totalPrice)
    $("#totalPrice").html(totalPrice)
}
function nullCheck(){

    if($("input:checkbox[name='buy']").is(":checked")==false){
        alert("한개 이상의 물품은 선택하셔야합니다");
        return false;
    }
}

function orderMaterials(){
    var id = $("#userId").val()

    var obj_length = document.getElementsByName("buy").length;
    var materialKeys = "";

    for (var i=0; i<obj_length; i++) {
      if (document.getElementsByName("buy")[i].checked == true) {
          if(i+1 == obj_length){
            materialKeys += document.getElementsByName("materialKey")[i].value;
          } else {
            materialKeys += document.getElementsByName("materialKey")[i].value + ",";
          }
      }
    }

    $("#materialForm").attr("action", "/material/order?keys=" + materialKeys);
    $("#materialForm").submit();

}

replyIndex.init();