package actionLike;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbc.*;
//�߰�
import tdl_Comment.*;
import tdl_likey.TDLLikeyDAO;


public class LikeAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("================���ƿ� actionLike Ŭ���� ����");
		//�ѱ�ó��	
		request.setCharacterEncoding("utf-8");
		
		String TPC_addr = request.getParameter("addr");
		String TU_id = request.getParameter("TU_id");
		int TP_num=Integer.parseInt(request.getParameter("TP_num"));
		TDLLikeyDAO TL = new TDLLikeyDAO();
		int like = TL.like(TPC_addr,TU_id, TP_num);
		if(like == 1) {
			System.out.println("================���ƿ� ���");
			System.out.println("================���ƿ� actionLike Ŭ���� ��");
			return "/index/TDL_POST/list.do"; // "/index.jsp"
		}else {
			System.out.println("================���ƿ� ����");
			System.out.println("================���ƿ� actionLike Ŭ���� ��");
			return "/index/TDL_POST/list.do"; // "/index.jsp"
		}
	}

}
