package Model;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * This class is all about creating a 2d world/map for the game from a text file and
 * checks for collision as well.
 *
 * @author Harshdeep Singh
 *
 */
public class World implements Serializable {

	// works for now, but may need to be replaced with another type if this list gets too long
	public enum Objects{
		TREE  , FLOWER , NOTHING , NORTH_DOOR , EAST_DOOR , WEST_DOOR , SOUTH_DOOR , BARREL , ROCK , GRASS, COIN
	}

	private Objects map[][]; //2D array for map
	private Scanner scanner;
	private String file; //name of file to be loaded
	private int height;
	private int width;
	private int value;

	public World(String file ){
		this.file = file;
		parseMapFile();
		Random rand = new Random();
		value = rand.nextInt(3);

	}

	/**
	 * Parse the map file and store the map into a 2D array
	 * @param file
	 */

	public void parseMapFile() {


		try(BufferedReader br = new BufferedReader(new FileReader( "resources/" +file + ".txt"))) {

			height = Integer.parseInt(br.readLine());
			width = Integer.parseInt(br.readLine());
			map = new Objects[width][height];

			String line;
			int x = 0;


			while((line = br.readLine()) != null) {

				String[] row = line.trim().split(" ");

				for(int y = 0 ; y < row.length ; y++) {

					if(row[y].equalsIgnoreCase("T")) {
						map[y][x] = Objects.TREE;
					}

					if(row[y].equalsIgnoreCase("N")) {

						map[y][x] = Objects.NOTHING;
					}

					if(row[y].equalsIgnoreCase("G")) {

						map[y][x] = Objects.GRASS;
					}

					if(row[y].equalsIgnoreCase("B")) {
						map[y][x] = Objects.BARREL;
					}

					if(row[y].equalsIgnoreCase("F")) {
						map[y][x] = Objects.FLOWER;
					}

					if(row[y].equalsIgnoreCase("R")) {
						map[y][x] = Objects.ROCK;
					}

					if(row[y].equalsIgnoreCase("ND")) {
						map[y][x] = Objects.NORTH_DOOR;
					}

					if(row[y].equalsIgnoreCase("SD")) {
						map[y][x] = Objects.SOUTH_DOOR;
					}

					if(row[y].equalsIgnoreCase("ED")) {
						map[y][x] = Objects.EAST_DOOR;
					}

					if(row[y].equalsIgnoreCase("WD")) {
						map[y][x] = Objects.WEST_DOOR;
					}

					if(row[y].equalsIgnoreCase("C")) {
						map[y][x] = Objects.COIN;
					}
				}

				x++;
			}


		}catch(IOException e) {}

	}


	/**
	 * Returns true if player can move up
	 *
	 * @return
	 */
	public boolean canMoveUp(int posX , int posY) {
		if( posY - 1 >= 0 ) {
			if(map[ posX ][ posY - 1 ] == Objects.NOTHING || map[ posX ][ posY - 1 ] == Objects.GRASS)
				return true;
		}
		return false;
	}

	/**
	 * Returns true if player can move down
	 *
	 * @return
	 */
	public boolean canMoveDown(int posX , int posY) {
		if( posY + 1 < height) {
			if(map[ posX ][ posY + 1 ] == Objects.NOTHING || map[ posX ][ posY + 1 ] == Objects.GRASS)
				return true;
		}
		return false;
	}

	/**
	 * Returns true if player can move left
	 *
	 * @return
	 */
	public boolean canMoveLeft(int posX , int posY) {
		if( posX - 1 >= 0) {
			if(map[ posX - 1 ][ posY  ] == Objects.NOTHING || map[ posX -1][ posY  ] == Objects.GRASS)
				return true;
		}
		return false;
	}

	/**
	 * Returns true if player can move right
	 *
	 * @return
	 */

	public boolean canMoveRight(int posX , int posY) {
		if( posX + 1 < width ) {
			if(map[ posX + 1][ posY  ] == Objects.NOTHING || map[ posX +1][ posY  ] == Objects.GRASS)
				return true;
		}
		return false;
	}

	/**
	 * Returns the width of the current map array
	 */
	public int getWidth(){
	    return this.width;
	}

	/**
	 * Returns the height of the current map array
	 */
	public int getHeight(){
	    return this.height;
	}

	/**
	 * Returns the object name at the given position
	 * @param x
	 * @param y
	 * @return
	 */

	public String getAtPosition(int x , int y) {

		return map[x][y].toString();



	}

	public Objects[][] getMap() {
		return map;
	}

	public int getValue() {
		return value;
	}

	public void updatingMap(int xPos, int i, String string) {
		// TODO Auto-generated method stub
        map[xPos][i] = Objects.NOTHING;
	}


}
