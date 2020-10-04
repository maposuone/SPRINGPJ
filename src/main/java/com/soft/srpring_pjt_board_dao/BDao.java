package com.soft.srpring_pjt_board_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.soft.srpring_pjt_board_dto.BDto;

public class BDao {
	
	DataSource dataSource;
	
	public BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void modify(String bId,String bName,String bTitle,String bContent) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "update mvc_board set bName = ?, bTitle = ? , bContent = ?  where bId = ?";
			ps = conn.prepareStatement(query);
			

			ps.setString(1, bName);
			ps.setString(2, bTitle);
			ps.setString(3, bContent);
			ps.setInt(4, Integer.parseInt(bId));			
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	public void reply(String bId,String bName,String bTitle,String bContent,String bGroup,String bStep,String bIndent) {
		replyShape(bGroup, bStep);

		Connection conn = null;
		PreparedStatement ps = null;
		 System.out.println("testinsertreply");
		 try {
			 conn = dataSource.getConnection();
			 String query = "insert into mvc_board (bId,bName,bTitle,bContent, bGroup, bStep, bIndent) values (mvc_board_seq.nextval,?,?,?,?,?,?)";
			 ps = conn.prepareStatement(query);
			 ps.setString(1, bName);
			 ps.setString(2, bTitle);
			 ps.setString(3, bContent);
			 ps.setInt(4, Integer.parseInt(bGroup));
			 ps.setInt(5, Integer.parseInt(bStep) + 1);
			 ps.setInt(6, Integer.parseInt(bIndent) + 1);
			 ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void replyShape(String bGroup , String bStep){
System.out.println("replyshape1");
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(bGroup));
			ps.setInt(2, Integer.parseInt(bStep));
			
			ps.executeUpdate();
			System.out.println("replyshape2");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public BDto reply_view(String strbId) {
		
		BDto dto = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rt = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "select * from mvc_board where bId = ?";
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, Integer.parseInt(strbId));
			rt = ps.executeQuery();
			
			if (rt.next()) {
				int bId = rt.getInt("bId");
				String bName = rt.getString("bName");
				String bTitle = rt.getString("bTitle");
				String bContent = rt.getString("bContent");
				Timestamp bDate = rt.getTimestamp("bDate");
				int bHit = rt.getInt("bHit");
				int bGroup = rt.getInt("bGroup");
				int bStep = rt.getInt("bStep");
				int bIndent = rt.getInt("bIndent");
				dto = new BDto(bId,bName,bContent,bTitle,bDate,bHit,bGroup,bStep,bIndent);				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}  finally {
			try {
				if(rt != null) rt.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
	
	
	public void delete(String bId) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "delete from mvc_board where bId = ?";
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, Integer.parseInt(bId));
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}
	public BDto contentView(String strbId) {
		System.out.println("contentView(1)");
		upHit(strbId);
		
		BDto dto = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rt = null;
		
		try {
			conn= dataSource.getConnection();
			String query = "select * from mvc_board where bId = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(strbId));
			rt = ps.executeQuery();			
			
			if(rt.next()) {
				int bId = rt.getInt("bId");
				String bName = rt.getString("bName");
				String bTitle = rt.getString("bTitle");
				String bContent = rt.getString("bContent");
				Timestamp bDate = rt.getTimestamp("bDate");
				int bHit = rt.getInt("bHit");
				int bGroup = rt.getInt("bGroup");
				int bStep = rt.getInt("bStep");
				int bIndent = rt.getInt("bIndent");
				dto = new BDto(bId,bName,bContent,bTitle,bDate,bHit,bGroup,bStep,bIndent);				
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if(rt != null) rt.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
	
	
	private void upHit(String strbId) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			
			conn = dataSource.getConnection();
			String query = "update mvc_board set bHit = bHit + 1 where bId = ?" ;
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(strbId));
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void write(String bName, String bTitle, String bContent) {
		Connection conn = null;
		PreparedStatement ps = null;   
		try {
			conn = dataSource.getConnection();
			String query = "insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values( mvc_board_seq.nextval, ?,?,?,0, mvc_board_seq.currval,0,0)";
			ps = conn.prepareStatement(query);
			
			ps.setString(1, bName);
			ps.setString(2, bTitle);
			ps.setString(3, bContent);
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();

		}finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();

			}
		}
		
		
	}
	
	public ArrayList<BDto> list() {
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rt = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "select * from mvc_board order by bGroup desc, bStep asc";
			ps = conn.prepareStatement(query);
			rt = ps.executeQuery();
			
			while(rt.next()) {
				int bId = rt.getInt("bId");
				String bName = rt.getString("bName");
				String bTitle = rt.getString("bTitle");
				String bContent = rt.getString("bContent");
				Timestamp bDate = rt.getTimestamp("bDate");
				int bHit = rt.getInt("bHit");
				int bGroup = rt.getInt("bGroup");
				int bStep = rt.getInt("bStep");
				int bIndent = rt.getInt("bIndent");
				
				BDto dto = new BDto(bId,bName,bContent,bTitle,bDate,bHit,bGroup,bStep,bIndent);
				dtos.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rt != null) rt.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		  
		return dtos;
	}

}
