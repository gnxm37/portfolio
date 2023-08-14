package com.google.phonebook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.google.phonebook.dto.PhoneBookDto;
import com.google.phonebook.dto.UserTableDto;

@Repository // repository는 클래스이다.
public class PhoneBookDao {
		final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
		final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";

		// DB 연결을 가져오는 메서드, DBCP를 사용하는 것이 좋음
		public Connection open() {
			Connection conn = null;
			try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(JDBC_URL, "ora_user", "hong");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
		}

		public ArrayList<PhoneBookDto> searchAll() throws Exception {
			Connection conn = open();
			ArrayList<PhoneBookDto> phonebookList = new ArrayList<>();
			StringBuilder sql = new StringBuilder();
			sql.append("select a.userid            ");
			sql.append("     ,a.membernm          ");
			sql.append("     ,a.phonenumber          ");
			sql.append("     ,a.address             ");
			sql.append("     ,a.groupno            ");
			sql.append("     ,b.groupnm            ");
			sql.append("  from phonebook a            ");
			sql.append("      ,phonegroup b            ");
			sql.append(" where a.groupno = b.groupno   ");

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			try (conn; pstmt; rs) {
				while (rs.next()) {
					PhoneBookDto phonebookDto = new PhoneBookDto();
					phonebookDto.setMembernm(rs.getString("userid"));
					phonebookDto.setMembernm(rs.getString("membernm"));
					phonebookDto.setPhonenumber(rs.getString("phonenumber"));
					phonebookDto.setAddress(rs.getString("address"));
					phonebookDto.setGroupno(rs.getString("groupno"));
					phonebookDto.setGroupnm(rs.getString("groupnm"));

					phonebookList.add(phonebookDto);
				}
				return phonebookList;
			}

		}// searchAll end
		public ArrayList<PhoneBookDto> searchByID(String sessionuserid) throws Exception {
			ArrayList<PhoneBookDto> PhoneBookList = new ArrayList<>();
			Connection conn = open();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT a.USERID		 					");
			sql.append("     , a.membernm						");
			sql.append("     , a.phonenumber					");
			sql.append("  	 , a.ADDRESS						");
			sql.append("	 , a.GROUPNO						");
			sql.append("	 , b.GROUPNM						");
			sql.append("  FROM phonebook a						");
			sql.append("	 , phonegroup b						");
			sql.append(" WHERE a.userid =  ?					");
			sql.append("   AND a.GROUPNO = b.GROUPNO			");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, sessionuserid);
			ResultSet rs = pstmt.executeQuery();
			
				while(rs.next()) {
//					try (conn; pstmt; rs) {
					PhoneBookDto phonebookDto = new PhoneBookDto();
					phonebookDto.setUserid(rs.getString("userid"));
					phonebookDto.setMembernm(rs.getString("MEMBERNM"));
					phonebookDto.setPhonenumber(rs.getString("PHONENUMBER"));
					phonebookDto.setAddress(rs.getString("ADDRESS"));
					phonebookDto.setGroupnm(rs.getString("GROUPNO"));
					phonebookDto.setGroupnm(rs.getString("GROUPNM"));
					
					PhoneBookList.add(phonebookDto);
//				}
			
			}
			return PhoneBookList;
		}
		
		public ArrayList<PhoneBookDto> searchByName(String sessionuserid, String membernm) throws Exception {
			ArrayList<PhoneBookDto> PhoneBookList = new ArrayList<>();
			Connection conn = open();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT a.userid		 					");
			sql.append("     , a.membernm						");
			sql.append("     , a.phonenumber					");
			sql.append("  	 , a.address						");
			sql.append("	 , a.groupno						");
			sql.append("	 , b.groupnm						");
			sql.append("  FROM phonebook a						");
			sql.append("	 , phonegroup b						");
			sql.append(" WHERE a.userid = ?						");
			sql.append("   AND a.membernm = ?					");
			sql.append("   AND a.GROUPNO = b.GROUPNO			");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, sessionuserid);
			pstmt.setString(2, membernm);
			ResultSet rs = pstmt.executeQuery();
			
				while(rs.next()) {
//					try (conn; pstmt; rs) {
					PhoneBookDto phonebookDto = new PhoneBookDto();
					phonebookDto.setUserid(rs.getString("userid"));
					phonebookDto.setMembernm(rs.getString("MEMBERNM"));
					phonebookDto.setPhonenumber(rs.getString("PHONENUMBER"));
					phonebookDto.setAddress(rs.getString("ADDRESS"));
					phonebookDto.setGroupnm(rs.getString("GROUPNO"));
					phonebookDto.setGroupnm(rs.getString("GROUPNM"));
					
					PhoneBookList.add(phonebookDto);
//				}
			
			}
			return PhoneBookList;
		}
	
		public void insert(PhoneBookDto phonebookdto) throws Exception {
		      Connection conn = open();
		      
		      String sql = "INSERT INTO phonebook VALUES (?,?,?,?,?)";
		      
		      PreparedStatement pstmt = conn.prepareStatement(sql);
		      
		      try(conn; pstmt) {
		         pstmt.setString(1,	phonebookdto.getUserid());
		         pstmt.setString(2, phonebookdto.getMembernm());
		         pstmt.setString(3, phonebookdto.getPhonenumber());
		         pstmt.setString(4, phonebookdto.getAddress());
		         pstmt.setString(5, phonebookdto.getGroupno());
		         pstmt.executeUpdate();
		      } 
		      
		   }
		public void delete(String phonenumber, String sessionuserid) throws SQLException {
			Connection conn = open();

			String sql = "delete from phonebook where phonenumber=? and userid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			try (conn; pstmt) {
				pstmt.setString(1, phonenumber);
				pstmt.setString(2, sessionuserid);
				if (pstmt.executeUpdate() == 0) {
					throw new SQLException("DB에러");
				}
			}
		}
		
		public void update(PhoneBookDto phonebookdto) throws SQLException {
			Connection conn = open();

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE phonebook		 						");
			sql.append("   SET membernm = ?, address = ?, groupno = ?	");
			sql.append(" WHERE phonenumber = ? and userid = ?			");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		    try(conn; pstmt) {
			    pstmt.setString(1, phonebookdto.getMembernm());
			    pstmt.setString(2, phonebookdto.getAddress());
			    pstmt.setString(3, phonebookdto.getGroupno());
		    	pstmt.setString(4, phonebookdto.getPhonenumber());
				pstmt.setString(5, phonebookdto.getUserid());
			    pstmt.executeUpdate();
			} 
		}
		
//		public ArrayList<PhoneBookDto> pncheck(String phonenumber) throws Exception {
//			Connection conn = open();
//			ArrayList<PhoneBookDto> list = new ArrayList<>();
//
//			String sql = "SELECT * FROM phonebook where phonenumber = ?";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, phonenumber);
//			ResultSet rs = pstmt.executeQuery();
//			try (conn; pstmt; rs) {
//				while (rs.next()) {
//					PhoneBookDto user = new PhoneBookDto();
//					user.setUserid(rs.getString("userid"));
//					user.setMembernm(rs.getString("membernm"));
//					user.setPhonenumber(rs.getString("phonenumber"));
//					user.setAddress(rs.getString("address"));
//					user.setGroupno(rs.getString("groupno"));
//					
//					list.add(user);
//				}
//			}
//
//			return list;
//		}
		
	}

