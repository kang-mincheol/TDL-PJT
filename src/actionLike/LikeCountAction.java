package actionLike;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbc.*;
//추가
import tdl_Comment.*;
import tdl_likey.TDLLikeyDAO;


public class LikeCountAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("================좋아요 actionLike 클래스 시작");
		//한글처리	
		request.setCharacterEncoding("utf-8");
		
		String TPC_addr=request.getParameter("addr");
		TDLLikeyDAO TL = new TDLLikeyDAO();
		int TL_like = TL.likeCount(TPC_addr);
		
		request.setAttribute("TL_like",TL_like );
		return "/index/TDL_POST/content.jsp";
	
		
	}

}
