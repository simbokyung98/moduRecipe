'use strict';

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
replyIndex.init();