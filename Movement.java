import java.util.ArrayList;

/**
* This interface is the interface of the Strategy design pattern.
* This interface is used to define how the pieces of different types move.
* This interface contains two abstract methods moveTo and generateValidPath.
* @author Ang Chong Wei (1161304060), Gan Jia Yong (1161304051), Tan Sheh Jing(1161304110)
*/
public interface Movement
{
	public boolean moveTo( int newColumn, int newRow, int oldColumn, int oldRow, Piece[][] piecesOnBoard );
	public ArrayList<Coordinate> generateValidPath( int column, int row, Piece[][] piecesOnBoard );
}

/**
* This class is a concrete class of the Strategy design pattern that implements the Movement interface.
* This class is used to model the movement of a Plus piece
* This class changes the executing generateValidPath() algorithm of the Plus object during run time.
* Plus can move vertically or horizontally, up to 2 steps in any direction in a straight line.
* @author Ang Chong Wei (1161304060), Gan Jia Yong (1161304051), Tan Sheh Jing(1161304110)
*/
class PlusMovement implements Movement
{
	/**
	* Author: Ang Chong Wei
	* Default constructor
	*/
	public PlusMovement(){};

	/**
    * Author: Gan Jia Yong
    * check the new coordinate that the Plus piece moving to is valid or not
    * @param newColumn    x value of the new coordinate
    * @param newRow       y value of the new coordinate
    * @param oldColumn    x value of the current coordinate
    * @param oldRow       y value of the current coordinate
    * @param piecesOnBoard  an 2D array of chess board with all pieces on their respective position
    * @return true or false
    */
	@Override
	public boolean moveTo( int newColumn, int newRow, int oldColumn, int oldRow, Piece[][] piecesOnBoard )
	{
		ArrayList<Coordinate> validPath = generateValidPath(oldColumn, oldRow, piecesOnBoard);

		for(Coordinate c : validPath)
		{
			if(newColumn == c.getX() && newRow == c.getY())	
				return true;
		}
		return false;
	}

	/**
    * Author: Ang Chong Wei & Gan Jia Yong & Tan Sheh Jing
    * determine the valid coordinates a Plus piece can move to 
    * @param column  x value of the current coordinate
    * @param row     y value of the current coordinate
    * @param piecesOnBoard  an 2D array of chess board with all pieces on their respective position
    * @return arraylist of valid coordinates to move to
    */
	@Override
	public ArrayList<Coordinate> generateValidPath( int column, int row, Piece[][] piecesOnBoard)
	{
		ArrayList<Coordinate> pathP = new ArrayList<Coordinate>();
		
		if( column + 1 >= 0 && column + 1 <= 6 )
		{
			pathP.add(new Coordinate(column+1, row));
		}
		if( column + 2 >= 0 && column + 2 <= 6 )
		{
			if(piecesOnBoard[column + 1][row] == null)
				pathP.add(new Coordinate(column+2, row));
		}
		if( column - 1 >= 0 && column - 1 <= 6 )
		{
			pathP.add(new Coordinate(column-1, row));
		}
		if( column - 2 >= 0 && column - 2 <= 6 )
		{
			if(piecesOnBoard[column - 1][row] == null)
				pathP.add(new Coordinate(column-2, row));
		}
		if( row + 1 >= 0 && row + 1 <= 5 )
		{
			pathP.add(new Coordinate(column, row+1));
		}
		if( row + 2 >= 0 && row + 2 <= 5 )
		{
			if(piecesOnBoard[column][row+1] == null)
				pathP.add(new Coordinate(column, row+2));
		}
		if( row - 1 >= 0 && row - 1 <= 5 )
		{
			pathP.add(new Coordinate(column, row-1));
		}
		if( row - 2 >= 0 && row - 2 <= 5 )
		{
			if(piecesOnBoard[column][row-1] == null)
				pathP.add(new Coordinate(column, row-2));
		}
		
		return pathP;
	}
	
}

/**
* This class is a concrete class of the Strategy design pattern that implements the Movement interface.
* This class is used to model the movement of a Triangle piece
* This class changes the executing generateValidPath() algorithm of the Triangle object during run time.
* Triangle can move diagonally, both up and down, up to 2 steps in any direction in a straight line.
* @author Ang Chong Wei (1161304060), Gan Jia Yong (1161304051), Tan Sheh Jing(1161304110)
*/
class TriangleMovement implements Movement
{
	/**
	* Author: Ang Chong Wei
	* Default constructor
	*/
	public TriangleMovement(){};

	/**
    * Author: Gan Jia Yong
    * check the new coordinate that the Plus piece moving to is valid or not
    * @param newColumn    x value of the new coordinate
    * @param newRow       y value of the new coordinate
    * @param oldColumn    x value of the current coordinate
    * @param oldRow       y value of the current coordinate
    * @param piecesOnBoard  an 2D array of chess board with all pieces on their respective position
    * @return true or false
    */
	@Override
	public boolean moveTo( int newColumn, int newRow, int oldColumn, int oldRow, Piece[][] piecesOnBoard )
	{
		ArrayList<Coordinate> validPath = generateValidPath(oldColumn, oldRow, piecesOnBoard);

		for(Coordinate c : validPath)
		{
			if(newColumn == c.getX() && newRow == c.getY())	
				return true;
		}

		return false;
	}

	/**
    * Author: Ang Chong Wei & Gan Jia Yong & Tan Sheh Jing
    * determine the valid coordinates a Triangle piece can move to 
    * @param column  x value of the current coordinate
    * @param row     y value of the current coordinate
    * @param piecesOnBoard  an 2D array of chess board with all pieces on their respective position
    * @return arraylist of valid coordinates to move to
    */
	@Override
	public ArrayList<Coordinate> generateValidPath( int column, int row, Piece[][] piecesOnBoard )
	{
		ArrayList<Coordinate> pathT = new ArrayList<Coordinate>();
		
		if( column + 1 >= 0 && row + 1 >= 0 && column + 1 <= 6 && row + 1 <= 5 )
		{
			pathT.add(new Coordinate(column+1, row+1));
		}
		if( column + 2 >= 0 && row + 2 >= 0 && column + 2 <= 6 && row + 2 <= 5 )
		{
			if(piecesOnBoard[column+1][row+1] == null)
				pathT.add(new Coordinate(column+2, row+2));
		}
		if( column + 1 >= 0 && row - 1 >= 0 && column + 1 <= 6 && row - 1 <= 5 )
		{
			pathT.add(new Coordinate(column+1, row-1));
		}
		if( column + 2 >= 0 && row - 2 >= 0 && column + 2 <= 6 && row - 2 <= 5 )
		{
			if(piecesOnBoard[column+1][row-1] == null)
				pathT.add(new Coordinate(column+2, row-2));
		}
		if( column - 1 >= 0 && row - 1 >= 0 && column - 1 <= 6 && row - 1 <= 5 )
		{
			pathT.add(new Coordinate(column-1, row-1));
		}
		if( column - 2 >= 0 && row - 2 >= 0 && column - 2 <= 6 && row - 2 <= 5 )
		{
			if(piecesOnBoard[column-1][row-1] == null)
				pathT.add(new Coordinate(column-2, row-2));
		}
		if( column - 1 >= 0 && row + 1 >= 0 && column - 1 <= 6 && row + 1 <= 5 )
		{
			pathT.add(new Coordinate(column-1, row+1));
		}
		if( column - 2 >= 0 && row + 2 >= 0 && column - 2 <= 6 && row + 2 <= 5 )
		{
			if(piecesOnBoard[column-1][row+1] == null)
				pathT.add(new Coordinate(column-2, row+2));
		}
		
		return pathT;
	}
}

/**
* This class is a concrete class of the Strategy design pattern that implements the Movement interface.
* This class is used to model the movement of a Chevron piece
* This class changes the executing generateValidPath() algorithm of the Chevron object during run time.
* Chevron must move in an L shape, exactly two steps then 1 step right or left.
* This is the only piece that can jump over other pieces.
* @author Ang Chong Wei (1161304060), Gan Jia Yong (1161304051)
*/
class ChevronMovement implements Movement
{
	/**
	* Author: Ang Chong Wei
	* Default constructor
	*/
	public ChevronMovement(){};

	/**
    * Author: Gan Jia Yong
    * check the new coordinate that the Plus piece moving to is valid or not
    * @param newColumn    x value of the new coordinate
    * @param newRow       y value of the new coordinate
    * @param oldColumn    x value of the current coordinate
    * @param oldRow       y value of the current coordinate
    * @param piecesOnBoard  an 2D array of chess board with all pieces on their respective position
    * @return true or false
    */
	@Override
	public boolean moveTo( int newColumn, int newRow, int oldColumn, int oldRow, Piece[][] piecesOnBoard )
	{
		ArrayList<Coordinate> validPath = generateValidPath(oldColumn, oldRow, piecesOnBoard);

		for(Coordinate c : validPath)
		{
			if(newColumn == c.getX() && newRow == c.getY())	
				return true;
		}

		return false;
	}

	/**
    * Author: Ang Chong Wei
    * determine the valid coordinates a Chevron piece can move to 
    * @param column  x value of the current coordinate
    * @param row     y value of the current coordinate
    * @param piecesOnBoard  an 2D array of chess board with all pieces on their respective position
    * @return arraylist of valid coordinates to move to
    */
	@Override
	public ArrayList<Coordinate> generateValidPath( int column, int row, Piece[][] piecesOnBoard )
	{
		ArrayList<Coordinate> pathC = new ArrayList<Coordinate>();
		
		if( column - 2 >= 0 && row - 1 >= 0 && column - 2 <= 6 && row - 1 <= 5 )
		{
			pathC.add(new Coordinate(column - 2, row - 1));
		}
		if( column - 2 >= 0 && row + 1 >= 0 && column - 2 <= 6 && row + 1 <= 5 )
		{
			pathC.add(new Coordinate(column - 2, row + 1));
		}
		if( column + 2 >= 0 && row - 1 >= 0 && column + 2 <= 6 && row - 1 <= 5 )
		{
			pathC.add(new Coordinate(column + 2, row - 1));
		}
		if( column + 2 >= 0 && row + 1 >= 0 && column + 2 <= 6 && row + 1 <= 5 )
		{
			pathC.add(new Coordinate(column + 2, row + 1));
		}
		if( column - 1 >= 0 && row - 2 >= 0 && column - 1 <= 6 && row - 2 <= 5 )
		{
			pathC.add(new Coordinate(column-1, row-2));
		}
		if( column + 1 >= 0 && row - 2 >= 0 && column + 1 <= 6 && row - 2 <= 5 )
		{
			pathC.add(new Coordinate(column+1, row-2));
		}
		if( column + 1 >= 0 && row + 2 >= 0 && column + 1 <= 6 && row + 2 <= 5 )
		{
			pathC.add(new Coordinate(column+1, row+2));
		}
		if( column - 1 >= 0 && row + 2 >= 0 && column - 1 <= 6 && row + 2 <= 5 )
		{
			pathC.add(new Coordinate(column-1, row+2));
		}
		return pathC;
	}
}

/**
* This class is a concrete class of the Strategy design pattern that implements the Movement interface.
* This class is used to model the movement of a Sun piece
* This class changes the executing generateValidPath() algorithm of the Sun object during run time.
* Sun can move only 1 step in any direction.
* Game ends when the sun is captured by the other side.
* @author Ang Chong Wei (1161304060), Gan Jia Yong (1161304051)
*/
class SunMovement implements Movement
{
	/**
	* Author: Ang Chong Wei
	* Default constructor
	*/
	public SunMovement(){};

	/**
    * Author: Gan Jia Yong
    * check the new coordinate that the Plus piece moving to is valid or not
    * @param newColumn    x value of the new coordinate
    * @param newRow       y value of the new coordinate
    * @param oldColumn    x value of the current coordinate
    * @param oldRow       y value of the current coordinate
    * @param piecesOnBoard  an 2D array of chess board with all pieces on their respective position
    * @return true or false
    */
	@Override
	public boolean moveTo( int newColumn, int newRow, int oldColumn, int oldRow, Piece[][] piecesOnBoard )
	{
		ArrayList<Coordinate> validPath = generateValidPath(oldColumn, oldRow, piecesOnBoard);

		for(Coordinate c : validPath)
		{
			if(newColumn == c.getX() && newRow == c.getY())	
				return true;
		}

		return false;
	}

	/**
    * Author: Ang Chong Wei
    * determine the valid coordinates a Sun piece can move to 
    * @param column  x value of the current coordinate
    * @param row     y value of the current coordinate
    * @param piecesOnBoard  an 2D array of chess board with all pieces on their respective position
    * @return arraylist of valid coordinates to move to
    */
	@Override
	public ArrayList<Coordinate> generateValidPath( int column, int row, Piece[][] piecesOnBoard)
	{
		ArrayList<Coordinate> pathS = new ArrayList<Coordinate>();
		
		if( column + 1 >= 0 && column + 1 <= 6 )
		{
			pathS.add(new Coordinate(column+1, row));
		}
		if( column - 1 >= 0 && column - 1 <= 6 )
		{
			pathS.add(new Coordinate(column-1, row));
		}
		if( row + 1 >= 0 && row + 1 <= 5 )
		{
			pathS.add(new Coordinate(column, row+1));
		}
		if( row - 1 >= 0 && row - 1 <= 5 )
		{
			pathS.add(new Coordinate(column, row-1));
		}
		if( column + 1 >= 0 && row + 1 >= 0 && column + 1 <= 6 && row + 1 <= 5 )
		{
			pathS.add(new Coordinate(column+1, row+1));
		}
		if( column + 1 >= 0 && row - 1 >= 0 && column + 1 <= 6 && row - 1 <= 5 )
		{
			pathS.add(new Coordinate(column+1, row-1));
		}
		if( column - 1 >= 0 && row - 1 >= 0 && column - 1 <= 6 && row - 1 <= 5 )
		{
			pathS.add(new Coordinate(column-1, row-1));
		}
		if( column - 1 >= 0 && row + 1 >= 0 && column - 1 <= 6 && row + 1 <= 5 )
		{
			pathS.add(new Coordinate(column-1, row+1));
		}
		
		return pathS;
	}
}