package actionNotice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tdl_Comment.*;
import tdl_notice.*;
import java.util.*;
import dbc.*;
import dbc.DBConnectionMgr;
// /list.do=action.ListAction ����
public class DeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("================�������� ����Ʈ actionPost - DeleteActionŬ���� ����");
		int TN_num = Integer.parseInt(request.getParameter("TN_num"));
		System.out.println("DeleteAction�� TN_num=>"+TN_num);	
		TDLNoticeDAO dbPro=new TDLNoticeDAO();
		dbPro.deleteArticle(TN_num);
		System.out.println("================�������� ����Ʈ actionPost -DeleteActionŬ���� ��");
		//3.�����ؼ� �̵��� �� �ֵ��� ����
		return "/index/TDL_NOTICE/list.do"; //request.getAttribute("currentPage")=${currentPage}
	}

}
  