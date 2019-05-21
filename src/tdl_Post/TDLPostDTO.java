package tdl_Post;

public class TDLPostDTO {

	private int TP_num;  // 자유게시판 글 번호
	private String TP_title; //자유게시판 제목
	private String TP_id; //유저 아이디
	private String TP_date;// 작성일
	private String TP_content; //글 내용
	private int TP_readcount;// 조회수
	

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
	    	   //문자열메서드->replaceAll(1.변경전문자열,2.변경후문자열) 
	    	   name=name.replaceAll("<", "&lt;");
	    	   name=name.replaceAll(">", " &gt;");
	    	   name=name.replaceAll("\r\n", "<br>");
	    	   name=name.replaceAll("\\(","&#40");
	    	   name=name.replaceAll("\\)","&#41");
	    	   name=name.replaceAll("\"","&quot");
	    	   name=name.replaceAll("\'","&apos");
	       }else{//name==null
	    	   return null;//입력을 하지 않았따면 더 이상 실행X
	       }  
		 return name;
	}
	
}
