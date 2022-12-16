package servlet;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MemberBean {
	private String name;
	private String uid;
	private String pwd;
	private String phone;
	private String email;
	private Date createDate;
	private String allow;
	
	public MemberBean() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberBean(String uid, String pwd, String name, String phone, String email) {
		this.uid=uid;
		this.pwd=pwd;
		this.name=name;
		this.phone=phone;
		this.email=email;
	}
}
