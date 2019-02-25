package Model;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
* Interface Class that stores the persons current position and returns when asked.
* 
* @Author Sim
* Review = Jeremy Logan
*/
public abstract class Entity implements Serializable {

	
	//protected Sprite s;
	//protected VectorMap pos;
	//protected int size;

	protected boolean up, down, left, right;
	protected int dx, dy;
	protected float maxSpeed, accel, deaccel;
	private String name;


	public Entity() {

	}

    /**
     * Interface Class that stores the persons current position and returns when asked.
     * 
     * @param dx  = int of current x Position in the array
     * @param dy  = int of current y Position in the array
     */
	public Entity(String name, int dx, int dy){//, float maxSpeed, float accel, float deaccel) {
        this.name=name;
		this.dx = dx;
		this.dy = dy;
		//this.maxSpeed = maxSpeed;
		//this.accel = accel;
		//this.deaccel = deaccel;
	}

    /**
     * Returns the Image file representing the person. To be sent to the View Class.
     */
	public abstract BufferedImage render();

	public void setPos(int x, int y) {
		dx = x;
		dy = y;
	}

	/**
	 * Returns the current X position of the person within the array.
	 */
	public int getXPos(){
	    return dx;
	}
	
	/**
	 * Returns the current Y position of the person within the array.
	 */
	public int getYPos(){
	    return dy;
	}

     public String getDirection(){
    	if(up){return "up";}
    	else if(down){return "down";}
    	else if(left){return "left";}
    	else {return "right";}
    	
    }
    
    public String getName(){
    	return name;
    }
    
    public abstract void pressedKey(String dir);
}
