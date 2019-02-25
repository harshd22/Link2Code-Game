package Model;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.World.Objects;


/**
 * Tests for World class.
 * @author Harshdeep Singh
 *
 */
public class worldTESTS {

    // the original test included a test that would parseFile. however for the
    //external part of the tests we do not need that tests as there are more
    //maps that needs to be implemented. As for the rest of the tests they seem
    //fit in well and work according to the overall project and the package of
    //the class.


	@Test
	public void checkMap() {

		World w = new World( "MAP_A" ) ;


		



		assert w.getMap()[0][0] == Objects.TREE;

	}

	@Test
	public void checkMap1() {

		World w = new World( "MAP_A" ) ;

		assert w.getMap()[3][5] == Objects.NOTHING;

	}


	@Test
	public void testGetPosition() {

		World w = new World( "MAP_A" ) ;


		assert w.getAtPosition(0, 0) == "TREE";


	}

	@Test
	public void testGetPosition1() {

		World w = new World( "MAP_A" ) ;


		assertFalse( w.getAtPosition(0, 0) == "ABC");


	}

	@Test
	public void testGetPosition2() {

		World w = new World( "MAP_A" ) ;


		assert w.getAtPosition(0, 0) != "NOTHING";

	}

	@Test
	public void testCollisionDetection() {

		World w = new World( "MAP_A" ) ;


		assert w.getAtPosition(3, 5) == "NOTHING"		;
		assert w.canMoveUp(4, 5);


	}

	@Test
	public void testCollisionDetection1() {

		World w = new World( "MAP_A" ) ;



		assert w.getAtPosition(3, 5) == "NOTHING"		;
		assert w.canMoveDown(2, 5);


	}

	@Test
	public void testCollisionDetection2() {

		World w = new World( "MAP_A" ) ;
		



		assert w.getAtPosition(3, 5) == "NOTHING"		;
		assert w.canMoveRight(3, 4);


	}

	@Test
	public void testCollisionDetection3() {

		World w = new World( "MAP_A" ) ;
		



		assert w.getAtPosition(3, 5) == "NOTHING"		;
		assert w.canMoveLeft(3, 6);


	}

	@Test
	public void testCollisionDetection4() {

		World w = new World( "MAP_A" ) ;
		


		assert w.getAtPosition(1, 3) != "NOTHING"		;
		assertFalse( w.canMoveUp(2, 3));


	}



	@Test
	public void testCollisionDetection6() {

World w = new World( "MAP_A" ) ;
		



		assert w.getAtPosition(2, 2) == "ROCK"		;
		assertFalse(w.canMoveRight(2, 1));


	}

	@Test
	public void testCollisionDetection7() {

		World w = new World( "MAP_A" ) ;
		



		assert w.getAtPosition(2, 2) == "ROCK"		;
		assertFalse(w.canMoveLeft(2, 3));


	}

}
