package View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Model.Entity;
import Model.Model;


/**
 * Custom JPanel that takes a Model class to reference.
 * Establishes the size of the squares available to it, depending on resize and draws a grid of locations around the current character position.
 * Movement is done via loading an offset value from the View class to interpolate the background movement rather than character.
 *
 *
 * @Author Jeremy Logan
 * @review Fran Colmenar
 */

@SuppressWarnings("serial")
public class GameScreen extends JPanel {

	private Model model;
	private int playerX, playerY;
	private int boxSizeX;
	private int boxSizeY;
	private int mapSizeX, mapSizeY;
	private double xOffset;
	private double yOffset;
	private BufferedImage door;
	private BufferedImage tree;
	private BufferedImage grass;
	private BufferedImage rock;
	private BufferedImage barrel;
	private BufferedImage back;
	private BufferedImage flower;
	private BufferedImage coin;
	private boolean doorOpen;

    /**
     * Constructs the window and zeros out the fields.
     *
     * @param Model is the core Model class of the game to reference.
     *
     */
	public GameScreen(Model m) {
		this.model = m;
		playerX = 0;
		playerY = 0;
		mapSizeX = 0;
		mapSizeY = 0;
		xOffset = 0;
		yOffset = 0;
	    loadImages();
	}

	/**
	 * Loads the images to be drawn later in the class into a single memory
	 * space.
	 *
	 */
	private void loadImages() {

		try {
			door = ImageIO.read(new File("resources/door.png"));
			tree = ImageIO.read(new File("resources/tree.png"));
			grass = ImageIO.read(new File("resources/grass.png"));
			rock = ImageIO.read(new File("resources/rock.png"));
			barrel = ImageIO.read(new File("resources/barrel.png"));
			back = ImageIO.read(new File("resources/back.png"));
			flower = ImageIO.read(new File("resources/flower.png"));
			coin = ImageIO.read(new File("resources/Gold_coin_icon.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Returns currently stored X position of the player.
	 * Used in the View class to check limits for the movement method.
	 *
	 * @return int representing the current position
	 */
	public int getPlayerX(){
		return playerX;
	}

	/**
	 * Returns currently stored Y position of the player.
	 * Used in the View class to check limits for the movement method.
	 *
	 * @return int representing the current position
	 */
	public int getPlayerY(){
		return playerY;
	}

	/**
	 * Reads in the input changes from the View class.
	 * Used for monitoring a change in player position and background incremental offsets.
	 *
	 * @param xPos = int of current player position in x axis.
	 * @param yPos = int of current player position in y axis.
	 * @param xoff = double of the current offset applied to teh background. Expects values between 0-0.9.
	 *
	 */
	public void update(int xPos, int yPos, boolean doors) {
		this.playerX = xPos;
		this.playerY = yPos;
		this.mapSizeX = model.getWidth();
		this.mapSizeY = model.getHeight();
		doorOpen = doors;
		repaint();
	}

	/**
	 * Used by the movement method in the View class to animate the screen movement
	 */
	public void moveRight(){
		xOffset-=0.2;
		if(xOffset<=-1.0){
			playerX+=1;
			xOffset=0.0;
		}
	}

	/**
	 * Used by the movement method in the View class to animate the screen movement
	 */
	public void moveLeft(){
		xOffset+=0.2;
		if(xOffset>=1.0){
			playerX-=1;
			xOffset=0.0;
		}
	}

	/**
	 * Used by the movement method in the View class to animate the screen movement
	 */
	public void moveDown(){
		yOffset-=0.2;
		if(yOffset<=-1.0){
			playerY+=1;
			yOffset=0.0;
		}
	}

	/**
	 * Used by the movement method in the View class to animate the screen movement
	 */
	public void moveUp(){
		yOffset+=0.2;
		if(yOffset>=1.0){
			playerY-=1;
			yOffset=0.0;
		}
	}

	/**
	 * Draws the Board within JPanel. Checks available size and adjusts boxSize
	 * then draws location backgrounds and gets the Icon draw from the Piece.
	 *
	 * @param g = Graphics object called by repaint
	 * @review The method drawAuxiliary is created in order to reuce the
	 * complexity of this method
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// check size of current display screen frame
		int width = this.getWidth();
		int height = this.getHeight();
		//sets both the boxsizes in the axises, otherwise extra is displayed on an edge.
		this.boxSizeX = width / 17;
		this.boxSizeY = height / 17;

		try {
			for (int x = -9; x < 10; x++) {
				for (int y = -9; y < 10; y++) {
					drawAuxiliary(x, y, g);
					drawNPC(x, y, g);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//draw player last so shows up over any terrain.
		try {
			drawPlayer(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Auxiliary method of paintComponent
	 *
	 * @param x
	 * @param y
	 * @param g = Graphics object called by repaint
	 * @throws IOException: in case that there is any problem. It is catch by paintComponent
     * @review Method created with the previously code of paintComponent method
     * to reduce the complexity of the previously method
   	 */
	private void drawAuxiliary(int x, int y, Graphics g) throws IOException {
		int drawX = playerX + x;
		int drawY = playerY + y;
		//draws a base background tile so things aren't floating
		drawGround(g, x+8, y+8);
		//if off the map then draw a tree.
		if (drawX < 0 || drawX >= mapSizeX) {
			drawTree(g, x+8, y+8);
		} else if (drawY < 0 || drawY >= mapSizeY) {
			drawTree(g, x+8, y+8);
		} else {
		    //handle each of the possible inputs
			String loc = model.getLocation(drawX, drawY);
			if (loc.equals("TREE")) {
				drawTree(g, x+8, y+8);
			} else if (loc.equals("NORTH_DOOR")) {
				drawDoor(g, x+8, y+8, "n");
			} else if (loc.equals("SOUTH_DOOR")) {
				drawDoor(g, x+8, y+8, "s");
			} else if (loc.equals("WEST_DOOR")) {
				drawDoor(g, x+8, y+8, "w");
			} else if (loc.equals("EAST_DOOR")) {
				drawDoor(g, x+8, y+8, "e");
			} else if (loc.equals("open")) {
				drawGateUnlocked(g, x+8, y+8);
			} else if (loc.equals("locked")) {
				drawGateLocked(g, x+8, y+8);
			} else if (loc.equals("NOTHING") || loc.equals("GRASS")) {
				drawGrass(g, x+8, y+8,drawX, drawY);
			} else if (loc.equals("BARREL")) {
				drawBarrel(g, x+8, y+8);
			} else if (loc.equals("ROCK")) {
				drawRock(g, x+8, y+8);
			} else if (loc.equals("FLOWER")) {
				drawFlower(g, x+8, y+8);
			}else if (loc.equals("COIN")) {
				drawCoin(g, x+8, y+8);
			}
		}
	}

	private void drawNPC(int x, int y, Graphics g) {
		int drawX = playerX + x;
		int drawY = playerY + y;
		BufferedImage img = null;
		Map entities = model.getGameState().getInstance().getEntities();
		ArrayList<Entity> npc = (ArrayList) entities.get("npc");
		// inside the map then draw a tree.
		if (drawX >= 0 && drawX < mapSizeX && drawY >= 0 && drawY < mapSizeY) {
			for (Entity e : npc) {
				if (e.getXPos() == drawX && e.getYPos() == drawY) {
					img = e.render();
					g.drawImage(img, (int) ((x+8) * boxSizeX + (xOffset * boxSizeX)),
							(int) ((y+8) * boxSizeY + (yOffset * boxSizeY)), boxSizeX, boxSizeY, null);
				}
			}
		}
	}

	private void drawCoin(int x, int y, Graphics g) {
		int drawX = playerX + x;
		int drawY = playerY + y;
		BufferedImage img = null;
		Map entities = model.getGameState().getInstance().getEntities();
		ArrayList<Entity> npc = (ArrayList) entities.get("npc");
		// inside the map then draw a tree.
		if (drawX >= 0 && drawX < mapSizeX && drawY >= 0 && drawY < mapSizeY) {
			for (Entity e : npc) {
				if (e.getXPos() == drawX && e.getYPos() == drawY) {
					img = e.render();
					g.drawImage(img, (int) ((x+8) * boxSizeX + (xOffset * boxSizeX)),
							(int) ((y+8) * boxSizeY + (yOffset * boxSizeY)), boxSizeX, boxSizeY, null);
				}
			}
		}
	}


	//Gold_coin_icon

	/**
	 * Rotates the given image by the amount of degrees Used mainly for the
	 * doors.
	 *
	 * @param img
	 *            = BufferedImage to be rotated.
	 * @param deg
	 *            = int of degrees to rotate ie. 90,180,270
	 *
	 * @return = rotated image.
	 */
	private BufferedImage rotate(BufferedImage img, int deg) {
		double rotationRequired = Math.toRadians(deg);
		double locationX = img.getWidth() / 2;
		double locationY = img.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		return op.filter(img, null);
	}

	/**
	 * Draws the player character icon received from the Player class in the middle of the screen.
	 *
	 * @param g = Graphics object passed from the core paintComponent() method
	 * @throws IOException
	 */
	private void drawPlayer(Graphics g) throws IOException {
		Image img = model.getCharacterIcon();

		g.drawImage(img, (this.getWidth() / 2) - (boxSizeX / 2), (this.getHeight() / 2) - (boxSizeY / 2), boxSizeX,
				boxSizeY, null);

	}

	/**
	 * Draws a tree at the specified position.
	 *
	 * @param g = Graphics component passed from the paintComponent() core Method.
	 * @param x = int of x-axis position in the displayed grid, expects value between -5->5.
	 * @param y = int of y-axis position in the displayed grid, expects value between -5->5.
	 */
	private void drawTree(Graphics g, int x, int y) throws IOException {

		g.drawImage(tree, (int) (x * boxSizeX + (xOffset * boxSizeX)), (int) (y * boxSizeY + (yOffset * boxSizeY)), boxSizeX,
				boxSizeY, null);

	}

	/**
	 * Draws a correct facing door at the specified position.
	 *
	 * @param g = Graphics component passed from the paintComponent() core Method.
	 * @param x = int of x-axis position in the displayed grid, expects value between -5->5.
	 * @param y = int of y-axis position in the displayed grid, expects value between -5->5.
	 * @param dir = string representing the direction the door leads to, expects values of "north","south","east","west"
	 */
	private void drawDoor(Graphics g, int x, int y, String dir) throws IOException {
		BufferedImage img = door.getSubimage(0, 0, door.getWidth() / 2, door.getHeight());
		if(!doorOpen){
			img = door.getSubimage(door.getWidth() / 2, 0, door.getWidth() / 2, door.getHeight());
		}

		if (dir.equals("n")) {
			// img = ImageIO.read(new File("resources/doorn.png"));
		} else if (dir.equals("s")) {
			img = rotate(img, 180);
		} else if (dir.equals("w")) {
			img = rotate(img, 270);
		} else if (dir.equals("e")) {
			img = rotate(img, 90);
		}
		if (img != null) {
			g.drawImage(img, (int) (x * boxSizeX + (xOffset * boxSizeX)), (int) (y * boxSizeY + (yOffset * boxSizeY)),
					boxSizeX, boxSizeY, null);
		}

	}

	/**
	* Draws a close gate at the specified position.
	*
	* @param g = Graphics component passed from the paintComponent() core Method.
	* @param x = int of x-axis position in the displayed grid, expects value between -5->5.
	* @param y = int of y-axis position in the displayed grid, expects value between -5->5.
	*/
	private void drawGateUnlocked(Graphics g, int x, int y) throws IOException {

		final Image img = ImageIO.read(new File("resources/open.png"));
		g.drawImage(img, (int) (x * boxSizeX + (xOffset * boxSizeX)), (int) (y * boxSizeY + (yOffset * boxSizeY)), boxSizeX,
				boxSizeY, null);

	}

    /**
     * Draws an open gate at the specified position.
     *
     * @param g = Graphics component passed from the paintComponent() core Method.
     * @param x = int of x-axis position in the displayed grid, expects value between -5->5.
     * @param y = int of y-axis position in the displayed grid, expects value between -5->5.
     */
	private void drawGateLocked(Graphics g, int x, int y) throws IOException {

		final Image img = ImageIO.read(new File("resources/close.png"));
		g.drawImage(img, (int) (x * boxSizeX + (xOffset * boxSizeX)), (int) (y * boxSizeY + (yOffset * boxSizeY)), boxSizeX,
				boxSizeY, null);

	}

    /**
     * Draws a grass tile at the specified position.
     *
     * @param g = Graphics component passed from the paintComponent() core Method.
     * @param x = int of x-axis position in the displayed grid, expects value between -5->5.
     * @param y = int of y-axis position in the displayed grid, expects value between -5->5.
     */
	private void drawGrass(Graphics g, int x, int y, int worldX, int worldY) throws IOException {
        BufferedImage img = grass;
		double radians = worldX + worldY;
		double internalXOffset = Math.sin(radians);
		double internalYOffset = Math.cos(radians);
		if (internalXOffset < 0) {
			internalXOffset = internalXOffset * -1;
		}
		if (internalYOffset < 0) {
			internalYOffset = internalYOffset * -1;
		}

		g.drawImage(grass, (int) (x * boxSizeX + (xOffset * boxSizeX) + (internalXOffset * boxSizeX / 2))
				, (int) (y * boxSizeY + (yOffset * boxSizeY) + (internalYOffset * boxSizeY / 2)),
				boxSizeX/2, boxSizeY/2, null);

	}

	/**
     * Draws a grass tile at the specified position.
     *
     * @param g = Graphics component passed from the paintComponent() core Method.
     * @param x = int of x-axis position in the displayed grid, expects value between -5->5.
     * @param y = int of y-axis position in the displayed grid, expects value between -5->5.
     */
	private void drawGround(Graphics g, int x, int y) throws IOException {

		g.drawImage(back, (int) (x * boxSizeX + (xOffset * boxSizeX)), (int) (y * boxSizeY + (yOffset * boxSizeY)), boxSizeX,
				boxSizeY, null);

	}

	/**
     * Draws a barrel at the specified position.
     *
     * @param g = Graphics component passed from the paintComponent() core Method.
     * @param x = int of x-axis position in the displayed grid, expects value between -5->5.
     * @param y = int of y-axis position in the displayed grid, expects value between -5->5.
     */
	private void drawBarrel(Graphics g, int x, int y) throws IOException {

		g.drawImage(barrel, (int) (x * boxSizeX + (xOffset * boxSizeX)), (int) (y * boxSizeY + (yOffset * boxSizeY)), boxSizeX,
				boxSizeY, null);

	}

	/**
     * Draws a flower at the specified position.
     *
     * @param g = Graphics component passed from the paintComponent() core Method.
     * @param x = int of x-axis position in the displayed grid, expects value between -5->5.
     * @param y = int of y-axis position in the displayed grid, expects value between -5->5.
     */
	private void drawFlower(Graphics g, int x, int y) throws IOException {

		g.drawImage(flower, (int) (x * boxSizeX + (xOffset * boxSizeX)), (int) (y * boxSizeY + (yOffset * boxSizeY)), boxSizeX,
				boxSizeY, null);
	}

	/**
     * Draws a rock at the specified position.
     *
     * @param g = Graphics component passed from the paintComponent() core Method.
     * @param x = int of x-axis position in the displayed grid, expects value between -5->5.
     * @param y = int of y-axis position in the displayed grid, expects value between -5->5.
     */
	private void drawRock(Graphics g, int x, int y) throws IOException {

		g.drawImage(rock, (int) (x * boxSizeX + (xOffset * boxSizeX)), (int) (y * boxSizeY + (yOffset * boxSizeY)), boxSizeX,
				boxSizeY, null);
	}

	/**
     * Draws a rock at the specified position.
     *
     * @param g = Graphics component passed from the paintComponent() core Method.
     * @param x = int of x-axis position in the displayed grid, expects value between -5->5.
     * @param y = int of y-axis position in the displayed grid, expects value between -5->5.
     */
	private void drawCoin(Graphics g, int x, int y) throws IOException {

		g.drawImage(coin, (int) (x * boxSizeX + (xOffset * boxSizeX)), (int) (y * boxSizeY + (yOffset * boxSizeY)), boxSizeX,
				boxSizeY, null);
	}



}
