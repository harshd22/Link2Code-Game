package mockClasses;
import java.util.Observable;

import Model.Player;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import javax.swing.Timer;



public class MockModel extends Observable{
	private Player player;
	//private world
	private int status;
	private String[][] arr;
	private int xPos = 5;
	private int yPos = 5;
	private int size = 10;
	private String gameState;

	public MockModel() {
		status = 1;//Status == 1 the movement is allowed
		arr = new String[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
					arr[i][j] = "nothing";

			}
		}
		gameState = "menu";

	}


	public void loadNewMap() {
		System.out.println("Load map");
	}

	public void save() {
		System.out.println("Save");
	}

	public void load() {
		System.out.println("Load");
	}

	public void exit() {
		System.out.println("Exit");
	}

	public void resume() {
		System.out.println("Resume");
	}

	public void gameMenu() {
		System.out.println("Game menu");
	}



	public void up() {
		System.out.println("Up");
	}

	public void down() {
		System.out.println("Down");
	}

	public void right() {
		System.out.println("Right");
	}

	public void left() {
		System.out.println("Left");
	}

	public void interact() {
		System.out.println("Interact");
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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



	public void correctAnswer() {
		// TODO Auto-generated method stub

	}



	public void closeTalk() {
		// TODO Auto-generated method stub

	}



	public void starGame() {
		// TODO Auto-generated method stub

	}

}
