import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JOptionPane;
import java.awt.event.*;
public class Game extends JPanel implements Runnable, KeyListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Thread animator;
    private final int DELAY = 5;
    Constants constants = new Constants();
    private int tickCounter = 0;
    private int gravity = 2;
    private ArrayList<Platform> platforms = new ArrayList<Platform>();
    private ArrayList<GameObject> all = new ArrayList<GameObject>();
    private Square player = new Square();
    private int score = 0;
    public Game() {
        super();
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        player.setX((int)d.getWidth()/2);
        player.setY((int)d.getHeight()/2);
        player.setNewX((int)d.getWidth()/2);
        player.setNewY((int)d.getHeight()/2);
        setPreferredSize(d);
        platforms.add(new Platform(150, (int)d.getHeight() - 100, 100, 10));
        //all.add(new Platform(150, (int)d.getHeight() - 100, 100, 10));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        
        g2d.setColor(Color.BLUE);
        g2d.setClip(player.getRect());
        g2d.fill(player.getRect());
        g2d.setColor(Color.WHITE);
        for(int i = 0; i < all.size(); i++) {
        	g2d.setClip(all.get(i).getRect());
        	g2d.fill(all.get(i).getRect());
        }
        for(int i = 0; i < platforms.size(); i++){
        	g2d.setClip(platforms.get(i).getRect());
        	g2d.fill(platforms.get(i).getRect());
        }
        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g2d.setClip(100, 50, 500, 100);
        g2d.drawString("Score: " + String.valueOf(score), 150, 100);
        Toolkit.getDefaultToolkit().sync();
    }
    public void keyTyped(KeyEvent key){ } 
    public void keyReleased(KeyEvent key){
        if (key.getKeyCode() == KeyEvent.VK_A) {
            player.modifyX(0);
        }

        if (key.getKeyCode() == KeyEvent.VK_S) {
            player.modifyX(0);
        }

        if (key.getKeyCode() == KeyEvent.VK_D) {
            player.modifyY(0);
        }

        if (key.getKeyCode() == KeyEvent.VK_W) {
            player.modifyY(0);
        }
    }
    public void keyPressed(KeyEvent key){
        if(key.getKeyCode() == KeyEvent.VK_A){
            player.setNewX(player.getX()-(int)(Math.random()*(constants.speedRange[1] - constants.speedRange[0]) + constants.speedRange[0]));
        }
        if(key.getKeyCode() == KeyEvent.VK_S){
            player.setNewY(player.getY()+(int)(Math.random()*(constants.speedRange[1] - constants.speedRange[0]) + constants.speedRange[0]));
        }
        if(key.getKeyCode() == KeyEvent.VK_D){
            player.setNewX(player.getX()+(int)(Math.random()*(constants.speedRange[1] - constants.speedRange[0]) + constants.speedRange[0]));
        }
        if(key.getKeyCode() == KeyEvent.VK_W){
        	player.setY(player.getY() - constants.LERP);
            player.setNewY(player.getY()- constants.JUMP);
        }
        if(key.getKeyCode() == KeyEvent.VK_Q) {
            if(player.getSize()>20) {
                player.setSize(player.getSize()-10);
            }
        }
        if(key.getKeyCode() == KeyEvent.VK_E) {
            if(player.getSize() < 2000) {
                player.setSize(player.getSize()+10);
            }
        }
    }
    @Override
    public void addNotify(){
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }
    public void spawnPlatforms() {
    	Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	int spawnY = (int)(Math.random() * (d.getHeight() - (int)d.getHeight()/2)) + (int)d.getHeight()/2;
    	Platform platToAdd = new Platform((int)d.getWidth(), spawnY, constants.DEFAULTWIDTH, constants.DEFAULTHEIGHT);
    	platforms.add(platToAdd);
    }
    public void check(){
    	//shift scenery
    	for(int i = 0; i < all.size(); i++) {
    		if(tickCounter % constants.UPDATE == 0) {
    			all.get(i).shiftX(constants.SHIFT);
    		}
    	}
    	for(GameObject e : platforms) {
    		if(tickCounter % constants.UPDATE == 0) {
    			e.shiftX(constants.SHIFT);
    		}
    	}
    	//check size and bounds
        if(player.getSize() < constants.minSize) {
            player.setSize(constants.minSize);
        }
        if(player.getX() < 0) {
            player.setX(0);
            player.setNewX(0);
        }
        if(player.getY() < 0) {
            player.setY(0);
            player.setNewY(0);
        }
        if(player.getX() > this.getWidth() - player.getSize() - 3) {
            player.setX(this.getWidth() - player.getSize() - 5);
            player.setNewX(this.getWidth() - player.getSize() - 5);
        }
        if(player.getY() >= this.getHeight() - player.getSize() - 17) {
        	gravity = 0;
            player.setY(this.getHeight() - player.getSize()- 17);
            player.setNewY(this.getHeight() - player.getSize()- 17);
        }
        else {
        	gravity = 3;
        }

        //collision detection for platforms
        for(int i = 0; i < platforms.size(); i++) {
        	Rectangle r = platforms.get(i).getRect();
        	if(player.getY() + player.getSize() < r.getY() && player.getY() + player.getSize() > r.getY() - 4) {
        		if(player.getX() + player.getSize() > r.getX() && player.getX() < r.getX() + r.getWidth()) {
        			gravity = 0;
        		}
        	}
        	
        }
        player.setNewY(player.getNewY()+gravity);        
        
        //moving the player by lerp
        if(Math.abs(player.getNewX() - player.getX()) >= constants.LERP) {
            if(player.getNewX() >= player.getX()) {
                player.setX(player.getX() + constants.LERP);
            }
            else {
                player.setX(player.getX() - constants.LERP);
            }
        }
        else {
            player.setX(player.getNewX());
        }
        if(Math.abs(player.getNewY() - player.getY()) >= constants.LERP) {
            if(player.getNewY() >= player.getY()) {
                player.setY(player.getY() + constants.LERP);
            }
            else {
                player.setY(player.getY() - constants.LERP);
            }
        }
        else {
            player.setY(player.getNewY());
        }
        
    }
    public void deexplode(){
        player.modifySize(-1);
        if(player.getSize() < 2){
            player.setSize(2);
        } 
        if(player.getY() > this.getHeight() -player.getSize() -5) {
            player.modifyY(1);
        }
    }
    public void explode(){
        player.modifySize(1);
    }
    @Override
    public void run(){
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        while(true){
        	tickCounter++;
            repaint();
            check();
            if(tickCounter % constants.SPAWNPLATFORMDELAY == 0) {
            	spawnPlatforms();
            }
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;
            if(sleep < 0){
                sleep = 2;
            }
            try{
                Thread.sleep(sleep);
            } catch (InterruptedException e){
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                JOptionPane.showMessageDialog(this,msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
            beforeTime = System.currentTimeMillis();
        }
    }
}
