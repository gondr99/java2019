package enemy;

public class Scouter {
	private int hp;
	private double speedX, speedY, x, y;
	
	public Scouter(int hp, double x, double y, double sx, double sy) {
		this.hp = hp;
		this.speedX = sx;
		this.speedY = sy;
		this.x = x;
		this.y = y;
	}
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public double getSpeedY() {
		return speedY;
	}

	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void move() {
		this.x += this.speedX;
		this.y += this.speedY;
	}
	
	public void scan() {
		System.out.println("스캔 개시");
	}
}
