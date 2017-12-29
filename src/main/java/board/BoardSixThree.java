package board;

public class BoardSixThree implements BoardType {

	private static final String[][] board = { {"N", "N", "N", "N", "1", "N", "N", "N", "N", "N", "N", "N", "N", "N", "N", "N", "N"},
											  {"N", "N", "N", "N", "1", "1", "N", "N", "N", "N", "N", "N", "N", "N", "N", "N", "N"},
											  {"N", "N", "N", "N", "1", "1", "1", "N", "N", "N", "N", "N", "N", "N", "N", "N", "N"},
											  {"N", "N", "N", "N", "1", "1", "1", "1", "N", "N", "N", "N", "N", "N", "N", "N", "N"},
											  {"6", "6", "6", "6", "C", "C", "C", "C", "C", "2", "2", "2", "2", "N", "N", "N", "N"},
											  {"N", "6", "6", "6", "C", "C", "C", "C", "C", "C", "2", "2", "2", "N", "N", "N", "N"},
											  {"N", "N", "6", "6", "C", "C", "C", "C", "C", "C", "C", "2", "2", "N", "N", "N", "N"},
											  {"N", "N", "N", "6", "C", "C", "C", "C", "C", "C", "C", "C", "2", "N", "N", "N", "N"},
											  {"N", "N", "N", "N", "C", "C", "C", "C", "C", "C", "C", "C", "C", "N", "N", "N", "N"},
											  {"N", "N", "N", "N", "5", "C", "C", "C", "C", "C", "C", "C", "C", "3", "N", "N", "N"},
											  {"N", "N", "N", "N", "5", "5", "C", "C", "C", "C", "C", "C", "C", "3", "3", "N", "N"},
											  {"N", "N", "N", "N", "5", "5", "5", "C", "C", "C", "C", "C", "C", "3", "3", "3", "N"},
											  {"N", "N", "N", "N", "5", "5", "5", "5", "C", "C", "C", "C", "C", "3", "3", "3", "3"},
											  {"N", "N", "N", "N", "N", "N", "N", "N", "N", "4", "4", "4", "4", "N", "N", "N", "N"},
											  {"N", "N", "N", "N", "N", "N", "N", "N", "N", "N", "4", "4", "4", "N", "N", "N", "N"},
											  {"N", "N", "N", "N", "N", "N", "N", "N", "N", "N", "N", "4", "4", "N", "N", "N", "N"},
											  {"N", "N", "N", "N", "N", "N", "N", "N", "N", "N", "N", "N", "4", "N", "N", "N", "N"} };

	private final static boolean[][] empty = { {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				   							   {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				   							   {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				   							   {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
				   							   {true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false},
				   							   {false, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false},
				   							   {false, false, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false},
				   							   {false, false, false, true, true, true, true, true, true, true, true, true, true, false, false, false, false},
				   							   {false, false, false, false, true, true, true, true, true, true, true, true, true, false, false, false, false},
				   							   {false, false, false, false, false, true, true, true, true, true, true, true, true, false, false, false, false},
				   							   {false, false, false, false, false, false, true, true, true, true, true, true, true, false, false, false, false},
				   							   {false, false, false, false, false, false, false, true, true, true, true, true, true, false, false, false, false},
				   							   {false, false, false, false, false, false, false, false, true, true, true, true, true, false, false, false, false},
				   							   {false, false, false, false, false, false, false, false, false, true, true, true, true, false, false, false, false},
				   							   {false, false, false, false, false, false, false, false, false, false, true, true, true, false, false, false, false},
				   							   {false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false},
				   							   {false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false} };
	
	private final int[] corners = {1, 3, 5};
	
	@Override
	public String[][] getBoard() {
		return board;
	}

	@Override
	public boolean[][] getBoardEmpty() {
		return empty;
	}

	@Override
	public int[] getCorners() {
		return corners;
	}

}
