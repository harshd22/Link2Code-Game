package mockClasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

/**
 * It is a mock controller in order to check the behaviour of the controller
 *
 * @author Fran Colmenar
 *
 */

public class MockController implements ActionListener,KeyListener {

	MockModel model;

	public MockController(MockModel m) {
		model = m;
	}
	
	//called upon the correct answer given in talk window, needs to update model accordingly
	public void rightAnswer(){}
	
	public void save(){}
	
	public void load(){}
	
	//called upon the option closing of talk window without correct answer, needs to change model state away from 'talk' back to game.
	public void closeTalk(){}


	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource() instanceof JButton) {//All the actions clicking a JButton
				actionsOfJButton(e);
			}
		 else if(e.getActionCommand().equals("NewMap")){//When we go to the next map through a door
			 model.loadNewMap();
		 }
	}

	/**
	 * I check all the possible actions that a JButton can make
	 * @param e the event
	 */
	private void actionsOfJButton(ActionEvent e) {
		JButton aux = (JButton)e.getSource();
		if(aux.getName() == "Save") {
			model.save();
		} else if(aux.getName() == "Load") {
			model.load();
		}  else if(aux.getName() == "Exit") {
			model.exit();
		} else if(aux.getName() == "Resume") {
			model.resume();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("typed");
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
		if(model.getStatus() == 1){return true;}
		else{ return false;}
	}

	/**
	 * It checks all the different keys that has an effect in the game
	 * @param e the event
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
					model.interact();
				}
			}
			else {
				System.out.println("Movement not allowed");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("released");
	}
}
