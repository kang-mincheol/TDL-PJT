package actionMember;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tdl_member.*;
import dbc.*;
public class LoginAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		System.out.println("로그인액션 호출");
		HttpSession session = request.getSession();
		String tu_id = request.getParameter("TU_id");
		String tu_passwd = request.getParameter("TU_passwd");
		
		MemberDAO mdao = new MemberDAO();	
		
		boolean check = mdao.loginCheck(tu_id, tu_passwd);
		
		if(check){ 						
			session.setAttribute("TU_id", tu_id);			
			return "/index/in/index.jsp";
		}else{ //id가 없다는 경우
			request.setAttribute("check", "1");
			return "/index/TDL_MEMBER/login.jsp";
		}
		
	}
 
}
