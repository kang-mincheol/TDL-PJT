package tdl_likey;

public class TDLLikeyDTO {
	String TL_addr;//���ƿ��Ϸù�ȣ ->����Ϸù�ȣ + �������̵�
	String TL_like;//����Ϸù�ȣ
	String TL_id;//�������̵�
	int TL_PNUM;//�Խù���ȣ
	
	public int getTL_PNUM() {
		return TL_PNUM;
	}
	public void setTL_PNUM(int tL_PNUM) {
		TL_PNUM = tL_PNUM;
	}
	public String getTL_id() {
		return TL_id;
	}
	public void setTL_id(String tL_id) {
		TL_id = tL_id;
	}
	public String getTL_addr() {
		return TL_addr;
	}
	public void setTL_addr(String tL_addr) {
		TL_addr = tL_addr;
	}
	public String getTL_like() {
		return TL_like;
	}
	public void setTL_like(String tL_like) {
		TL_like = tL_like;
	}
	
}
