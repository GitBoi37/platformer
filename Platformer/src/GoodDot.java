
public class GoodDot extends Dot{
    private int scoreValue = 0;
    private int sliding = 0;
    public GoodDot(){
        super();
        setScoreValue(constants.DOTVALUE);
    }
    public GoodDot(int top, int bottom, int left, int right, int buffer){
        super(top,bottom,left,right,buffer);
        setScoreValue(constants.DOTVALUE);
    }
    public GoodDot(int top, int bottom, int left, int right, int buffer, int v){
    	super(top,bottom,left,right,buffer);
        this.setScoreValue(v);
    }
    public boolean inProximity(int[] pos){
        if(Math.abs(getX() - pos[0]) < constants.DOTPROXIM && Math.abs(getX() - pos[1]) < constants.DOTPROXIM){
            return true;
        }
        else{
            return false;
        }
    }
	public int getScoreValue() {
		return scoreValue;
	}
	public void setScoreValue(int scoreValue) {
		this.scoreValue = scoreValue;
	}
	public int getSliding() {
		return sliding;
	}
	public void setSliding(int sliding) {
		this.sliding = sliding;
	}
}
