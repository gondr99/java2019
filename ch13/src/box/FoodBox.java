package box;

public class FoodBox implements Box{

	@Override
	public void open() {
		System.out.println("음식 박스를 열어봅니다.");		
	}

	@Override
	public void put() {
		System.out.println("음식을 박스에 넣습니다.");		
	}

	@Override
	public void get() {
		System.out.println("음식을 꺼냈습니다.");		
	}
}
