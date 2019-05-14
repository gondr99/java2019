package main;

public class Teacher extends Human{
	private String major;
	
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Override
	public void introduce() {
		System.out.println("교사 : " + name + ", " + age + ", " + major);		
	}
	
	public void ready() {
		System.out.println(name + " : " + major + " 수업 준비중");
	}
}
