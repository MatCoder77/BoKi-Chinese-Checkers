package board;

/**
 * @author filipbk
 * Class that stores information about a single board field
 *
 */
public class Field {

	private boolean empty;
	private String fieldType; //is center, corner or over the board
	
	public Field(String fieldType, boolean empty) {
		this.fieldType = fieldType;
		this.empty = empty;
	}
	
	public boolean isEmpty() {
		return this.empty;
	}
	
	public String getFieldType() {
		return this.fieldType;
	}
	
	public void setEmpty() {
		this.empty = true;
	}
	
	public void setTaken() {
		this.empty = false;
	}
	
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
}
