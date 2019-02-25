package View;


import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.junit.*;
import org.junit.runners.MethodSorters;

import Model.Model;


public class ViewTests {

	@Test
	public void test01_menu() throws InterruptedException {
		Model m = new Model();
		SwingUtilities.invokeLater(()->{
		      View v=new View(m);
		      new Timer(3000,e->v.dispose()).start();
		      });
		    Thread.sleep(3000);

	}

	@Test
	public void test02_gameDefault() throws InterruptedException {
		Model m = new Model();
		m.getGameState().state = "game";
		m.update();
		
		SwingUtilities.invokeLater(()->{
		      View v=new View(m);
		      try {
				m.update();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      new Timer(3000,e->v.dispose()).start();
		      });
		    Thread.sleep(3000);

	}

	@Test
	public void test03_gameTree() throws InterruptedException {
		Model m = new Model();
		m.getGameState().state = "game";
		m.update();
		
		SwingUtilities.invokeLater(()->{
		      View v=new View(m);
		      try {
				m.update();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      new Timer(3000,e->v.dispose()).start();
		      });
		    Thread.sleep(3000);

	}

	@Test
	public void test04_gameBarrel() throws InterruptedException {
		Model m = new Model();
		m.getGameState().state = "game";
		m.update();
		
		SwingUtilities.invokeLater(()->{
		      View v=new View(m);
		      try {
				m.update();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      new Timer(3000,e->v.dispose()).start();
		      });
		    Thread.sleep(3000);

	}

	//Display window
	//Check can't close using 'x'.
	//Check wrong answer
	//check right answer ("a")
	@Test
	public void test05_TalkState() throws InterruptedException {
		Model m = new Model();
		m.getGameState().state = "game";
		m.update();
		m.update();
		m.getGameState().state = "talk";
		View v=new View(m);
		m.update();
		SwingUtilities.invokeLater(()->{

		      new Timer(3000,e->v.dispose()).start();
		      });
		    Thread.sleep(3000);

	}


	//move up one space smoothly then stop
	@Test
	public void test06_moveUp() throws InterruptedException{
		Model m = new Model();
		m.getGameState().state = "game";
		m.update();


		SwingUtilities.invokeLater(()->{
			View v=new View(m);
			try {
				m.update();
				m.up();
				m.update();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		      new Timer(3000,e->v.dispose()).start();
		      });
		    Thread.sleep(3000);

	}

	//move down one space smoothly then stop
	@Test
	public void test07_moveDown() throws InterruptedException{
		Model m = new Model();
		m.getGameState().state = "game";
		m.update();


		SwingUtilities.invokeLater(()->{
			View v=new View(m);
			try {
				m.update();
				m.down();
				m.update();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		      new Timer(3000,e->v.dispose()).start();
		      });
		    Thread.sleep(3000);

	}

	//move to the left one space smoothly then stop
	@Test
	public void test08_moveLeft() throws InterruptedException{
		Model m = new Model();
		m.getGameState().state = "game";
		m.update();


		SwingUtilities.invokeLater(()->{
			View v=new View(m);
			try {
				m.update();
				m.left();
				m.update();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		      new Timer(3000,e->v.dispose()).start();
		      });
		    Thread.sleep(3000);

	}

	//move to the right one space smoothly then stop
	@Test
	public void test09_moveRight() throws InterruptedException{
		Model m = new Model();
		m.getGameState().state = "game";
		m.update();

		SwingUtilities.invokeLater(()->{
			View v=new View(m);
			try {
				m.update();
				m.right();
				m.update();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		      new Timer(3000,e->v.dispose()).start();
		      });
		    Thread.sleep(3000);

	}

	//should move left twice and display a line of trees of left.
	@Test
	public void test08_moveLefttwice() throws InterruptedException{
		Model m = new Model();
		m.getGameState().state = "game";
		m.update();

		View v=new View(m);
		SwingUtilities.invokeLater(()->{
			try {
				m.update();
				m.left();
				m.update();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		      new Timer(3000,e->m.getQuestion()).start();
		      });
		    Thread.sleep(3000);
		    SwingUtilities.invokeLater(()->{
				try {
					m.update();
					m.left();
					m.update();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			      new Timer(3000,e->v.dispose()).start();
			      });
			    Thread.sleep(3000);

	}
	
	@Test
	public void test09_moveRightLeft() throws InterruptedException{
		Model m = new Model();
		m.getGameState().state = "game";
		m.update();

		View v=new View(m);
		SwingUtilities.invokeLater(()->{
			try {
				m.update();
				m.left();
				m.update();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		      new Timer(3000,e->m.getQuestion()).start();
		      });
		    Thread.sleep(1000);
			SwingUtilities.invokeLater(()->{
				
				try {
					m.update();
					m.right();
					m.update();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			      new Timer(3000,e->v.dispose()).start();
			      });
			    Thread.sleep(3000);
			  
	}
	
	@Test
	public void test10_moveUPDown() throws InterruptedException{
		Model m = new Model();
		m.getGameState().state = "game";
		m.update();

		View v=new View(m);
		SwingUtilities.invokeLater(()->{
			try {
				m.update();
				m.up();
				m.update();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		      new Timer(3000,e->m.getQuestion()).start();
		      });
		    Thread.sleep(1000);
			SwingUtilities.invokeLater(()->{
				
				try {
					m.update();
					m.down();
					m.update();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			      new Timer(3000,e->v.dispose()).start();
			      });
			    Thread.sleep(3000);
			  
	}
	

}

