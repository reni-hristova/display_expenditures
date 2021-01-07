import java.util.Arrays;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 * This class displays a pie chart from a given 
 * array of expenditures. 
 * 
 * @author Reni Hristova
 * @version 2019-12-03 last updated 2021-01-08
 */
public class Pie extends Application {
	/**expenditures: Array of the company's expenditures*/
	public static Expenditure[] expenditures;
	/**maximum: number of sections to be displayed.*/
	public static int 		 maximum;
	private static final int TEXT_OFFSET 	= 20,
							 CIRCLE_OFFSET  = TEXT_OFFSET*2;
	private int 			 sceneWidth 	= 800,
							 sceneHeight 	= 500,
							 xCentre		= sceneWidth/2,				// x and y cordinates of the pie chart centre
							 yCentre		= sceneHeight/2,
							 radius 		= sceneHeight/2 - CIRCLE_OFFSET*2;
	private double[][]	coordinatesOfLines 	= new double[maximum][2],
						coordinatesOfText	= new double[maximum][2];

	/**
	 * Calculates the proportions of the expenditures, maps 
	 * them to angles and computes the coordinates of the lines
	 * for the pie chart. The start line is along the 12 o'clock 
	 * line.The last element is labelled "Other" and 
	 * does not correspond to one single expenditure.
	 */
	public void calculateAngle() {
		if(maximum > expenditures.length) throw new IllegalArgumentException("Maximum is larger than the "
																				+ "size of the array.");
		int 	spaceTaken 	= 0;
		float 	total 		= 0; 		//sum of all expenditures
		for(int i = 0; i < expenditures.length; i++) {
			total += expenditures[i].getValue();
		}
		// 
		for(int i = 0; i <maximum; i++) {
			int angle = (int)Math.round((expenditures[i].getValue()/total)*360);
			
			// Compute coordinates of each line 
			if(i == maximum-1) 
				angle = 360 - spaceTaken;
			
			spaceTaken += angle;
			
			coordinatesOfLines[i][0] = xCentre + radius*((Math.sin(Math.toRadians(spaceTaken))));
			coordinatesOfLines[i][1] = yCentre + radius*(-(Math.cos(Math.toRadians(spaceTaken))));
			
			coordinatesOfText[i][0] = xCentre + (radius + TEXT_OFFSET)*((Math.sin(Math.toRadians(spaceTaken - angle/2))));
			coordinatesOfText[i][1] = yCentre + (radius + TEXT_OFFSET)*(-(Math.cos(Math.toRadians(spaceTaken - angle/2))));
		}
	}
	
	/**
	 * Display the pie chart. 
	 *  
	 * @param stage The window to be displayed.
	 */
	@Override
	public void start(Stage pieChart) throws Exception {
		Group group = new Group();
		Scene scene = new Scene(group, sceneWidth, sceneHeight);
		
		// Sort the expenditures
		Arrays.sort(expenditures, (Expenditure expenditure1, Expenditure expenditure2)
				-> expenditure2.getValue() - expenditure1.getValue());
		
		// Add the pie chart circle
		Circle circle = new Circle(xCentre, yCentre, radius, Color.WHITE);
		circle.setStroke(Color.BLACK);
		group.getChildren().add(circle);
		
		// Compute lines to separate the sections of the chart
		calculateAngle();
		
		
		// Display the lines and labels on the pie chart
		Line newLine;
		for(int expIndex = 0; expIndex < maximum; expIndex++) {
			newLine = new Line(xCentre,yCentre, coordinatesOfLines[expIndex][0], coordinatesOfLines[expIndex][1]);
			group.getChildren().add(newLine);
			
			// Add the corresponding label
			String label 	 = (expIndex == (maximum-1)) ? "Other" : expenditures[expIndex].getDescription();
			Text description = new Text(coordinatesOfText[expIndex][0], coordinatesOfText[expIndex][1], label);
			
			// Check if the text would intersect with the chart and alter it accordingly
			if(expIndex > 0 && coordinatesOfLines[expIndex-1][0] > xCentre && coordinatesOfLines[expIndex][0] < xCentre) {
				description.setX(coordinatesOfText[expIndex][0] - (description.getLayoutBounds().getWidth()/2));
			}
			else if(coordinatesOfLines[expIndex][0] < xCentre) {
				description.setX(coordinatesOfText[expIndex][0] - (description.getLayoutBounds().getWidth()));
			}
			group.getChildren().add(description);
		}
		
		// Display the pie chart
		pieChart.setTitle("Pie Chart");
		pieChart.setScene(scene);
		pieChart.show();
	}
	
	public static void main(String[] args) {
		
		Expenditure[]expenditure1 = new Expenditure[] {	new Expenditure("AI", 15000),
														new Expenditure("Actors", 5600),
														new Expenditure("Lighting", 10500),
														new Expenditure("Food", 6000),
														new Expenditure("Spoons", 6000),
														new Expenditure("Dog pictures", 15600),
														new Expenditure("Developers", 20000),
														new Expenditure("Renovation", 60500),
														new Expenditure("Notebooks", 15000),
														new Expenditure("Fancy dress", 10000),
														new Expenditure("", 2500),
														new Expenditure("Dolls", 2500),
														new Expenditure("Toy robots", 5000),
														new Expenditure("Kids' entertainer", 15000),
														new Expenditure("Pizza", 10000)
													};
		expenditures = expenditure1;
		maximum = 13;
		try {
			launch(args);
		}
		catch(IllegalArgumentException e){
			System.out.println("Maximum is larger than the size of the array.");
		}
	}
}
