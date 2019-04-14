
import java.awt.Rectangle;

public class Square implements GameObject{
	private int x = 0;
	private int y = 0;
	private int size = 50;
	private int newX = 0;
	private int newY = 0;
	public Square() {}
	public void shiftX(int x) {
		this.x -= x;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int[] getPos() {
		int[] e =  {x,y};
		return e;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Rectangle getRect() {
		return new Rectangle(x,y,size,size);
	}
	public void modifyX(int x) {
		this.x += x;
	}
	public void modifyY(int y) {
		this.y += y;
	}
	public void modifySize(int modify) {
		size+=modify;
	}
	public int getNewX() {
		return newX;
	}
	public void setNewX(int newX) {
		this.newX = newX;
	}
	public int getNewY() {
		return newY;
	}
	public void setNewY(int newY) {
		this.newY = newY;
	}
}
