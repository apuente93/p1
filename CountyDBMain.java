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
	 * The main method starts the entire program's execution. The user provides
	 * an input file, which is then converted to a county database. The user 
	 * can then manipulate the database through simple commands.
	 */
	public static void main(String[] args)
	{
		try
		{
			//Check whether exactly one command-line argument is given
			if (args.length != 1)
			{
				throw new IllegalArgumentException();
			}

			//Variable used to store the input file
			File file1 = new File(args[0]);
			//Variable used to store the county information
			CountyDatabase countyDatabase = new CountyDatabase();
			//Variable used to scan through the input file
			Scanner in = new Scanner(file1);
			
			//If the input file is empty, throw a FileNotFoundException
			if (!in.hasNextLine())
			{
				throw new FileNotFoundException();
			}

			//While the input file has data, add the given information to the
			//database
			while (in.hasNextLine())
			{
				//Variable used to store each line of the input file
				String inputLine = in.nextLine();
				//Variable used to split each section of the inputLine into 
				//separate sections
				String [] wordsInLine = inputLine.split(",");

				if (!countyDatabase.containsCounty(wordsInLine[0].trim()))
				{
					countyDatabase.addCounty(wordsInLine[0].trim());
				}

				//Variable used to store the storm information of a given 
				//county
				Storm storm = new Storm(wordsInLine[1].trim(),
				wordsInLine[2].trim(), Integer.parseInt(wordsInLine[3].trim())
				);
				countyDatabase.addStorm(storm, wordsInLine[0].trim());
			}

			//Keeps programming running until user types "x"
			boolean stop = false;
			//Scanner object that handles user input
			Scanner stdin = new Scanner(System.in);
			while (!stop) 
			{
				System.out.println("Enter Options");
				//Variable that stores the user input
				String input = stdin.nextLine();
				//Variable that stores the remainder of the user input
				String remainder = null;
				//Variable that stores an array list of storms
				List<Storm> storms = new ArrayList<Storm>();
				if (input.length() > 0) 
				{
					//Variable that stores the user command option
					char option = input.charAt(0);
					//If the user types more than just one character, store the
					//rest in remainder
					if (input.length() > 1) 
					{
						remainder = input.substring(1).trim();
					}

					switch (option) 
					{

					case 'a':
						//Get all the storms with the specified damage amount
						storms = countyDatabase.getStormsWithDamageAmount(
						Integer.parseInt(remainder));

						if (storms.isEmpty())
						{
							System.out.println("no storms found");
						}
						else
						{
							for (int i = 0; i < storms.size(); i++)
							{
								System.out.println(storms.get(i).getName() +
								", " + storms.get(i).getDate());
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
							storms = countyDatabase.getStormsFromCounty(
							remainder);
							for (int i = 0; i < storms.size(); i++)
							{
								System.out.println(storms.get(i).getName() +
								", " + storms.get(i).getDate() + ", " + 
								storms.get(i).getDamageAmount());
							}
						}
						break;
						
					case 'd':
						//Get all the storms with the specified date
						storms = countyDatabase.getStormsWithDate(remainder);

						if (storms.isEmpty())
						{
							System.out.println("storm not found");
						}
						else
						{
							for (int i = 0; i < storms.size(); i++)
							{
								System.out.println(storms.get(i).getName() +
								", " + storms.get(i).getDamageAmount());
							}
						}
						break;

					case 'i':
						//Variable that is used to iterate through the list of
						//counties and gather the total number of storms in 
						//the database
						Iterator<County> countyIterator = 
						countyDatabase.iterator();
						//Variable that is used to iterate through the list of
						//counties to figure out which county has the 
						//max number of storms
						Iterator<County> countyIterator2 = 
						countyDatabase.iterator();
						//Variable that is used to iterate through the list of
						//counties to figure out which county has the min 
						//number of storms
						Iterator<County> countyIterator3 = 
						countyDatabase.iterator();

						//Variable that holds the total number of storms in the
						//county database
						int totalStorms = 0;
						//Variable that holds the max number of storms a county
						//has in the county database
						int maxStorms = 0;
						//Variable that holds the min number of storms a county
						//has in the county database
						int minStorms = 100;

						while (countyIterator.hasNext())
						{
							totalStorms += countyIterator.next().getStorms(
							).size();
						}

						while (countyIterator2.hasNext())
						{
							if (countyIterator2.next().getStorms().
							size() > maxStorms)
							{
								maxStorms = countyIterator2.next().
								getStorms().size();
							}
						}

						while (countyIterator3.hasNext())
						{
							if (countyIterator3.next().getStorms().size()
							< minStorms)
							{
								minStorms = countyIterator3.next().getStorms()
								.size();
							}
						}

						System.out.println("Storms: " + totalStorms + ", " + 
						"Counties: " + countyDatabase.size());
						System.out.println("# of storms/county: most " + maxStorms
						+ ", least " + minStorms + ", average " + (totalStorms/
						countyDatabase.size()));
						System.out.println("% of storms that have a damage "
						+ "amount of 0: " + countyDatabase.
						getPercentageOfStormsNoDamage());
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
						//Variable that is used to store the two counties given
						//by the user
						String [] counties = remainder.split(";");
						if (!countyDatabase.containsCounty(counties[0].trim())
						|| !countyDatabase.containsCounty(counties[1].trim()))
						{
							System.out.println("counties are not valid");
						}
						else
						{
							//Variable that holds the array list of storms of
							//the first county given
							List<Storm> storm1 = new ArrayList<Storm>();
							//Variable that holds the array list of 
							//storms of the second county given
							List<Storm> storm2 = new ArrayList<Storm>();
							storm1 = countyDatabase.getStormsFromCounty(
							counties[0]);
							storm2 = countyDatabase.getStormsFromCounty(
							counties[1]);
							//Variable that holds the total damage amount
							//of the storms in the first county
							Integer amount1 = 0;
							//Variable that holds the total damage amount
							//of the storms in the second county
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
							//Variable used to store the total damage amount of
							//the given storm
							int totalDamage = 0;
							storms = countyDatabase.getStormsWithName(
							remainder);
							for (int i = 0; i < storms.size(); i++)
							{
								totalDamage += storms.get(i).getDamageAmount();
							}
							System.out.println("average damage amount: " + 
							(double)totalDamage / storms.size());
						}
						break;

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
