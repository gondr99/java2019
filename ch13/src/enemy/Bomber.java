package enemy;

public class Bomber implements Enemy{
	private int hp;
	private double x, y, w, h; 
	
	public Bomber(int hp, double x, double y, double width, double height) {
		this.hp = hp;
		this.x = x;
		this.y = y;
		this.w = width;
		this.h = height;
	}

	@Override
	public boolean checkCollision(double x, double y) {
		if(this.x <= x && this.y <= y && this.x + w >= x && this.y + h >= y) {
			return true;
		}
		return false;
	}

	@Override
	public void hit(int damage) {
		hp -= damage;
		if(hp <= 0) {
			destroy();
		}
	}

	@Override
	public void destroy() {
		System.out.println("봄버 죽었쪄염. 뿌우.");		
	}
	
}
