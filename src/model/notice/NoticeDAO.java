package model.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DAOBase;

public class NoticeDAO extends DAOBase{
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public List<NoticeVO> listNotice(int startlist, int endlist){
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		String sql ="select * from (select rownum as rn, B.* from (select * from notice A order by idx desc) B) where rn >= ? and rn <= ?";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, startlist);
			ps.setInt(2, endlist);
			rs = ps.executeQuery();
			while(rs.next()) {
				NoticeVO vo = new NoticeVO();
				vo.setAuthor(rs.getString("author"));
				vo.setReadcnt(rs.getInt("readcnt"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setTitle(rs.getString("title"));
				vo.setIdx(rs.getInt("idx"));
				list.add(vo);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return list;
	}
	
	public NoticeVO selectNotice(int idx) {
		NoticeVO vo  = new NoticeVO();
		String sql = "select * from notice where idx=?";
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
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return vo;
	}
	
	public int modifyNotice(NoticeVO vo) {
		int row= 0;
		String sql = "update notice set author=?, content=?, title=?, filename=? where idx=?";
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
	
	public int deleteNotice(int idx) {
		int row = 0;
		String sql = "delete from notice where idx = ?";
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
	
	public List<NoticeVO> searchListNotice(String genre, String key, int startlist, int endlist){
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		String sql = "select * from (select rownum as rn, B.* from (select * from notice A where "+genre+" like ? order by idx desc) B) where rn >= ? and rn <= ?";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+key+"%");
			ps.setInt(2, startlist);
			ps.setInt(3, endlist);
			rs = ps.executeQuery();
			while(rs.next()) {
				NoticeVO vo = new NoticeVO();
				vo.setAuthor(rs.getString("author"));
				vo.setReadcnt(rs.getInt("readcnt"));
				vo.setRegdate(rs.getString("regdate"));
				vo.setTitle(rs.getString("title"));
				vo.setIdx(rs.getInt("idx"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return list;
	}
	
	public int countNotice() {
		int cnt = 0;
		String sql = "select count(*) from notice";
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
	
	public int searchCountNotice(String genre, String key) {
		String sql = "select count(*) from notice where "+genre+" like ?"; 
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
	
	public int insertNotice(NoticeVO vo ) {
		String sql = "insert into notice values(notice_idx_seq.nextval,?,?,?,sysdate,0,?,?,null)";
		int row = 0;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getAuthor());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getFilename());
			ps.setString(5, vo.getFilepath());
			row = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return row;
	}
	
	public int incrementReadcnt(int idx) {
		String sql = "update notice set readcnt=readcnt+1 where idx=?";
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
}
