
public class GoodDot extends Dot{
    private int scoreValue = 0;
    private int sliding = 0;
    public GoodDot(){
        super();
        scoreValue = constants.DOTVALUE;
    }
    public GoodDot(int top, int bottom, int left, int right, int buffer){
        super(top,bottom,left,right,buffer);
    }
    public GoodDot(int top, int bottom, int left, int right, int buffer, int v){
        this.scoreValue = v;
    }
    public boolean inProximity(int[] pos){
        boolean proxim = false;
        if(Math.abs(getX() - pos[0]) < constants.DOTPROXIM && Math.abs(getX() - pos[1]) < constants.DOTPROXIM){
            return true;
        }
        else{
            return false;
        }
    }
}
