package board;

public class Field {

	private boolean empty;
	private String fieldType; //is center, corner or over the board
	
	public Field(String fieldType) {
		this.fieldType = fieldType;
		
		if(fieldType.equals("C")) {
			this.setEmpty();
		}
		else {
			this.setTaken();
		}
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
