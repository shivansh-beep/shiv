package com.shivansh.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class MailInfoDATO {
	@Id
	@GenericGenerator(name="auto",strategy="increment")
	@GeneratedValue(generator="auto")
	private int mid;
	private String uinbox;
	private String usent;
	private String udraft;
	private String uto;
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getUinbox() {
		return uinbox;
	}
	public void setUinbox(String uinbox) {
		this.uinbox = uinbox;
	}
	public String getUsent() {
		return usent;
	}
	public void setUsent(String usent) {
		this.usent = usent;
	}
	public String getUdraft() {
		return udraft;
	}
	public void setUdraft(String udraft) {
		this.udraft = udraft;
	}
	public String getUto() {
		return uto;
	}
	public void setUto(String uto) {
		this.uto = uto;
	}
	
}
