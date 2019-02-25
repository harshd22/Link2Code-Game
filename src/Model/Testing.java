package Model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

//@author Sim

public class Testing {

	boolean move = false;
	Player p;
	World w;

	@Before
	public void initializePlayer() {
		p = new Player("Player", 1,1);
	}

	@Test
	public void moveLeftTest() {
		float tempY = p.dy;
		float tempX = p.dx;
		p.pressedKey("left");
		assertEquals(p.dy, tempY, 0);
		assertTrue(tempX > p.dx);

	}

	@Test
	public void moveRightTest() {
		float tempY = p.dy;
		float tempX = p.dx;
		p.pressedKey("right");
		assertEquals(p.dy, tempY, 0);
		assertTrue(tempX < p.dx);
	}

	@Test
	public void moveUpTest() {
		float tempY = p.dy;
		float tempX = p.dx;
		p.pressedKey("up");
		assertEquals(p.dx, tempX, 0);
		assertTrue(tempY > p.dy);
	}

	@Test
	public void moveDownTest() {
		float tempY = p.dy;
		float tempX = p.dx;
		p.pressedKey("down");
		assertEquals(p.dx, tempX, 0);
		assertTrue(tempY < p.dy);
	}
	
	@Test
	public void collisionLeft() {
		p.pressedKey("up");
		assert w.canMoveLeft(1, 4);
	}

	@Test
	public void collisionRight() {
		p.pressedKey("up");
		assert w.canMoveRight(1, 3);
	}

	@Test
	public void collisionTop() {
		p.pressedKey("up");
		assert w.canMoveUp(3, 1);
	}

	@Test
	public void collisionBottom() {
		p.pressedKey("up");
		assert w.canMoveDown(-3, 1);
	}

}
