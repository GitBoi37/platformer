import java.awt.Rectangle;

public interface GameObject {
	public abstract Rectangle getRect();
	public int[] getPos();
	public void shiftX(int x);
}
