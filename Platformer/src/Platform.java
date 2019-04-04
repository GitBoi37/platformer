import java.awt.Color;
import java.awt.Rectangle;
public class Platform extends Rectangle implements GameObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6212064077418096128L;
	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	public Platform() {
		x = 0;
		y = 0;
		width = 100;
		height = 10;
	}
	public Platform(int a, int b, int c, int d, Color e) {
		x = a;
		y = b;
		width = c;
		height = d;
		color = e;
	}
	public Platform(int a, int b, int c, int d) {
		x = a;
		y = b;
		width = c;
		height = d;
		color = Color.WHITE;
	}
	public void shiftX(int in) {
		x = x + in;
	}
	public int[] getPos() {
		int[] e = {x,y};
		return e;
	}
	public Rectangle getRect() {
		return new Rectangle(x,y,width,height);
	}
	public double getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
}
