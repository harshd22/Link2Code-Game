package mockClasses.ViewMock;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Model extends Observable {

	private String[][] arr;
	private int xPos = 5;
	private int yPos = 5;
	private int size = 10;
	private String gameState;

	public Model() {
		arr = new String[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {/*
				if (i == 3 || i == 7) {
					arr[i][j] = "tree";
				} else if (j == 3 || j == 7) {
					arr[i][j] = "barrel";
				} else {*/
					arr[i][j] = "nothing";
				//}
			}
		}
		gameState = "menu";
	}

	public int getSizeX() {
		return size;
	}

	public int getSizeY() {
		return size;
	}

	public String getLocation(int x, int y) {
		return arr[x][y];
	}
	
	public Image getCharacterIcon() throws IOException {
		Image img = null;
		img = ImageIO.read(new File("resources/player.png"));
		return img;
	}
	
	public String getState() {
		return gameState;
	}
	
	public String getAnswer(){
		return "A";
	}
	
	public String getQuestion(){
		return "Test question::input A";
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

    //below methods only for testing
	public void moveUp() {
		yPos--;
	}

	public void moveDown() {
		yPos++;
	}

	public void moveLeft() {
		xPos--;
	}

	public void moveRight() {
		xPos++;
	}

    public void setState(String s){
		gameState=s;
	}

	public void update(){
		
		setChanged();
		notifyObservers();
	}
	
	public void setLocation(int x, int y,String input){
		arr[x][y] = input;
	}
	
	
}
