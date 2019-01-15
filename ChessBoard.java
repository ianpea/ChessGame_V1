/**
* This class is used to represent the chess board.
* This class uses Singleton design pattern. It has a private static instance of itself 
* and a private constructor to prevent instantiation from outside this class.
* It also has a public static getter method for its private static instance.
* @author Gan Jia Yong (1161304051)
*/
public class ChessBoard
{
	private int width = 7;    //the width of chess board in number of square unit
	private int height = 6;   //the height of chess board in number of square unit
	private Piece[][] piecesOnBoard = new Piece[width][height]; //a two dimensional array of Piece to store pieces on the chess board in their respective position
	private static ChessBoard board;  //a static instance of this class

	/**
    * Author: Gan Jia Yong
    * Constructor
    * This constructor is made private so that no instantiation outside this class.
    * Initialize the initial coordinate of each piece on the chess board.
    */
	private ChessBoard()
	{
		initialize();
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to return the static instance of this class.
	* @return board
	*/
	public static ChessBoard getBoard()
	{
		if(board == null)
			board = new ChessBoard();
		return board;
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to return the width of chess board
	* @return width
	*/
	public int getWidth()
	{
		return width;
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to return the height of chess board
	* @return height
	*/
	public int getHeight()
	{
		return height;
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to return a piece on the specified column and row
	* @param column
	* @param row
	* @return piece
	*/
	public Piece getPiece(int column, int row)
	{
		return piecesOnBoard[column][row];
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to set a specified piece on a specified column and row
	* @param column
	* @param row
	* @param piece
	*/
	public void setPiece(int column, int row, Piece piece)
	{
		piecesOnBoard[column][row] = piece;
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to return a two dimensional array that stores all the positions of the pieces on the chess board
	* @return piecePiecesOnBoard[][]
	*/
	public Piece[][] getPiecesOnBoard()
	{
		return piecesOnBoard;
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to set all positions of pieces on the chess board
	* @param piecesOnBoard
	*/
	public void setPiecesOnBoard(Piece[][] piecesOnBoard)
	{
		this.piecesOnBoard = piecesOnBoard;
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to initialize the board by placing all the pieces on their initial position
	*/
	public void initialize()
	{
		//load all types of piece
		PiecePrototype.loadPiece();

		//create pieces from Prototype
		//set color for the pieces
		//Color for red player = 1, blue player = 2
		//assign to initial position
		Piece redSun = PiecePrototype.getPiece(4);
		redSun.setColor(1);
		setPiece(3, 5, redSun);

		Piece redChevron = PiecePrototype.getPiece(3);
		redChevron.setColor(1);
		setPiece(2, 5, redChevron);
		setPiece(4, 5, redChevron);

		Piece redTriangle = PiecePrototype.getPiece(2);
		redTriangle.setColor(1);
		setPiece(1, 5, redTriangle);
		setPiece(5, 5, redTriangle);

		Piece redPlus = PiecePrototype.getPiece(1);
		redPlus.setColor(1);
		setPiece(0, 5, redPlus);
		setPiece(6, 5, redPlus);


		Piece blueSun = PiecePrototype.getPiece(4);
		blueSun.setColor(2);
		setPiece(3, 0, blueSun);

		Piece blueChevron = PiecePrototype.getPiece(3);
		blueChevron.setColor(2);
		setPiece(2, 0, blueChevron);
		setPiece(4, 0, blueChevron);

		Piece blueTriangle = PiecePrototype.getPiece(2);
		blueTriangle.setColor(2);
		setPiece(1, 0, blueTriangle);
		setPiece(5, 0, blueTriangle);

		Piece bluePlus = PiecePrototype.getPiece(1);
		bluePlus.setColor(2);
		setPiece(0, 0, bluePlus);
		setPiece(6, 0, bluePlus);
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to remove a particular piece from the chess board after it has been eaten by opponent's piece
	* @param column
	* @param row
	*/
	public void removePiece(int column, int row)
	{
		//check if the particular position is a valid position or is empty
		if(isValidPosition(column, row) && !isEmpty(column, row))
		{
			piecesOnBoard[column][row] = null;
		}
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to check whether a particular position is inside the board
	* @param column
	* @param row
	*/
	public boolean isValidPosition(int column, int row)
	{
		return (column < width && row < height);
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to check whether a particular position is empty
	* @param column
	* @param row
	*/
	public boolean isEmpty(int column, int row)
	{
		return (piecesOnBoard[column][row] == null);
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to clear pieces from piecesOnBoard
	*/
	public void clearBoard()
	{
		for(int j = 0; j < 6; j++)
		{
			for(int i = 0; i < 7; i++)
				piecesOnBoard[i][j] = null;
		}
	}
}