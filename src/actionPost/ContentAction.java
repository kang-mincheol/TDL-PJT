package actionPost;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbc.*;
import tdl_Comment.TDLCommentDAO;
//�߰�
import tdl_Post.*;
import tdl_likey.*;

//content.jsp���� ó���� �ڹ��ڵ�κ��� ��� ó�����ִ� �׼�Ŭ����
public class ContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("================�����Խ��� �ۺ��� actionPost - ContentActionŬ���� ����");
		int TP_num = Integer.parseInt(request.getParameter("TP_num"));
		System.out.println("TP_num �� �� -> " + TP_num);
		String pageNum = request.getParameter("pageNum");
		System.out.println("pageNum�� �� -> " + pageNum);
		TDLPostDAO dbPro = new TDLPostDAO();
		TDLPostDTO article = dbPro.getArticle(TP_num);

		System.out.println("content.do�� �Ű�����");

		// ModelAndView mav=new ModelAndView();
		// mav.addObject("TP_num",TP_num);
		request.setAttribute("TP_num", TP_num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("article", article);

		System.out.println("================�����Խ��� ��۳��� ����");
		// ---------------------------������ʹ� ���
		// ---------------------------------------------------------
		String pageNumC = request.getParameter("pageNumC");

		int countC = 0;// �ѷ��ڵ��
		int numberC = 0;// beginPerPage -> ���������� �����ϴ� �� ó���� ������ �Խù� ��ȣ
		List articleListC = null;// ȭ�鿡 ����� ���ڵ带 ������ ����
		String articleLike = null;
		TDLCommentDAO dbProC = new TDLCommentDAO();
		TDLLikeyDAO dbProL = new TDLLikeyDAO();
		int TPC_num = dbProC.commentNum(TP_num);
		countC = dbProC.getArticleCommentCount(TPC_num);// sql������ ���� �޶�����.
		System.out.println("TPC_num �� �� => " + TPC_num);
		System.out.println("���� �˻��� ���ڵ��(count) : " + countC);
		Hashtable<String, Integer> pgListC = dbProC.pageList(pageNumC, countC);
		String TU_id = request.getParameter("TU_id");//���� ���̵� �޾ƿ���
		System.out.println(pgListC.get("startRow") + "," + pgListC.get("endRow"));
		articleListC = dbProC.getTDLArticles(pgListC.get("pageSize"), TPC_num);// ù��° ���ڵ��ȣ,�ҷ��ð���
		articleLike = dbProL.TLLike(TU_id, TP_num);
		
		System.out.println("articleLike�����Ȯ��=>"+articleLike);
		
		// 2.request��ü�� ����
		request.setAttribute("articleLike", articleLike);
		request.setAttribute("countC", countC); // ����� ����� ���� -> �̰� ������ ����Ϸù�ȣ ���� �� ����
		request.setAttribute("TPC_num", TPC_num);
		request.setAttribute("pgListC", pgListC);// ����¡ó�� 10������
		request.setAttribute("articleListC", articleListC);// ${articleList}
		System.out.println("actionCommentC - ListAction ȣ��");
		System.out.println("================�����Խ��� �ۺ��� actionPost - ContentActionŬ���� ��");

		return "/index/TDL_POST/content.jsp";
	}

}
