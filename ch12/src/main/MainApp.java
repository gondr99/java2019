package main;

public class MainApp {
	public static void main(String[] args) {
		Student stu = new Student();
		stu.setName("최선한");
		stu.setAge(18);
		stu.setCode("20322");
		
		stu.introduce();
		
		stu.setKor(80.0);
		stu.grade();
		stu.study();
		stu.grade();
		
		Teacher tea = new Teacher();
		tea.setName("최티쳐");
		tea.setAge(40);
		tea.setMajor("프로그래밍");
		
		tea.introduce();
		tea.ready();
	}
}