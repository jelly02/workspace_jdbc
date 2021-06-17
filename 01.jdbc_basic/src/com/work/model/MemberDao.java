package com.work.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
	
	//JDBC resource property
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private String user = "scott";
	private String password = "tiger";
	
	/**
	 *  1. 드라이버 로딩 
	 */
	public MemberDao() {
		
		try {
			Class.forName(driver);
			System.out.println("[성공] 드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("[오류] 드라이버 로딩 오류");
			e.printStackTrace();
		}
		
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
		
		try {
			
			// 2. DB 서버연결 
			Connection conn = DriverManager.getConnection(url, user, password);

			// * 주의사항 : sql 구문 뒤에 ;(세미콜론)이 오면 안됨 member_pw =?; " 이렇게 
			String sql = "select Grade from member where member_ID =? and member_pw =?";
					
			// 3. SQL 통로개설 : 동적 SQL 수행 
			PreparedStatement stmt = conn.prepareStatement(sql);
					
			// ? 에 매핑되는 값을 설정 
			stmt.setString(1, member_id);
			stmt.setString(2, member_pw);
	
	
			// 5. SQL수행요청 :	 () 안에 sql 변수를 전달하면 안됨 . 왜냐면 앞에 다 했으니까!
			
			ResultSet rs = stmt.executeQuery();
			
			//select index 순서대로 > 1부터 , 컬럼이나 별명 이름으로 가져올 때는 String으로 가져오기
			// while 말고 if로 쓰기!
			
			//cursor 옮기기 : 데이터가 있니 없니?
			if(rs.next()) {
													//컬럼 이름 or Alias 이름
				String grade = rs.getString("grade");
				
				//로그인이 성공하면 등급 반환
				return grade;
			}
			
			//6. 자원해제
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("[오류] 로그인 실패");
			e.printStackTrace();
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
	
		try{
			Connection conn = DriverManager.getConnection(url, user, password);
			
			
			String sql = "select * from member where member_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
					
			stmt.setString(1, memberId);
	
			ResultSet rs = stmt.executeQuery();
			
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
			
			//6. 자원해제
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("[오류] 회원 상세 조회 실패");
			e.printStackTrace();
		}
		//회원 객체가 만들어지지 않았을 때 
		return null;
		
	}

	
	public String login2(String member_id, String member_pw) {
		
		try {
			
			// 2. DB 서버연결 
			Connection conn = DriverManager.getConnection(url, user, password);
			
			// 3. SQL 통로개설 : 동적 SQL 수행 
			Statement stmt = conn.createStatement();
			
			//4. 로그인 SQL 구문
//			String sql = "select Grade from member where member_ID ='user01' and member_pw = 'password01'";
			String sql = "select Grade from member where member_ID ='"+member_id+"' and member_pw = '"+ member_pw+" ' ";
			
			// 5. SQL수행요청
			ResultSet rs = stmt.executeQuery(sql);
			
			//select index 순서대로 > 1부터 , 컬럼이나 별명 이름으로 가져올 때는 String으로 가져오기
			//if로 쓰기!
			
			//cursor 옮기기 : 데이터가 있니 없니?
			if(rs.next()) {
				
													//컬럼 이름 or Alias 이름
				String grade = rs.getString("grade");
				
				//로그인이 성공하면 등급 반환
				return grade;
			}
			
			//6. 자원해제
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("[오류] 로그인 실패");
			e.printStackTrace();
		}
		//회원 아이디가 존재하지 않을 때
		return null;
	}



 /**
  * 회원 전체 조회
  * @return
  */
	public ArrayList<Member> selectAll() {

		ArrayList<Member> members = new ArrayList<Member>();
		
		try{

			Connection conn = DriverManager.getConnection(url, user, password);
			
			
			String sql = "select * from member ";
			PreparedStatement stmt = conn.prepareStatement(sql);
	
			ResultSet rs = stmt.executeQuery();
			
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
			
			//6. 자원해제
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("[오류] 회원 전체 조회 실패");
			e.printStackTrace();
		}
	
		return members;
		
	}
	
	/**
	 * 회원 등급 별 전체 조회
	 * @param grade
	 * @return
	 */
	public ArrayList<Member> selectAllByGrade(String grade) {
		
	ArrayList<Member> members = new ArrayList<Member>();
		
		try{

			Connection conn = DriverManager.getConnection(url, user, password);
			
			
			String sql = "select * from member where grade=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, grade);
	
			ResultSet rs = stmt.executeQuery();
			
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
			
			//6. 자원해제
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("[오류] 회원 전체 조회 실패");
			e.printStackTrace();
		}
	
		return members;
		
		
		
	}


	/**
	 *  이메일 조회  
	 */

	public String idCheck(String email) {
			
			try {
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = "select email from member where email =? ";
					
			PreparedStatement stmt = conn.prepareStatement(sql);
					
			stmt.setString(1, email);
	
			ResultSet rs = stmt.executeQuery();
			
		
			if(rs.next()) {
													
				email = rs.getString("email");
				
				//이메일이 중복이면 이메일을 보낸다
				return email;
			}
			
			//6. 자원해제
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("[오류] 중복 확인 실패");
			e.printStackTrace();
		}
		//회원 이메일이 없으면 때 
		return null;
	}





	
}