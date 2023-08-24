package com.trading.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.trading.dto.TradingNewsDto;

@Repository // repository는 클래스이다.
public class TradingNewsDao {
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		static final String DB_URL = "jdbc:mysql://localhost/test?serverTimezone=UTC";
	
		// DB 연결을 가져오는 메서드, DBCP를 사용하는 것이 좋음
		public static Connection open() {
			Connection conn = null;
	        try {
	            Class.forName("org.mariadb.jdbc.Driver");
	            String url = "jdbc:mariadb://34.64.240.96:3306/final_project";
	            String id = "handyeon77";
	            String pw = "tndusWkd1.";
	            conn = DriverManager.getConnection(url, id, pw);
	            System.out.println("연결 성공");
	        } catch(ClassNotFoundException e) { 
	            System.out.println("드라이버 로딩 실패");
	        } catch(SQLException e) {
	            e.printStackTrace();
	        }
	        return conn;
		}

		public ArrayList<TradingNewsDto> searchAll() throws Exception {
			Connection conn = open();
			ArrayList<TradingNewsDto> TradingNewsList = new ArrayList<>();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT 		NewsDates            						");
			sql.append("			  , 			NewsTitles            						");
			sql.append("			  , 			NewsContents           					");
			sql.append("			  , 			Numb			           						");
			sql.append("   FROM  		past_news_realtime				      	");
			sql.append(" WHERE  		nullif(NewsContents,' ') is not null	");
			sql.append("ORDER BY		NewsDates DESC      		  			");
			sql.append("limit 1000     		  			  									");

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			try (conn; pstmt; rs) {
				while (rs.next()) {
					TradingNewsDto TradingNewsDto = new TradingNewsDto();
					TradingNewsDto.setNewsdates(rs.getString("newsdates"));
					TradingNewsDto.setNewstitles(rs.getString("newstitles"));
					TradingNewsDto.setNewscontents(rs.getString("newscontents"));
					TradingNewsDto.setNumber(rs.getInt("numb"));

					TradingNewsList.add(TradingNewsDto);
				}
				return TradingNewsList;
			}

		}// searchAll end
		
		
	    // 상세정보 가져오기
	    public static List<TradingNewsDto> getContents(int number) throws SQLException {
	        Connection conn = open();
	        ArrayList<TradingNewsDto> TradingNewsList = new ArrayList<>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("SELECT 	NewsDates					");
	        sql.append("			   , 	NewsTitles					");
	        sql.append("			   , 	NewsContents				");
	        sql.append("	   FROM 	past_news_realtime		");
	        sql.append(" WHERE		Numb=?						");

	        try (PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
	            pstmt.setInt(1, number);

	            try (ResultSet rs = pstmt.executeQuery()) {
	                while (rs.next()) {
	                    TradingNewsDto TradingNewsDto = new TradingNewsDto();
	                    TradingNewsDto.setNewsdates(rs.getString("newsdates"));
	                    TradingNewsDto.setNewstitles(rs.getString("newstitles"));
	                    TradingNewsDto.setNewscontents(rs.getString("newscontents"));

	                    TradingNewsList.add(TradingNewsDto);
	                }
	            }
	        }
	        return TradingNewsList;
	    } // getContent end
	    
	    public List<Map<String, Object>> kospi() throws Exception {
	    	Connection conn = open();
	    	List<Map<String, Object>> TradingKospiList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 	Kospi																																	");
	    	sql.append("			  ,		DATE_FORMAT(Ymd_Time, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time		");
	    	sql.append("   FROM 	kospi_exchage_real																												");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		try (ResultSet rs = pstmt.executeQuery()) {
	    			while (rs.next()) {
	    				Map<String, Object> tradingDataMap = new HashMap<>();
	    				tradingDataMap.put("kospi", rs.getString("Kospi"));
	    				tradingDataMap.put("ymd_time", rs.getString("converted_time"));
	    				TradingKospiList.add(tradingDataMap);
	    			}
	    		}
	    	} finally {
	    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
	    	}
	    	return TradingKospiList;
	    } //kospi end	    
	    
	    public List<Map<String, Object>> kospit() throws Exception {
	        Connection conn = open();
	        String now = LocalDate.now().toString();
	        List<Map<String, Object>> TradingKospiList = new ArrayList<>();
	        StringBuilder sql = new StringBuilder();
	        sql.append("SELECT 	Kospi																							");
	        sql.append("			  ,		DATE_FORMAT(Ymd_Time, '%H:%i:%s') AS converted_time		");
	        sql.append("   FROM 	kospi_exchage_real																		");
	        sql.append(" WHERE 	Ymd_Time like	?																			");

	        try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
//	        	pstmt.setString(1, now+"%");
	        	pstmt.setString(1, "2023-07-28%");
	        	 try (ResultSet rs = pstmt.executeQuery()) {
	        			while (rs.next()) {
	        				Map<String, Object> tradingDataMap = new HashMap<>();
	        				tradingDataMap.put("kospi", rs.getString("Kospi"));
	        				tradingDataMap.put("ymd_time", rs.getString("converted_time"));
	        				TradingKospiList.add(tradingDataMap);
	        			}
	        	 }
	        } finally {
	            conn.close(); // 반드시 연결을 닫아주어야 합니다.
	        }
	        return TradingKospiList;
	    } //kospi end	   

	    public List<Map<String, Object>> exchange() throws Exception {
	    	Connection conn = open();
	    	List<Map<String, Object>> TradingKospiList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 	Exchange																																");
	    	sql.append("			   ,		DATE_FORMAT(Ymd_Time, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time		");
	    	sql.append("   FROM 	kospi_exchage_real																												");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		try (ResultSet rs = pstmt.executeQuery()) {
	    			while (rs.next()) {
	    				Map<String, Object> tradingDataMap = new HashMap<>();
	    				tradingDataMap.put("exchange", rs.getString("Exchange"));
	    				tradingDataMap.put("ymd_time", rs.getString("converted_time"));
	    				
	    				TradingKospiList.add(tradingDataMap);
	    			}
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    			throw e;
	    		} finally {
	    			conn.close(); // 반드시 연결을 닫아주어야 합니다.
	    		}
	    	}
	    	return TradingKospiList;
	    } //exchange end	    
	    
	    public List<Map<String, Object>> exchanget() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> TradingKospiList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 	Exchange																						");
	    	sql.append("			   ,		DATE_FORMAT(Ymd_Time, '%H:%i:%s') AS converted_time		");
	    	sql.append("   FROM 	kospi_exchage_real																		");
	    	sql.append(" WHERE 	Ymd_Time like	?																			");

		        try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
//		        	pstmt.setString(1, now+"%");
		        	pstmt.setString(1, "2023-07-28%");
		        	 try (ResultSet rs = pstmt.executeQuery()) {
		        			while (rs.next()) {
			    			Map<String, Object> tradingDataMap = new HashMap<>();
			    			tradingDataMap.put("exchange", rs.getString("Exchange"));
			    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
			    			
			    			TradingKospiList.add(tradingDataMap);
			    		}
		        	 } catch (Exception e) {
		        		 e.printStackTrace();
		        		 throw e;
		        	 } finally {
		        		 conn.close(); // 반드시 연결을 닫아주어야 합니다.
		        	 }
		        }
	    	return TradingKospiList;
	    } //exchange end	    
	    
	    public ArrayList<TradingNewsDto> dealo() throws Exception {
	    	Connection conn = open();
	    	ArrayList<TradingNewsDto> DealList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 	Bs_Type										");
	    	sql.append("			  ,		round(Ror, 2) AS Ror					");
	    	sql.append("			  ,		round(Hbuy)	AS Hbuy					");
	    	sql.append("			  ,		MVolume										");
	    	sql.append("   FROM 	maemae										");
			sql.append("ORDER BY		CheTime DESC      		  			");
	    	sql.append("limit 1      		  			  									");
	    	
	    	PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	    	ResultSet rs = pstmt.executeQuery();
	    	try (conn; pstmt; rs) {
				while (rs.next()) {
					TradingNewsDto TradingNewsDto = new TradingNewsDto();
					TradingNewsDto.setBs_type(rs.getString("bs_type"));
					TradingNewsDto.setRor(rs.getString("ror"));
					TradingNewsDto.setHbuy(rs.getString("hbuy"));
					TradingNewsDto.setMvolume(rs.getString("mvolume"));
					DealList.add(TradingNewsDto);
				}
	    	} finally {
	    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
	    	}
	    	return DealList;
	    } //deal end	    
	    
	    public ArrayList<TradingNewsDto> deal() throws Exception {
	    	Connection conn = open();
	    	ArrayList<TradingNewsDto> DealList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 	Bs_Type										");
	    	sql.append("			  ,		round(Ror, 2) AS Ror					");
	    	sql.append("			  ,		round(Hbuy) AS Hbuy					");
	    	sql.append("			  ,		MVolume										");
	    	sql.append("   FROM 	maemae										");
			sql.append("ORDER BY		CheTime DESC      		  			");
	    	sql.append("limit 4      		  			  									");
	    	
	    	PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	    	ResultSet rs = pstmt.executeQuery();
	    	try (conn; pstmt; rs) {
				while (rs.next()) {
					TradingNewsDto TradingNewsDto = new TradingNewsDto();
					TradingNewsDto.setBs_type(rs.getString("bs_type"));
					TradingNewsDto.setRor(rs.getString("ror"));
					TradingNewsDto.setHbuy(rs.getString("hbuy"));
					TradingNewsDto.setMvolume(rs.getString("mvolume"));
					DealList.add(TradingNewsDto);
				}
	    	} finally {
	    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
	    	}
	    	return DealList;
	    } //deal end	    
	    
	    public ArrayList<TradingNewsDto> jusikh() throws Exception {
			Connection conn = open();
			ArrayList<TradingNewsDto> TradingTicListH = new ArrayList<>();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT 		Price	            							");
			sql.append("   		  ,	 		Volume         						");
			sql.append("   		  ,	 		CVolume         						");
			sql.append("   FROM 		jusik_real_A012450      		");
			sql.append("ORDER BY		CheTime DESC      		  		");
			sql.append("limit 1      		  			  								");

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			try (conn; pstmt; rs) {
				while (rs.next()) {
					TradingNewsDto TradingNewsDto = new TradingNewsDto();
					TradingNewsDto.setPrice(rs.getString("price"));
					TradingNewsDto.setVolume(rs.getString("volume"));
					TradingNewsDto.setCvolume(rs.getString("cvolume"));

					TradingTicListH.add(TradingNewsDto);
				}
				return TradingTicListH;
			}

		}// 012450 end
	    
	    public ArrayList<TradingNewsDto> jusiks() throws Exception {
	    	Connection conn = open();
	    	ArrayList<TradingNewsDto> TradingTicListS = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            							");
	    	sql.append("   		  ,	 		Volume          						");
	    	sql.append("   		  ,	 		CVolume          						");
	    	sql.append("   FROM 		jusik_real_A009150      		");
	    	sql.append("ORDER BY		CheTime DESC      		  		");
	    	sql.append("limit 1      		  			  								");
	    	
	    	PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	    	ResultSet rs = pstmt.executeQuery();
	    	try (conn; pstmt; rs) {
	    		while (rs.next()) {
	    			TradingNewsDto TradingNewsDto = new TradingNewsDto();
	    			TradingNewsDto.setPrice(rs.getString("price"));
					TradingNewsDto.setVolume(rs.getString("volume"));
	    			TradingNewsDto.setCvolume(rs.getString("cvolume"));
	    			
	    			TradingTicListS.add(TradingNewsDto);
	    		}
	    		return TradingTicListS;
	    	}
	    	
	    }// 009150 end
	    
	    public ArrayList<TradingNewsDto> jusikm() throws Exception {
	    	Connection conn = open();
	    	ArrayList<TradingNewsDto> TradingTicListM = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            							");
	    	sql.append("   		  ,	 		Volume          						");
	    	sql.append("   		  ,	 		CVolume          						");
	    	sql.append("   FROM 		jusik_real_A042700      		");
	    	sql.append("ORDER BY		CheTime DESC      		  		");
	    	sql.append("limit 1      		  			  								");
	    	
	    	PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	    	ResultSet rs = pstmt.executeQuery();
	    	try (conn; pstmt; rs) {
	    		while (rs.next()) {
	    			TradingNewsDto TradingNewsDto = new TradingNewsDto();
	    			TradingNewsDto.setPrice(rs.getString("price"));
	    			TradingNewsDto.setVolume(rs.getString("volume"));
	    			TradingNewsDto.setCvolume(rs.getString("cvolume"));
	    			
	    			TradingTicListM.add(TradingNewsDto);
	    		}
	    		return TradingTicListM;
	    	}
	    	
	    }// 042700 end
	    
	    public ArrayList<TradingNewsDto> jusikt() throws Exception {
	    	Connection conn = open();
	    	ArrayList<TradingNewsDto> TradingTicListT = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            					");
	    	sql.append(" 		   ,	 		Volume         				");
	    	sql.append("			   ,	 		CVolume         				");
	    	sql.append("   FROM 		jusik_real_A068270     ");
	    	sql.append("ORDER BY		CheTime DESC      		");
	    	sql.append("limit 1      		  			  						");
	    	
	    	PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	    	ResultSet rs = pstmt.executeQuery();
	    	try (conn; pstmt; rs) {
	    		while (rs.next()) {
	    			TradingNewsDto TradingNewsDto = new TradingNewsDto();
	    			TradingNewsDto.setPrice(rs.getString("price"));
	    			TradingNewsDto.setVolume(rs.getString("volume"));
	    			TradingNewsDto.setCvolume(rs.getString("cvolume"));
	    			
	    			TradingTicListT.add(TradingNewsDto);
	    		}
	    		return TradingTicListT;
	    	}
	    	
	    }// 068270 end
	    
	    // KT&G 033780
	    public List<Map<String, Object>> charta() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time		");
	    	sql.append("   FROM 		jusik_real_A033780      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {
	    			while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
	    		}
	    	}catch (Exception e) {
	    			e.printStackTrace();
	    			throw e;
    		} finally {
	    			conn.close(); // 반드시 연결을 닫아주어야 합니다.
    		}
	    	}
    		return ChartaList;
	    }// KT&G 033780 end
	    
	    // 현대로템 064350
	    public List<Map<String, Object>> chartb() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartbList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time		");
	    	sql.append("   FROM 		jusik_real_A064350      																									");
			sql.append(" WHERE 		CheTime like	?																													");
				    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {
	    			while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartbList.add(tradingDataMap);
	    		}
	    	}catch (Exception e) {
	    			e.printStackTrace();
	    			throw e;
    		} finally {
	    			conn.close(); // 반드시 연결을 닫아주어야 합니다.
    		}
	    	}
    		return ChartbList;
	    }// chartb end
	    
	    // SK텔레콤 017670
	    public List<Map<String, Object>> chartc() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartcList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time		");
	    	sql.append("   FROM 		jusik_real_A017670	    																									");
			sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartcList.add(tradingDataMap);
		    		}
	    		}catch (Exception e) {
	    			e.printStackTrace();
	    			throw e;
	    		} finally {
	    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
	    		}
	    	}
	    	return ChartcList;
	    }// chartc end
	    
	    // POSCO홀딩스 005490
	    public List<Map<String, Object>> chartd() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartdList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time		");
	    	sql.append("   FROM 		jusik_real_A005490      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
	    			Map<String, Object> tradingDataMap = new HashMap<>();
	    			tradingDataMap.put("price", rs.getString("Price"));
	    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
	    			
	    			ChartdList.add(tradingDataMap);
	    		}
	    		}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartdList;
	    }// chartd end
	    
	    // 한솔케미칼 014680
	    public List<Map<String, Object>> charte() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> CharteList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A014680      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
			    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			CharteList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return CharteList;
	    }// 한솔케미칼 014680 end
	    
	    // 한화에어로스페이스 012450
	    public List<Map<String, Object>> chartf() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartfList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A012450      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartfList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartfList;
	    }// 한화에어로스페이스 012450 end
	    
	    // LG생활건강 051900
	    public List<Map<String, Object>> chartg() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A051900      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// LG생활건강 051900 end
	    
	    // 삼성전기 009150
	    public List<Map<String, Object>> charth() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time		");
	    	sql.append("   FROM 		jusik_real_A009150      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// 삼성전기 009150 end
	    
	    // LG전자 066570
	    public List<Map<String, Object>> charti() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A066570      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// LG전자 066570 end
	    
	    // LX세미콘 108320
	    public List<Map<String, Object>> chartj() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A108320      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// LX세미콘 108320 end
	    
	    // 한국항공우주 047810
	    public List<Map<String, Object>> chartk() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A047810      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// 한국항공우주 047810 end
	    
	    // SK하이닉스 000660
	    public List<Map<String, Object>> chartl() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time		");
	    	sql.append("   FROM 		jusik_real_A000660      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {
	    			while (rs.next()) {
	    			Map<String, Object> tradingDataMap = new HashMap<>();
	    			tradingDataMap.put("price", rs.getString("Price"));
	    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
	    			
	    			ChartaList.add(tradingDataMap);
	    		}
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    		throw e;
	    	} finally {
	    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
	    	}
	    	}
	    	return ChartaList;
	    }// SK하이닉스 000660 end
	    
	    // 삼성중공업 010140
	    public List<Map<String, Object>> chartm() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A010140      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// 삼성중공업 010140 end
	    
	    // NAVER 035420
	    public List<Map<String, Object>> chartn() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A035420      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// NAVER 035420 end
	    
	    // 현대차 005380
	    public List<Map<String, Object>> charto() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A005380      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// 현대차 005380 end
	    
	    // 한화솔루션 009830
	    public List<Map<String, Object>> chartp() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A009830      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// 한화솔루션 009830 end
	    
	    // 한전기술 052690
	    public List<Map<String, Object>> chartq() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A052690      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// 한전기술 052690 end
	    
	    // 두산에너빌리티 034020
	    public List<Map<String, Object>> chartr() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A034020      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// 두산에너빌리티 034020 end
	    
	    // 코스모신소재 005070
	    public List<Map<String, Object>> charts() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A005070      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// 코스모신소재 005070 end
	    
	    // 코스모화학 005420
	    public List<Map<String, Object>> chartt() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A005420      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// 코스모화학 005420 end
	    
	    // 한미반도체 042700
	    public List<Map<String, Object>> chartu() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A042700      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// 한미반도체 042700 end
	    
	    // 삼성엔지니어링 028050
	    public List<Map<String, Object>> chartv() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A028050      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// 삼성엔지니어링 028050 end
	    
	    // 셀트리온 068270
	    public List<Map<String, Object>> chartw() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A068270      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// 셀트리온 068270 end
	    
	    // 에스디바이오센서 137310
	    public List<Map<String, Object>> chartx() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A137310      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// 에스디바이오센서 137310 end
	    
	    // 삼성전자 005930
	    public List<Map<String, Object>> charty() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A005930      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// 삼성전자 005930 end
	    
	    // 한국카본 017960
	    public List<Map<String, Object>> chartz() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A017960      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// 한국카본 017960 end
	    
	    // 세진중공업 075580
	    public List<Map<String, Object>> chartaa() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A075580     																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// 세진중공업 075580 end
	    
	    // 대한항공 003490
	    public List<Map<String, Object>> chartbb() throws Exception {
	    	Connection conn = open();
	    	String now = LocalDate.now().toString();
	    	List<Map<String, Object>> ChartaList = new ArrayList<>();
	    	StringBuilder sql = new StringBuilder();
	    	sql.append("SELECT 		Price	            																														");
	    	sql.append("			   ,			DATE_FORMAT(CheTime, '%Y-%m-%dT%H:%i:%s.000+0000') AS converted_time	");
	    	sql.append("   FROM 		jusik_real_A003490      																									");
	    	sql.append(" WHERE 		CheTime like	?																													");
	    	
	    	try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
	    		pstmt.setString(1, now+"%");
	    		try (ResultSet rs = pstmt.executeQuery()) {	    	
		    		while (rs.next()) {
		    			Map<String, Object> tradingDataMap = new HashMap<>();
		    			tradingDataMap.put("price", rs.getString("Price"));
		    			tradingDataMap.put("ymd_time", rs.getString("converted_time"));
		    			
		    			ChartaList.add(tradingDataMap);
		    		}
		    	}catch (Exception e) {
		    		e.printStackTrace();
		    		throw e;
		    	} finally {
		    		conn.close(); // 반드시 연결을 닫아주어야 합니다.
		    	}
	    	}
	    	return ChartaList;
	    }// 대한항공 003490 end
	    
}