package JavaExam;

public class basic0201 {

	public static final double PI = Math.PI;
	private double radius;
	public basic0201(double radius) {
		this.radius = radius;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double getArea() {
		return radius * PI;
	}
}
