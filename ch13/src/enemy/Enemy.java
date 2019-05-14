package enemy;

public interface Enemy {
	public boolean checkCollision(double x, double y);
	public void hit(int damage);
	public void destroy();
}