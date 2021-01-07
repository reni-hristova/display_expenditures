import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Junit tests for class Expenditure.
 * 
 * @author Reni Hristova
 * @version 2019-12-02
 */


public class ExpenditureTest {

	Expenditure expense1;
	Expenditure expense2;
	Expenditure expense3;
	
	@BeforeEach
	public void initial() {
		expense1 = new Expenditure("", 0);
		expense2 = new Expenditure("Taxes", 6000);
		expense3 = new Expenditure("Printing", 60000);
	}

	/**
	 * The first set of tests tests the getters.
	 */
	@Test
	public void testGetDescription1() {
		String expected = "";
		String actual = expense1.getDescription();
		assertEquals(expected, actual, "Error in getDescription()");
	}
	
	@Test
	public void testGetDescription2() {
		String expected = "Taxes";
		String actual = expense2.getDescription();
		assertEquals(expected, actual, "Error in getDescription()");
	}
	
	@Test
	public void testGetDescription3() {
		String expected = "Printing";
		String actual = expense3.getDescription();
		assertEquals(expected, actual, "Error in getDescription()");
	}
	
	@Test
	public void testGetValue1() {
		int expected = 0;
		int actual = expense1.getValue();
		assertEquals(expected, actual, "Error in getValue()");
	}
	
	@Test
	public void testGetValue2() {
		int expected = 6000;
		int actual = expense2.getValue();
		assertEquals(expected, actual, "Error in getValue()");
	}
	
	@Test
	public void testGetValue3() {
		int expected = 60000;
		int actual = expense3.getValue();
		assertEquals(expected, actual, "Error in getValue()");
	}
	
	@Test
	public void testSetDescription1() {
		String expected = "Coffee";
		expense1.setDescription(expected);
		String actual = expense1.getDescription();
		assertEquals(expected, actual, "Error in setDescription()");
	}
	
	/**
	 * The second set of tests tests the setters.
	 */
	@Test
	public void testSetDescription2() {
		String expected = "";
		expense2.setDescription(expected);
		String actual = expense2.getDescription();
		assertEquals(expected, actual, "Error in setDescription()");
	}
	
	@Test
	public void testSetDescription3() {
		String expected = "Blueberry Cheesecake";
		expense3.setDescription(expected);
		String actual = expense3.getDescription();
		assertEquals(expected, actual, "Error in setDescription()");
	}
	
	@Test
	public void testSetValue1() {
		int expected = -20;
		expense1.setValue(expected);
		int actual = expense1.getValue();
		assertEquals(expected, actual, "Error in setValue()");
	}
	
	@Test
	public void testSetValue2() {
		int expected = 0;
		expense2.setValue(expected);
		int actual = expense2.getValue();
		assertEquals(expected, actual, "Error in setValue()");
	}
	
	@Test
	public void testSetValue3() {
		int expected = 50000;
		expense3.setValue(expected);
		int actual = expense3.getValue();
		assertEquals(expected, actual, "Error in setValue()");
	}
}
