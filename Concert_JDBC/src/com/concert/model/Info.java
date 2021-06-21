package com.concert.model;

/**
 *  공연 정보 도메인 클래스 
 * @author KIMJURI
 *
 */

public class Info {

	/** 공연 코드, 식별키 */
	private String infoCode;
	
	/** 공연 이름, 필수 */
	private String infoName;
	
	/** 공연 날짜, 필수 */
	private String infoDate;
	
	/** 공연 시간, 필수 */
	private String infoTime;
	
	/** 공연 여주인공, 필수 */
	private String infoFemaleActor;
	
	/** 공연 남주인공, 필수 */
	private String infoMaleActor;
	
	/** 공연 가격, 필수 */
//	private int ticketPrice;
	
	/** 기본생정자 */
	public Info() {}

	
	/**
	 * 공연 필수 생성자 
	 * 
	 * @param infoCode 공연 코드
	 * @param infoName 공연 이름
	 * @param infoDate 공연 날짜
	 * @param infoTime 공연 시간
	 * @param infoFemaleActor 공연 여주인공
	 * @param infoMaleActor 공연 남주인공
	 * @param ticketPrice 공연 가격
	 */
	public Info(String infoCode, String infoName, String infoDate, String infoTime, String infoFemaleActor,
			String infoMaleActor) {
		this.infoCode = infoCode;
		this.infoName = infoName;
		this.infoDate = infoDate;
		this.infoTime = infoTime;
		this.infoFemaleActor = infoFemaleActor;
		this.infoMaleActor = infoMaleActor;
	}


	/**
	 * @return the infoCode
	 */
	public String getInfoCode() {
		return infoCode;
	}


	/**
	 * @param infoCode the infoCode to set
	 */
	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}


	/**
	 * @return the infoName
	 */
	public String getInfoName() {
		return infoName;
	}


	/**
	 * @param infoName the infoName to set
	 */
	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}


	/**
	 * @return the infoDate
	 */
	public String getInfoDate() {
		return infoDate;
	}


	/**
	 * @param infoDate the infoDate to set
	 */
	public void setInfoDate(String infoDate) {
		this.infoDate = infoDate;
	}


	/**
	 * @return the infoTime
	 */
	public String getInfoTime() {
		return infoTime;
	}


	/**
	 * @param infoTime the infoTime to set
	 */
	public void setInfoTime(String infoTime) {
		this.infoTime = infoTime;
	}


	/**
	 * @return the infoFemaleActor
	 */
	public String getInfoFemaleActor() {
		return infoFemaleActor;
	}


	/**
	 * @param infoFemaleActor the infoFemaleActor to set
	 */
	public void setInfoFemaleActor(String infoFemaleActor) {
		this.infoFemaleActor = infoFemaleActor;
	}


	/**
	 * @return the infoMaleActor
	 */
	public String getInfoMaleActor() {
		return infoMaleActor;
	}


	/**
	 * @param infoMaleActor the infoMaleActor to set
	 */
	public void setInfoMaleActor(String infoMaleActor) {
		this.infoMaleActor = infoMaleActor;
	}




	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(infoCode);
		builder.append(", ");
		builder.append(infoName);
		builder.append(", ");
		builder.append(infoDate);
		builder.append(", ");
		builder.append(infoTime);
		builder.append(", ");
		builder.append(infoFemaleActor);
		builder.append(", ");
		builder.append(infoMaleActor);
		builder.append(", ");
		return builder.toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((infoCode == null) ? 0 : infoCode.hashCode());
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
		Info other = (Info) obj;
		if (infoCode == null) {
			if (other.infoCode != null)
				return false;
		} else if (!infoCode.equals(other.infoCode))
			return false;
		return true;
	};
	
	
	
	
	
	
}
