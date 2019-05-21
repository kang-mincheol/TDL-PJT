package tdl_Post;

public class TDLPostDTO {

	private int TP_num;  // �����Խ��� �� ��ȣ
	private String TP_title; //�����Խ��� ����
	private String TP_id; //���� ���̵�
	private String TP_date;// �ۼ���
	private String TP_content; //�� ����
	private int TP_readcount;// ��ȸ��
	

	public int getTP_readcount() {
		return TP_readcount;
	}
	public void setTP_readcount(int tP_readcount) {
		TP_readcount = tP_readcount;
	}
	public int getTP_num() {
		return TP_num;
	} 
	public void setTP_num(int tP_num) {
		TP_num = tP_num;
	}
	public String getTP_title() {
		return TP_title;
	}
	public void setTP_title(String tP_title) {
		tP_title=convert(tP_title);
		TP_title = tP_title;
	}
	public String getTP_id() {
		return TP_id;
	}
	public void setTP_id(String tP_id) {
		TP_id = tP_id;
	}
	public String getTP_date() {
		return TP_date;
	}
	public void setTP_date(String tP_date) {
		TP_date = tP_date;
	}
	public String getTP_content() {
		return TP_content;
	}
	public void setTP_content(String tP_content) {
		tP_content=convert(tP_content);
		TP_content = tP_content;
	}
	
	private String convert(String name) {
		 if(name!=null){
	    	   //���ڿ��޼���->replaceAll(1.���������ڿ�,2.�����Ĺ��ڿ�) 
	    	   name=name.replaceAll("<", "&lt;");
	    	   name=name.replaceAll(">", " &gt;");
	    	   name=name.replaceAll("\r\n", "<br>");
	    	   name=name.replaceAll("\\(","&#40");
	    	   name=name.replaceAll("\\)","&#41");
	    	   name=name.replaceAll("\"","&quot");
	    	   name=name.replaceAll("\'","&apos");
	       }else{//name==null
	    	   return null;//�Է��� ���� �ʾҵ��� �� �̻� ����X
	       }  
		 return name;
	}
	
}