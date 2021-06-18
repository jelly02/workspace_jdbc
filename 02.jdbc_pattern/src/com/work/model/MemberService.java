package com.work.model;

import java.util.ArrayList;
import java.util.List;

public class MemberService {
	
	// (sevice > dao) : 서비스 안에서만 dao를 사용하기 때문에 private
	/**
	 * MemberDao 객체
	 * 
	 * DAO 생성자를 호출해서 호출함과 동시에 드라이버를 로딩
	 */
	
	//singleton Pattern 적용  > The constructor MemberDao() is not visible (private 에 접근할 때)
//	private MemberDao dao = new MemberDao();
	private MemberDao dao = MemberDao.getInstance();
	
	
/**
 *  로그인 
 * @param member_id 아이디
 * @param member_pw 비밀번호
 * @return 회원등급, 미존재시 null
 */
	public String login(String member_id, String member_pw) {
				
								//DB로 부터 가져오는 데이터
			String grade = dao.login(member_id, member_pw);
			
			if (grade != null)
			{
				//dao에서 전달 받은 등급이 null이 아니면 해당 아이디를 가진 회원이 존재한다는 의미
				// test class에다가 넘겨주자
				return grade;
			}
			
			//아이디가 존재하지 않거나, 비밀번호가 틀려서 로그인이 실패할 경우 
			return null;
	}
	
	/**
	 * 회원 상세 조회
	 * 
	 * @param memberId
	 * @return
	 */
	
	
	public Member getMember(String memberId) {
		
		//Member dto = dao.selectOne(memberId);
		// return dto;
		
		//어차피 return type이 member라서 
		return dao.selectOne(memberId);
	}

	/**
	 *  회원 전체 조회
	 * @return
	 */
	public  ArrayList<Member>  getAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}
	
	/**
	 *  등급별  회원 전체 조회
	 * @return
	 */
	public  ArrayList<Member>  selectAllByGrade(String grade) {
		// TODO Auto-generated method stub
		return dao.selectAllByGrade(grade);
	}
	
	/**
	 *  이메일 중복 확인 
	 */

	public String idCheck(String email) {
		
		return dao.idCheck(email);
		
	}



	/**
	 * 비밀번호 찾기 
	 * @param memberId
	 * @param name
	 * @param email
	 * @return
	 */
	public String findPw(String memberId, String name, String email) {
		
		//1. 해당 회원 존재 유무 확인
		boolean result = dao.findPw(memberId, name, email);
		
		
		//2. 
		if(result) {
			//임시 번호 발급
			String memberNewPw = "newpass";
			//dao 변경 요청
			int result2 = updateMemberPw(memberId, memberNewPw);
		
			if(result2 > 0 ) {
				return memberNewPw;
			}
		}
		
		return null;
	}


	/**
	 * 비밀번호 변경 
	 * @param memberId
	 * @param memberNewPw
	 * @return
	 */
	public int updateMemberPw(String memberId, String memberNewPw) {
		return dao.updateMemberPw(memberId, memberNewPw);
	}
}
