package actionNotice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbc.*;

public class WriteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		System.out.println("================�������� �۾�����  actionNotice - WriteFormActionŬ���� ����");
		//jsp���� ó���ؾ��� �ڹ��ڵ带 ���ó�����ִ� ���� -> ��� ���� -> jsp ����
		//list.jsp(�۾���)->�űԱ�, content.jsp(�ۻ󼼺���)-> �۾���-> �亯��
   		int TN_num=0;//writePro.jsp
   		
   		//content.jsp���� �Ű������� ����
   		if(request.getParameter("TN_num")!=null){//��� (����,0�� �ƴϴ�)
   			TN_num=Integer.parseInt(request.getParameter("TN_num"));//"3"->3
   			System.out.println("WriteFormAction if��");
   			System.out.println("content.jsp���� �Ѿ�� �Ű����� Ȯ��");
   			System.out.println("TN_num�� null�� �ƴҶ� : "+TN_num);
   		}
   		System.out.println("�������� �۾��� TN_num -> "+TN_num);
   		System.out.println("WriteFormAction ȣ��2");
   		//2.������(��������,�Ű�����,�ż����� ��������)->request������ ����
   			request.setAttribute("TN_num", TN_num); //request.getAttribute("num")->${num}
   			System.out.println("================�������� �۾��� �� actionNotice - WriteFormActionŬ���� ��");
			return "/index/TDL_NOTICE/writeForm.jsp";
	}

}
