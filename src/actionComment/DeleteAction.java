package actionComment;

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
		System.out.println("actionComment DeleteAction 호출");
		String addr = request.getParameter("likeAddr");
		System.out.println("DeleteAction의 addr=>"+addr);	
		TDLCommentDAO dbProC=new TDLCommentDAO();
		dbProC.deleteAction(addr);	
		System.out.println("actionComment DeleteAction 종료");
		//3.공유해서 이동할 수 있도록 설정
		return "/index/TDL_POST/list.do"; //request.getAttribute("currentPage")=${currentPage}
	}

}
