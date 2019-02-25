package Model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * It makes some external tests to the Player class
 * @author Fran Colmenar
 *
 */

public class PlayerExternalTest {

	private Player p;

	/**
	 * It initializes the player before the tests
	 */
	@Before
	public void initializePlayer() {
		p = new Player("Player", 1,1);
	}

	/**
	 * It checks if the player is created correctly
	 */
	@Test
	public void existsPlayer() {
		assertTrue(p != null);
		assertTrue(p.getXPos() == 1);//It checks the default values of the test's player
		assertTrue(p.getYPos() == 1);
	}

	/**
	 * It test the update when the left key has been pressed
	 */
	@Test
	public void updateLeftTest() {
		float tempY = p.dy;
		float tempX = p.dx;
		p.pressedKey("left");
		p.update();
		assertTrue(tempY == p.getYPos());
		assertTrue(tempX > p.getXPos());
	}

	/**
	 * It test the update when the right key has been pressed
	 */
	@Test
	public void updateRightTest() {
		float tempY = p.dy;
		float tempX = p.dx;
		p.pressedKey("right");
		p.update();
		assertTrue(tempY == p.getYPos());
		assertTrue(tempX < p.getXPos());
	}

	/**
	 * It test the update when the up key has been pressed
	 */
	@Test
	public void updateUpTest() {
		float tempY = p.dy;
		float tempX = p.dx;
		p.pressedKey("up");
		p.update();
		assertTrue(tempX == p.getXPos());
		assertTrue(tempY > p.getYPos());
	}

	/**
	 * It test the update when the down key has been pressed
	 */
	@Test
	public void updateDownTest() {
		float tempY = p.dy;
		float tempX = p.dx;
		p.pressedKey("down");
		p.update();
		assertTrue(tempX == p.getXPos());
		assertTrue(tempY < p.getYPos());
	}
}
