package tdl_master;

import java.sql.*;
import dbc.*;
import tdl_master.*;

public class MasterDAO {

	private DBConnectionMgr pool = null;// 1.����

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";// �����ų SQL���� ����
	
	public MasterDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
			System.out.println("pool=>" + pool);
		} catch (Exception e) {
			System.out.println("DB���ӿ���=>" + e);
		}
	}// ������
	
	
	//�α���üũ
		public boolean loginCheck(String id, String passwd) {
			
			boolean check=false;
			System.out.println("������ ���̵� =>"+id);
			System.out.println("������ ��� =>"+passwd);
			try {
				con=pool.getConnection();
				System.out.println("con=>"+con);
				sql="select * from TDL_MASTER where TM_id=? and TM_passwd=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1,id);
				pstmt.setString(2,passwd);
				rs=pstmt.executeQuery();
				check=rs.next();
			}catch(Exception e) {
				System.out.println("������ loginCheck()���� ��������=>"+e);
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return check;
		}
}
