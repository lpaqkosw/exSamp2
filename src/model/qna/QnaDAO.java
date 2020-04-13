package model.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import util.DAOBase;

public class QnaDAO extends DAOBase{
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public List<QnaVO> listQna(int startlist, int endlist){
		List<QnaVO> list = new ArrayList<QnaVO>();
		String sql ="select * from (select rownum as rn, B.* from (select * from qna A order by idx desc) B) where rn >= ? and rn <= ?";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, startlist);
			ps.setInt(2, endlist);
			rs = ps.executeQuery();
			while(rs.next()) {
				QnaVO vo = new QnaVO();
				vo.setAuthor(rs.getString("author"));
				vo.setReadcnt(rs.getInt("readcnt"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setTitle(rs.getString("title"));
				vo.setIdx(rs.getInt("idx"));
				vo.setStatus(rs.getString("status"));
				vo.setCategory(rs.getString("category"));
				list.add(vo);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return list;
	}
	
	public QnaVO selectQna(int idx) {
		QnaVO vo  = new QnaVO();
		String sql = "select * from qna where idx=?";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, idx);
			rs = ps.executeQuery();
			if(rs.next()) {
				vo.setAuthor(rs.getString("author"));
				vo.setCategory(rs.getString("category"));
				vo.setContent(rs.getString("content"));
				vo.setFilename(rs.getString("filename"));
				vo.setIdx(rs.getInt("idx"));
				vo.setReadcnt(rs.getInt("readcnt"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setTitle(rs.getString("title"));
				vo.setFilepath(rs.getString("filepath"));
				vo.setAnswer(rs.getString("answer"));
				vo.setStatus(rs.getString("status"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return vo;
	}
	
	public int modifyQna(QnaVO vo) {
		int row= 0;
		String sql = "update Qna set author=?, content=?, title=?, filename=? where idx=?";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getAuthor());
			ps.setString(2, vo.getContent());
			ps.setString(3, vo.getTitle());
			ps.setString(4, vo.getFilename());
			ps.setInt(5, vo.getIdx());
			row = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return row;
	}
	
	public int deleteQna(int idx) {
		int row = 0;
		String sql = "delete from Qna where idx = ?";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, idx);
			row = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return row;
	}
	
	public List<QnaVO> searchListQna(String genre, String key, int startlist, int endlist){
		List<QnaVO> list = new ArrayList<QnaVO>();
		String sql = "select * from (select rownum as rn, B.* from (select * from qna A where "+genre+" like ? order by idx desc) B) where rn >= ? and rn <= ?";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+key+"%");
			ps.setInt(2, startlist);
			ps.setInt(3, endlist);
			rs = ps.executeQuery();
			while(rs.next()) {
				QnaVO vo = new QnaVO();
				vo.setAuthor(rs.getString("author"));
				vo.setReadcnt(rs.getInt("readcnt"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setTitle(rs.getString("title"));
				vo.setIdx(rs.getInt("idx"));
				vo.setStatus(rs.getString("status"));
				vo.setCategory(rs.getString("category"));
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return list;
	}
	
	public int countQna() {
		int cnt = 0;
		String sql = "select count(*) from Qna";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return cnt;
	}
	
	public int searchCountQna(String genre, String key) {
		String sql = "select count(*) from Qna where "+genre+" like ?"; 
		int cnt = 0;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+key+"%");
			rs = ps.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return cnt;
	}
	
	public int insertQna(QnaVO vo ) {
		String sql = "insert into qna values(qna_idx_seq.nextval,?,?,?,sysdate,0,?,?,?,null,null,?)";
		int row = 0;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getAuthor());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getFilename());
			ps.setString(5, vo.getFilepath());
			ps.setString(6, vo.getCategory());
			ps.setString(7, vo.getPhone());
			row = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return row;
	}
	
	public int incrementReadcnt(int idx) {
		String sql = "update Qna set readcnt=readcnt+1 where idx=?";
		int row = 0;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, idx);
			row = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return row;
	}
	
	public List<QnaVO> categoryListQna(String category, int startlist, int endlist){
		List<QnaVO> list = new ArrayList<QnaVO>();
		String sql = "select * from (select rownum as rn, B.* from (select * from qna A where category=? order by idx desc) B) where rn >= ? and rn <= ?";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, category);
			ps.setInt(2, startlist);
			ps.setInt(3, endlist);
			rs = ps.executeQuery();
			while(rs.next()) {
				QnaVO vo = new QnaVO();
				vo.setAuthor(rs.getString("author"));
				vo.setReadcnt(rs.getInt("readcnt"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setTitle(rs.getString("title"));
				vo.setIdx(rs.getInt("idx"));
				vo.setStatus(rs.getString("status"));
				vo.setCategory(rs.getString("category"));
				
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return list;
	}
	
	public List<QnaVO> categorySearchListQna(String category, String genre, String key, int startlist, int endlist){
		List<QnaVO> list = new ArrayList<QnaVO>();
		String sql = "select * from (select rownum as rn, B.* from (select * from qna A where category=? and "+genre+" like ? order by idx desc) B) where rn >= ? and rn <= ?";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, category);
			ps.setString(2, "%"+key+"%");
			ps.setInt(3, startlist);
			ps.setInt(4, endlist);
			rs = ps.executeQuery();
			while(rs.next()) {
				QnaVO vo = new QnaVO();
				vo.setAuthor(rs.getString("author"));
				vo.setReadcnt(rs.getInt("readcnt"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setTitle(rs.getString("title"));
				vo.setIdx(rs.getInt("idx"));
				vo.setStatus(rs.getString("status"));
				vo.setCategory(rs.getString("category"));
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return list;
	}
	
	public int categoryListCountQna(String category) {
		String sql = "select count(*) from Qna where category=?"; 
		int cnt = 0;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, category);
			rs = ps.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return cnt;
	}
	
	public int categorySearchListCountQna(String category, String genre, String key) {
		String sql = "select count(*) from qna where category=? and "+genre+" like ?"; 
		int cnt = 0;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, category);
			ps.setString(2, "%"+key+"%");
			rs = ps.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return cnt;
	}
	
	public int replyQna(int idx, String answer) {
		String sql = "update qna set answer=?, status=? where idx=?";
		int row = 0;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, answer);
			if(answer.equals("")) {
				ps.setNull(2, Types.NULL);
			}
			else {
				ps.setString(2, "y");
			}
			ps.setInt(3, idx);
			row = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return row;
	}
}
