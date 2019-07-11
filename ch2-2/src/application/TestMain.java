package application;

public class TestMain {
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			private int a = 10;
			@Override
			public void run() {
				System.out.println("익명클래스입니다. " + a);				
			}
		};
		
		r.run();
	}
}
