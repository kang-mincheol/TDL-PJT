package actionComment;

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
		System.out.println("actionComment DeleteAction ȣ��");
		String addr = request.getParameter("likeAddr");
		System.out.println("DeleteAction�� addr=>"+addr);	
		TDLCommentDAO dbProC=new TDLCommentDAO();
		dbProC.deleteAction(addr);	
		System.out.println("actionComment DeleteAction ����");
		//3.�����ؼ� �̵��� �� �ֵ��� ����
		return "/index/TDL_POST/list.do"; //request.getAttribute("currentPage")=${currentPage}
	}

}
