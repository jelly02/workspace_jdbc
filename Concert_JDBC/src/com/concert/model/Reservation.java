package com.concert.model;

public class Reservation {
	
	/** 아이디, 식별키 */
	private String memberId;
	
	/** 공연 코드, 필수 */
	private String infoCode;
	
	
	/** 좌석 정보, 필수
	 * vip > v, s > s, r > r */
	private String seat;
	
	/** 좌석 번호, 좌석 번호 */
	private int seatNum;
	
	public Reservation() {}

	/**
	 * 필수 데이터 생성자
	 * 
	 * @param memberId
	 * @param infoCode
	 * @param seat
	 * @param seatNum
	 */
	public Reservation(String memberId, String infoCode, String seat, int seatNum) {
		super();
		this.memberId = memberId;
		this.infoCode = infoCode;
		this.seat = seat;
		this.seatNum = seatNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getInfoCode() {
		return infoCode;
	}

	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	@Override
	public String toString() {
		return " memberId=" + memberId + "infoCode=" + infoCode + " / 좌석 타입 =" + seat + " 좌석 "+ seatNum + "";
	}
	
	
	public String getPrint() {
		return "공연 코드 : " + infoCode + "좌석 타입 : " + seat + " 좌석 번호 : "+ seatNum;
	}
	


}
