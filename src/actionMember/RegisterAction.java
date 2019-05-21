package actionMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tdl_member.*;
import dbc.*;

public class RegisterAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("TU_id");
		String passwd = request.getParameter("TU_passwd");
		String name = request.getParameter("TU_name");
		String gender = request.getParameter("TU_gender");
		String phone = request.getParameter("TU_phone");
		String email = request.getParameter("TU_email");
		
		System.out.println("id=>"+id);
		
		MemberDTO mem = new MemberDTO();
		
		mem.setTU_id(id);
		mem.setTU_passwd(passwd);
		mem.setTU_name(name);
		mem.setTU_gender(gender);
		mem.setTU_phone(phone);
		mem.setTU_email(email);
		
		MemberDAO mdao = new MemberDAO();		
		
		mdao.register(mem);
		System.out.println("=======회원가입 성공=======");
		return "/index/TDL_MEMBER/complete.jsp";
	}

}
