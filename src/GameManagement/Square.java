/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameManagement;

// import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
/**
 *
 * @author User
 */
public class Square
{
    //properties
    private boolean isFilled;
    private Rectangle singleSquare;
    //constructor
    public Square(double minX, double minY, double width, double height )
    {
        isFilled = false;
        singleSquare = new Rectangle( minX, minY, width, height );
    }
    
    public double getHeight()
    {
        return singleSquare.getHeight();
    }
    
    public double getXCoodinate()
    {
        return singleSquare.getX();
    }
    
    public double getYCoordinate()
    {
        return singleSquare.getY();
    }
    
    public void setXCoordinate( double newX )
    {
        singleSquare.setX( newX );
    }
    
    public void setYCoordinate( double newY )
    {
        singleSquare.setY( newY );
    }
    
    public void setHeight(double h1)
    {
        singleSquare.setWidth(h1);
        singleSquare.setHeight(h1);
    }
    
    public void setFilled( boolean state )
    {
        isFilled = state;
    }
    
    public boolean getStateOfSquare()
    {
        return isFilled;
    }
    
    public Rectangle getRect()
    {
        return singleSquare;
    }
}
