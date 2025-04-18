package net.scit.spring3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//public class PhoneDTO {
//	private String name;
//	private String phone;
//	private String addr;
//	private Boolean type;	//Boolean 객체타입 boolean 원시타입
//	
//	//생성자
//	public PhoneDTO() {
//		System.out.println("기본생성자");
//	}
//
//	//Getter & Setter
//	public String getName() {
//		return name;
//	}
//
//	public String getPhone() {
//		return phone;
//	}
//
//	public String getAddr() {
//		return addr;
//	}
//
//	public Boolean getType() {
//		return type;
//	}
//
//	public void setName(String name) {
//		System.out.println("setName");
//		this.name = name;
//	}
//
//	public void setPhone(String phone) {
//		System.out.println("setPhone");
//		this.phone = phone;
//	}
//
//	public void setAddr(String addr) {
//		System.out.println("setAddr");
//		this.addr = addr;
//	}
//
//	public void setType(Boolean type) {
//		System.out.println("setType");
//		this.type = type;
//	}
//
//	@Override
//	public String toString() {
//		return "PhoneDTO [name=" + name + ", phone=" + phone + ", addr=" + addr + ", type=" + type + "]";
//	}
//	
//}

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PhoneDTO{
	private String name;
	private String phone;
	private String addr;
	private Boolean type;
}
