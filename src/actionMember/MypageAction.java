package actionMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tdl_member.*;
import dbc.*;

public class MypageAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String id = (String)session.getAttribute("TU_id");
		
		System.out.println("MypageAction id=>"+id);

		MemberDAO mdao = new MemberDAO();		
		MemberDTO mem = mdao.getMember(id);
		
		request.setAttribute("TU_name", mem.getTU_name());
		request.setAttribute("TU_gender", mem.getTU_gender());
		request.setAttribute("TU_email", mem.getTU_email());
		request.setAttribute("TU_phone", mem.getTU_phone());
		
		return "/index/TDL_MEMBER/mypage.jsp";
	}

}
