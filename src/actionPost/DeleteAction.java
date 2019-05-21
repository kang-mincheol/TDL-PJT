package actionPost;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tdl_Comment.*;
import tdl_Post.*;
import java.util.*;
import dbc.*;
import dbc.DBConnectionMgr;
// /list.do=action.ListAction 설정
public class DeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("\"================자유게시판 딜리트 actionPost - DeleteAction클래스 시작");
		int TP_num = Integer.parseInt(request.getParameter("TP_num"));
		System.out.println("DeleteAction의 TP_num=>"+TP_num);	
		TDLPostDAO dbPro=new TDLPostDAO();
		dbPro.deleteArticle(TP_num);
		System.out.println("\"================자유게시판 딜리트 actionPost -DeleteAction클래스 끝");
		//3.공유해서 이동할 수 있도록 설정
		return "/index/TDL_POST/list.do"; //request.getAttribute("currentPage")=${currentPage}
	}

}
