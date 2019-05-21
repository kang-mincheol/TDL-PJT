package tdl_likey;

//DB사용
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbc.DBConnectionMgr; // DBC 불러오기
import tdl_Comment.TDLCommentDTO;

public class TDLLikeyDAO { // MemberDAO

	private DBConnectionMgr pool = null;// 1.선언

	// 공통으로 접속할 경우 필요한 멤버변수
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";// 실행시킬 SQL 구문

	// 2.생성자를 통해서 연결=>의존성
	public TDLLikeyDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
			System.out.println("pool : " + pool);
		} catch (Exception e) {
			System.out.println("TDLCommentDAO -> DB접속오류 : " + e);
		}
	}// 생성자

	public int likeCount(String TPC_addr) { // 해당 댓글의 좋아요
		int x = 0;
		try {
			con = pool.getConnection();
			System.out.println("likeCount 호출");
			sql = "select count(*) from TDL_LIKEY where TL_like=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, TPC_addr); // 좋아요는 댓글일련번호+id로 저장되어있어서
											// like 댓글일련번호% 로 검색하면 수가 나옴
			rs = pstmt.executeQuery();
			while (rs.next()) {// 보여주는 결과가 있다면
				x = rs.getInt(1);// 좋아요 누른사람들
			}
		} catch (Exception e) {
			System.out.println("TL_likeCount()메서드 에러유발 : " + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}

	// 추천 체크부분
	public int like(String TPC_addr, String TU_id,int TP_num) {
		System.out.println("like() 좋아요 체크 시작");
		System.out.println("TPC_addr 의 매개변수 값 =>" + TPC_addr);
		System.out.println("TU_id 의 매개변수 값 =>" + TU_id);
		System.out.println("TP_num의 매개변수 값 =>"+TP_num);
		System.out.println("LikeyDAO like() 호출!!");
		String like_id = TPC_addr + TU_id;
		try {// 좋아요는 댓글당 한 번씩 가능해서 이미 좋아요를 눌렀는지 확인
			con = pool.getConnection();
			sql = "INSERT INTO TDL_LIKEY(TL_addr,TL_like,TL_id,TL_PNUM) VALUES (?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, like_id);
			pstmt.setString(2, TPC_addr);
			pstmt.setString(3, TU_id);
			pstmt.setInt(4, TP_num);
			pstmt.executeUpdate();
			System.out.println("================TL_like() 좋아요 성공!!");
			System.out.println("================댓글에 좋아요 증가시키기");
			sql = "update TDL_POST_COMMENT set TPC_good=TPC_good+1 where TPC_addr=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, TPC_addr);
			pstmt.executeUpdate();
			System.out.println("댓글에 좋아요 증가성공");
			return 2; // 좋아요 추가 되었다면 반환값 2
		} catch (Exception e1) {
			System.out.println("좋아요 에러");
			System.out.println("TL_like 좋아요 취소");
			try {
				con = pool.getConnection();
				sql = "delete from TDL_likey where TL_addr=?"; // delete문으로 해당 좋아요를 삭제하기 -> 취소
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, like_id);
				int rs = pstmt.executeUpdate();
				System.out.println("================TL_like() 좋아요 취소 성공!!");
				System.out.println("================TPC_good 좋아요 취소시키기");
				sql = "update TDL_POST_COMMENT set TPC_good=TPC_good-1 where TPC_addr=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, TPC_addr);
				pstmt.executeUpdate();
				System.out.println("================TPC_good 좋아요 취소성공");
			} catch (Exception e) {
				System.out.println("좋아요 취소 에러");
				e.printStackTrace();
			}

			return 1; // 좋아요 취소가 되었다면 반환값 1
		}
	}

	// 좋아요 가능 여부
	public int likeCheck(String TU_id, String likeAddr) {
		String addr = likeAddr + TU_id;

		try {
			con = pool.getConnection();
			sql = "select TL_addr from TDL_LIKEY where TL_addr=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, addr);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return 1;// 이미 존재하는 회원
			} else {
				return -1;// 좋아요 가능한 회원
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0; // 데이터베이스 오류
	}

	public String TLLike(String TU_id, int TP_num) {//아이디 체크
		// id asc name desc
		System.out.println("articleLike() 에 넘어온 TU_id 값 =>"+TU_id);
		System.out.println("articleLike() 에 넘어온 TP_num 값 =>"+TP_num);
		String articleList = null;
		try {
			con = pool.getConnection();
			sql = "select count(*) from TDL_LIKEY where TL_id=? and TL_PNUM=?" ; 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, TU_id);
			pstmt.setInt(2, TP_num);
			rs = pstmt.executeQuery();
			System.out.println("==============여기까지 나옴222222222");
			if(rs.next() && rs.getInt(1)>0) {
				System.out.println("articleLike 만들 갯수"+rs.getInt(1));		
				sql = "select TL_like from TDL_LIKEY where TL_id=? and TL_PNUM=?" ; 
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, TU_id);
				pstmt.setInt(2, TP_num);
				rs = pstmt.executeQuery();
				System.out.println("TU_id =>"+TU_id);
				System.out.println("TP_num =>"+TP_num);
				System.out.println("-------------출력됨11111111111");				
				while(rs.next()){
					articleList+=rs.getString("TL_like");
				}
			}
		} catch (Exception e) {
			System.out.println("TDLLikeyDAO -> articleLike()메서드 에러유발" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
			if(articleList==null) {
				articleList="tm$이런아이디는없지";
			}
		}
		return articleList;
	}

	

}