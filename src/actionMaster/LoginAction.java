package actionMaster;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tdl_master.*;
import dbc.*;
public class LoginAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		System.out.println("������ �α��ξ׼� ȣ��");
		HttpSession session = request.getSession();
		String tm_id = request.getParameter("TU_id");
		String tm_passwd = request.getParameter("TU_passwd");
		
		MasterDAO mdao = new MasterDAO();	
		
		boolean check = mdao.loginCheck(tm_id, tm_passwd);
		
		if(check){ 
			System.out.println("������ �α��� ����");
			session.setAttribute("TM_id", tm_id);			
			return "/index/in/index.jsp";
		}else{ //id�� ���ٴ� ���
			System.out.println("������ �α��� ����");
			request.setAttribute("check", "1");
			return "/index/TDL_MEMBER/login.jsp";
		}
		
	}
 
}
