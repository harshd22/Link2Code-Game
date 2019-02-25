package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Model.Model;
import controller.Controller;

/**
 * Core View handling frame. Creates a JFrame and puts the misc JPanels into a card layout as it creates them.
 *
 * @Author Jeremy Logan
 * @review Fran Colmenar
 */
@SuppressWarnings("serial")
public class View extends JFrame implements Observer {

	private Model model;
	private Controller control;

	private JFrame frame;
	private JLabel title;

	private JPanel core; // main game card panel
	private JPanel menu;
	private JPanel over;

	private GameScreen game; // main game panel (contains board etc)
	private JButton start;
	private CardLayout layout;
	private Timer timer;

	private int playerX;
	private int playerY;

	/**
 	* @param m = Model Class for referencing the world view.
 	*/
	public View(Model m) {
		super("A Link to the Code");
		this.model = m;
		playerX = model.getX();
		playerY = model.getY();
		m.addObserver(this);
		control = new Controller(m);
		this.addKeyListener(control);
		game = new GameScreen(m);
		this.setFocusable(true);
		this.setSize(700, 700);
		initGui();
	}

	/**
	 * Initializes the core interface, creates the layout and calls to create each of the viewable screens.
	 */
	private void initGui() {
		// create Core frame
		core = new JPanel();
		core.setPreferredSize(new Dimension(700, 700));
		this.layout = new CardLayout();
		core.setLayout(layout);

		buildMenu();
		buildOver();

		core.add(menu, "menu");
		core.add(game, "game");
		core.add(over, "over");

		this.add(core);

		this.setVisible(true);
		this.pack();
		this.setResizable(false);
	}

	/**
	 * Creates the field JPanel menu that holds the interaction buttons and title.
	 * @review Some methods are created in order to reduce the complexity of the method
	 * and in order to be able to test all the functionalities of it separately
	 */
	public void buildMenu(){
	    menu = new JPanel();
		menu.setPreferredSize(new Dimension(700, 700));
		menu.setBackground(Color.lightGray);
		BoxLayout MenuLayout = new BoxLayout(menu, BoxLayout.Y_AXIS);
		menu.setLayout(MenuLayout);

		JButton start = createButton("Start Game");
		start.setFont(new Font("Arial", Font.PLAIN, 40));

		JButton quit = createButton("Quit");
		quit.setFont(new Font("Arial", Font.PLAIN, 40));

		JButton resume = createButton("Resume");
		resume.setFont(new Font("Arial", Font.PLAIN, 30));

		JButton save = createButton("Save");
		save.setFont(new Font("Arial", Font.PLAIN, 30));

		JButton load = createButton("Load");
		load.setFont(new Font("Arial", Font.PLAIN, 30));

		// current filler
		JLabel title = confTitle("A Link to the Code");//I set the label

		start.setAlignmentX(Component.CENTER_ALIGNMENT);
		save.setAlignmentX(Component.CENTER_ALIGNMENT);
		load.setAlignmentX(Component.CENTER_ALIGNMENT);
		quit.setAlignmentX(Component.CENTER_ALIGNMENT);
		resume.setAlignmentX(Component.CENTER_ALIGNMENT);

		menu.add(title);
		menu.add(Box.createRigidArea(new Dimension(0, 75)));
		menu.add(start);
		menu.add(Box.createRigidArea(new Dimension(0, 60)));
		menu.add(resume);
		menu.add(Box.createRigidArea(new Dimension(0, 60)));
		menu.add(save);
		menu.add(Box.createRigidArea(new Dimension(0, 60)));
		menu.add(load);
		menu.add(Box.createRigidArea(new Dimension(0, 60)));
		menu.add(quit);
	}

	/**
	 * It creates a JButton given a string
	 * It assigns it an action listener
	 * @param string: the name of the button
	 * @return: the button
	 * @review Method created to facilitate the task of adding new buttons in the future
	 */
	private JButton createButton(String string) {
		JButton button = new JButton(string);
		button.addActionListener(control);
		button.setName(string);
		return button;
	}

	/**
	 * Creates the field over which is the game over screen. Holds a quit button.
	 */
	public void buildOver(){
	    over = new JPanel();

		JButton closeButton = createButton("Quit");
		closeButton.setFont(new Font("Arial", Font.PLAIN, 40));
		JLabel gameOver = new JLabel();
		gameOver = confTitle("The Game is Over");
		over.add(gameOver);
		over.add(Box.createRigidArea(new Dimension(0, 100)));
		over.add(closeButton);
	}

	/**
	 * It creates the JLabel title with a specific String
	 * @param string: The text of the header
	 * @review method created to have less code in the main methods
	 * and also for making easier the creation of new header in the future
	 */
	private JLabel confTitle(String string) {
		JLabel temp = new JLabel();
		temp.setText(string);
		Font f = new Font("TimesRoman", Font.PLAIN, 60);
		temp.setFont(f);
		temp.setAlignmentX(Component.CENTER_ALIGNMENT);
		return temp;
	}

    /**
     * Call to change the screen from external source
     */
	public void changeScreen() {
		layout.next(core);
	}

    /**
     * Builds the next move to be graphically represented by checking if theres a new position.
     * Once it has worked out the new position it increments the game screens draw position with a timer.
     */
	private void movement() {
		// sort out if player has moved. if has animate with timer, incrementing
		// as needed.
		game.update(playerX, playerY,model.getGameState().getInstance().getPlayer().doorsOpen());
		int newX = model.getX();
		int newY = model.getY();
		final String dir;
		if (newY != playerY || newX != playerX) {
			if (newY < playerY) {
				dir = "up";
			} else if (newY > playerY) {
				dir = "down";
			}else if (newX < playerX) {
				dir = "left";
			} else if (newX > playerX) {
				dir = "right";
			}else{dir=null;}
			
			//creates a new int and alters it by the stored variable.
			int maxSpeed = 100-model.getGameState().getInstance().getPlayer().getSpeed();
			//speed variable is then used to set timer speed.
			timer = new Timer(maxSpeed, new ActionListener() {
				public void actionPerformed(ActionEvent evt) {

					if(dir.equals("up")){
						if(game.getPlayerY()!=newY){
							game.moveUp();
						}else{
						    timer.stop();
			                playerX=newX;
			                playerY=newY;
			                control.moveFinished();
						}
					}else if(dir.equals("down")){
						if(game.getPlayerY()!=newY){
							game.moveDown();
						}else{
						    timer.stop();
			                playerX=newX;
			                playerY=newY;
			                control.moveFinished();
						}
					}else if(dir.equals("left")){
						if(game.getPlayerX()!=newX){
							game.moveLeft();
						}else{
						    timer.stop();
			                playerX=newX;
			                playerY=newY;
			                control.moveFinished();
						}
					}else if(dir.equals("right")){
						if(game.getPlayerX()!=newX){
							game.moveRight();
						}else{
						    timer.stop();
			                playerX=newX;
			                playerY=newY;
			                control.moveFinished();
						}
					}
					repaint();
				}
			});
			timer.start();
		}

	}
	

    /**
     * Checks to see which stage it is and makes sure that the appropriate screen is shown in the card layout.
     */
	@Override
	public void update(Observable arg0, Object arg1) {
		String state = model.getState();
		if (state.equals("menu")) {
			layout.show(core, "menu");
		} else if (state.equals("game") || state.equals("move")) {
			layout.show(core, "game");
		} else if (state.equals("over")) {
			layout.show(core, "over");
		}
        if(state.equals("load")){
			playerX = model.getX();
			playerY = model.getY();
		}
		movement();
		repaint();
		if (state.equals("talk")) {
			layout.show(core, "game");
			new TalkWindow(this, model, control).setVisible(true);
		}
	}

	/**
	 * Sets size.
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(707, 727);
	}

	@Override
	public void paint(Graphics g) {
		super.paintComponents(g);

	}

}
