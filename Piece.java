/**
* This abstract class represents the pieces.
* This class is a part of the Prototype design pattern. 
* It implements Cloneable interface so its objects can be cloned.
* This class is also a part of the Strategy design pattern.
* It has a Movement object which is a strategy object that define how the pieces of different types move during runtime.
* @author Gan Jia Yong (1161304051), Pee Wu Jian (1161303933)
*/
public abstract class Piece implements Cloneable
{
    protected int typeID;             //type ID of piece
    protected int color;              //color of piece 
    protected String name;            //name of piece
    protected Movement move;          //a Movement strategy object
    protected Coordinate coordinate;  //coordinate of the piece


    /**
    * Author: Pee Wu Jian
    * Constructor
    * Initialze the the value of name
    * @param name
    */
    public Piece(String name)
    {
        this.name = name;
    }

    /**
    * Author: Pee Wu Jian
    * This method is used to return the value of name
    * @return name
    */
    public String getName()
    {
        return name;
    }

    /**
    * Author: Pee Wu Jian
    * This method is used to set the value of name
    * @param name
    */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
    * Author: Pee Wu Jian
    * This method is used to return the value of typeID
    * @return typeID
    */
    public int getTypeID()
    {
        return typeID;
    }

    /**
    * Author: Pee Wu Jian
    * This method is used to set the value of typeID
    * @param type
    */
    public void setTypeID(int type)
    {
        typeID = type;
    }
    
    /**
    * Author: Pee Wu Jian
    * This method is used to return the value of color
    * @return color
    */
    public int getColor()
    {
        return color;
    }
    
    /**
    * Author: Pee Wu Jian
    * This method is used to set the value of color
    * @param color
    */
    public void setColor(int color)
    {
        this.color = color;
    }

    /**
    * Author: Pee Wu Jian
    * This method is used to return the coordinate object
    * @return coordinate
    */
    public Coordinate getCoordinate()
    {
        return coordinate;
    }

    /**
    * Author: Pee Wu Jian
    * This method is used to set the value of coordinate object
    * @param coordinate
    */
    public void setCoordinate(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }

    /**
    * Author: Pee Wu Jian
    * This method is used to return the move object
    * @return move
    */
    public Movement getMove()
    {
        return move;
    }

    /**
    * Author: Gan Jia Yong
    * abstract method
    * check the new coordinate that the Plus piece moving to is valid or not
    * @param newColumn  x value of the new coordinate
    * @param newRow     y value of the new coordinate
    * @param oldColumn  x value of the current coordinate
    * @param oldRow     y value of the current coordinate
    * @param piecesOnBoard  an 2D array of chess board with all pieces on their respective position
    * @return true or false
    */
    public abstract boolean move(int newColumn, int newRow, int oldColumn, int oldRow, Piece[][] piecesOnBoard);

    public abstract Piece transform();

    /**
    * Author: Pee Wu Jian
    * This method is used to return the clone of a piece object
    * @return clone of a piece object
    */
    public Object clone()
    {
        Object clone = null;

        try
        {
            clone = super.clone();
        } 
        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return clone;
    }
}

/**
* This class represents Plus piece.
* typeID = 1
* This class is a concrete subclass of Piece.
* This class is also a part of the Strategy design pattern.
* It has a Transformation object which is a strategy object that defines how the Plus piece transform after every 3 turns.
* @author Gan Jia Yong (1161304051), Pee Wu Jian (1161303933)
*/
class Plus extends Piece
{
    protected Transformation transformation;  //a Transformation strategy object

    /**
    * Author: Pee Wu Jian
    * Constructor
    * Initialze the the value of name, typeID, move, transformation
    * @param name
    */
    public Plus(String name)
    {
        super(name);
        setTypeID(1);
        move = new PlusMovement();
        transformation = new PlusTransform();
    }

    /**
    * Author: Gan Jia Yong
    * This method is used to return a new piece
    * @return piece
    */
    public Piece transform()
    {
        return transformation.transform();
    }

    /**
    * Author: Gan Jia Yong
    * check the new coordinate that the Plus piece moving to is valid or not
    * @param newColumn  x value of the new coordinate
    * @param newRow     y value of the new coordinate
    * @param oldColumn  x value of the current coordinate
    * @param oldRow     y value of the current coordinate
    * @param piecesOnBoard  an 2D array of chess board with all pieces on their respective position
    * @return true or false
    */
    @Override
    public boolean move(int newColumn, int newRow, int oldColumn, int oldRow, Piece[][] piecesOnBoard)
    {
        return move.moveTo(newColumn, newRow, oldColumn, oldRow, piecesOnBoard);
    }
}

/**
* This class represents Triangle piece.
* typeID = 2
* This class is a concrete subclass of Piece.
* This class is also a part of the Strategy design pattern.
* It has a Transformation object which is a strategy object that defines how the Triangle piece transform after every 3 turns.
* @author Gan Jia Yong (1161304051), Pee Wu Jian (1161303933)
*/
class Triangle extends Piece
{
    protected Transformation transformation;  //a Transformation strategy object

    /**
    * Author: Pee Wu Jian
    * Constructor
    * Initialze the the value of name, typeID, move, transformation
    * @param name
    */
    public Triangle(String name)
    {
        super(name);
        setTypeID(2);
        move = new TriangleMovement();
        transformation = new TriangleTransform();
    }

    /**
    * Author: Gan Jia Yong
    * This method is used to return a new piece
    * @return piece
    */
    public Piece transform()
    {
        return transformation.transform();
    }

    /**
    * Author: Gan Jia Yong
    * check the new coordinate that the Plus piece moving to is valid or not
    * @param newColumn  x value of the new coordinate
    * @param newRow     y value of the new coordinate
    * @param oldColumn  x value of the current coordinate
    * @param oldRow     y value of the current coordinate
    * @param piecesOnBoard  an 2D array of chess board with all pieces on their respective position
    * @return true or false
    */
    @Override
    public boolean move(int newColumn, int newRow, int oldColumn, int oldRow, Piece[][] piecesOnBoard)
    {
        return move.moveTo(newColumn, newRow, oldColumn, oldRow, piecesOnBoard);
    }
}

/**
* This class represents Chevron piece.
* typeID = 3
* This class is a concrete subclass of Piece.
* This class is also a part of the Strategy design pattern.
* It has a Transformation object which is a strategy object that defines how the Chevron piece transform after every 3 turns.
* @author Gan Jia Yong (1161304051), Pee Wu Jian (1161303933)
*/
class Chevron extends Piece
{
    protected Transformation transformation;  //a Transformation strategy object

    /**
    * Author: Pee Wu Jian
    * Constructor
    * Initialze the the value of name, typeID, move, transformation
    * @param name
    */
    public Chevron(String name)
    {
        super(name);
        setTypeID(3);
        move = new ChevronMovement();
        transformation = new ChevronTransform();
    }

    /**
    * Author: Gan Jia Yong
    * This method is used to return a new piece
    * @return piece
    */
    public Piece transform()
    {
        return transformation.transform();
    }

    /**
    * Author: Gan Jia Yong
    * check the new coordinate that the Plus piece moving to is valid or not
    * @param newColumn  x value of the new coordinate
    * @param newRow     y value of the new coordinate
    * @param oldColumn  x value of the current coordinate
    * @param oldRow     y value of the current coordinate
    * @param piecesOnBoard  an 2D array of chess board with all pieces on their respective position
    * @return true or false
    */
    @Override
    public boolean move(int newColumn, int newRow, int oldColumn, int oldRow, Piece[][] piecesOnBoard)
    {
        return move.moveTo(newColumn, newRow, oldColumn, oldRow, piecesOnBoard);
    }
}

/**
* This class represents Sun piece.
* typeID = 4
* This class is a concrete subclass of Piece.
* @author Gan Jia Yong (1161304051), Pee Wu Jian (1161303933)
*/
class Sun extends Piece
{
    /**
    * Author: Pee Wu Jian
    * Constructor
    * Initialze the the value of name, typeID, move, transformation
    * @param name
    */
    public Sun(String name)
    {
        super(name);
        setTypeID(4);
        move = new SunMovement();
    }

    /**
    * Author: Gan Jia Yong
    * check the new coordinate that the Plus piece moving to is valid or not
    * @param newColumn  x value of the new coordinate
    * @param newRow     y value of the new coordinate
    * @param oldColumn  x value of the current coordinate
    * @param oldRow     y value of the current coordinate
    * @param piecesOnBoard  an 2D array of chess board with all pieces on their respective position
    * @return true or false
    */
    @Override
    public boolean move(int newColumn, int newRow, int oldColumn, int oldRow, Piece[][] piecesOnBoard)
    {
        return move.moveTo(newColumn, newRow, oldColumn, oldRow, piecesOnBoard);
    }

    @Override
    public Piece transform()
    {
        return this;
    }
}
