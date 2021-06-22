package com.concert.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.concert.dao.FactoryDao;
import com.concert.dao.MemberDao;
import com.concert.model.Info;
import com.concert.model.Reservation;
import com.concert.model.ReservationList;

public class MemberDao {
	
	//FactoryDao 객체 멤버 변수 선언 및 할당
		private FactoryDao factory = FactoryDao.getInstance();
		
		//singleton Pattern 적용
		private static MemberDao instance = new MemberDao();
		
		//생성자를 안 만들면 jvm이 public으로 자동 생성자를 만들어줘서 아무 곳에서 접근 가능하기 때문에 private로 설정해야함
		 private MemberDao() {
			
		}
		 
		//singleton Pattern 적용 : instance 반환 메서드 
		public static MemberDao getInstance() {
			return instance;
		}

		/** 공연 정보 저장 리스트 */
		public ArrayList<Info> play = new ArrayList<Info>();
		
		/** 예약 리스트 */
		public ArrayList<ReservationList> list = new ArrayList<ReservationList>();
		
		/**
		 * 
		 * 로그인 
		 * 
		 * @param memberId
		 * @param memberPw
		 * @return
		 */
		public boolean login(String memberId, String memberPw) {
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs =  null;
			
			try {
			conn = factory.getConnection();
			String sql = "select * from member where memberId =? and memberPw =?";
			
			// 3. SQL 통로개설 : 동적 SQL 수행 
			 stmt = conn.prepareStatement(sql);
			
			// ? 에 매핑되는 값을 설정 
			stmt.setString(1, memberId);
			stmt.setString(2, memberPw);
			
			 rs = stmt.executeQuery();
			 
			if(rs.next()) {
				System.out.println("[안내] 로그인 성공! "+memberId+"님 환영합니다!");
				return true;
			}
			
			}catch(SQLException e) {
				System.out.println("[안내] 로그인 실패! 정확한 회원 정보를 적어주세요");
				e.printStackTrace();
			}finally {
				//6. 자원해제
				factory.close(conn, stmt, rs);
			}
			
			return false;
		
		}

		/**
		 *  회원 아이디 중복 체크 
		 * @param memberId
		 * @return
		 */
		public boolean exist(String memberId) {
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs =  null;
			
			try {
			conn = factory.getConnection();
			String sql = "select * from member where memberId =?";
			
			// 3. SQL 통로개설 : 동적 SQL 수행 
			 stmt = conn.prepareStatement(sql);
			
			// ? 에 매핑되는 값을 설정 
			stmt.setString(1, memberId);
			
			 rs = stmt.executeQuery();
			 
			if(rs.next()) {
				//중복 아이디 
				return true;
			}
			
			}catch(SQLException e) {
				System.out.println("[안내] 회원 가입 실패! 정확한 회원 정보를 적어주세요");
				e.printStackTrace();
			}finally {
				//6. 자원해제
				factory.close(conn, stmt, rs);
			}
			
			return false;
		}
		
		
		/**
		 *  회원 가입
		 *  
		 * @param memberId
		 * @param memberPw
		 * @param name
		 * @param mobile
		 */
		public boolean add(String memberId, String memberPw, String name, String mobile) {
			
			Connection conn = null;
			PreparedStatement stmt = null;
			
			String grade = "g";
			
			try {
				conn = factory.getConnection();
				String sql = "INSERT INTO Member VALUES (?,?,?,?,?)";
				
				// 3. SQL 통로개설 : 동적 SQL 수행 
				 stmt = conn.prepareStatement(sql);
				
				// ? 에 매핑되는 값을 설정 
				stmt.setString(1, memberId);
				stmt.setString(2, memberPw);
				stmt.setString(3, name);
				stmt.setString(4, mobile);
				stmt.setString(5, grade);
				
				int result  = stmt.executeUpdate();				 
				if(result>0) {
					//회원가입 성공
					return true;
				}
				
				}catch(SQLException e) {
					System.out.println("[안내] 회원 가입 실패! 정확한 회원 정보를 적어주세요");
					e.printStackTrace();
				}finally {
					//6. 자원해제
					factory.close(conn, stmt);
				}
				
				return false;
			
		}
		
		/**
		 * 	공연 정보 출력  
		 * @return play
		 */
		
		public ArrayList<Info> playList() {
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs =  null;
			
			try {
				
				conn = factory.getConnection();
				String sql = "select * from info";
						
				 stmt = conn.prepareStatement(sql);
						
				 rs = stmt.executeQuery();
				
				while(rs.next()) {
					
					Info info = new Info();
					
					info.setInfoCode(rs.getString("infoCode"));
					info.setInfoName(rs.getString("infoName"));
					info.setInfoDate(rs.getString("infoDate"));
					info.setInfoTime(rs.getString("infoTime"));
					info.setInfoFemaleActor(rs.getString("infoFemaleActor"));
					info.setInfoMaleActor(rs.getString("infoMaleActor"));
					
					play.add(info);
				}
				
			} catch (SQLException e) {
				System.out.println("[오류] 공연 데이터를 불러오지 못했습니다. 실패");
				e.printStackTrace();
			}finally {
				factory.close(conn, stmt, rs);
			}
			//공연 정보 불러오기 실패시 
			return play;
			
		}

		/**
		 *  공연 번호 존재 유무 파악 
		 * 
		 * @param editCode
		 * @return
		 */
		public boolean playExist(String editCode) {
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs =  null;
			
			try {
			conn = factory.getConnection();
			String sql = "select * from info where infoCode =?";
			
			// 3. SQL 통로개설 : 동적 SQL 수행 
			 stmt = conn.prepareStatement(sql);
			
			// ? 에 매핑되는 값을 설정 
			stmt.setString(1, editCode);
			
			 rs = stmt.executeQuery();
			 
			 // 존재하는 공연
			if(rs.next()) {
				return true;
			}
			
			}catch(SQLException e) {
				System.out.println("[안내] 하는 공연의 번호를 정확하게 입력해주세요.");
				e.printStackTrace();
			}finally {
				//6. 자원해제
				factory.close(conn, stmt, rs);
			}
			
			//존재하지 않는 공연
			return false;
		}

		/**
		 * 예약 내역 조회
		 * 
		 * @param memberId
		 * @return
		 */
		public ArrayList<ReservationList> rerservationList(String memberId) {
				
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs =  null;
			
			try {
				
				conn = factory.getConnection();
				String sql = "select infoName, seat, seatNum from reservation, info where memberId = ? and reservation.infocode = info.infocode";
						
				 stmt = conn.prepareStatement(sql);
				
				 stmt.setString(1, memberId);
				 
				 rs = stmt.executeQuery();
				
				while(rs.next()) {
					
					ReservationList reservationList = new ReservationList();
					
					reservationList.setInfoName(rs.getString("infoName"));
					reservationList.setSeat(rs.getString("seat"));
					reservationList.setSeatNum(rs.getInt("seatNum"));
					
					list.add(reservationList);
				}
				
			} catch (SQLException e) {
				System.out.println("[오류] 예약 리스트 데이터를 불러오지 못했습니다. 실패");
				e.printStackTrace();
			}finally {
				factory.close(conn, stmt, rs);
			}
			
			//예약 정보 불러오기 실패시 
			return list;
		}

		/**
		 * 예약된 정보 다 가져오기
		 * @return
		 */
		public ArrayList<ReservationList> rerservationSeat(String editCode) {
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs =  null;
			
			try {
				
				conn = factory.getConnection();
				String sql = "select * from reservation where infocode =?";
						
				 stmt = conn.prepareStatement(sql);
				 stmt.setString(1, editCode);
				 
				 rs = stmt.executeQuery();
				
				while(rs.next()) {
					
					ReservationList reservationList = new ReservationList();
					
					reservationList.setInfoCode(rs.getString("infocode"));
					reservationList.setSeat(rs.getString("seat"));
					reservationList.setSeatNum(rs.getInt("seatNum"));
					
					list.add(reservationList);
				}
				
			} catch (SQLException e) {
				System.out.println("[오류] 예약 리스트 데이터를 불러오지 못했습니다. 실패");
				e.printStackTrace();
			}finally {
				factory.close(conn, stmt, rs);
			}
			
			//예약 정보 불러오기 실패시 
			return list;
		}

		/**
		 * 예약하기
		 * 	
		 * @param modifyCode
		 * @param type
		 * @param seatChoice
		 * @param memberId
		 * @return
		 */
		public boolean resevaion(String modifyCode, String seat, int seatChoice, String memberId) {
			Connection conn = null;
			PreparedStatement stmt = null;
			
			try {
				conn = factory.getConnection();
				String sql = "INSERT INTO reservation VALUES (?,?,?,?)";
				
				// 3. SQL 통로개설 : 동적 SQL 수행 
				 stmt = conn.prepareStatement(sql);
				
				// ? 에 매핑되는 값을 설정 
				stmt.setString(1, memberId);
				stmt.setString(2, modifyCode);
				stmt.setString(3, seat);
				stmt.setInt(4, seatChoice);

				
				int result  = stmt.executeUpdate();				 
				if(result>0) {
					// 예약 성공
					return true;
				}
				
				}catch(SQLException e) {
					System.out.println("[안내] 예약이 정상적으로 처리되지 않았습니다.");
					e.printStackTrace();
				}finally {
					//6. 자원해제
					factory.close(conn, stmt);
				}
				
				return false;
		}

		/**
		 * 예약하기
		 * 
		 * @param modifyCode
		 * @param type
		 * @param seatChoice
		 * @param memberId
		 * @return
		 */
		public boolean insertReservation(String modifyCode, String seat, int seatChoice, String memberId) {
		
			Connection conn = null;
			PreparedStatement stmt = null;
			
			
			try {
				conn = factory.getConnection();
				String sql = "INSERT INTO reservation VALUES (?,?,?,?)";
				
				// 3. SQL 통로개설 : 동적 SQL 수행 
				 stmt = conn.prepareStatement(sql);
				
				// ? 에 매핑되는 값을 설정 
				stmt.setString(1, memberId);
				stmt.setString(2, modifyCode);
				stmt.setString(3, seat);
				stmt.setInt(4, seatChoice);
				
				int result  = stmt.executeUpdate();				 
				if(result>0) {
					//예약 성공
					return true;
				}
				
				}catch(SQLException e) {
					System.out.println("[안내] 예약 실패 : 적은 내용을 확인해주세요");
					e.printStackTrace();
				}finally {
					//6. 자원해제
					factory.close(conn, stmt);
				}			
				return false;
		}
		
	

}
