function getSearchList(){
	$.ajax({
		type: 'GET',
		url : "/getSearchList",
		data : $("form[name=search-form]").serialize(),
		success : function(result){
			//테이블 초기화
			$('#boardtable > tbody').empty();
			if(result.length>=1){
				result.forEach(function(item){
					str='<tr>'
					str += "<td>"+member.id+"</td>";
					str+="<td>"+member.username+"</td>";
					str+="<td>"+member.date+"</td>";

					str+="</tr>"
					$('#membertable').append(str);
        		})
			}
		}
	})
}
