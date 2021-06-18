package com.work.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	
	

	/**
	 *  로그인 
	 *  
	 *  2. DB 서버연결 
	 *  3. SQL 통로개설 : 동적 SQL 수행 
	 *  4. SQL수행요청
	 *  5. 통로개설 : 정적 SQL 수행 
	 *  
	 * @param member_id 아이디
	 * @param member_pw 비밀번호
	 * @return 회원등급, 미존재시 null
	 */
	
	public String login(String member_id, String member_pw) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =  null;
		
		try {
			
			// 2. DB 서버연결 
//			 conn = DriverManager.getConnection(url, user, password);
			conn = factory.getConnection();

			// * 주의사항 : sql 구문 뒤에 ;(세미콜론)이 오면 안됨 member_pw =?; " 이렇게 
			String sql = "select Grade from member where member_ID =? and member_pw =?";
					
			// 3. SQL 통로개설 : 동적 SQL 수행 
			 stmt = conn.prepareStatement(sql);
					
			// ? 에 매핑되는 값을 설정 
			stmt.setString(1, member_id);
			stmt.setString(2, member_pw);
	
	
			// 5. SQL수행요청 :	 () 안에 sql 변수를 전달하면 안됨 . 왜냐면 앞에 다 했으니까!
			 rs = stmt.executeQuery();
			
			//select index 순서대로 > 1부터 , 컬럼이나 별명 이름으로 가져올 때는 String으로 가져오기
			// while 말고 if로 쓰기!
			
			//cursor 옮기기 : 데이터가 있니 없니?
			if(rs.next()) {
													//컬럼 이름 or Alias 이름
				String grade = rs.getString("grade");
				
				//로그인이 성공하면 등급 반환
				return grade;
			}

			
		} catch (SQLException e) {
			System.out.println("[오류] 로그인 실패");
			e.printStackTrace();
		}finally {
			//6. 자원해제
			factory.close(conn, stmt, rs);
//			try {
//				if(rs != null) {
//				rs.close();
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				stmt.close();
//				
//				if(stmt != null) {
//					stmt.close();
//					}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				if(conn != null) {
//					conn.close();
//					}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		//회원 아이디가 존재하지 않을 때
		return null;
	}
	
	/**
	 * 회원 상세 조회
	 * 
	 * @param memberId
	 * @return
	 */
	
	public Member selectOne(String memberId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =  null;
	
		try{
			conn = factory.getConnection();
			
			
			String sql = "select * from member where member_id = ?";
			 stmt = conn.prepareStatement(sql);
					
			stmt.setString(1, memberId);
	
			 rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				String memberPw = rs.getString("member_pw");
	            String name = rs.getString("name");
	            String mobile = rs.getString("mobile");
	            String email = rs.getString("email");
	            String entryDate = rs.getString("entry_date");
	            String grade = rs.getString("grade");
	            int mileage = rs.getInt("mileage");
	            String manager = rs.getString("manager");
				
				//select 결과로 가져온 회원의 정보로 Member 객체 생성자 이용해서 Member 객체 생성
				Member dto = new Member(memberId, memberPw, name, mobile, email, entryDate, grade, mileage, manager);
				
				// 생성한 Member 객체 반환
				return dto;
			}
		
			
		} catch (SQLException e) {
			System.out.println("[오류] 회원 상세 조회 실패");
			e.printStackTrace();
		}finally {
			//6. 자원해제
			factory.close(conn, stmt, rs);
		}
		//회원 객체가 만들어지지 않았을 때 
		return null;
		
	}

	
	public String login2(String member_id, String member_pw) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs =  null;
		
		
		
		try {
			
			// 2. DB 서버연결 
			conn = factory.getConnection();
			
			// 3. SQL 통로개설 : 동적 SQL 수행 
			 stmt = conn.createStatement();
			
			//4. 로그인 SQL 구문
//			String sql = "select Grade from member where member_ID ='user01' and member_pw = 'password01'";
			String sql = "select Grade from member where member_ID ='"+member_id+"' and member_pw = '"+ member_pw+" ' ";
			
			// 5. SQL수행요청
			 rs = stmt.executeQuery(sql);
			
			//select index 순서대로 > 1부터 , 컬럼이나 별명 이름으로 가져올 때는 String으로 가져오기
			//if로 쓰기!
			
			//cursor 옮기기 : 데이터가 있니 없니?
			if(rs.next()) {
				
													//컬럼 이름 or Alias 이름
				String grade = rs.getString("grade");
				
				//로그인이 성공하면 등급 반환
				return grade;
			}
			
		} catch (SQLException e) {
			System.out.println("[오류] 로그인 실패");
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt, rs);
		}
		//회원 아이디가 존재하지 않을 때
		return null;
	}



 /**
  * 회원 전체 조회
  * @return
  */
	public ArrayList<Member> selectAll() {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =  null;
		
		ArrayList<Member> members = new ArrayList<Member>();
		
		try{

			conn = factory.getConnection();
			
			
			String sql = "select * from member ";
			 stmt = conn.prepareStatement(sql);
	
			 rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Member member = new Member();
				
				member.setMember_id(rs.getString("member_id"));
				member.setMember_pw(rs.getString("member_pw"));
				member.setName(rs.getString("name"));
				member.setMobile(rs.getString("mobile"));

				member.setEmail(rs.getString("email"));
				member.setEntry_date(rs.getString("entry_date"));
				member.setGrade(rs.getString("grade"));
	            member.setMileage(rs.getInt("mileage")); 
	            member.setManager(rs.getString("manager"));
				
	             members.add(member);
			}
		} catch (SQLException e) {
			System.out.println("[오류] 회원 전체 조회 실패");
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt, rs);
		}
	
		return members;
		
	}
	
	/**
	 * 회원 등급 별 전체 조회
	 * @param grade
	 * @return
	 */
	public ArrayList<Member> selectAllByGrade(String grade) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =  null;
		
	ArrayList<Member> members = new ArrayList<Member>();
		
		try{

			conn = factory.getConnection();
			
			
			String sql = "select * from member where grade=?";
			 stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, grade);
	
			 rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Member member = new Member();
				
				member.setMember_id(rs.getString("member_id"));
				member.setMember_pw(rs.getString("member_pw"));
				member.setName(rs.getString("name"));
				member.setMobile(rs.getString("mobile"));

				member.setEmail(rs.getString("email"));
				member.setEntry_date(rs.getString("entry_date"));
				member.setGrade(rs.getString("grade"));
	            member.setMileage(rs.getInt("mileage")); 
	            member.setManager(rs.getString("manager"));
				
	             members.add(member);
			}
			
			
		} catch (SQLException e) {
			System.out.println("[오류] 회원 전체 조회 실패");
			e.printStackTrace();
		}finally {
			//6. 자원해제
			factory.close(conn, stmt, rs);
		}
	
		return members;
		
		
		
	}


	/**
	 *  이메일 조회  
	 */

	public String idCheck(String email) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =  null;
			
			try {
				
			conn = factory.getConnection();
			String sql = "select email from member where email =? ";
					
			 stmt = conn.prepareStatement(sql);
					
			stmt.setString(1, email);
	
			 rs = stmt.executeQuery();
			
		
			if(rs.next()) {
													
				email = rs.getString("email");
				
				//이메일이 중복이면 이메일을 보낸다
				return email;
			}
			
		} catch (SQLException e) {
			System.out.println("[오류] 중복 확인 실패");
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt, rs);
		}
		//회원 이메일이 없으면 때 
		return null;
	}


	/**
	 * 비밀번호 찾기 
	 * @param memberId
	 * @param name
	 * @param email
	 * @return
	 */
	public boolean findPw(String memberId, String name, String email) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs =  null;
		
		try{

			conn = factory.getConnection();			
			
			String sql = "select member_pw from member where member_id = ? and name = ? and email = ?";
			 stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, memberId);
			stmt.setString(2, name);
			stmt.setString(3, email);
	
			 rs = stmt.executeQuery();
			
			//만약에 회원 조회 결과가 떴으면 
			if(rs.next()) {
				
				return true;
			}
		} catch (SQLException e) {
			System.out.println("[오류] 비밀번호 찾기 조회 실패");
			e.printStackTrace();
		}finally {
			factory.close(conn, stmt, rs);
		}
	
		return false;
		
	}


	/**
	 * 비밀번호 변경 
	 * @param memberId
	 * @param memberNewPw
	 * @return
	 */
	public int updateMemberPw(String memberId, String memberNewPw) {
		
		Connection conn = null;
		PreparedStatement stmt = null;

		
		int rows = 0;
		
		try{

			conn = factory.getConnection();			
			
			String sql = "update member set member_pw = ? where member_id = ?";
			 stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, memberNewPw);
			stmt.setString(2, memberId);
	
			rows = stmt.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("[오류] 비밀번호 임시 발급 변경 실패");
			e.printStackTrace();
		}
		finally {
			factory.close(conn, stmt, null);
		}
	
		return rows;
		
	}
}	


	

