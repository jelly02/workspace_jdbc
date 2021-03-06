package com.work.model;

import java.io.Serializable;

public class Member implements Serializable {
	
	private String member_id;
	private String member_pw;
	private String name;
	private String mobile;
	private String email;
	private String entry_date ;
	private String grade;
	private int mileage;
	private String manager;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String member_id, String member_pw, String name, String mobile, String email, String entry_date,
			String grade, int mileage, String manager) {
		super();
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.entry_date = entry_date;
		this.grade = grade;
		this.mileage = mileage;
		this.manager = manager;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(String entry_date) {
		this.entry_date = entry_date;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Member [member_id=" + member_id + ", member_pw=" + member_pw + ", name=" + name + ", mobile=" + mobile
				+ ", email=" + email + ", entry_date=" + entry_date + ", grade=" + grade + ", mileage=" + mileage
				+ ", manager=" + manager + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((member_id == null) ? 0 : member_id.hashCode());
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
		if (member_id == null) {
			if (other.member_id != null)
				return false;
		} else if (!member_id.equals(other.member_id))
			return false;
		return true;
	}
	
	
	

}
