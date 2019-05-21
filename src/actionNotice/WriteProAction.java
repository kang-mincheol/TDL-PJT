package actionNotice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbc.*;
//�߰�
import tdl_notice.*;


public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("================�������� �۾��� ���� actionNotice - WriteProActionŬ���� ����");
		//�ѱ�ó��	
		request.setCharacterEncoding("utf-8");
		
		//DTO,DAO ��ü �ʿ�
		TDLNoticeDTO article=new TDLNoticeDTO();
		article.setTN_num(Integer.parseInt(request.getParameter("TN_num")));
		article.setTN_title(request.getParameter("TN_title"));
		article.setTN_content(request.getParameter("TN_content"));
		//==========================================================
		System.out.println("�۾���׼� �ѹ���"+Integer.parseInt(request.getParameter("TN_num")));

		TDLNoticeDAO dbPro=new TDLNoticeDAO();
		dbPro.insertArticle(article);
		System.out.println("================�������� �۾��� ���� actionNotice - WriteProActionŬ���� ����");
		//response.sendRedirect("http://localhost:8090/JspBoard2/list.do");
		return "/index/TDL_NOTICE/writePro.jsp"; // "/index.jsp"
	}

}
