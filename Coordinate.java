/**
* This class is used to represent the coordinate of the piece on the chess board.
* @author Gan Jia Yong (1161304051)
*/

public class Coordinate
{
	private int x;  // x represents the column number on chess board
	private int y;  // y represents the row number on chess board

	/**
	* Author: Gan Jia Yong
	* Constructor
	* Initialze the the value of x and y
	* @param x 
	* @param y 
	*/
	public Coordinate(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to return the value of x
	* @return x
	*/
	public int getX()
	{
		return x;
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to set the value of x
	* @param x
	*/
	public void setX(int x)
	{
		this.x = x;
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to return the value of y
	* @return y
	*/
	public int getY()
	{
		return y;
	}

	/**
	* Author: Gan Jia Yong
	* This method is used to set the value of y
	* @param y
	*/
	public void setY(int y)
	{
		this.y = y;
	}
}