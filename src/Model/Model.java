package Model;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import exceptions.WrongListOfQuestionsFormat;


public class Model extends Observable{

	private Game gameState;
	private ListOfQuestions q;

	public Model() {
		try {
			gameState = new Game(this);
			gameState.startNewGame();
			createListOfQuestions();
		} catch (WrongListOfQuestionsFormat e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadNewMap(String key) {
		System.out.println("Load map");
	}

	public void startGame() {
		gameState.startNewGame();
		gameState.state = "load";
		update();
		gameState.state = "game";
		update();
	}

	public void save() {
		gameState.saveCurrentGame();
	}

	public void load() {
		gameState.loadSavedGame();
	}

	public void exit() {
		System.exit(0);
	}

	public void resume() {
		gameState.state = "game";
		update();
	}

	public void gameMenu() {
		gameState.state = "menu";
		update();
	}

	public void up() {
		Player p = gameState.getInstance().getPlayer();
	    p.pressedKey("up");
		if(gameState.getInstance().getCurrentWorld().canMoveUp(getX(),getY())){
			if(canMove(p.getXPos(), p.getYPos()-1)==null){
				p.move();
				gameState.state = "move";
			}
		}
		update();
	}

	public void down() {
		Player p = gameState.getInstance().getPlayer();
	    p.pressedKey("down");
		if(gameState.getInstance().getCurrentWorld().canMoveDown(getX(),getY())){
			if(canMove(p.getXPos(), p.getYPos()+1)==null){
				p.move();
				gameState.state = "move";
			}
		}
		update();
	}

	public void right() {
		Player p = gameState.getInstance().getPlayer();
	    p.pressedKey("right");
		if(gameState.getInstance().getCurrentWorld().canMoveRight(getX(),getY())){
			if(canMove(p.getXPos()+1, p.getYPos())==null){
				p.move();
				gameState.state = "move";
			}
		}
		update();
	}

	public void left() {
		Player p = gameState.getInstance().getPlayer();
	    p.pressedKey("left");
		if(gameState.getInstance().getCurrentWorld().canMoveLeft(getX(),getY())){
			if(canMove(p.getXPos()-1, p.getYPos())==null){
				p.move();
				gameState.state = "move";
			}
		}
		update();
	}

	public void interact() {
	    Player p = gameState.getInstance().getPlayer();
	    World w = gameState.getInstance().getCurrentWorld();
		String dir = p.getDirection();

		ArrayList<Entity> npc = (ArrayList) gameState.getInstance().getEntities().get("npc");
		Entity npcPresent = null;
		
		//triggers game over if its the last map
		if(gameState.getInstance().getCurrentWorldName().equals("f")){			
			if(getLocation(p.getXPos(), p.getYPos()-1).equals("NORTH_DOOR")){
				gameState.state="over";
				update();
				return;
			}
		}
		
		if(dir.equals("up")){
		    if(getLocation(p.getXPos(), p.getYPos()-1).equals("NORTH_DOOR")){
		    	if(gameState.getInstance().getPlayer().doorsOpen()) {
		    	if(gameState.getInstance().getCurrentWorld().getValue() == 0) {
					nextWorld();
			    	}
		    	else {
		    		JOptionPane.showMessageDialog(null, "Try another door");
		    	}
		    }
		    	else
		    		JOptionPane.showMessageDialog(null, "Answer the question first");

		    	}
		    else if(getLocation(p.getXPos(), p.getYPos()-1).equals("COIN")) {
		    	gameState.getInstance().getCurrentWorld().updatingMap(p.getXPos(), p.getYPos()-1, "N");
		    	speed();
		    }
			npcPresent=canMove(p.getXPos(), p.getYPos()-1);
			if(npcPresent.getName().equals("Real")){
				npcPresent.pressedKey("down");
				gameState.state="talk";
			}else{
				npcPresent.pressedKey("up");
			}

		}else if(dir.equals("down")){
		    if(getLocation(p.getXPos(), p.getYPos()+1).equals("SOUTH_DOOR")){
		    	if(gameState.getInstance().getPlayer().doorsOpen()) {
			    	if(gameState.getInstance().getCurrentWorld().getValue() == 1) {
						nextWorld();
				    	}
			    	else {
			    		JOptionPane.showMessageDialog(null, "Try another door");
			    	}
			    }
			    	else
			    		JOptionPane.showMessageDialog(null, "Answer the question first");
		    	}
		    else if(getLocation(p.getXPos(), p.getYPos()+1).equals("COIN")) {
		    	gameState.getInstance().getCurrentWorld().updatingMap(p.getXPos(), p.getYPos()+1, "N");
		    	speed();
		    }
			npcPresent=canMove(p.getXPos(), p.getYPos()+1);
			if(npcPresent.getName().equals("Real")){
				npcPresent.pressedKey("up");
				gameState.state="talk";
			}else{
				npcPresent.pressedKey("down");
			}
		}else if(dir.equals("left")){
		    if(getLocation(p.getXPos()-1, p.getYPos()).equals("WEST_DOOR")){
		    	if(gameState.getInstance().getPlayer().doorsOpen()) {
			    	if(gameState.getInstance().getCurrentWorld().getValue() == 2) {
						nextWorld();
				    	}
			    	else {
			    		JOptionPane.showMessageDialog(null, "Try another door");
			    	}
			    }
			    	else
			    		JOptionPane.showMessageDialog(null, "Answer the question first");
			}
		    else if(getLocation(p.getXPos()-1, p.getYPos()).equals("COIN")) {
		    	gameState.getInstance().getCurrentWorld().updatingMap(p.getXPos()-1, p.getYPos(), "N");
		    	speed();
		    }
			npcPresent=canMove(p.getXPos()-1, p.getYPos());
			if(npcPresent.getName().equals("Real")){
				npcPresent.pressedKey("right");
				gameState.state="talk";
			}else{
				npcPresent.pressedKey("left");
			}
		}else if(dir.equals("right")){
		    if(getLocation(p.getXPos()+1, p.getYPos()).equals("EAST_DOOR")){
		    	if(gameState.getInstance().getPlayer().doorsOpen()) {
			    	if(gameState.getInstance().getCurrentWorld().getValue() == 3) {
						nextWorld();
				    	}
			    	else {
			    		JOptionPane.showMessageDialog(null, "Try another door");
			    	}
			    }
			    	else
			    		JOptionPane.showMessageDialog(null, "Answer the question first");
		    	}
		    else if(getLocation(p.getXPos()+1, p.getYPos()).equals("COIN")) {
		    	gameState.getInstance().getCurrentWorld().updatingMap(p.getXPos()+1, p.getYPos(), "N");
		    	speed();
		    }
			npcPresent=canMove(p.getXPos()+1, p.getYPos());
			if(npcPresent.getName().equals("Real")){
				npcPresent.pressedKey("left");
				gameState.state="talk";
			}else{
				npcPresent.pressedKey("right");
			}
		}
		update();
	}


	private void nextWorld(){

		char current = gameState.getInstance().getCurrentWorldName().charAt(0);
		current++;
		String c = Character.toString(current);
		changeCurrentWorld(c);
		gameState.state = "load";
		gameState.getInstance().getPlayer().doorSwap();
		update();
		gameState.state = "game";
		update();
	}

	public void changeCurrentWorld(String worldKey) {
		gameState.getInstance().setCurrentWorld(worldKey);
		createNPC();
		gameState.getInstance().getPlayer().setPos(
				gameState.getInstance().getCurrentWorld().getWidth()/2,
				gameState.getInstance().getCurrentWorld().getHeight()/2);
		/*gameState.getInstance().setPlayer(new Player("Player",
				gameState.getInstance().getCurrentWorld().getWidth()/2 ,
				gameState.getInstance().getCurrentWorld().getHeight()/2));
				*/
	}

	/**
	 * Populates all the maps in hashMap .
	 */
	public void populateMap() {
		gameState.getInstance().putWorldList("a",new World("MAP_A"));
		gameState.getInstance().putWorldList("b",new World("MAP_B"));
		gameState.getInstance().putWorldList("c",new World("MAP_C"));
		gameState.getInstance().putWorldList("d",new World("MAP_D"));
		gameState.getInstance().putWorldList("e",new World("MAP_E"));
		gameState.getInstance().putWorldList("f",new World("MAP_F"));
		changeCurrentWorld("a");


	}

	public void createNPC(){
		int count  = 0;
		ArrayList<Entity> input = new ArrayList<Entity>();
		Random rn = new Random();
		while (count<4){
			boolean create = false;
			while(!create){
				int worldWidth = gameState.getInstance().getCurrentWorld().getWidth();
				int worldHeight = gameState.getInstance().getCurrentWorld().getHeight();
				int x = rn.nextInt(worldWidth);
				int y = rn.nextInt(worldHeight);
				if(gameState.getInstance().getCurrentWorld().getAtPosition(x, y).equals("NOTHING")
						&& !gameState.getInstance().getCurrentWorld().getAtPosition(x, y).equals("GRASS")){
					if(count==0){
						input.add(new Player("Real",x,y));
					}else{
						input.add(new Player("Npc",x,y));
					}
					create=true;
				}
			}
			count++;
		}
		gameState.getInstance().putEntityList("npc",input);
	}

	public Entity canMove(int x, int y){
		ArrayList<Entity> npc = (ArrayList) gameState.getInstance().getEntities().get("npc");
		for(Entity e : npc){
			if(e.getXPos()==x && e.getYPos()==y){
				return e;
			}
		}

		return null;
	}

	public String getLocation(int x, int y) {
		return gameState.getInstance().getCurrentWorld().getAtPosition(x,y);
	}

	public Image getCharacterIcon() throws IOException {
		Image img = gameState.getInstance().getPlayer().render();
		return img;
	}

	public String getState() {
		return gameState.state;
	}

	public Question getQuestion(){
		Question question = getQ().getNextAnswer();
		if(question == null) {//If there is no more unused questions we ask random ones
			question = getQ().getAnyAnswer();
		}
		return question;
	}

	public int getX() {
		return gameState.getInstance().getPlayer().getXPos();
	}

	public int getY() {
		return gameState.getInstance().getPlayer().getYPos();
	}

	public void update(){
		setChanged();
		notifyObservers();
	}

	public void correctAnswer() {
		// TODO Auto-generated method stub
        //load new map.
		gameState.state = "game";
		gameState.getInstance().getPlayer().doorSwap();
		update();
	}


	/**
	 * It creates the list of questions
	 */
	public void createListOfQuestions() throws WrongListOfQuestionsFormat{
		ArrayList<String> questions = new ArrayList<String>();
		ArrayList<String> answers = new ArrayList<String>();
		try
		{
			Scanner scan = new Scanner (new File("resources/Questions_MAP_A"));
			while ( scan.hasNext() )
			{
				questions.add(scan.nextLine());//question statement
				answers.add(scan.nextLine());//answer of the question
			}
			scan.close();
			this.q = new ListOfQuestions(questions, answers);
		}
		catch(IOException e) {
			throw new WrongListOfQuestionsFormat();
		}
	}


	public ListOfQuestions getQ() {
		return q;
	}


	public void setQ(ListOfQuestions q) {
		this.q = q;
	}

	public void closeTalk() {
		gameState.state = "game";
        update();
	}


	public Game getGameState() {
		return gameState;
	}

	public int getWidth(){
	    return gameState.getInstance().getCurrentWorld().getWidth();
	}

	public int getHeight(){
	    return gameState.getInstance().getCurrentWorld().getHeight();
	}


	public void setGameState(Game gameState) {
		this.gameState = gameState;
	}


	public void moveFinished(){
		gameState.state = "game";
	}


	public void speed() {
		gameState.getInstance().getPlayer().accelerate();
		update();
	}
}
