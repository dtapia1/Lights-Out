/* Daniel Tapia
 * Studio05
 */

package edu.angelo.studio05;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.angelo.studio04.LightsOutBoard;

/**
 * Lights Out!
 * @author Daniel
 *	Class will create a Lights Out game to run on 
 *	Android device.
 *
 */
public class MainActivity extends AppCompatActivity {
	
	// variables for rows, columns, and number of colors in the array
	private final int NUM_ROWS = 4;
	private final int NUM_COLUMNS = 3;
	private final int[] colors = new int[] {
	   Color.rgb(170, 170, 170), // a light gray
	   Color.rgb(102, 136, 204), // a grayish blue
	   Color.rgb(0, 85, 255)     // an intense blue
	};
	// declare LightsOutBoard object
	private LightsOutBoard gameBoard;
	// declare and create a two dimensional array of buttons
	private Button[][] lightButtons = new Button[NUM_ROWS][NUM_COLUMNS];
	
	/**
	 * Method that will set the default text at the start of a new game.
	 * The colors will be set for each button depending on the int
	 * stored in given element.
	 */
	private void updateGameBoard(){
		
		/*TextView text = (TextView) findViewById(R.id.textView1);
	       text.setTextColor(Color.BLACK);
	       text.setBackgroundColor(Color.WHITE);
	       text.setText("Can you turn all the lights out?");*/

	     // set a color for each button
		for(int i = 0; i < NUM_ROWS; i++){
			for(int j = 0; j < NUM_COLUMNS; j++){
				
				if(gameBoard.getLightColor(i, j) == 0)
					lightButtons[i][j].setBackgroundColor(colors[0]);
				else if(gameBoard.getLightColor(i, j) == 1)
					lightButtons[i][j].setBackgroundColor(colors[1]);
				else
					lightButtons[i][j].setBackgroundColor(colors[2]);
							
			}
		}	
		/* Game has been won; change current text to indicate that game 
		 * has been won.
		 */
		
		if(gameBoard.isCleared()){
	    	   /*text.setTextColor(Color.WHITE);
	           text.setBackgroundColor(Color.rgb(0, 153, 0));
	           text.setText("THE WINNER IS YOU");*/
	       }
	}
	

	/**
	 * Method will create the gameBoard array using the variables
	 * for the number of rows, columns, and colors.
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // create new array using NUM_ROWS, NUM_COLUMNS, and number of colors
       gameBoard = new LightsOutBoard(NUM_ROWS, NUM_COLUMNS, colors.length);
       
       // initialize the array to corresponding button objects
       lightButtons[0][0] = (Button) findViewById(R.id.button00);
       lightButtons[0][1] = (Button) findViewById(R.id.button01);
       lightButtons[0][2] = (Button) findViewById(R.id.button02);
       lightButtons[1][0] = (Button) findViewById(R.id.button10);
       lightButtons[1][1] = (Button) findViewById(R.id.button11);
       lightButtons[1][2] = (Button) findViewById(R.id.button12);
       lightButtons[2][0] = (Button) findViewById(R.id.button20);
       lightButtons[2][1] = (Button) findViewById(R.id.button21);
       lightButtons[2][2] = (Button) findViewById(R.id.button22);
       lightButtons[3][0] = (Button) findViewById(R.id.button30);
       lightButtons[3][1] = (Button) findViewById(R.id.button31);
       lightButtons[3][2] = (Button) findViewById(R.id.button32);

       // update the gameBoard with initializations
       updateGameBoard();
            
    }
    
    /**
     * Method will be called when Start new game button is clicked
     * gameBoard will be randomized
     * @param view button
     */
    public void startNewGame(View view) {
    	
    	gameBoard.randomize();
    	updateGameBoard();
    }

    /**
     * 	Calls the click method
     *  for each corresponding button when clicked. 
     * @param view button
     */

	public void clickLight(View view) {

		String tag = (String) view.getTag();
		String[] gridLocation = tag.split(",");
		int row = Integer.parseInt(gridLocation[0]);
		int col = Integer.parseInt(gridLocation[1]);

		gameBoard.click(row,col);
		updateGameBoard();
	}
}