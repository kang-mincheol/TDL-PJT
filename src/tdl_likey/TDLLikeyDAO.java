package tdl_likey;

//DB���
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbc.DBConnectionMgr; // DBC �ҷ�����
import tdl_Comment.TDLCommentDTO;

public class TDLLikeyDAO { // MemberDAO

	private DBConnectionMgr pool = null;// 1.����

	// �������� ������ ��� �ʿ��� �������
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";// �����ų SQL ����

	// 2.�����ڸ� ���ؼ� ����=>������
	public TDLLikeyDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
			System.out.println("pool : " + pool);
		} catch (Exception e) {
			System.out.println("TDLCommentDAO -> DB���ӿ��� : " + e);
		}
	}// ������

	public int likeCount(String TPC_addr) { // �ش� ����� ���ƿ�
		int x = 0;
		try {
			con = pool.getConnection();
			System.out.println("likeCount ȣ��");
			sql = "select count(*) from TDL_LIKEY where TL_like=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, TPC_addr); // ���ƿ�� ����Ϸù�ȣ+id�� ����Ǿ��־
											// like ����Ϸù�ȣ% �� �˻��ϸ� ���� ����
			rs = pstmt.executeQuery();
			while (rs.next()) {// �����ִ� ����� �ִٸ�
				x = rs.getInt(1);// ���ƿ� ���������
			}
		} catch (Exception e) {
			System.out.println("TL_likeCount()�޼��� �������� : " + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}

	// ��õ üũ�κ�
	public int like(String TPC_addr, String TU_id,int TP_num) {
		System.out.println("like() ���ƿ� üũ ����");
		System.out.println("TPC_addr �� �Ű����� �� =>" + TPC_addr);
		System.out.println("TU_id �� �Ű����� �� =>" + TU_id);
		System.out.println("TP_num�� �Ű����� �� =>"+TP_num);
		System.out.println("LikeyDAO like() ȣ��!!");
		String like_id = TPC_addr + TU_id;
		try {// ���ƿ�� ��۴� �� ���� �����ؼ� �̹� ���ƿ並 �������� Ȯ��
			con = pool.getConnection();
			sql = "INSERT INTO TDL_LIKEY(TL_addr,TL_like,TL_id,TL_PNUM) VALUES (?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, like_id);
			pstmt.setString(2, TPC_addr);
			pstmt.setString(3, TU_id);
			pstmt.setInt(4, TP_num);
			pstmt.executeUpdate();
			System.out.println("================TL_like() ���ƿ� ����!!");
			System.out.println("================��ۿ� ���ƿ� ������Ű��");
			sql = "update TDL_POST_COMMENT set TPC_good=TPC_good+1 where TPC_addr=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, TPC_addr);
			pstmt.executeUpdate();
			System.out.println("��ۿ� ���ƿ� ��������");
			return 2; // ���ƿ� �߰� �Ǿ��ٸ� ��ȯ�� 2
		} catch (Exception e1) {
			System.out.println("���ƿ� ����");
			System.out.println("TL_like ���ƿ� ���");
			try {
				con = pool.getConnection();
				sql = "delete from TDL_likey where TL_addr=?"; // delete������ �ش� ���ƿ並 �����ϱ� -> ���
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, like_id);
				int rs = pstmt.executeUpdate();
				System.out.println("================TL_like() ���ƿ� ��� ����!!");
				System.out.println("================TPC_good ���ƿ� ��ҽ�Ű��");
				sql = "update TDL_POST_COMMENT set TPC_good=TPC_good-1 where TPC_addr=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, TPC_addr);
				pstmt.executeUpdate();
				System.out.println("================TPC_good ���ƿ� ��Ҽ���");
			} catch (Exception e) {
				System.out.println("���ƿ� ��� ����");
				e.printStackTrace();
			}

			return 1; // ���ƿ� ��Ұ� �Ǿ��ٸ� ��ȯ�� 1
		}
	}

	// ���ƿ� ���� ����
	public int likeCheck(String TU_id, String likeAddr) {
		String addr = likeAddr + TU_id;

		try {
			con = pool.getConnection();
			sql = "select TL_addr from TDL_LIKEY where TL_addr=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, addr);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return 1;// �̹� �����ϴ� ȸ��
			} else {
				return -1;// ���ƿ� ������ ȸ��
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
		return 0; // �����ͺ��̽� ����
	}

	public String TLLike(String TU_id, int TP_num) {//���̵� üũ
		// id asc name desc
		System.out.println("articleLike() �� �Ѿ�� TU_id �� =>"+TU_id);
		System.out.println("articleLike() �� �Ѿ�� TP_num �� =>"+TP_num);
		String articleList = null;
		try {
			con = pool.getConnection();
			sql = "select count(*) from TDL_LIKEY where TL_id=? and TL_PNUM=?" ; 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, TU_id);
			pstmt.setInt(2, TP_num);
			rs = pstmt.executeQuery();
			System.out.println("==============������� ����222222222");
			if(rs.next() && rs.getInt(1)>0) {
				System.out.println("articleLike ���� ����"+rs.getInt(1));		
				sql = "select TL_like from TDL_LIKEY where TL_id=? and TL_PNUM=?" ; 
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, TU_id);
				pstmt.setInt(2, TP_num);
				rs = pstmt.executeQuery();
				System.out.println("TU_id =>"+TU_id);
				System.out.println("TP_num =>"+TP_num);
				System.out.println("-------------��µ�11111111111");				
				while(rs.next()){
					articleList+=rs.getString("TL_like");
				}
			}
		} catch (Exception e) {
			System.out.println("TDLLikeyDAO -> articleLike()�޼��� ��������" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
			if(articleList==null) {
				articleList="tm$�̷����̵�¾���";
			}
		}
		return articleList;
	}

	

}