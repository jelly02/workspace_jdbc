package com.concert.model;

/**
 *  Member 도메인 클래스 
 * @author KIMJURI
 *
 */
public class Member {

	/** 아이디, 식별키 */
	private String memberId;
	
	/** 비밀번호, 필수 */
	private String memberPw;
	
	/** 이름, 필수 */
	private String name;
	
	/** 휴대폰, 필수, 형식 01012341234 */
	private String mobile;
	
	/** 등급, 필수, 일반(G), 관리자(A), 시스템 제공 */
	private String grade;
	
	/**공연 결제 용 캐시*/
	int money;
	
	/**Member 기본 생성자 */
	public Member() {}

	
	/**
	 * 회원 필수 데이터 초기화 생성자
	 * 
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @param name 이름
	 * @param mobile 휴대폰
	 * @param grade 등급
	 * @param money 캐시
	 */
	public Member(String memberId, String memberPw, String name, String mobile, String grade, int money) {
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.name = name;
		this.mobile = mobile;
		this.grade = grade;
		this.money = money;
	}


	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}


	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	/**
	 * @return the memberPw
	 */
	public String getMemberPw() {
		return memberPw;
	}


	/**
	 * @param memberPw the memberPw to set
	 */
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}


	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}


	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}


	/**
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}


	/**
	 * @param money the money to set
	 */
	public void setMoney(int money) {
		this.money = money;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(memberId);
		builder.append(", ");
		builder.append(memberPw);
		builder.append(", ");
		builder.append(name);
		builder.append(", ");
		builder.append(mobile);
		builder.append(", ");
		builder.append(grade);
		builder.append(", ");
		builder.append(money);
		return builder.toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		return true;
	}


	
	
	
	
	
}
