package actionPost;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tdl_Comment.*;
import tdl_Post.*;
import java.util.*;
import dbc.*;
import dbc.DBConnectionMgr;
// /list.do=action.ListAction ����
public class DeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("\"================�����Խ��� ����Ʈ actionPost - DeleteActionŬ���� ����");
		int TP_num = Integer.parseInt(request.getParameter("TP_num"));
		System.out.println("DeleteAction�� TP_num=>"+TP_num);	
		TDLPostDAO dbPro=new TDLPostDAO();
		dbPro.deleteArticle(TP_num);
		System.out.println("\"================�����Խ��� ����Ʈ actionPost -DeleteActionŬ���� ��");
		//3.�����ؼ� �̵��� �� �ֵ��� ����
		return "/index/TDL_POST/list.do"; //request.getAttribute("currentPage")=${currentPage}
	}

}
