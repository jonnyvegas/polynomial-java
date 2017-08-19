/**
 * Polynomial.java
 * Project 5
 * COMP 182
 * Prof. Putnam
 * Jonathan Villegas
 * November 24, 2014
 */
package polynomial;

import java.util.Scanner;

/**
 * Class for a polynomial. It is stored with two nodes,
 * a node for the coefficient, and a node for the power.
 * @author Jonny
 */
public class Polynomial 
{
    PolyNode head1;
    PolyNode prev1;
    PolyNode prev2;
    PolyNode curr1;
    PolyNode curr2;
    int count1;
    
    /*
    * Constructor.
    */
    public Polynomial()
    {
        head1 = new PolyNode(null);
        count1 = 0;
    }
    
    /**
     * Adds a node to the polynomial.
     * @param theCoeff the coefficient of the node.
     * @param thePower the power of the node.
     */
    public void addNode(int theCoeff, int thePower)
    {
        //If there is nothing in the polynomial
        if(count1 == 0)
        {
            PolyNode theNode = new PolyNode(theCoeff, thePower, null);
            head1 = theNode;
            count1++;
        }
        //There is something in the polynomial
        else
        {
            PolyNode theNode = new PolyNode(theCoeff, thePower);
            curr1 = head1;
            //Go through the list.
            while(curr1.getNext() != null)
            {
                curr1 = curr1.getNext();
            }
            curr1.setNext(theNode);
            count1++;
        }
    }
    
    /**
     * Adds two polynomials and returns a result.
     * @param p2 the second polynomial to be added.
     * @return The polynomial that has the sum of the two polynomials.
     */
    public Polynomial add(Polynomial p2)
    {
        //Set the currs to the heads of the polynomials.
        curr1 = this.head1;
        curr2 = p2.head1;
        Polynomial pSum = new Polynomial();
        //Walk through the polynomials if they are not empty.
        while(curr1 != null && curr2 != null)
        {
            //The power we are at with the first poly does not have a match.
            if(curr1.getPower() > curr2.getPower())
            {
                //Add it to the polynomial.
                pSum.addNode(curr1.getCoefficient(), curr1.getPower());
                curr1 = curr1.getNext();
            }
            //The power we are at with the second poly does not have a match.
            else if(curr1.getPower() < curr2.getPower())
            {
                //Add it to the polynomial.
                pSum.addNode(curr2.getCoefficient(), curr2.getPower());
                curr2 = curr2.getNext();
            }
            //curr1.getPower() == curr2.getPower()
            else
            {
                int theCoeff, thePower;
                //Add the coefficients.
                theCoeff = curr1.getCoefficient() + curr2.getCoefficient();
                //Get the power of the coefficient.
                thePower = curr1.getPower();
                //Add it to the sum.
                pSum.addNode(theCoeff, thePower);
                //Go to the next link of each.
                curr1 = curr1.getNext();
                curr2 = curr2.getNext();
            }
        }
        //If we are not at the end of both polynomials
        if(curr1 != null && curr2 == null)
        {
            //The first poly is not finished
            while(curr1 != null)
            {
                pSum.addNode(curr1.getCoefficient(), curr1.getPower());
                curr1 = curr1.getNext();
            }
        }
        else if(curr2 != null && curr1 == null)
        {
            //The second poly is not finished
            while(curr2 != null)
            {
                pSum.addNode(curr2.getCoefficient(), curr2.getPower());
                curr2 = curr2.getNext();
            }
        }
        return pSum;
    }
    
    /**
     * Subtracts two polynomials.
     * @param p2 The polynomial to subtract.
     * @return The difference of the two polynomials. p1 - p2.
     */
    public Polynomial subtract(Polynomial p2)
    {
        //Get to the top of both lists.
        curr1 = this.head1;
        curr2 = p2.head1;
        Polynomial pDiff = new Polynomial();
        //We are not at the end of either one.
        while(curr1 != null && curr2 != null)
        {
            //Poly 1 does not have a match in poly 2.
            if(curr1.getPower() > curr2.getPower())
            {
                //Add poly 1's node.
                pDiff.addNode(curr1.getCoefficient(), curr1.getPower());
                curr1 = curr1.getNext();
            }
            //Poly 2 does not have a match in poly 1.
            else if(curr1.getPower() < curr2.getPower())
            {
                //Add poly 2's node, but it must be subtracted.
                pDiff.addNode((0 - curr2.getCoefficient()), curr2.getPower());
                curr2 = curr2.getNext();
            }
            //curr1.getPower() == curr2.getPower()
            else
            {
                int theCoeff, thePower;
                //Subtract poly 2 from poly 1 (p1 - p2).
                theCoeff = curr1.getCoefficient() - curr2.getCoefficient();
                thePower = curr1.getPower();
                //Add the node.
                pDiff.addNode(theCoeff, thePower);
                //Advance to the next part of the list.
                curr1 = curr1.getNext();
                curr2 = curr2.getNext();
            }
        }
        //If one isn't finished...
        if(curr1 != null && curr2 == null)
        {
            //Go until poly 1 is finished.
            while(curr1 != null)
            {
                pDiff.addNode(curr1.getCoefficient(), curr1.getPower());
                curr1 = curr1.getNext();
            }
        }
        //If one isn't finished...
        else if(curr2 != null && curr1 == null)
        {
            //Go until poly 2 is finished.
            while(curr2 != null)
            {
                //Make sure to subtract!!
                pDiff.addNode((0 - curr2.getCoefficient()), curr2.getPower());
                curr2 = curr2.getNext();
            }
        }
        return pDiff;
    }
    
    @Override
    public String toString()
    {
        String theString = new String();
        theString = "";
        prev1 = null;
        curr1 = head1;
        while(curr1.getNext() != null)
        {    
            prev1 = curr1;
            //The first node.
            if(curr1 == head1)
            {
                theString = theString + curr1.getCoefficient() + "x^" 
                        + curr1.getPower() + " ";
            }
            //The next is the end.
            else if(curr1.getNext() == null)
            {
                theString = theString + curr1.getCoefficient() + "x^" 
                        + curr1.getPower() + " ";
            }
            //We are in the middle.
            else
            {
                //Negative.
                if(prev1.getCoefficient() < 0)
                {
                    theString = theString + "- ";
                }
                //Positive.
                else if(prev1.getCoefficient() > 0)
                {
                    theString = theString + "+ ";
                }
                //Print x^ if we are not at the end.
                if(curr1.getCoefficient() != 0)
                {
                    theString = theString + Math.abs(curr1.getCoefficient()) + 
                            "x^" + curr1.getPower() + " ";
                }
            }
            curr1 = curr1.getNext();
        }
        //Negative.
        if(curr1 != null && curr1.getCoefficient() < 0)
        {
            theString = theString + "- ";
        }
        //Positive.
        else if(curr1 != null && curr1.getCoefficient() > 0)
        {
            theString = theString + "+ ";
        }
        //We are not at the end yet.
        if(curr1 != null && curr1.getCoefficient() != 0)
        {
            //Print x^ because we are not at the end.
            if(curr1.getPower() != 0)
            {
                theString = theString + Math.abs(curr1.getCoefficient()) + "x^" 
                        + curr1.getPower() + " ";
            }
            //We are at the end.
            else
            {
                theString = theString + Math.abs(curr1.getCoefficient());
            }
        }
        else if (curr1.getNext() == null && curr1.getPower() == 0)
        {
            theString = theString + Math.abs(curr1.getCoefficient());
        }
        return theString;
    }
    
    /**
     * Input up to twelve polynomials. You can then specify the operations.
     */
    public static void inputPoly()
    {
        //Maxmimum number of polynomials.
        final int MAX = 12;
        //Array of polynomial reference variables.
        Polynomial[] p = new Polynomial[MAX];
        //Input each polynomial.
        for(int i = 0; i < MAX; i++)
        {
            p[i] = new Polynomial();
        }
        int coeff, power, cont;
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter the num of polynomials: ");
        int theNum = in.nextInt();
        //Too many polynomials!!!
        if(theNum > MAX)
        {
            do
            {
                System.out.println("Too many polynomials! Try again.");
                System.out.print("Please enter the num of polynomials: ");
                theNum = in.nextInt();
            }while(theNum > MAX);
            //Input each polynomial.
            for(int i = 0; i < theNum; i++)
            {
                System.out.println("Please enter polynomial " + (i + 1) + ": ");
                coeff = in.nextInt();
                power = in.nextInt();
                while(power != 0)
                {
                    p[i].addNode(coeff, power);
                    coeff = in.nextInt();
                    power = in.nextInt();
                }
                if(power == 0 && coeff != 0)
                {
                    p[i].addNode(coeff, power);
                }
                System.out.println("You entered: " + p[i]);
            }
            //Add or subtract two polynomials.
            do
            {
                System.out.print("Enter the number of the first poly: ");
                int first = in.nextInt();
                System.out.print("Please enter the number of the second poly: ");
                int second = in.nextInt();
                System.out.print("Please enter 1 to add or 2 to subtract: ");
                int choice = in.nextInt();
                if(choice == 1)
                {
                    Polynomial pAdd = p[first - 1].add(p[second - 1]);
                    System.out.println(" " + p[first - 1]);
                    System.out.println("+ " + p[second - 1]);
                    System.out.println("--------------------");
                    System.out.println(pAdd);
                }
                else if(choice == 2)
                {
                    Polynomial pDiff = p[first - 1].subtract(p[second - 1]);
                    System.out.println(" " + p[first - 1]);
                    System.out.println("- " + p[second - 1]);
                    System.out.println("--------------------");
                    System.out.println(pDiff);
                }
                System.out.print("Please enter 1 to continue or 0 to exit: ");
                cont = in.nextInt();
            }while(cont == 1);
        }
        //It was a correct number of polynomials. Proceed as usual.
        else
        {
            //Enter each polynomial.
            for(int i = 0; i < theNum; i++)
            {
                System.out.println("Please enter polynomial " + (i + 1) + ": ");
                coeff = in.nextInt();
                power = in.nextInt();
                while(power != 0)
                {
                    p[i].addNode(coeff, power);
                    coeff = in.nextInt();
                    power = in.nextInt();
                }
                if(power == 0 && coeff != 0)
                {
                    p[i].addNode(coeff, power);
                }
                System.out.println("You entered: " + p[i]);
            }
            //Add or subtract a polynomial.
            do
            {
                System.out.print("Enter the number of the first poly: ");
                int first = in.nextInt();
                System.out.print("Please enter the number of the second poly: ");
                int second = in.nextInt();
                System.out.print("Please enter 1 to add or 2 to subtract: ");
                int choice = in.nextInt();
                if(choice == 1)
                {
                    Polynomial pAdd = p[first - 1].add(p[second - 1]);
                    System.out.println(" " + p[first - 1]);
                    System.out.println("+ " + p[second - 1]);
                    System.out.println("--------------------");
                    System.out.println(pAdd);
                }
                else if(choice == 2)
                {
                    Polynomial pDiff = p[first - 1].subtract(p[second - 1]);
                    System.out.println(" " + p[first - 1]);
                    System.out.println("- " + p[second - 1]);
                    System.out.println("--------------------");
                    System.out.println(pDiff);
                }
                System.out.print("Please enter 1 to continue or 0 to exit: ");
                cont = in.nextInt();
            }while(cont == 1);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        inputPoly();
    }  
}
