package mockClasses.ControllerMock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * It is a mock controller in order to check the behaviour of the controller
 *
 * @author Fran Colmenar
 *
 */

public class MockController implements ActionListener,KeyListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("perform");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("typed");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("pressed");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("released");
	}


}
