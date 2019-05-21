package actionMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tdl_member.*;
import dbc.*;

public class MemberDeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String id = (String)session.getAttribute("TU_id");
		String passwd = request.getParameter("TU_passwd");		
		
		System.out.println("MemberUpdateAction id=>"+id);
				
		MemberDAO mdao = new MemberDAO();		
		
		boolean check = mdao.deleteMember(id,passwd);
		
		if(check) {
			request.getSession().invalidate();
			return "/index/in/index.jsp";
		}else {
			request.setAttribute("check", "1");
			return "/index/TDL_MEMBER/MemberDeleteForm.jsp";			
		}
		
		
		
		
	}

}
