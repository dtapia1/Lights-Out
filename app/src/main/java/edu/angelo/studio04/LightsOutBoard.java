/* Daniel Tapia
 * Studio 04
 * Lights Out
 */
package edu.angelo.studio04;

import android.util.Log;

import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Daniel
 * LightsOutBoard class declaration 
 * 
 */
public class LightsOutBoard {
	public static String TAG = LightsOutBoard.class.getSimpleName();

	// number of rows in array
	private final int numRows;
	// number of columns in array
	private final int numColumns;
	// number of light colors for each button
	private final int numColors;
	
	// default two dimensional array
	private final int[][] lights;
	
	/**
	 * Constructor will initialize variables at object creation
	 * @param numRows number of rows on light board
	 * @param numColumns number of columns on light board
	 * @param numColors number of colors or light
	 */
	public LightsOutBoard(int numRows, int numColumns, int numColors) {
		
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.numColors = numColors;
		
		
		lights = new int[numRows][numColumns];
		randomize();
		
	}
	
	/**
	 * Constructor will call other constructor and initialize lights array
	 * with variables that are passed
	 * @param numRows
	 * @param numColumns
	 */
	public LightsOutBoard(int numRows, int numColumns) {
		
		this(numRows, numColumns, 2);
	}
	
	/**
	 * Method will retrieve int to represent the light color
	 * of each button.
	 * @param row current row
	 * @param column current column
	 * @return int for each light color
	 */
	public int getLightColor (int row, int column) {
		
		try{
			return lights[row][column];
		//ArrayIndexOutOfBounds exception
		} catch(ArrayIndexOutOfBoundsException Exception) {
			
			return 0;
		}
		
	}
	
	/**
	 * Method will change light color accordingly based on current light color
	 * @param row current row
	 * @param column current column
	 */
	private void flip (int row, int column) {
		
		try{
			
			/// check issue with 1,0 and 2,0
			if(lights[row][column] == 0)
				lights[row][column] = 2;	// set element to wrap around to 2
			else
				lights[row][column] -= 1;	// decrement element if not zero
			
			// add if statement in the case that the element is on zero 0
		//ArrayIndexOutOfBounds exception
		} catch(ArrayIndexOutOfBoundsException Exception) {
			Log.d(TAG, Exception.getMessage());
		}
	}
	
	/**
	 * Method will call flip method in order to change current light
	 * color based on number of clicks
	 * @param row current row
	 * @param column current column
	 */
	public void click (int row, int column) {
	
			
			flip(row, column); //current light
			if (row > 0)
				flip(row - 1, column);
			if (row < numRows )
				flip(row + 1, column);
			if (column > 0)
				flip (row, column - 1);
			if (column < numColumns)
				flip (row, column + 1);		
				
		}	
	
	/**
	 * Method will call primary click method specific number of times
	 * based on timesToClick parameter
	 * @param row current row
	 * @param column current column
	 * @param timesToClick total clicks
	 */
	public void click (int row, int column, int timesToClick) {
		
		for (int i = 0; i < timesToClick; i++)
			click(row, column);		
	}	
	
	/**
	 * Will test board to see if it has been cleared
	 * @return true or false
	 */
	public boolean isCleared () {
		
		for (int i = 0; i < numRows; i++) {
		    for (int j = 0; j < numColumns; j++) {
		        if (lights[i][j] != 0)
		            return false;
		    }
		}
		return true;
	}
	
	/**
	 * Will randomize ints within the lights array
	 */
	public void randomize() {
		
		Random rand = new Random();
		
	    //int randomNum = rand.nextInt((numColors - 0) + 1) + 0;
		
	    // set each element to default value of zero
		for (int i = 0; i < numRows; i++) {
		    for (int j = 0; j < numColumns; j++) {
		        lights[i][j] = 0;
		   }
	    }
		
		// randomize if array is set to all zeros
		while(isCleared()){
			for (int i = 0; i < numRows; i++) {
			    for (int j = 0; j < numColumns; j++) {
			    	int randomNum = rand.nextInt((numColors - 0) + 1) + 0;
				    click(i,j,randomNum);  // call click on each element 0, 1, or 2 times   
			   }
		    }	
		}	
	}
	
	/**
	 * toString method to print out the formatted lights array
	 */
	public String toString() {
        
		String string = "";
		for (int i = 0; i < numRows; i++) {
			string+= "[";
		    for (int j = 0; j < numColumns; j++) {
		        string+= lights[i][j];
		       
		    }
		    string+= "]";
		    string+= "\n";
		}
		return string;
    }
		    
	/**
	 * Main method to test lightsOutBoard object
	 */
	public static void main(String [ ] args) {
		
	// scanner object to accept input from user
	Scanner input = new Scanner(System.in);	
	// default board size
	LightsOutBoard board = new LightsOutBoard(4,3,2);
	
	// variables to hold input values from user
	int row, column;
	
	// print initial board 
	System.out.print(board);
	System.out.println("-----");
	
	// prompt for input while board is not cleared
	while(!board.isCleared()) {
		
		System.out.println("Enter the row and column of the board to click.");
		System.out.print("Row:");
		row = input.nextInt();
		System.out.print("Column:");		
		column = input.nextInt();
		
		board.click(row, column);
		System.out.print(board);
		System.out.println("-----");
	}
	
	// print win message if board is cleared
	System.out.println("You Win!");

	//close scanner
	input.close();
	
	}
}
