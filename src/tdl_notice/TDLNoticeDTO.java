package tdl_notice;

public class TDLNoticeDTO {

	private int TN_num;  // �����Խ��� �� ��ȣ
	private String TN_title; //�����Խ��� ����
	private String TN_id; //���� ���̵�
	private String TN_date;// �ۼ���
	private String TN_content; //�� ����
	private int TN_readcount;// ��ȸ��
	
	public int getTN_num() {
		return TN_num;
	}
	public void setTN_num(int tN_num) {
		TN_num = tN_num;
	}
	public String getTN_title() {
		return TN_title;
	}
	public void setTN_title(String tN_title) {
		tN_title =convert(tN_title);
		TN_title = tN_title;
	}
	public String getTN_id() {
		return TN_id;
	}
	public void setTN_id(String tN_id) {
		TN_id = tN_id;
	}
	public String getTN_date() {
		return TN_date;
	}
	public void setTN_date(String tN_date) {
		TN_date = tN_date;
	}
	public String getTN_content() {
		return TN_content;
	}
	public void setTN_content(String tN_content) {
		tN_content =convert(tN_content);
		TN_content = tN_content;
	}
	public int getTN_readcount() {
		return TN_readcount;
	}
	public void setTN_readcount(int tN_readcount) {
		TN_readcount = tN_readcount;
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
