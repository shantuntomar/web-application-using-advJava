package in.shantun.dto;

import java.io.Serializable;

public class Student implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer sid;
	private String sname;
	private int sage;
	private String saddr;
	
	public void setId(Integer id) {
		this.sid = id;
	}
	
	public Integer getId() {
		return this.sid;
	}
	
	public void setName(String sname) {
		this.sname = sname;
	}
	
	public String getName() {
		return this.sname;
	}
	
	public void setAge(int sage) {
		this.sage = sage;
	}
	
	public int getAge() {
		return this.sage;
	}
	
	public void setAddr(String saddr) {
		this.saddr = saddr;
	}
	
	public String getAddr() {
		return this.saddr;
	}
	
	public String toString() {
		return "[NAME = " + sname + " , AGE = " + sage + " , ADDRESS = " + saddr + " ] ";
	}

}
