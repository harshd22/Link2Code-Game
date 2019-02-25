package controller;

import static org.junit.Assert.assertEquals;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.junit.Test;

import mockClasses.MockController;
import mockClasses.MockModel;

/**
 * It test the controller class using the mock controller in order to
 * ensure the correct behaviour of the controller
 *
 * @author Fran Colmenar
 *
 */

public class ControllerTest {
	MockModel model = new MockModel();

	MockController c = new MockController(model);//controller

	/**
	 * It test that when the save button is pressed
	 */
	@Test
	public void saveButtonTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JButton button = new JButton();//I create the button which will be pressed
		button.addActionListener(c);
		button.setName("Save");
		button.doClick();//I click the button

		String message = baos.toString();//I get the string which is supposed to be printed

		assertEquals(message, "Save\n");
	}

	/**
	 * It test that when the Load button is pressed
	 */
	@Test
	public void loadButtonTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JButton button = new JButton();//I create the button which will be pressed
		button.addActionListener(c);
		button.setName("Load");
		button.doClick();//I click the button

		String message = baos.toString();//I get the string which is supposed to be printed

		assertEquals(message, "Load\n");
	}

	/**
	 * It test that when the Exit button is pressed
	 */
	@Test
	public void exitButtonTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JButton button = new JButton();//I create the button which will be pressed
		button.addActionListener(c);
		button.setName("Exit");
		button.doClick();//I click the button

		String message = baos.toString();//I get the string which is supposed to be printed

		assertEquals(message, "Exit\n");
	}

	/**
	 * It test that when the Resume button is pressed
	 */
	@Test
	public void resumeButtonTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JButton button = new JButton();//I create the button which will be pressed
		button.addActionListener(c);
		button.setName("Resume");
		button.doClick();//I click the button

		String message = baos.toString();//I get the string which is supposed to be printed

		assertEquals(message, "Resume\n");
	}

	/**
	 * It test that when the load map event is sent
	 */
	@Test
	public void loadMapTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		Object aux = new Object();
		ActionEvent event = new ActionEvent(aux, 0, "NewMap");
		c.actionPerformed(event);

		String message = baos.toString();//I get the string which is supposed to be printed

		assertEquals(message, "Load map\n");
	}

	/**
	 * It test when the key esc is pressed
	 */
	@Test
	public void escPressedTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JFrame jf = new JFrame("title");
		jf.addKeyListener(c);
		jf.setVisible(false);

		KeyEvent key = new KeyEvent(jf, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_ESCAPE,'A');
		jf.getKeyListeners()[0].keyPressed(key);

		String message = baos.toString();//I get the string which is supposed to be printed
		jf.dispose();

		assertEquals(message, "Game menu\n");
	}

	/**
	 * It test when a key is pressed but the movement is not allowed
	 */
	@Test
	public void movementNotAllowedTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JFrame jf = new JFrame("title");
		jf.addKeyListener(c);
		jf.setVisible(false);

		model.setStatus(0);//I change the status
		KeyEvent key = new KeyEvent(jf, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_W,'A');
		jf.getKeyListeners()[0].keyPressed(key);

		String message = baos.toString();//I get the string which is supposed to be printed
		jf.dispose();

		assertEquals(message, "Movement not allowed\n");
	}

	/**
	 * It test that when the key up is pressed
	 */
	@Test
	public void upPressedTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JFrame jf = new JFrame("title");
		jf.addKeyListener(c);
		jf.setVisible(false);

		KeyEvent key = new KeyEvent(jf, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'A');
		jf.getKeyListeners()[0].keyPressed(key);

		String message = baos.toString();//I get the string which is supposed to be printed

		jf.dispose();
		assertEquals(message, "Up\n");
	}

	/**
	 * It test that when the w is pressed
	 */
	@Test
	public void wPressedTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JFrame jf = new JFrame("title");
		jf.addKeyListener(c);
		jf.setVisible(false);

		KeyEvent key = new KeyEvent(jf, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_W,'A');
		jf.getKeyListeners()[0].keyPressed(key);

		String message = baos.toString();//I get the string which is supposed to be printed
		jf.dispose();

		assertEquals(message, "Up\n");
	}

	/**
	 * It test that when the down key is pressed
	 */
	@Test
	public void downPressedTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JFrame jf = new JFrame("title");
		jf.addKeyListener(c);
		jf.setVisible(false);

		KeyEvent key = new KeyEvent(jf, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_DOWN,'A');
		jf.getKeyListeners()[0].keyPressed(key);

		String message = baos.toString();//I get the string which is supposed to be printed
		jf.dispose();

		assertEquals(message, "Down\n");
	}

	/**
	 * It test that when the s key is pressed
	 */
	@Test
	public void sPressedTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JFrame jf = new JFrame("title");
		jf.addKeyListener(c);
		jf.setVisible(false);

		KeyEvent key = new KeyEvent(jf, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_S,'A');
		jf.getKeyListeners()[0].keyPressed(key);

		String message = baos.toString();//I get the string which is supposed to be printed
		jf.dispose();

		assertEquals(message, "Down\n");
	}

	/**
	 * It test that when the right key is pressed
	 */
	@Test
	public void rightPressedTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JFrame jf = new JFrame("title");
		jf.addKeyListener(c);
		jf.setVisible(false);

		KeyEvent key = new KeyEvent(jf, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_RIGHT,'A');
		jf.getKeyListeners()[0].keyPressed(key);

		String message = baos.toString();//I get the string which is supposed to be printed
		jf.dispose();

		assertEquals(message, "Right\n");
	}

	/**
	 * It test that when the d key is pressed
	 */
	@Test
	public void dPressedTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JFrame jf = new JFrame("title");
		jf.addKeyListener(c);
		jf.setVisible(false);

		KeyEvent key = new KeyEvent(jf, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_D,'A');
		jf.getKeyListeners()[0].keyPressed(key);

		String message = baos.toString();//I get the string which is supposed to be printed
		jf.dispose();

		assertEquals(message, "Right\n");
	}

	/**
	 * It test that when the left key is pressed
	 */
	@Test
	public void leftPressedTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JFrame jf = new JFrame("title");
		jf.addKeyListener(c);
		jf.setVisible(false);

		KeyEvent key = new KeyEvent(jf, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_LEFT,'A');
		jf.getKeyListeners()[0].keyPressed(key);

		String message = baos.toString();//I get the string which is supposed to be printed
		jf.dispose();

		assertEquals(message, "Left\n");
	}

	/**
	 * It test that when the a key is pressed
	 */
	@Test
	public void aPressedTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JFrame jf = new JFrame("title");
		jf.addKeyListener(c);
		jf.setVisible(false);

		KeyEvent key = new KeyEvent(jf, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_A,'A');
		jf.getKeyListeners()[0].keyPressed(key);

		String message = baos.toString();//I get the string which is supposed to be printed
		jf.dispose();

		assertEquals(message, "Left\n");
	}

	/**
	 * It test that when the space key is pressed
	 */
	@Test
	public void spacePressedTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JFrame jf = new JFrame("title");
		jf.addKeyListener(c);
		jf.setVisible(false);

		KeyEvent key = new KeyEvent(jf, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_SPACE,'A');
		jf.getKeyListeners()[0].keyPressed(key);

		String message = baos.toString();//I get the string which is supposed to be printed
		jf.dispose();

		assertEquals(message, "Interact\n");
	}



	/**
	 * It test that when a key is typed the correct method is executed
	 */
	@Test
	public void keyTypedTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests

		JFrame jf = new JFrame("title");
		jf.addKeyListener(c);
		jf.setVisible(true);

		KeyEvent key = new KeyEvent(jf, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_A,'A');
		jf.getKeyListeners()[0].keyPressed(key);

		key = new KeyEvent(jf, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  KeyEvent.VK_A,'A');
		jf.getKeyListeners()[0].keyReleased(key);

		System.setOut(new PrintStream(baos));//I set the
		jf.getKeyListeners()[0].keyTyped(key);


		String message = baos.toString();//I get the string which is supposed to be printed

		assertEquals(message, "typed\n");
	}


	/**
	 * It test that when a key is released the correct method is executed
	 *
	@Test
	public void keyReleasedTest() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//Auxiliary to the tests
		System.setOut(new PrintStream(baos));//I set the

		JFrame jf = new JFrame("title");
		jf.addKeyListener(c);
		jf.setVisible(true);

		KeyEvent key = new KeyEvent(jf, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0,  KeyEvent.VK_A,'A');
		jf.getKeyListeners()[0].keyReleased(key);

		String message = baos.toString();//I get the string which is supposed to be printed

		assertEquals(message, "released\n");
	}*/}
