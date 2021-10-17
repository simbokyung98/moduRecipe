'use strict';
$(document).ready(function(){
    setTotalPrice()
 });


let replyIndex = {
    init: function () {
        $("#review-btn-save").on("click", () => {
            this.reviewsave();
        });
    },

    reviewsave: function () {
        let data = {
            content: $("#reviewcontent").val(),
        }
        let recipekey = $("#recipekey").val();
        console.log(data);
        console.log(recipekey);
        $.ajax({
            type: "POST",
            url: `/recipedetail/{recipekey}`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "text"
        }).done(function (res) {
            alert("댓글작성이 완료되었습니다.");
            location.href = `/recipedetail/{recipekey}`;
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },

}

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
