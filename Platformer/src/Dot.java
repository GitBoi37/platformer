
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
public class Dot implements GameObject{
    private int y;
    private int x;
    private int size;
    private Color color;
    public Constants constants = new Constants();
    public Dot(){
    	size = 3;
    	Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        x = (int)(Math.random() * d.getWidth() - size) + 5;
        y = (int)(Math.random() * (d.getHeight() - size - 15));
        color = Color.YELLOW;
    }
    public Dot(int top, int bottom, int left, int right, int buffer){
    	//dots.add(new Dot(10, 20, (int)d.getHeight()-10, 20, 10, 20, (int)d.getWidth()-10, 20));
    	size = 3;
    	color = Color.YELLOW;
        boolean leftT = false;
        boolean topT = false;
        boolean topLeft = false;
        int temp = (int)(Math.random()*2);
        if(temp == 0){
            topT = true;
        }
        else{
            topT = false;
        }
        temp = (int)(Math.random()*2);
        if(temp == 0){
            leftT = false;
        }
        else{
            leftT  = true;
        }
        temp = (int)(Math.random()*2);
        if(temp == 0) {
        	topLeft = false;
        }
        else {
        	topLeft = true;
        }
        if(!topLeft) {
        	if(leftT){
        		x = (int)(Math.random()*buffer)+left;
          		y = (int)(Math.random()*bottom-top) + top;
        	}
        	else{
        		x = (int)(Math.random()*buffer)+right-buffer;
        		y = (int)(Math.random()*bottom - top) + top;
        	}
        }
        else {
        	if(topT){
        		x = (int)(Math.random()*right-left) + left;
        		y= (int)(Math.random()*buffer) + top;//fix all of this
        	}
        	else{
        		x = (int)(Math.random()*right-left) + left;
        		y = (int)(Math.random()*buffer) + bottom - buffer;
        	}
        }
    }
    //compliance with implementing GameObject
    public void shiftX(int x){
        this.x -= x;
    }
    public int[] getPos(){
        int[] pos = {x,y};
        return pos;
    }
    public Rectangle getRect(){
        Rectangle john = new Rectangle(x,y,size,size);
        return john;
    }

    //no fucking clue what this is here for
    public void randomize(){
        Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        x = (int)(Math.random() * d.getWidth() - size) + 5;
        y = (int)(Math.random() * (d.getHeight() - size - 15));
    }
    public void modifyX(int dX){x += dX;}
    public void modifyY(int dY){y += dY;}
    public int getX(){return x;}
    public int getY(){return y;}
    public int getSize(){return size;}
    public Color getColor(){return color;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public void setSize(int size){this.size = size;}
    public void setColor(Color color){this.color = color;}
    public Rectangle bounds() {return (new Rectangle(x,y,size,size));}
}
