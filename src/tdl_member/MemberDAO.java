package tdl_member;

//DBConnectionMgr(DB접속,관리), BoardDTO(매개변수,반환형)
import java.sql.*;//DB사용
import java.util.*;//ArrayList,List을 사용하기 위해서

import tdl_member.MemberDTO;
import dbc.*;
public class MemberDAO { // MemberDAO

	private DBConnectionMgr pool = null;// 1.선언

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";// 실행시킬 SQL구문 저장

	public MemberDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
			System.out.println("pool=>" + pool);
		} catch (Exception e) {
			System.out.println("DB접속오류=>" + e);
		}
	}// 생성자
	
	//ID중복체크
	public boolean checkId(String id) {
		boolean check=false;  
		try {
			con=pool.getConnection();
			sql="select TU_id from just_log where TU_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			check=rs.next();//데이터가 존재->true,없으면->false
		}catch(Exception e) {
			System.out.println("loginCheck()실행 에러유발=>"+e);
		}finally {//3.DB연결해제
			pool.freeConnection(con,pstmt,rs);
		}
		return check;
	}	
	
	//회원가입
	public void register(MemberDTO mem) {
		try {
			con = pool.getConnection();
			sql="insert into TDL_USER values(?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			System.out.println(mem.getTU_id());
			pstmt.setString(1,mem.getTU_id());
			pstmt.setString(2,mem.getTU_passwd());
			pstmt.setString(3,mem.getTU_name());
			pstmt.setString(4,mem.getTU_gender());
			pstmt.setString(5,mem.getTU_phone());
			pstmt.setString(6,mem.getTU_email());
			
			int register = pstmt.executeUpdate();
			System.out.println("회원가입 성공유무=>"+register);
			if(register == 1) {
				sql="insert into just_log values(?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, mem.getTU_id());
				pstmt.setString(2,mem.getTU_passwd());
				int log_register = pstmt.executeUpdate();
				System.out.println("로그인전용 테이블 회원가입 성공유무=>"+log_register);
			}
		}catch(Exception e) {
			System.out.println("register 회원가입 메서드 오류!=>"+e);
		}finally {
			pool.freeConnection(con,pstmt);
		}
	}
	
	//로그인체크
	public boolean loginCheck(String id, String passwd) {
		
		boolean check=false;
		
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);
			sql="select TU_id,TU_passwd from just_log where TU_id=? and TU_passwd=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,passwd);
			rs=pstmt.executeQuery();
			check=rs.next();
		}catch(Exception e) {
			System.out.println("loginCheck()실행 에러유발=>"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return check;
	}
	
	//회원정보 가져오기
	public MemberDTO getMember(String TU_id) {
		
		MemberDTO mem = null; 		
		try {
			con=pool.getConnection();
			sql="select * from tdl_user where TU_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, TU_id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {			
				mem = new MemberDTO();
				
				mem.setTU_id(rs.getString("TU_id")); 
				mem.setTU_passwd(rs.getString("TU_passwd"));
				mem.setTU_name(rs.getString("TU_name"));
				mem.setTU_phone(rs.getString("TU_phone"));				
				mem.setTU_email(rs.getString("TU_email"));
				mem.setTU_gender(rs.getString("TU_gender"));					
			}
		}catch(Exception e) {
			System.out.println("getMember()실행오류=>"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return mem;
	}
	
	//회원수정
	public void updateMember(MemberDTO mem) {
		try {
			con=pool.getConnection();
			con.setAutoCommit(false);

			sql="update tdl_user set TU_passwd=?, TU_name=?, TU_email=?, TU_phone=? where TU_id=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,mem.getTU_passwd());
			pstmt.setString(2,mem.getTU_name());
			pstmt.setString(3,mem.getTU_email());
			pstmt.setString(4,mem.getTU_phone());			
			pstmt.setString(5,mem.getTU_id());
						
			int update=pstmt.executeUpdate();
			System.out.println("update(데이터 입력유무)=>"+update);
			con.commit();			
		}catch(Exception e) {
			System.out.println("updateMember()실행 에러유발=>"+e);
		}finally {
			pool.freeConnection(con,pstmt);
		}
	}
	
	//회원탈퇴
	public boolean deleteMember(String id,String passwd) {
		String dbpasswd="";
		boolean check=false;
		try {
			con=pool.getConnection();
			con.setAutoCommit(false);
			pstmt=con.prepareStatement("select TU_passwd from tdl_user where TU_id=?");
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dbpasswd=rs.getString("TU_passwd");//rs.getString(1)
				System.out.println("dbpasswd=>"+dbpasswd);
				System.out.println("passwd=>"+passwd);
				if(dbpasswd.equals(passwd)) {
					pstmt=con.prepareStatement("delete from tdl_user where TU_id=?");
					pstmt.setString(1, id);
					int delete=pstmt.executeUpdate();
					System.out.println("delete(회원탈퇴 성공유무)=>"+delete);
					con.commit();		
					check = true;
				}else {
					check = false;
				}
			}			
		}catch(Exception e) {
			System.out.println("deleteMember() 호출됨=>"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}		
		return check;
	}
}






