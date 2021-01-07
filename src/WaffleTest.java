import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Junit tests for class Waffle.
 * 
 * @author Reni Hristova
 * @version 2019-12-03
 *
 */
public class WaffleTest {
	Expenditure[] 	expenditure1, expenditure2;
	int 			maximum1, maximum2;
	
	@BeforeEach
	public void initial() {
		expenditure1 = new Expenditure[] {	new Expenditure("Robots", 250000),
											new Expenditure("AI", 15000),
											new Expenditure("Actors", 5600),
											new Expenditure("Lighting", 10500),
											new Expenditure("Food", 6000),
											new Expenditure("Spoons", 6000),
											new Expenditure("Dog pictures", 15600),
											new Expenditure("Developers", 20000),
											new Expenditure("Renovation", 60500),
											new Expenditure("Notebooks", 15000),
											new Expenditure("Fancy dress", 10000)
										};
		expenditure2 = new Expenditure[] {	new Expenditure("", 2500),
											new Expenditure("Dolls", 2500),
											new Expenditure("Toy robots", 5000),
											new Expenditure("Kids' entertainer", 15000),
											new Expenditure("Pizza", 10000),
										};
		maximum1 = 10;
		maximum2 = 3;
	}

	@Test
	public void testInnitial1() {
		Waffle.expenditures = expenditure1;
		Expenditure[] expectedExp = expenditure1;
		Expenditure[] actualExp = Waffle.expenditures;
		for(int i = 0; i<expenditure1.length; i++) {
			assertEquals(expectedExp[i], actualExp[i], "Error (index: " + i + ")");
		}
		Waffle.maximum = maximum1;
		int expectedMaximum = maximum1;
		int actualMaximum = Waffle.maximum;
		assertEquals(expectedMaximum, actualMaximum, "Error");
	}
	
	@Test
	public void testInnitial2() {
		Waffle.expenditures = expenditure2;
		Expenditure[] expectedExp = expenditure2;
		Expenditure[] actualExp = Waffle.expenditures;
		for(int i = 0; i<expenditure2.length; i++) {
			assertEquals(expectedExp[i], actualExp[i], "Error (index: " + i + ")");
		}
		Waffle.maximum = maximum2;
		int expectedMaximum = maximum2;
		int actualMaximum = Waffle.maximum;
		assertEquals(expectedMaximum, actualMaximum, "Error");
	}
}
