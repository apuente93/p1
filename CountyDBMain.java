///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            p1
// Files:            CountyDBMain.java, CountyDatabase.java, Storm.java,
//					 County.java
// Semester:         CS367 Spring 2014
//
// Author:           Alejandro Puente
// Email:            apuente@wisc.edu
// CS Login:         alejandr
// Lecturer's Name:  Jim Skrentny
// Lab Section:      Lecture 1
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.*;
import java.io.*;
/**
 * The CountyDatabase class stores the counties.
 *
 * <p>Bugs: None
 *
 * @author Alejandro Puente
 */
public class CountyDBMain 
{
	
	/**
	 * The main method starts the entire program's execution. This main 
	 * method creates a file and produces an output file. The arguments given
	 * by the user, determine the names and the size of the output file.
	 */
	public static void main(String[] args) 
	{
		/** Step 1: Check whether exactly one command-line argument is given */
        try
        {
        	if (args.length == 1)
        	{
        		throw new IllegalArgumentException();
        	}
        }
        catch (IllegalArgumentException e)
        {
        	System.out.println("Usage: java CountyDBMain FileName");
        }

        File file1 = new File(args[2]);

        /** Step 2: Check whether the input file exists and is readable ***/
        // *** Add code for step2 here ***/


        /** Step 3: Load the data from the input file and use it to 
         *  construct a county database. Note: counties and storms are to be 
         *  added to the database in the order in which they appear in the text 
         *  file. 
         */
        // *** Add code for step3 here ***/

		/** Step 4: Prompt the user to enter command options and 
         *  process them until the user types x for exit. 
         */
		
		//Keeps programming running until user types "exit"
		boolean stop = false;
		//Scanner object that handles user input
		Scanner stdin = new Scanner(System.in);
        while (!stop) 
        {

            System.out.println("Enter Options");
            String input = stdin.nextLine();
            String remainder = null;
            if (input.length() > 0) 
            {
                char option = input.charAt(0);
                if (input.length() > 1) 
                {
                    remainder = input.substring(1).trim();
                }

                switch (option) 
                {

                case 'a':
                {
                    // *** TODO: Add code to implement this option ***
                    break;
                }
                
                case 'c':
                {
                    // *** TODO: Add code to implement this option ***
                    break;
                }
                
                case 'd':
                {
                    // *** TODO: Add code to implement this option ***
                    break;
                }

                case 'i':
                {
                    // *** TODO: Add code to implement this option ***
                    break;
                }

                case 'r':
                {
                    // *** TODO: Add code to implement this option ***
                    break;
                }
                
                case 's':
                {
                    // *** TODO: Add code to implement this option ***
                    break;
                }

                case 'w':
                {
                    // *** TODO: Add code to implement this option ***
                    break;
                }

                //***exits program***
                case 'x':
                {
                    stop = true;
                    System.out.println("exit");
                    break;
                }
                
                default:
                    break;
                }
            }
        }
    }
}
