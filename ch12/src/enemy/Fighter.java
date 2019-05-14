package enemy;

public class Fighter extends Enemy{
	private int ammo;
	
	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}

	public Fighter(int hp, double x , double y, double speedX, double speedY, int ammo) {
		super(hp, x, y, speedX, speedY);
		this.ammo = ammo;
	}
	
	public void report() {
		System.out.println("x : " + x + ", y : " + y + ", sX : " + speedX + ", sY : " + speedY);
	}
	
	public void spin(double speedX, double speedY) {
		if(hp <= 0) {
			System.out.println("선회 불가 : 이미 폭파됨");
		}
		this.speedX = speedX;
		this.speedY = speedY;
		hp--;
		if(hp <= 0) {
			System.out.println("폭발");
		}
	}
	
	public void move() {
		if(hp <= 0) {
			System.out.println("이동 불가 : 이미 폭파됨");
		}
		super.move();
	}
	
	public void attack() {
		if(hp <= 0) System.out.println("공격불가 : 이미 폭파됨");
		if(ammo <= 0) System.out.println("공격불가 : 탄환없음");
		System.out.println("공격");
		ammo--;
	}
}
