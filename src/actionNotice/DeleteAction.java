package actionNotice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tdl_Comment.*;
import tdl_notice.*;
import java.util.*;
import dbc.*;
import dbc.DBConnectionMgr;
// /list.do=action.ListAction 설정
public class DeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("================공지사항 딜리트 actionPost - DeleteAction클래스 시작");
		int TN_num = Integer.parseInt(request.getParameter("TN_num"));
		System.out.println("DeleteAction의 TN_num=>"+TN_num);	
		TDLNoticeDAO dbPro=new TDLNoticeDAO();
		dbPro.deleteArticle(TN_num);
		System.out.println("================공지사항 딜리트 actionPost -DeleteAction클래스 끝");
		//3.공유해서 이동할 수 있도록 설정
		return "/index/TDL_NOTICE/list.do"; //request.getAttribute("currentPage")=${currentPage}
	}

}
  