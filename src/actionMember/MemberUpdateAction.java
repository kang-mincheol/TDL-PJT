package actionMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tdl_member.*;
import dbc.*;

public class MemberUpdateAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String id = (String)session.getAttribute("TU_id");
		String passwd = request.getParameter("TU_passwd");
		String name = request.getParameter("TU_name");
		String gender = request.getParameter("TU_gender");
		String phone = request.getParameter("TU_phone");
		String email = request.getParameter("TU_email");
		
		System.out.println("MemberUpdateAction id=>"+id);
		
		MemberDTO mem = new MemberDTO();
		
		mem.setTU_id(id);
		mem.setTU_passwd(passwd);
		mem.setTU_name(name);
		mem.setTU_gender(gender);
		mem.setTU_phone(phone);
		mem.setTU_email(email);
		
		MemberDAO mdao = new MemberDAO();		
		
		mdao.updateMember(mem);
		return "/index/in/index.jsp";
	}

}
