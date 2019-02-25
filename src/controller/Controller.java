package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

import Model.Model;

/**
 * It is the class in charge of connecting the inputs of the user and the model
 *
 * @author Fran Colmenar
 * @review Simarjit Singh
 *
 */

public class Controller implements ActionListener,KeyListener {
	Model model;

	public Controller(Model m) {
		model = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource() instanceof JButton) {//All the actions clicking a JButton
				actionsOfJButton(e);
			}
		 else if(e.getActionCommand().equals("NewMap")){//When we go to the next map through a door
			 model.loadNewMap(e.getActionCommand());//We pass a string reference of the new map
		 }
		 else if(e.getActionCommand().equals("Correct answer given")){//When we go to the next map through a door
			 model.correctAnswer();
		 }
		 else if(e.getActionCommand().equals("Close talk")){//When we go to the next map through a door
			 model.closeTalk();
		 }	else if(e.getActionCommand().equals("Speed")){//When we go to the next map through a door
			 model.speed();
		 }
	}

	/**
	 * I check all the possible actions that a JButton can make
	 * @param e the event
	 * @review Changed the variable name to be more descriptive as to what it is
	 * as aux is not clear as to what it refers to.
	 */
	private void actionsOfJButton(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if(button.getName() == "Save") {
			model.save();
		} else if(button.getName() == "Load") {
			model.load();
		}  else if(button.getName() == "Quit") {
			model.exit();
		} else if(button.getName() == "Resume") {
			model.resume();
		}else if(button.getName() == "Start Game") {
			model.startGame();
		}


	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		checkKey(e);//I check the keys
	}

	/**
	 * It checks if we are in the game screen
	 * @return true if we are in the game screen and false otherwise
	 */
	private boolean moveAllowed() {
		if(model.getState() == "game"){return true;}
		else{ return false;}
	}

	/**
	 * It checks all the different keys that has an effect in the game
	 * @param e the event
	 * @review Checking this method here, it gave me an idea to add code in my
	 * player class that will also consider the buttons WASD as well as the
	 * arrow keys. As before it only considered the arrow keys pressed but not
	 * WASD
	 */
	private void checkKey(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_ESCAPE) {//The key esc has the same action everywhere
			model.gameMenu();//The menu inside the game screen
		}
		else {
			if(moveAllowed()) {//I check if the movement is allowed
				if ((keyCode == KeyEvent.VK_UP) || (keyCode == KeyEvent.VK_W)){
					model.up();
				} else if ((keyCode == KeyEvent.VK_DOWN) || (keyCode == KeyEvent.VK_S)){
					model.down();
				} else if ((keyCode == KeyEvent.VK_RIGHT) || (keyCode == KeyEvent.VK_D)){
					model.right();
				} else if ((keyCode == KeyEvent.VK_LEFT) || (keyCode == KeyEvent.VK_A)){
					model.left();
				} else if(keyCode == KeyEvent.VK_SPACE) {
					try {
					model.interact();
					}catch(NullPointerException er) { }

				}
			}
		}
	}

	/**
	 * Method to notify model that movement animation is finished
	 */
	public void moveFinished(){
		model.moveFinished();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
