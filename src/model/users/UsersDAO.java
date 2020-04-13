package model.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DAOBase;

public class UsersDAO extends DAOBase{
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	
	public UsersVO selectUsers(String userid, String hashpw) {
		UsersVO vo = new UsersVO();
		String sql = "select * from users where userid = ? and userpw = ?";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			ps.setString(2, hashpw);
			rs = ps.executeQuery();
			if(rs.next()) {
				vo.setEmail(rs.getString("email"));
				vo.setIdx(rs.getInt("idx"));
				vo.setUserid(rs.getString("userid"));
				vo.setUsername(rs.getString("username"));
				vo.setUserpw(rs.getString("userpw"));
				vo.setAdmin(rs.getString("admin"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return vo;
	}
	
	
	
	public List<UsersVO> listUsers(){
		List<UsersVO> list = new ArrayList<UsersVO>();
		String sql = "select * from users";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				UsersVO vo = new UsersVO();
				vo.setEmail(rs.getString("email"));
				vo.setIdx(rs.getInt("idx"));
				vo.setUserid(rs.getString("userid"));
				vo.setUsername(rs.getString("username"));
				vo.setUserpw(rs.getString("userpw"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return list;
	}
	
	public int insertUsers(UsersVO vo) {
		String sql = "insert into users values(users_idx_seq.nextval, ?,?,?,?)";
		int row = 0;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getUserid());
			ps.setString(2, vo.getUserpw());
			ps.setString(3, vo.getUsername());
			ps.setString(4, vo.getEmail());
			row = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return row;
	}
	
	public int searchUserId(String id) {
		String sql = "select userid from users where userid=?";
		int row =0;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			System.out.println(rs.getFetchSize());
			if(rs.next()) {
				row = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeCon(rs, ps, con);
		return row;
	}
	
	
}
