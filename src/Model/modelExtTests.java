package Model;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;

public class modelExtTests {

	//Game tests
	@Test
	public void instanceCreated(){
		Model m = new Model();
		Game g = m.getGameState();
		assertTrue(g.getInstance()!=null);
	}
	
	@Test
	public void startGame(){
		Model m = new Model();
		Game g = m.getGameState();
		m.startGame();
		assertTrue(g.getInstance().getCurrentWorldName().equals("a"));
	}
	
	//Instance Tests
	@Test
	public void initInstance(){
		Model m = new Model();
		m.startGame();
		Game g = m.getGameState();
		Instance i = g.getInstance();
		assertTrue(i.getPlayer()!=null);
		assertTrue(i.getCurrentWorldName().equals("a"));
		assertTrue(i.getEntities()!=null);
		assertTrue(i.getWorldCells()!=null);
	}
	
	@Test
	public void loadMapStored(){
		Instance i = new Instance();
		int count = i.getWorldCells().size();
		i.putWorldList("b",new World("MAP_A"));
		assertTrue(i.getWorldCells().size()>count);
	}
	
	@Test
	public void changeCurrentMap(){
		Instance i = new Instance();
		String old = i.getCurrentWorldName();
		i.putWorldList("b",new World("MAP_A"));
		i.setCurrentWorld("b");
		assertFalse(i.getCurrentWorldName().equals(old));
	}
	
	@Test
	public void entityAdd(){
		Instance i = new Instance();
		int size = i.getEntities().size();
		ArrayList<Entity> chars = new ArrayList<Entity>();
		chars.add(new Player("Player",1,1));
		i.putEntityList("a", chars);
		assertTrue(i.getEntities().size()>size);
	}
	
	@Test
	public void getWorld(){
		Model m = new Model();
		assertFalse(m.getGameState().getInstance().getCurrentWorld()==null);
	}
	
	//Model Tests
	
	@Test
	public void initModel(){
		Model m = new Model();
		assertTrue(m.getGameState()!=null);
	}
	
	@Test
	public void changeMap(){
		Model m = new Model();
		String old = m.getGameState().getInstance().getCurrentWorldName();
		m.getGameState().getInstance().putWorldList("b", new World("MAP_A"));
		m.getGameState().getInstance().setCurrentWorld("b");
		assertFalse(m.getGameState().getInstance().getCurrentWorldName().equals(old));
		assertTrue(m.getGameState().getInstance().getCurrentWorldName().equals("b"));
	}
	
	@Test
	public void resume(){
		Model m = new Model();
		assertTrue(m.getGameState().state.equals("menu"));
		m.resume();
		assertTrue(m.getGameState().state.equals("game"));
	}
	
	@Test
	public void menuEscape(){
		Model m = new Model();
		assertTrue(m.getGameState().state.equals("menu"));
		m.resume();
		assertTrue(m.getGameState().state.equals("game"));
		m.gameMenu();
		assertTrue(m.getGameState().state.equals("menu"));
	}
	
	@Test
	public void getLocationSuccess(){
		Model m = new Model();
		assertFalse(m.getLocation(1, 1)==null);		
	}
	
	@Test
	public void getLocationFail(){
		Model m = new Model();
		assertTrue(m.getLocation(-1, -1)==null);
	}
	
	@Test
	public void checkState(){
		Model m = new Model();
		m.getGameState().state = "test";
		assertTrue(m.getState().equals("test"));
	}
	
	@Test
	public void correctX(){
		Model m = new Model();
		assertTrue(m.getX()==15);
	}
	
	@Test
	public void correctY(){
		Model m = new Model();
		assertTrue(m.getY()==10);
	}
	
	@Test
	public void correctWidth(){
		Model m = new Model();
		assertTrue(m.getWidth()==30);
	}
	
	@Test
	public void correctHeight(){
		Model m = new Model();
		assertTrue(m.getHeight()==20);
	}
	
	@Test
	public void finishedMoving(){
		Model m = new Model();
		assertTrue(m.getGameState().state.equals("menu"));
		m.moveFinished();
		assertTrue(m.getGameState().state.equals("game"));
	}
	
	@Test
	public void talkOver(){
		Model m = new Model();
		assertTrue(m.getGameState().state.equals("menu"));
		m.closeTalk();
		assertTrue(m.getGameState().state.equals("game"));
	}
	
	@Test
	public void correctAnswer(){
		Model m = new Model();
		assertTrue(m.getGameState().state.equals("menu"));
		m.correctAnswer();
		assertTrue(m.getGameState().state.equals("game"));
	}
	
	@Test
	public void getQuestion(){
		Model m = new Model();
		assertTrue(m.getQuestion()!=null);
	}
	
	@Test
	public void up(){
		Model m = new Model();
		m.startGame();
		int y = m.getGameState().getInstance().getPlayer().getYPos();
		m.left();
		m.up();
		assertTrue(m.getState().equals("move"));
		assertTrue(m.getGameState().getInstance().getPlayer().getYPos()<y);
	}

	@Test
	public void down(){
		Model m = new Model();
		m.startGame();
		int y = m.getGameState().getInstance().getPlayer().getYPos();
		m.down();
		assertTrue(m.getState().equals("move"));
		assertTrue(m.getGameState().getInstance().getPlayer().getYPos()>y);
	}

	@Test
	public void left(){
		Model m = new Model();
		m.startGame();
		int x = m.getGameState().getInstance().getPlayer().getXPos();
		m.left();
		assertTrue(m.getState().equals("move"));
		assertTrue(m.getGameState().getInstance().getPlayer().getXPos()<x);
	}

	@Test
	public void right(){
		Model m = new Model();
		m.startGame();
		int x = m.getGameState().getInstance().getPlayer().getXPos();
		m.right();
		assertTrue(m.getState().equals("move"));
		assertTrue(m.getGameState().getInstance().getPlayer().getXPos()>x);
	}
}
