package mockClasses.ViewMock;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import View.View;

public class Controller implements KeyListener{

	public Controller(View v,Model m){
		
	}
	
	//called upon the correct answer given in talk window, needs to update model accordingly
	public void rightAnswer(){}
	
	public void save(){}
	
	public void load(){}
	
	//called upon the option closing of talk window without correct answer, needs to change model state away from 'talk' back to game.
	public void closeTalk(){}


    //ignore from here down, required to compile. not used in mock.
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
