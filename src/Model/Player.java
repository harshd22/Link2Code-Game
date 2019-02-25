package Model;

import java.awt.Graphics2D;
import java.beans.Transient;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Observer;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Player class that uses the Entity interface, handles movement and passing of the relevant sprite
 * 
 * @Author Sim
 * Review = Jeremy Logan
 * 
 */
public class Player extends Entity implements Serializable {
    
    //The constructor will call in the sprite and map classes depending on
    //how the person making it has made the class. Therefore, some parts are 
    //commented out which will allow smaller changes later during the process
    //of integration
    
    //private VectorMap map;
	private String s;


	private transient BufferedImage spriteSheet;
	//count to keep reference of position within the horizontal scope of the sprite sheet.
	private int spriteCount;
	//reference int for getting sub image from sprite sheet
	private int dirCount;
	
	private boolean doorOpen = false;
	private int speed = 0;

	public Player() {

	}

    /**
     * Player class that uses the Entity interface, handles movement and passing of the relevant sprite
     *  
     * @param s = string to represent the current entity = expects "Player", "NPC"
     * @param x = current x position to be assigned to the entity, int expected >0 and <map size
     * @param y = current y position to be assigned to the entity, int expected >0 and <map size
     * 
     */ 
	public Player(String s, int x, int y) {
		super(s,x,y);
		this.s = s;
		spriteCount = 0;
		dirCount = 0;
		loadSpriteSheet();
		speed = 0;
	}

	/**
	 * Not used in current implementation
	 */
	public void update() {
		move();
		// pos.x += dx;
		// pos.y += dy;
	}


    /**
     * Checks current move direction and then changes the stored location
     */
	public void move() {
		// TODO Auto-generated method stub
		if (up) {
			dy --;//-= accel;
			/*
			if (dy < -maxSpeed) {
				dy = -maxSpeed;
			}*/
		} else if (down) {
			dy ++;//+= accel;
			/*
			if (dy < maxSpeed) {
				dy = maxSpeed;
			}*/
		} else if (left) {
			dx --;//-= accel;
			/*
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}*/
		} else if (right) {
			dx ++;//+= accel;
			/*
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}*/
		}
	}

	@Override
	public BufferedImage render(){
		
		//get the sub image from the sprite sheet.
		BufferedImage img = spriteSheet.getSubimage(spriteCount*spriteSheet.getWidth()/3,dirCount*(spriteSheet.getHeight()/4),spriteSheet.getWidth()/3,spriteSheet.getHeight()/4);
		spriteCount++;
		//keep reference for the sprite within scope (ie 5 different images for walking animation in each direction)
		if(spriteCount>2){
		    spriteCount=0;
		}
		if(!s.equals("Player")){spriteCount=1;}
		return img;
	}
	
	
	/*
	//not currently needed
	public void input(MouseHandler mh, KeyHandler kh) {
		if (mh.getButton() == 1) {
			System.out.println("Player Pos At: " + pos.x + "," + pos.y);
		}
		if (kh.up.down) {
			up = true;
		} else {
			up = false;
		}
		if (kh.down.down) {
			down = true;
		} else {
			down = false;
		}
		if (kh.left.down) {
			left = true;
		} else {
			left = false;
		}
		if (kh.right.down) {
			right = true;
		} else {
			right = false;
		}
	}
	*/
	
	/**
	 * Takes input from the Model class to establish the current movement direction
	 * 
	 * @param dir = String of direction, expects "up","down","left", or "right"
	 */
	public void pressedKey(String dir) {
		String temp = dir.toLowerCase();
		if (temp.equals("up")) {
			this.up = true;
			this.down = false;
			this.left = false;
			this.right = false;
			dirCount = 1;
		} else if (temp.equals("down")) {
			this.up = false;
			this.down = true;
			this.left = false;
			this.right = false;
			dirCount = 0;
		} else if (temp.equals("left")) {
			this.up = false;
			this.down = false;
			this.left = true;
			this.right = false;
			dirCount = 2;
		} else if (temp.equals("right")) {
			this.up = false;
			this.down = false;
			this.left = false;
			this.right = true;
			dirCount = 3;
		}
	}
	
	
	public void doorSwap(){
		doorOpen = !doorOpen;
	}
	
	public boolean doorsOpen(){
		return doorOpen;
	}

	private void loadSpriteSheet() {
		String filePath;
		if (s.equals("Player")) {
			filePath = "resources/player.png";
		} else {
			filePath = "resources/npc.png";
		}

		try {
			spriteSheet = ImageIO.read(new File(filePath));
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeObject(s);
		out.writeInt(spriteCount);
		out.writeInt(dirCount);
		out.writeBoolean(doorOpen);
		out.writeInt(speed);
	}
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		s = (String) in.readObject();
		spriteCount = in.readInt();
		dirCount = in.readInt();
		doorOpen = in.readBoolean();
        speed = in.readInt();
		loadSpriteSheet();
	}
	private void readObjectNoData() throws ObjectStreamException {

	}
	
	/**
	 * Returns the speed value field
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * increases teh stored speed by 15 with a cap of 90
	 */
	public void accelerate() {
		if(speed<75){
			speed+=15;
		}
	}

}
