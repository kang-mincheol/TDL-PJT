package tdl_master;

import java.sql.*;
import dbc.*;
import tdl_master.*;

public class MasterDAO {

	private DBConnectionMgr pool = null;// 1.선언

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";// 실행시킬 SQL구문 저장
	
	public MasterDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
			System.out.println("pool=>" + pool);
		} catch (Exception e) {
			System.out.println("DB접속오류=>" + e);
		}
	}// 생성자
	
	
	//로그인체크
		public boolean loginCheck(String id, String passwd) {
			
			boolean check=false;
			System.out.println("관리자 아이디 =>"+id);
			System.out.println("관리자 비번 =>"+passwd);
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
				System.out.println("관리자 loginCheck()실행 에러유발=>"+e);
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return check;
		}
}
