package tdl_notice;

//DBConnectionMgr(DB����,����),BoardDTO(�Ű�����,��ȯ��)
import java.sql.*;//DB���
import java.util.*;//ArrayList,List�� ����ϱ� ���ؼ�

import tdl_notice.TDLNoticeDTO;
import dbc.DBConnectionMgr;
 
public class TDLNoticeDAO {

	private DBConnectionMgr pool=null;//1.����
	
	//�������� ������ ��� �ʿ��� �������
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private String sql="";//�����ų SQL ����
	
	//2.�����ڸ� ���ؼ� ����=>������
	public TDLNoticeDAO() { // ������
		try {
			
			System.out.println("================TDLNotice - TDLNoticeDAO() ����");
			pool=DBConnectionMgr.getInstance();
			System.out.println("pool : "+pool);
		}catch(Exception e) {
			System.out.println("DB���ӿ��� : "+e);
		}
		System.out.println("================ TDLNotice - TDLNoticeDAO() ��");
	}

	//1.����¡ ó���� ���ؼ� ��ü ���ڵ���� ���ؿ;� �Ѵ�.		
	public int getArticleCount() {
		System.out.println("================ TDLNotice  - getArticleCount() ����");
		int x=0;//���ڵ尹��
		
		try {
			con=pool.getConnection();
			System.out.println("con : "+con);
			sql="select count(*) from TDL_Notice";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {//�����ִ� ����� �ִٸ�
				x=rs.getInt(1);//������ = rs.get�ڷ���(�ʵ�� �Ǵ� �ε�����ȣ)
									 //�ʵ���� �ƴϱ⶧���� select ~ from ���̿� ������ ����
			}
			System.out.println("TDLNoticeDAO ī��Ʈ ��¼���");
		}catch(Exception e) {
			System.out.println("getArticleCount()�޼��� �������� : "+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		System.out.println("================ TDLNotice  - getArticleCount() ��");
		return x;
	}

	// �۸�Ϻ��⿡ ���� �޼��� ����->���ڵ尡 �Ѱ��̻�=>�� �������� 10���� ��� �����ش�.
	public List getArticles(int start, int end) {// getMemberArticle(int start,int end)
		// id asc name desc
		System.out.println("================ TDLNotice  - getArticles() ����");
		List articleList = null;// ArrayList articleList=null;

		try {
			con = pool.getConnection();
//�׷��ȣ�� ���� �ֽ��� �����߽����� �����ϵ�,���࿡ level�� ���� ��쿡��
//step������ ���������� ���ؼ� ���° ���ڵ� ��ȣ�� �����ؼ� �����϶�.
			sql = "select * from TDL_Notice order by TN_num limit ?,?";// 1,10
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			;// mysql�� ���ڵ������ ���������� 0���� ����
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
//������ ���ڵ�ܿ� �߰��� ���ڵ带 ÷���ؼ� �ٰ��� �����ִ� ���� -> ��������(����) 
			if (rs.next()) {// ���ڵ尡 �����Ѵٸ� (�ּҸ��� 1��)
//articleList=new List(); X
//����) articleList=new �ڽ�Ŭ������();
				articleList = new ArrayList(end);// 10 -> end ������ŭ �����͸� ���� ���������϶�
				do {
					
					TDLNoticeDTO article=makeArticleFromResult(); //�ߺ� �޼��� ���� �ҷ�����
					/*
					TDLNoticeDTO article = new TDLNoticeDTO();// MemberDTO mem=new MemberDTO();
					article.setTN_num(rs.getInt("num"));//�����Խ��� ��ȣ
					article.setTN_title(rs.getString("TN_title"));//�����Խ��� ����
					article.setTN_id(rs.getString("TN_id"));// �������̵�
					article.setTN_date(rs.getString("TN_date"));// �ۼ���
					article.setTN_content(rs.getString("TN_content")); //�����Խ��� �� ����
					*/
//�߰� 
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("TDLNoticeDAO getArticles()�޼��� ��������" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		System.out.println("================ TDLNotice  - getArticles() ��");
		return articleList;
	}

	
	//����¡ ó���� ���������ִ� �޼����ۼ�(ListAction Ŭ�������� �ҷ�����)
	//1.ȭ�鿡 �����ִ� ������ ��ȣ 2.ȭ�鿡 ����� ���ڵ尹��
	public Hashtable pageList(String pageNum,int count) {
		System.out.println("================ TDLNotice  - pageList() ����");
		//1.����¡ ó������� ������ hashtable��ü�� ����
		Hashtable<String,Integer> pgList = new Hashtable<String,Integer>();
		
		
		//1.Jsp���� ����ߴ� �ڹ��� �ڵ带 ���⿡�� ����
		int pageSize=10;//numperpage; -> �������� �����ִ� �Խù���(=���ڵ��) -> 20���̻�
		int blockSize=2;//pagePerBlock -> ���� �����ִ� ��������
		
		//����¡ó���� �ش��ϴ� ȯ�漳���� ������
		//�Խ����� �� ó�� �����Ű�� ������ 1���������� ���
		if(pageNum==null){
			pageNum="1";//default(������ 1�������� �������� �ʾƵ� ������� �Ǳ⶧����)
		}
		int currentPage=Integer.parseInt(pageNum);//���������� -> nowPage
		//���۷��ڵ��ȣ -> limit ?,?
		//					(1-1)*10+1=1,(2-1)*10+1=11,(3-1)*10+1=21
		int startRow=(currentPage-1)*pageSize+1;
		int endRow=currentPage*pageSize;//1*10=10,2*10=20,3*10=30
		int number=0;//beginPerPage->�������� �����ϴ� �� ó���� ������ �Խù� ��ȣ
		System.out.println("Hashtable pageList() ���� ���ڵ��(count) -> "+count);
		number=count-(currentPage-1)*pageSize;
		System.out.println("Hashtable pageList() �������� number : "+number);
		 
		//�� ��������, ����,���������� ���
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
	       //2.���������� 
	       //���� �������� ���->10->10���,3->3�� ���
	       int startPage=0;//1,2,3,,,,10 [������ 10],11,12,,,,,20
	       if(currentPage%blockSize!=0){ //1~9,11~19,21~29,,,
	    	   startPage=currentPage/blockSize*blockSize+1;
	       }else{ //10%10 (10,20,30,40~)
	    	   //             ((10/10)-1)*10+1=1
	    	  startPage=((currentPage/blockSize)-1)*blockSize+1; 
	       }
	       int endPage=startPage+blockSize-1;//1+10-1=10
	       System.out.println("startPage="+startPage+",endPage=>"+endPage);
	       //������ �����ؼ� ��ũ�ɾ ���
	       if(endPage > pageCount) endPage=pageCount;//������������=����������
	      
	       //����¡ ó���� ���� ����� -> Hashtable -> ListAction ���� -> request -> list.jsp���
	       //~DAO -> �������� ������ ���õ� �ڵ�-> �׼�Ŭ������ ���� -> view(jsp)�� �������
	       pgList.put("pageSize", pageSize);// <-> pgList.get("pageSize")
	       pgList.put("blockSize", blockSize);
	       pgList.put("currentPage", currentPage);
	       pgList.put("startRow", startRow);
	       pgList.put("endRow", endRow);
	       pgList.put("count", count);
	       pgList.put("number", number);
	       pgList.put("startPage", startPage);
	       pgList.put("endPage", endPage);
	       pgList.put("pageCount", pageCount);
	       System.out.println("================ TDLNotice  - pageList() ��");
		return pgList;
	}
	
	
///////�߰�(1) �˻��о߿� ���� �˻�� �Է������� ���ǿ� �����ϴ� ���ڵ尹���� �ʿ�
	
	public int getArticleSearchCount(String search,String searchtext) { 
		System.out.println("================ TDLNotice  - getArticleSearchCount() ����");
		int x=0;//���ڵ尹��
		
		try {
			con=pool.getConnection();
			System.out.println("con : "+con);
			//�˻�� �Է����� �������(�˻��о� ����x)
			
			if(search == null  || search=="") {
				sql="select count(*) from TDL_Notice";	
			}else { // �˻��о�(����,�ۼ���,����+����)
				if(search.contentEquals("subject_content")) {
					sql="select count(*) from TDL_Notice where TN_title like '%"+searchtext
					+"%' or TN_content like '%"+searchtext+"%'";
				}else { //����, �ۼ��� -> �Ű������� �̿��ؼ� �ϳ��� sql ����
					sql="select count(*) from TDL_Notice where "+search+" like '%"+searchtext+"%' ";
				}
			}
			System.out.println("getArticleSearchCount �� �˻��� sql=>"+sql);
			//----------------------------------------------------------------------
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {//�����ִ� ����� �ִٸ�
				x=rs.getInt(1);//������ = rs.get�ڷ���(�ʵ�� �Ǵ� �ε�����ȣ)
									 //�ʵ���� �ƴϱ⶧���� select ~ from ���̿� ������ ����
			}
		}catch(Exception e) {
			System.out.println("getArticleSearchCount()�޼��� �������� : "+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		System.out.println("================ TDLNotice  - getArticleSearchCount() ��");
		return x;
	}
	
	public List getTDLArticles(int start, int end,String search,String searchtext) {// getMemberArticle(int start,int end)
		// id asc name desc 
		System.out.println("================ TDLNotice  - getTDLArticles() ����");
		List articleList = null;// ArrayList articleList=null;

		try {
			con = pool.getConnection();
			//-----------------------------------------------------------------------------
			if(search == null || search =="") {
				sql = "select * from TDL_Notice order by TN_num desc limit ?,?";// 1,10
			}else {
				if(search.contentEquals("subject_content")) {
					sql="select * from TDL_Notice where TN_title like '%"+searchtext
					+"%' or TN_content like '%"+searchtext
					+"%' order by TN_num desc limit ?,?";
				}else { //����, �ۼ��� -> �Ű������� �̿��ؼ� �ϳ��� sql ����
					sql="select * from TDL_Notice where "+search+" like '%"+searchtext
					+"%' order by TN_num desc limit ?,?";
				}
			}
			System.out.println("TDLNotice getTDLArticles()�� sql => "+sql);
			//-----------------------------------------------------------------------------
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			;// mysql�� ���ڵ������ ���������� 0���� ����
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
//������ ���ڵ�ܿ� �߰��� ���ڵ带 ÷���ؼ� �ٰ��� �����ִ� ���� -> ��������(����) 
			if (rs.next()) {// ���ڵ尡 �����Ѵٸ� (�ּҸ��� 1��)
//articleList=new List(); X
//����) articleList=new �ڽ�Ŭ������();
				articleList = new ArrayList(end);// 10 -> end ������ŭ �����͸� ���� ���������϶�
				do {
					TDLNoticeDTO article = makeArticleFromResult();
					
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getArticles()�޼��� ��������" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		System.out.println("================ TDLNotice  - getTDLArticles() ��");
		return articleList;
	}
	
	
	// �ۼ��� ���ϱ� ---------------------------------------------------------------------------------------------------------
	public String getTN_date() {
		System.out.println("================ TDLNotice  - getTN_date() ����");
		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch(Exception e) {
			System.out.print("TDLNoticeDAO getTN_date() ��������  ->");
			e.printStackTrace();
		}
		System.out.println("================ TDLNotice  - getTN_date() ��");
		return ""; // �����ͺ��̽� ����
	}
	
	
	//---------------�Խ����� �۾��� �� �� �亯�ޱ�-------------------------------------------------------------------------
		//insert into board values(?,,,
		public void insertArticle(TDLNoticeDTO article) {//~(MemberDTO mem)
			System.out.println("================ TDLNotice  - insertArticle() ����");
			//1.article -> �űԱ����� �亯������ ����
			int TN_num=article.getTN_num();//0(�űԱ�����) 0�� �ƴ� ���(�亯��)
			//���̺� �Է��� �Խù� ��ȣ�� ������ ����
			int number=0;
			System.out.println("insertArticle �޼����� ������ num : "+TN_num);
			
			try {
				con=pool.getConnection();
				sql="select max(TN_num) from TDL_Notice";//�ִ밪+1=���� ������ �Խù���ȣ
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					number=rs.getInt(1)+1;
				}else {//�� ó���� ���ڵ尡 �Ѱ��� ���ٸ� 
					number=1;
					
				}
				System.out.println("insertArticle()�� ��¥ �� ->"+getTN_date());
				String name ="test";
				//12�� -> num,reg_date,reaconut(���� -> default -> sysdate.now() <- mysql
				sql="insert into TDL_Notice(TN_num,TN_title,TN_id,TN_date,TN_content)";
				sql+="values(?,?,?,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,number);
				pstmt.setString(2,article.getTN_title());//�������� Setter Method�� �޸𸮿� ����
				pstmt.setString(3,name);
				pstmt.setString(4,getTN_date());//�ۼ���¥
				pstmt.setString(5,article.getTN_content());//�۳���
				
				int insert=pstmt.executeUpdate();
				System.out.println("TDLNoticeDAO �Խ����� �۾��� ��������(insert) : "+insert );
			}catch(Exception e) {
				System.out.println("TDLNoticeDAO insertArticle()�޼��� �������� : "+e);
				e.printStackTrace();
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}//finally
			System.out.println("================ TDLNotice  - insertArticle() ��");
		}
	
	//------------������Ʈ-----------------------
		
		public TDLNoticeDTO getArticle(int TN_num) {
			System.out.println("================ TDLNotice  - TDLNoticeDTO getArticle() ����");
			TDLNoticeDTO article=null;
			
			try {
				con=pool.getConnection();
				sql="update TDL_Notice set TN_readcount = TN_readcount+1 where TN_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,TN_num);
				int update=pstmt.executeUpdate();
				System.out.println("TDLNotice - getArticle ��ȸ�� ��������(update) : "+update);
				
				con=pool.getConnection();
				
				sql="select * from TDL_Notice where TN_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,TN_num);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {//���ڵ尡 �����Ѵٸ�
					article=makeArticleFromResult();
					
				}
			}catch(Exception e) {
				System.out.println(" getArticle()�޼��� ��������"+e);
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
			System.out.println("================ TDLNotice  - TDLNoticeDTO getArticle() ��");
			return article;
		}
	

	
	
	//�Խ����� �ۼ����ϱ�   ������Ʈ
	
		//������Ʈ �� �����Խ��� �Խù��� �����ֱ�==============================
		public TDLNoticeDTO updateGetArticle(int TN_num) { //updateForm.jsp���� ���
			System.out.println("================ TDLNotice  - TDLNoticeDTO updateGetArticle() ����");
			TDLNoticeDTO article=null;
			
			try {
				con=pool.getConnection();
				sql="select * from TDL_Notice where TN_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,TN_num);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {//���ڵ尡 �����Ѵٸ�
					article=makeArticleFromResult();
				
				}
			}catch(Exception e) {
				System.out.println("TDLNoticeDAO updateGetArticle()�޼��� ��������"+e);
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
			System.out.println("================ TDLNotice  - TDLNoticeDTO updateGetArticle() ��");
			return article;
		}
	
	//  �����Խ��� ���� �������� �����ϴ� ���� ============================
		public int updateArticle(TDLNoticeDTO article) {
			System.out.println("================ TDLNotice  -  updateArticle() ����");
			int x=-1;//�Խù��� ������������
			
			try {				

				System.out.println("������Ʈ��ƼŬupdateArticle() "+article.getTN_title());
				System.out.println("������Ʈ��ƼŬupdateArticle() "+article.getTN_content());
				System.out.println("������Ʈ��ƼŬupdateArticle() "+article.getTN_num());
				
				con=pool.getConnection();
				sql="update TDL_Notice set TN_title=?,TN_content=? where TN_num=?";
				
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, article.getTN_title());
				pstmt.setString(2, article.getTN_content());
				pstmt.setInt(3, article.getTN_num());
				
				int update=pstmt.executeUpdate();
				System.out.println("TDLNoticeDAO �Խ����� �ۼ��� ��������(update) : "+update);//1����
				x=1;					
				
			}catch(Exception e) {
				System.out.println("TDLNoticeDAO updateArticle()�޼��� �������� : "+e);
			}finally {
				pool.freeConnection(con,pstmt,rs);//��ȣ�� ã�� ������
			}
			System.out.println("================ TDLNotice  -  updateArticle() ��");
			return x;
		}
	
		
		//�� ���������ִ� �޼��� -> ȸ��Ż��(����)=>��ȣ�� �����.=> deleteArticle	
		public int deleteArticle(int TN_num) { 
			System.out.println("================ TDLNotice  -  deleteArticle() ����");
			int x=-1;//�Խù��� ������������	
			try {
				con=pool.getConnection();
				
						sql="delete from TDL_Notice where TN_num=?";
						pstmt=con.prepareStatement(sql);
						pstmt.setInt(1, TN_num);
						int delete=pstmt.executeUpdate();
						System.out.println("TDLNoticeDAO �Խ����� �ۼ��� ��������(delete) : "+delete);//1����
						x=1;
				//if(rs.next()); -> x=-1;
			}catch(Exception e) {
				System.out.println("TDLNoticeDAO deleteArticle()�޼��� �������� : "+e);
			}finally {
				pool.freeConnection(con,pstmt,rs);//��ȣ�� ã�� ������
			}
			System.out.println("================ TDLNotice  -  deleteArticle() ��");
			return x;
		
		}
		
		
		//�ݺ��Ǵ� �ڵ� ���̱�
		private TDLNoticeDTO makeArticleFromResult() throws Exception{
			
			TDLNoticeDTO article = new TDLNoticeDTO();// MemberDTO mem=new MemberDTO();
			article.setTN_num(rs.getInt("TN_num"));//�����Խ��� ��ȣ
			article.setTN_title(rs.getString("TN_title"));//�����Խ��� ����
			article.setTN_id(rs.getString("TN_id"));// �������̵�
			article.setTN_date(rs.getString("TN_date"));// �ۼ���
			article.setTN_content(rs.getString("TN_content")); //�����Խ��� �� ����
			article.setTN_readcount(rs.getInt("TN_readcount"));//��ȸ��
			return article;
		}
		
		
		
		
		
	
}
