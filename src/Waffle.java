import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Displays a waffle chart showing the proportions of the expenses 
 * of a company divided into sections according to the amount spent.
 * 
 * @author Reni Hristova
 * @version 2019-11-24 last updated 2021-01-07
 *
 */
public class Waffle extends Application {
	private static final int 	WIDTH 	= 30,				// The width of each square.
			 					OFFSET 	= WIDTH + 10;
	
	/**expenditures: Array of the company's expenditures*/
	private static Expenditure[] expenditures;
	
	/**maximum: number of sections to be displayed.*/
	private int 	maximum 		= 10;
	
	/** numberOfSquares: the proportion of each expenditure as an integer. */
	private int[] 	numberOfSquares = new int[maximum];
	
	/** colours: a two dimensional array, which contains 
	 * a set of 3 values (RGB) for each of the displayed expenditures.*/
	private int[][] colours 		= new int[maximum][3];
	
	/**
	 * This method calculates the proportions of each
	 * expenditure. 
	 * 
	 * First, find the sum of all the values of the expenditures.
	 * Then, compute the number of squares each section
	 * consists (rounded to the nearest integer). The last element
	 * is labelled as 'Other'.  
	 */
	public void calculateSquares() {
		if(maximum > expenditures.length) throw new IllegalArgumentException("Maximum too large.");
		
		int 	squares = 0;
		float 	total 	= 0;
		for(int i = 0; i < expenditures.length; i++) {
			total += expenditures[i].getValue();
		}
		for(int i = 0; i < maximum - 1; i++) {
			numberOfSquares[i] 	 = (int)Math.round((expenditures[i].getValue()/total)*100);
			squares 			+= numberOfSquares[i];
		}
			numberOfSquares[maximum-1] = 100 - squares;
	}

	/**
	 * This method creates a number of colours, which will be 
	 * displayed in the waffle chart to visualise the proportions as 
	 * colour-coded squares. The result is stored in the array colours.
	 */
	public void assignColours(){
		int counter = maximum;
		int r = 255, g = 200, b = 200;			// values for red, green and blue channel respectively
		for(int i = 0; counter > 0; i++, counter--){
			colours[i][0] 	= r;
			colours[i][1] 	= g;
			colours[i][2] 	= b;
			
			// Change the RGB values after each iteration.
			r += b; b = Math.abs(r - b); r -= b;	//swaps the values of r and b.
			r += g; g = Math.abs(r - g); r -= g;	//swaps the values of r and g. => r = g; g = b; b = r;
			
			if (i>5 && i<15){
				g = Math.abs(g-100);
				while(g>250) g-=50;
			}
			else {
				if(i>15) {
					b = Math.abs(b-150);
					while(b>250) b-=50;
				}
				else {
					r = Math.abs(r-200);
					while(r>250) r-=50;
				}
			}
		}
	 }
	
	/**
	 * Create the waffle chart square by square, add all 
	 * elements to a group and then displays the waffle chart.
	 */
	@Override
	public void start(Stage waffleChart) throws Exception {
		Group group = new Group();
		Scene scene = new Scene(group, 850, 650);
		
		// Sort the expenditures
		Arrays.sort(expenditures, (Expenditure expenditure1, Expenditure expenditure2)
				->expenditure2.getValue() - expenditure1.getValue());
		// Calculate the proportion of each one
		calculateSquares();
		// Assign a colour to each for visualisation
		assignColours();
		
		// Iterate over every square in the grid
		/**expIndex:  index of the element from the array numberOfSquares. */
		for(int i = 0, expIndex = 0; i < 10; i++) {
			for(int j = 0; j < 10 && expIndex < maximum; j++) {
				
				// Create a new square using the generated colours
				if (numberOfSquares[expIndex] > 0) {
					Rectangle square = new Rectangle(OFFSET*(j+1), OFFSET*(i+1), WIDTH, WIDTH);
					square.setFill(Color.rgb(colours[expIndex][0], 
											 colours[expIndex][1], 
											 colours[expIndex][2]));
					group.getChildren().add(square);
					numberOfSquares[expIndex]--;
				}
				
				// Add the legend on the right side of the grid 
				if(numberOfSquares[expIndex] == 0) {
					Rectangle descriptionSquare = new Rectangle(OFFSET*15, OFFSET*(expIndex+1), WIDTH, WIDTH);
					descriptionSquare.setFill(Color.rgb(colours[expIndex][0], 
													 	colours[expIndex][1], 
													 	colours[expIndex][2]));
					group.getChildren().add(descriptionSquare);
					
					String description = (expIndex == (maximum-1)) ? "Other" : expenditures[expIndex].getDescription();
					group.getChildren().add(new Text(OFFSET*16, OFFSET*(expIndex+1.5), description));		
					
					// Go to the next expenditure
					expIndex++;
				}
			}
		}
		
		// Display the Waffle Chart
		waffleChart.setTitle("Waffle Chart");
		waffleChart.setScene(scene);
		waffleChart.show();
	} 

	
	public static void main(String[] args) {
		Expenditure[] expendituresA = new Expenditure[]{ 	new Expenditure("Salaries", 11000),
															new Expenditure("Paper", 2000),
															new Expenditure("Rent", 5000),
															new Expenditure("Most popular books on Java etc.", 10000),
															new Expenditure("Heating", 3000),
															new Expenditure("Coffee/Tea", 7000),
															new Expenditure("Biscuits", 8000),
															new Expenditure("Travel", 18000),
															new Expenditure("Electricity", 1000),
															new Expenditure("Pencils", 3000)
														};
		expenditures = expendituresA;
		launch(args);
	}
}
