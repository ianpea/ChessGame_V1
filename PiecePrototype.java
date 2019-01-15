/**
* This class is a part of the Prototype design pattern.
* This class is used to clone the pieces of different types while keeping performance in mind instead of creating a new object.
* This class also stores Piece objects in a Hashtable and returns their clone when requested.
* @author Gan Jia Yong (1161304051), Pee Wu Jian (1161303933)
*/
import java.util.Hashtable;

public class PiecePrototype
{
    //a hash table to store a distinct piece of each type
    private static Hashtable<Integer, Piece> pieceTable = new Hashtable<Integer, Piece>();

    /**
    * Author: Pee Wu Jian
    * This static method gets typeID of a piece as the parameter, 
    * and return a clone of the piece of the desired typeID.
    * @param typeID
    * @return piece
    */
    public static Piece getPiece(int typeID)
    {
      Piece cachedPiece = pieceTable.get(typeID);
      return (Piece)cachedPiece.clone();
    }

    /**
    * Author: Pee Wu Jian, Gan Jia Yong
    * This static method creates pieces of all types,
    * and store the pieces in the hash table.
    */
    public static void loadPiece()
    {
        Plus plus = new Plus("plus");
        pieceTable.put(plus.getTypeID(), plus);
    
        Triangle triangle = new Triangle("triangle");
        pieceTable.put(triangle.getTypeID(), triangle);
    
        Chevron chevron = new Chevron("chevron");
        pieceTable.put(chevron.getTypeID(), chevron);
    
        Sun sun = new Sun("sun");
        pieceTable.put(sun.getTypeID(), sun);
    }
}
