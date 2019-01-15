/**
* This class is used to represent a player
* @author Ang Chong Wei
*/
public class Player
{
	private String name;  //name of the player
	private int color;    //color number of the player, 1 for red and 2 for blue
	
	/**
	* Author: Ang Chong Wei
	* Default constructor
	*/
	public Player(){}
	
	/**
	* Author: Ang Chong Wei
	* Constructor
	* Initialze the the value of name
	* @param name
	*/
	public Player( String name )
	{
		this.name = name;
	}

	/**
	* Author: Ang Chong Wei
	* This method is used to return the value of name
	* @return name
	*/
	public String getName()
	{
		return name;
	}

	/**
	* Author: Ang Chong Wei
	* This method is used to return the value of color
	* @return color
	*/
	public int getColor()
	{
		return color;
	}

	/**
	* Author: Ang Chong Wei
	* This method is used to set the value of name
	* @param name
	*/
	public void setName( String name )
	{
		this.name = name;
	}

	/**
	* Author: Ang Chong Wei
	* This method is used to set the value of color
	* @param color
	*/
	public void setColor( int color )
	{
		this.color = color;
	}
}

