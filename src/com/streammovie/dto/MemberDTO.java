package com.streammovie.dto;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDTO {
	
	private String id;
	private String pwd;
	private String nickname;
	private String email;
	private String signature;
	private String profimg;
	private String phone;
	private String address;
	private String mailing;
	private Date regdate;
	private Timestamp logdate;
	private String useyn;
	private int point;
	private int grade;
	
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pwd=" + pwd + ", nickname=" + nickname + ", email=" + email + ", signature="
				+ signature + ", profimg=" + profimg + ", phone=" + phone + ", address=" + address + ", mailing="
				+ mailing + ", regdate=" + regdate + ", logdate=" + logdate + ", useyn=" + useyn + ", point=" + point
				+ ", grade=" + grade + "]";
	}
	
}
