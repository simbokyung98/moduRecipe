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

/*
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

*/

/*
<script>
 window.onload = function() {
  //isCheck라는 아이디를 가진 객체를 찾아서 값을 false로 설정
  document.getElementById("isCheck").value = "false";

  document.getElementById("checkId").onclick = function() {
   var sid = document.getElementById("id").value;
   if (sid == null || sid.length == 0) {
    alert("아이디를 입력하고 검사하세요");
   } else {
    //새창 출력
    window
      .open("checkId.jsp?id=" + sid, "",
        "width=400 height=400");
   }



  }
  */


  /*

  //joinForm에서 submit을 눌렀을때 호출될 함수 설정
  //true를 리턴하거나 리턴값이 없으면 action으로 전송되고
  //false를 리턴하면 전송되지 않습니다.

  document.getElementById("joinForm").onsubmit = function() {
   //id의 값이 없으면 전송하지 않기

   var sid = document.getElementById("id").value;
   var name = document.getElementById("name").value;
   var password = document.getElementById("password").value;
   if (sid == null || sid.length == 0) {
    alert("아이디는 필수 입력입니다.");
    return false;
   } else if (name == null || name.length == 0) {
    alert("이름은 필수 입력 입니다.");
    return false;
   } else if (password == null || password.length == 0) {
    alert("패스워드는 필수 입력 입니다.")
    return false;
   }

   //아이디 중복체크를 하지 않았으면 전송되지 않도록

   var isCheck = document.getElementById("isCheck").value;
   if (isCheck != "true") {
    alert("아이디 중복 체크를 하지 않으셨습니다.");
    return false;
   }
   return true;
  }

  //id 란에 포커스가 오면 중복체크를 다시 하도록 isCheck의 값을 변경
  document.getElementById("id").onblur = function() {
   document.getElementById("isCheck").value = "false";
  }

 }
 */

/*
 window.onload=function (){
 	//1. 아이디 중복 체크
 	document.getElementById("id_request").onclick=function(){
         var gsWin = window.open("about:blank", "winName", "width=400,height=300,top=100,left=200");
 		var fr = document.getElementById("server");
         fr.action = "id";
         fr.target = "winName";
         fr.submit();
 	}
 }
 */

  function idCheck() {

     $('.id').change(function () {
       $('#id_check_sucess').hide();
       $('.id_overlap_button').show();
       $('.id').attr("check_result", "fail");
     })


     if ($('.id').val() == '') {
       alert('아이디를 입력해주세요.')
       return;
     }

     id_overlap_input = document.querySelector('input[name="username"]');

     $.ajax({
       url: "{% url 'lawyerAccount:idCheck' %}",
       data: {
         'username': id_overlap_input.value
       },
       datatype: 'json',
       success: function (data) {
         console.log(data['overlap']);
         if (data['overlap'] == "fail") {
           alert("이미 존재하는 아이디 입니다.");
           id_overlap_input.focus();
           return;
         } else {
           alert("사용가능한 아이디 입니다.");
           $('.id').attr("check_result", "success");
           $('#id_check_sucess').show();
           $('.idcheck').hide();
           return;
         }
       }
     });
   }

   # memberJoin
   def idCheck(request):
       username = request.GET.get('username')
       try:
           # 중복 검사 실패
           user = User.objects.get(username=username)
       except:
           # 중복 검사 성공
           user = None
       if user is None:
           overlap = "pass"
       else:
           overlap = "fail"
       context = {'overlap': overlap}
       return JsonResponse(context)



if ($('.id').attr("check_result") == "fail"){
    alert("아이디 중복체크를 해주시기 바랍니다.");
    $('.id').focus();
    return false;
  }
