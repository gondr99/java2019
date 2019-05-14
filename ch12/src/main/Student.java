package main;

public class Student extends Human {
	private String code;
	private Double kor = 0d;
	private Double math = 0d;
	private Double eng = 0d;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getKor() {
		return kor;
	}

	public void setKor(Double kor) {
		this.kor = kor;
	}

	public Double getMath() {
		return math;
	}

	public void setMath(Double math) {
		this.math = math;
	}

	public Double getEng() {
		return eng;
	}

	public void setEng(Double eng) {
		this.eng = eng;
	}

	@Override
	public void introduce() {
		System.out.println("학생 : " + name + ", " + code + ", " + age);
	}
	
	public void study() {
		math++;
		eng++;
		kor++;
	}
	
	public void grade() {
		if(kor != 0)
			System.out.println("국어 : " + kor);
		else 
			System.out.println("국어 : 없음");
		
		if(eng != 0)
			System.out.println("영어 : " + eng);
		else 
			System.out.println("영어 : 없음");
		
		if(math != 0)
			System.out.println("수학 : " + math);
		else 
			System.out.println("수학 : 없음");
	}
}
