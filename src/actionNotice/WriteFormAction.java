package actionNotice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbc.*;

public class WriteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		System.out.println("================공지사항 글쓰기폼  actionNotice - WriteFormAction클래스 시작");
		//jsp에서 처리해야할 자바코드를 대신처리해주는 역할 -> 결과 전달 -> jsp 전달
		//list.jsp(글쓰기)->신규글, content.jsp(글상세보기)-> 글쓰기-> 답변글
   		int TN_num=0;//writePro.jsp
   		
   		//content.jsp에서 매개변수가 전달
   		if(request.getParameter("TN_num")!=null){//양수 (음수,0은 아니다)
   			TN_num=Integer.parseInt(request.getParameter("TN_num"));//"3"->3
   			System.out.println("WriteFormAction if문");
   			System.out.println("content.jsp에서 넘어온 매개변수 확인");
   			System.out.println("TN_num이 null이 아닐때 : "+TN_num);
   		}
   		System.out.println("공지사항 글쓰기 TN_num -> "+TN_num);
   		System.out.println("WriteFormAction 호출2");
   		//2.실행결과(변수선언,매개변수,매서드의 실행결과물)->request영역에 저장
   			request.setAttribute("TN_num", TN_num); //request.getAttribute("num")->${num}
   			System.out.println("================공지사항 글쓰기 폼 actionNotice - WriteFormAction클래스 끝");
			return "/index/TDL_NOTICE/writeForm.jsp";
	}

}
