package com.work.view;

import java.util.ArrayList;
import com.work.model.Member;
import com.work.model.MemberService;

public class Test {

	public static void main(String[] args) {
		
		MemberService service = new MemberService();

		print("로그인");
		
		String grade = service.login("user01", "password01");
		
		if (grade != null) {
			System.out.println("[안내] 로그인 성공 : "+grade);
		}else {
			System.out.println("[안내] 로그인 실패 : 정확한 회원 정보를 적어주세요.");
		}
		
		print("로그인");
		
		grade = service.login("user05", "password05");
		
		if (grade != null) {
			System.out.println("[안내] 로그인 성공 : "+grade);
		}else {
			System.out.println("[안내] 로그인 실패 : 정확한 회원 정보를 적어주세요.");
		}
		
		print("회원 상세 조회 : user03");
		Member dto = service.getMember("user03");
		
		if (dto != null) {
			System.out.println(dto);
		}else {
			System.out.println("[안내] 내정보조회 실패 : 회원 정보가 존재하지 않습니다");
		}
		
		print("회원 전체 조회");
		ArrayList<Member> members = service.getAll();
		
		for(Member member : members){
	        System.out.println("member : " + member);
	        }    
		
		print("등급별 회원 전체 조회 : 일반회원 ");
		
		members = service.selectAllByGrade("G");
		for(Member member : members){
	        System.out.println("member : " + member);
	        }    
		
		print("등급별 회원 전체 조회 : 우수회원 ");
				
				members = service.selectAllByGrade("S");
				for(Member member : members){
			        System.out.println("member : " + member);
			        }    
				
		print("등급별 회원 전체 조회 : 관리자 회원 ");
				
				members = service.selectAllByGrade("A");
				for(Member member : members){
			        System.out.println("member : " + member);
			        }    
				
		print("이메일 중복 조회");
		
		String email = service.idCheck("user0777@work.com");
		
		if (email != null) {
			System.out.println("[안내] "+email+" 이 이메일은 사용할 수 없습니다");
		}else {
			System.out.println("[안내] 이 이메일은 사용 할 수 있습니다");
		}
		
		
		print("회원 유무 체크");
		
		String result = service.findPw("user01", "홍길동", "user01@work.com");
		System.out.println(result);
		
	}
	
	public static void print(String message) {
		System.out.println("\n###"+message);
	}

}

































