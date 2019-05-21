package actionNotice;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbc.*;
import tdl_Comment.TDLCommentDAO;
//�߰�
import tdl_notice.*;

//content.jsp���� ó���� �ڹ��ڵ�κ��� ��� ó�����ִ� �׼�Ŭ����
public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("================�������� �ۺ��� actionPost - ContentActionŬ���� ����");
		int TN_num=Integer.parseInt(request.getParameter("TN_num"));
		System.out.println("TN_num �� �� -> "+TN_num);
		String pageNum=request.getParameter("pageNum");
		System.out.println("pageNum�� �� -> "+pageNum);
		TDLNoticeDAO dbPro=new TDLNoticeDAO();
		TDLNoticeDTO article=dbPro.getArticle(TN_num);
	
		System.out.println("content.do�� �Ű�����");
		
		//ModelAndView mav=new ModelAndView(); 
		//mav.addObject("TN_num",TN_num);
		request.setAttribute("TN_num", TN_num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);
	
	
			System.out.println("================�������� �ۺ��� actionPost - ContentActionŬ���� ��");	
		return "/index/TDL_NOTICE/content.jsp";
	}

}
