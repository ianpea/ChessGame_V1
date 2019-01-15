/**
* This interface is the interface of the Strategy design pattern.
* This interface contains only a single method called 'transform' 
* which is used to define the strategy of how the pieces transform after every 3 turns.
* @author Tan Sheh Jing (1161304110), Gan Jia Yong (1161304051)
*/
public interface Transformation 
{
	public Piece transform();
}

/**
* This class is a concrete class of the Strategy design pattern that implements the Transformation interface.
* This class is used to model the transformation of a Plus piece to a Triangle piece after every 3 turns.
* This class changes the executing transform() algorithm of the context object 
* that is the object of Plus class during run time.
* @author Tan Sheh Jing (1161304110), Gan Jia Yong(1161304051)
*/
class PlusTransform implements Transformation 
{
	/**
	* Author: Gan Jia Yong
	* Default constructor
	*/
	public PlusTransform(){}

	/**
    * Author: Tan Sheh Jing
    * This method return a new cloned Triangle piece for the Plus piece.
    * @return Triangle
    */
	@Override
	public Piece transform()
	{
		Piece plusTransform = PiecePrototype.getPiece(2);
		return plusTransform;
	}
}

/**
* This class is a concrete class of the Strategy design pattern that implements the Transformation interface.
* This class is used to model the transformation of a Triangle piece to a Chevron piece after every 3 turns.
* This class changes the executing transform() algorithm of the context object 
* that is the object of Triangle class during run time.
* @author Tan Sheh Jing (1161304110), Gan Jia Yong(1161304051)
*/
class TriangleTransform implements Transformation 
{
	/**
	* Author: Gan Jia Yong
	* Default constructor
	*/
	public TriangleTransform(){}

	/**
    * Author: Tan Sheh Jing
    * This method return a new cloned Chevron piece for the Triangle piece.
    * @return Chevron
    */
	@Override
	public Piece transform()
	{
		Piece triangleTransform = PiecePrototype.getPiece(3);		
		return triangleTransform;
	}
}

/**
* This class is a concrete class of the Strategy design pattern that implements the Transformation interface.
* This class is used to model the transformation of a Chevron piece to a Plus piece after every 3 turns.
* This class changes the executing transform() algorithm of the context object 
* that is the object of Chevron class during run time.
* @author Tan Sheh Jing (1161304110), Gan Jia Yong(1161304051)
*/
class ChevronTransform implements Transformation 
{
	/**
	* Author: Gan Jia Yong
	* Default constructor
	*/
	public ChevronTransform(){}

	/**
    * Author: Tan Sheh Jing
    * This method return a new cloned Plus piece for the Chevron piece.
    * @return Plus
    */
	@Override
	public Piece transform()
	{
		Piece chevronTransform = PiecePrototype.getPiece(1);
		return chevronTransform;
	}
}