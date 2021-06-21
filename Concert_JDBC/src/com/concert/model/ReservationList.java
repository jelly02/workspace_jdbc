package com.concert.model;

public class ReservationList {
	
	/** 아이디, 식별키 */
	private String memberId;
	
	/** 공연 코드, 필수 */
	private String infoCode;
	
	/** 공연 이름, 필수 */
	private String infoName;
	
	
	/** 좌석 정보, 필수
	 * vip > v, s > s, r > r */
	private String seat;
	
	/** 좌석 번호, 좌석 번호 */
	private int seatNum;
	
	public ReservationList() {}

	public ReservationList(String memberId, String infoCode, String infoName, String seat, int seatNum) {
		super();
		this.memberId = memberId;
		this.infoCode = infoCode;
		this.infoName = infoName;
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

	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
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
		return "ReservationList [memberId=" + memberId + ", infoCode=" + infoCode + ", infoName=" + infoName + ", seat="
				+ seat + ", seatNum=" + seatNum + "]";
	}




}
