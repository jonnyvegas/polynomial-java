/**
 * Polynomial.java
 * Project 5
 * COMP 182
 * Prof. Putnam
 * Jonathan Villegas
 * November 24, 2014
 */
package polynomial;

/**
 * A class for a linked list of a polynomial.
 * @author Jonny
 */
public class PolyNode 
{
    private int coefficient;
    private int power;
    private PolyNode next;
    
    //Constructor.
    public PolyNode()
    {
        coefficient = -1;
        power = -1;
        next = null;
    }
    
    //Constructor.
    public PolyNode(int theCoefficient, int thePower, PolyNode theNode)
    {
        coefficient = theCoefficient;
        power = thePower;
        next = theNode;
    }
    
    //Constructor.
    public PolyNode(int theCoefficient, int thePower)
    {
        if(theCoefficient != 0)
        {
            coefficient = theCoefficient;
            power = thePower;
            next = null;
        }
        //Else the coefficient is 0 and is not added.
    }
    
    //Constructor.
    public PolyNode(PolyNode theNode)
    {
        next = theNode;
    }
    
    /**
     * Sets coefficient and power.
     * @param theCoefficient The coefficient to set.
     * @param thePower The power to set.
     */
    public void setCoeffAndPower(int theCoefficient, int thePower)
    {
        coefficient = theCoefficient;
        power = thePower;
    }
    
    /**
     * Sets the coefficient.
     * @param theCoefficient The coefficient to set. 
     */
    public void setCoefficient(int theCoefficient)
    {
        coefficient = theCoefficient;
    }
    
    /**
     * Retrieves the coefficient.
     * @return The coefficient.
     */
    public int getCoefficient()
    {
        return coefficient;
    }
    
    /**
     * Sets the power.
     * @param thePower The power to set. 
     */
    public void setPower(int thePower)
    {
        power = thePower;
    }
    
    /**
     * Gets the power.
     * @return The power.
     */
    public int getPower()
    {
        return power;
    }
    
    /**
     * Sets the next node.
     * @param theNode The next node to be set in the list.
     */
    public void setNext(PolyNode theNode)
    {
        next = theNode;
    }
    
    /**
     * Get the next node in the list.
     * @return The next node.
     */
    public PolyNode getNext()
    {
        return next;
    }
}
