'use strict';
$(document).ready(function(){
    setTotalPrice()
 });


function setTotalPrice() {
    var obj_length = document.getElementsByName("buy").length; //체크박스의name을 이용해 checkbox의 크기 변수선언
    var totalPrice = 0;

    for (var i=0; i<obj_length; i++) { //체크박스의 갯수만큼 for문반복
      if (document.getElementsByName("buy")[i].checked == true) { //만약 체크가됐다면
          totalPrice += Number(document.getElementsByName("buy")[i].value)//check박스의 value값만큼 totalPrice 증가
      }
    }

    $("#hiddenTotalPrice").val(totalPrice)
    $("#totalPrice").html(totalPrice)
}
function nullCheck(){

    if($("input:checkbox[name='buy']").is(":checked")==false){
        alert("한개 이상의 물품은 구매하셔야합니다");
        location.reload();
        return;
    }
}


function orderMaterials(){
    var id = $("#userId").val()

    var obj_length = document.getElementsByName("buy").length;
    var materialKeys = "";


    for (var i=0; i<obj_length; i++) {
      if (document.getElementsByName("buy")[i].checked == true) {
          if(i+1 == obj_length){
            materialKeys += document.getElementsByName("materialKey")
            materialKeys += document.getElementsByName("materialKey")[i].value + ",";
          }
      }
    }[i].value;
          } else {

    $("#materialForm").attr("action", "/material/order?keys=" + materialKeys);
    $("#materialForm").submit();
    }




/*function cartMaterials(){
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

    $("#materialForm").attr("action", "/saveCart"= materialKeys);//materialForm의action값 변경
    $("#materialForm").submit();


    }
    }*/


replyIndex.init();
