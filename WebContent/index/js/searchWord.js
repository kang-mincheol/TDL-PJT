/**
 * xhr 객체를 생성 -> 요청 -> jsp로 요청 -> 응답 -> 콜백함수 실시간으로 출력 
 */

var xhrObject;//xhr객체를 전역변수로 선언

//1.xhr객체를 생성해주는 함수
function createXHR(){
	if(window.XMLHttpRequest){
		xhrObject = new XMLHttpRequest(); //객체를 생성->반환
		//alert(xhrObject);//주소값을 확인
	}
}

//2.중복id를 입력->처리
function idCheck(id){
	if(id==""){
		//var mem_id=document.getElementById("ducheck");
		var TU_id = $("idResult");
		//alert(TU_id);
		$("idResult").innerHTML="<font color='red'><b>아이디 입력하세요</b></font>";
		//document.regForm.mem_id.focus();
		$("TU_id").focus();
		return false;
	}
	//입력하고나서 Ajax Programming(IdCheck.jsp호출)
	//1.xhr객체 얻어오기
	createXHR();
	/*var url="http://localhost:8090/TDL/index/TDL_MEMBER/IdCheck.jsp?"+getParameterValues();*/
	var url="http://192.168.0.71:8090/TDL/index/TDL_MEMBER/IdCheck.jsp?"+getParameterValues();
	//alert(url);
	//2.콜백함수 지정
	xhrObject.onreadystatechange=resultProcess;
	//3.open함수를 이용 서버에 요청준비
	xhrObject.open("GET",url,true);
	//4.send()요청
	xhrObject.send(null);
}

//3.파라미터값을 처리해주는 함수 ->메모리 해제를 해주는 방법
function getParameterValues(){
	var TU_id = $("TU_id").value; //document.regForm.mem_id.value(X)
	//서버캐시에 요청메모리에 저장하지 않는 방법
	return "TU_id="+TU_id+"&timestamp="+new Date().getTime();
}

//4.콜백함수
function resultProcess(){
	//alert("resultProcess");
	if(xhrObject.readyState==4){ //서버가 요청을 다 받았다면
		if(xhrObject.status==200){ //서버의 결과를 받았다면
			var result=xhrObject.responseText; //태그+문자열 -> xml형식으로 전달받음
			//alert(result)
			$("idResult").innerHTML=result;
		}
	}
}




