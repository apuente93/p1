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
import java.util.Collections;
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
			if (args.length != 1)
			{
				throw new IllegalArgumentException();
			}

			File file1 = new File(args[0]);
			CountyDatabase countyDatabase = new CountyDatabase();


			Scanner in = new Scanner(file1);
			
			if (!in.hasNextLine())
			{
				throw new FileNotFoundException();
			}

			while (in.hasNextLine())
			{
				String inputLine = in.nextLine();
				String [] wordsInLine = inputLine.split(",");

				if (!countyDatabase.containsCounty(wordsInLine[0].trim()))
				{
					countyDatabase.addCounty(wordsInLine[0].trim());
				}

				Storm storm = new Storm(wordsInLine[1].trim(), wordsInLine[2].trim()
						, Integer.parseInt(wordsInLine[3].trim()));
				countyDatabase.addStorm(storm, wordsInLine[0].trim());
			}




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
				List<Storm> storms = new ArrayList<Storm>();
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
						storms = countyDatabase.getStormsWithDamageAmount(Integer.parseInt(remainder));

						if (storms.isEmpty())
						{
							System.out.println("no storms found");
						}
						else
						{
							for (int i = 0; i < storms.size(); i++)
							{
								System.out.println(storms.get(i).getName() + ", " + storms.get(i).getDate());
							}
						}
						break;

					case 'c':
						if (!countyDatabase.containsCounty(remainder))
						{
							System.out.println("county not found"); 
						}
						else
						{
							storms = countyDatabase.getStormsFromCounty(remainder);
							for (int i = 0; i < storms.size(); i++)
							{
								System.out.println(storms.get(i).getName() + ", " + storms.get(i).getDate() + ", " + storms.get(i).getDamageAmount());
							}
						}
						break;
					case 'd':

						storms = countyDatabase.getStormsWithDate(remainder);

						if (storms.isEmpty())
						{
							System.out.println("storm not found");
						}
						else
						{
							for (int i = 0; i < storms.size(); i++)
							{
								System.out.println(storms.get(i).getName() + ", " + storms.get(i).getDamageAmount());
							}
						}
						break;


					case 'i':
						Iterator<County> countyIterator = countyDatabase.iterator();
						Iterator<County> countyIterator2 = countyDatabase.iterator();
						Iterator<County> countyIterator3 = countyDatabase.iterator();

						int totalStorms = 0;
						int maxStorms = 0;
						int minStorms = 10;

						while (countyIterator.hasNext())
						{
							totalStorms += countyIterator.next().getStorms().size();
						}

						while (countyIterator2.hasNext())
						{
							if (countyIterator2.next().getStorms().size() > maxStorms)
							{
								maxStorms = countyIterator2.next().getStorms().size();
							}
						}

						while (countyIterator3.hasNext())
						{
							if (countyIterator3.next().getStorms().size() < minStorms)
							{
								minStorms = countyIterator3.next().getStorms().size();
							}
						}

						System.out.println("Storms: " + totalStorms + ", " + "Counties: " + countyDatabase.size());
						System.out.println("# of storms/county: " + maxStorms  + ", " + minStorms + ", " + (totalStorms/countyDatabase.size()));
						System.out.println("% of storms that have a damage amount of 0: " + countyDatabase.getPercentageOfStormsNoDamage());
						countyDatabase.printThreeMostExpensiveStorms();
						break;


					case 'r':

						if (!countyDatabase.containsCounty(remainder))
						{
							System.out.println("county not found");
						}
						else
						{
							countyDatabase.removeCounty(remainder);
							System.out.println("county removed");
						}
						break;


					case 's':

						String [] counties = remainder.split(";");
						if (!countyDatabase.containsCounty(counties[0].trim()) || !countyDatabase.containsCounty(counties[1].trim()))
						{
							System.out.println("counties are not valid");
						}
						else
						{
							List<Storm> storm1 = new ArrayList<Storm>();
							List<Storm> storm2 = new ArrayList<Storm>();
							storm1 = countyDatabase.getStormsFromCounty(counties[0]);
							storm2 = countyDatabase.getStormsFromCounty(counties[1]);
							Integer amount1 = 0;
							Integer amount2 = 0;

							for (int i = 0; i < storm1.size(); i++)
							{
								amount1 += storm1.get(i).getDamageAmount();
							}
							for (int j = 0; j < storm2.size(); j++)
							{
								amount2 += storm2.get(j).getDamageAmount();
							}

							if (amount1.equals(amount2))
							{
								System.out.println("same damage amount");
							}
							else
							{
								System.out.println("different damage amounts");
							}
						}

						break;


					case 'w':
						if (!countyDatabase.containsStorm(remainder))
						{
							System.out.println("no storms found");
						}
						else
						{
							int totalDamage = 0;
							storms = countyDatabase.getStormsWithName(remainder);
							for (int i = 0; i < storms.size(); i++)
							{
								totalDamage += storms.get(i).getDamageAmount();
							}
							System.out.println("average damage amount: " + (double)totalDamage / storms.size());
						}
						break;


						//***exits program***
					case 'x':

						stop = true;
						System.out.println("exit");
						break;


					default:

						break;
					}
				}
				System.out.println();
			}
		}
		catch (IllegalArgumentException e)
		{
			System.out.println("Usage: java CountyDBMain FileName");
		}
		catch (FileNotFoundException e2)
		{
			System.out.println("Error: Cannot access input file");
		}
	}
}
