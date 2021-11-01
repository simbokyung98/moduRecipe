//이메일 입력방식 선택
$('#selectEmail').change(function(){
    $("#selectEmail option:selected").each(function () {

        if($(this).val()== '1'){ //직접입력일 경우
            $("#Email2").val(''); //값 초기화
            $("#Email2").attr("disabled",false); //활성화
        }else{ //직접입력이 아닐경우
            $("#Email2").val($(this).text()); //선택값 입력
            $("#Email2").attr("disabled",true); //비활성화
        }
    });
});




