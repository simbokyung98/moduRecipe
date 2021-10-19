function updatePwd(){
    let pwd1 = $("#pwd1").val();
    let pwd2 = $("#pwd2").val();
    let id = $("#userId").val()

    if(pwd1 == pwd2){
        $("#datachange").attr("action", "/datachange/" + id);
        $("#datachange").submit();
    } else {
        alert("비밀번호가 서로 다릅니다 .")
    }

}

function deleteUser(){

    if(confirm("탈퇴하시겠습니까?")){
        let id = $("#userId").val()

        $("#delete-form").attr("action", "/member/" + id);
        $("#delete-form").submit();
    }
}