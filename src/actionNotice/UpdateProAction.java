package actionNotice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbc.*;
//�߰�
import tdl_notice.*;

// /updatePro.do?num=?&pageNum=1
public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("================�������� ������Ʈ ���� actionPost - UpdateProActionŬ���� ����");
		//�ѱ�ó��	
		request.setCharacterEncoding("utf-8");
		String pageNum=request.getParameter("pageNum");
		
		TDLNoticeDTO article=new TDLNoticeDTO();
		System.out.println("UpdateProAction ������� ����");
		article.setTN_num(Integer.parseInt(request.getParameter("TN_num")));
		article.setTN_title(request.getParameter("TN_title"));
		article.setTN_content(request.getParameter("TN_content"));

		TDLNoticeDAO dbPro=new TDLNoticeDAO();
		int check=dbPro.updateArticle(article);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		System.out.println("================�������� ������Ʈ ���� actionPost - UpdateProActionŬ���� ��");
		return "/index/TDL_NOTICE/updatePro.jsp"; // "/index.jsp"
	}

}
