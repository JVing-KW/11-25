package com.standout.sopang.mypage.vo;


import lombok.*;
import org.springframework.stereotype.Component;


//나중에 @Setter ㅈ우기
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component("myPageVO")
public class MyPageVO {
	private String member_id;
	private String beginDate;
	private String endDate;


	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
