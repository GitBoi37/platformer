
public class FollowDot extends Dot{
    private int damage = 0;
    public FollowDot(){
        super();
        setDamage(constants.DOTDAMAGE);
    }
    public FollowDot(int top, int bottom, int left, int right, int buffer){
    	//dots.add(new Dot(10, 20, (int)d.getHeight()-10, 20, 10, 20, (int)d.getWidth()-10, 20));
    	super(top,bottom,left,right,buffer);
    }
    public FollowDot(int top, int bottom, int left, int right, int buffer, int damage){
    	//dots.add(new Dot(10, 20, (int)d.getHeight()-10, 20, 10, 20, (int)d.getWidth()-10, 20));
    	this.setDamage(constants.DOTDAMAGE);
    }
    //pathfinding to wherever, might make better in the future, could lerp it
    //time to LEERRRPPPPP
    public void pathfind(int xN, int yN, int size) {
    	if(xN > getX() - size/2) {
    		modifyX(constants.DOTSPEED);
    	}
    	else {
    		modifyX(-constants.DOTSPEED);
    	}
    	if(yN > getY() - size/2) {
    		modifyY(constants.DOTSPEED);
    	}
    	else {
    		modifyY(-constants.DOTSPEED);
    	}
    }
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
}
