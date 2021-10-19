/* 아이디 중복 체크 : ajax 비동기처리
function idCheck() {

    var username = $("#username").val();

    if(username.search(/\s/) != -1) {
        alert("아이디에는 공백이 들어갈 수 없습니다.");
    } else {
        if(username.trim().length != 0) {
            $.ajax({
                async : true,
                type : 'POST',
                data: username,
                url: "/idCheck",
                dataType: "json",
                contentType: "application/json; charset=UTF-8",
                success: function(count) {
                    if(count > 0) {
                        alert("해당 아이디 존재");
                        $("#username").attr("disabled", "disabled");
                        window.location.reload();
                    } else {
                        alert("사용가능 아이디");
                        $("#username").removeAttr("disabled");
                    }
                },
                error: function(error) {
                    alert("아이디를 입력해주세요.");
                }
            });
        } else {
            alert("아이디를 입력해주세요.");
        }
    }
}*/

function openIdChk(){

	    window.name = "parentForm";
	    window.open("IdCheckForm.html",
	            "chkForm", "width=500, height=300, resizable = no, scrollbars = no");
	}

	// 아이디 입력창에 값 입력시 hidden에 idUncheck를 세팅한다.
	// 이렇게 하는 이유는 중복체크 후 다시 아이디 창이 새로운 아이디를 입력했을 때
	// 다시 중복체크를 하도록 한다.
	function inputIdChk(){
	    document.userInfo.idDuplication.value ="idUncheck";
	}
