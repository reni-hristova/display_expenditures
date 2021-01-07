/**
 * This class represents an expense with field variables
 * for the value and the description of the expense.
 * 
 * @author Reni Hristova
 * @version 2019-11-24
 */
public class Expenditure{
	private String 	description;
	private int 	value;

	/**
	 * Constructor for the class.
	 * @param description	The description of the expenses as a String.
	 * @param value 		The value  of the expenses as an integer.
	 */
	public Expenditure(String description, int value) {
		this.description 	= description;
		this.value 			= value;
	}

	/**
	 * @return The description of the expenses as a String.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return The value of the expenses as an integer.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * The method changes the description of the expenses
	 * to a new one.
	 * @param description The new description of the expenses 
	 * as a String.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * The method changes the value of the expenses
	 * to a new one.
	 * @param value The new value of the expenses as an integer.
	 */
	public void setValue(int value) {
		this.value = value;
	}
}