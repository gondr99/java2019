package application;

public class TestMain {
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			public void run() {
				System.out.println("익명 클래스입니다.");
			}
		};
		
		Runnable r2 = () -> {
			System.out.println("익명클래스 2");
		};
		
		r.run();
		r2.run();
	}
}
