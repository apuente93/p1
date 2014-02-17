///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  CountyDBMain.java
// File:             CountyDatabase.java
// Semester:         CS302 Spring 2014
//
// Author:           Alejandro Puente
// Email:            apuente@wisc.edu
// CS Login:         alejandr
// Lecturer's Name:  Jim Skrentny
// Lab Section:      Lecture 1
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * The CountyDatabase class stores the counties.
 *
 * <p>Bugs: None
 *
 * @author Alejandro Puente
 */
public class CountyDatabase
{
	//Variable used to store the counties in the county database
	private ArrayList<County> counties;
	
	/**
	 * Constructs an empty county database.
	 */
	public CountyDatabase() 
	{
		this.counties = new ArrayList<County>();
	}
	
	/**
	 * Add a county with the given name name to the end of the 
	 * database. If a county with the name name is already in 
	 * the database, just return.
	 * @param name
	 */
	void addCounty(String name) 
	{
		//Variable used to create a county with the given name
		County county = new County(name);
		this.counties.add(county);
	}
	
	/**
	 * Add the given storm storm to county county in the database.
	 * If county county is not in the database 
	 * throw a java.lang.IllegalArgumentException.
	 *
	 * @param storm
	 * @param county
	 */
	void addStorm(Storm storm, String county)
	{
		if (!this.containsCounty(county))
		{
			throw new IllegalArgumentException();
		}
		else
		{
			for (int i = 0; i < this.counties.size(); i++)
			{
				if (this.counties.get(i).getName().equals(county))
				{
					this.counties.get(i).getStorms().add(storm);
				}
			}
		}
		
	}
	
	/**
	 * Return true iff county county is in the database. 
	 *
	 * @param county
	 * @return boolean
	 */
	boolean containsCounty(String county) 
	{
		for (int i = 0; i < this.counties.size(); i++)
		{
			if (this.counties.get(i).getName().equals(county))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Return true iff storm with the name storm appears in at
	 * least one county's list of storms in the database.
	 *
	 * @param storm
	 * @return boolean
	 */
	boolean containsStorm(String storm) 
	{
		for (int i = 0; i < this.counties.size(); i++)
		{
			for (int j = 0; j < this.counties.get(i).getStorms().size(); j++)
			{
				if (this.counties.get(i).getStorms().get(j).getName().
				equals(storm))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns true iff storm with the name storm is in the list of 
	 * storms for county county. If county county is not in the 
	 * database, return false.
	 *
	 * @param storm
	 * @param county
	 * @return boolean
	 */
	boolean hasStorm(String storm, String county) 
	{
		if (!this.containsCounty(county))
		{
			return false;
		}
		else
		{
			for (int i = 0; i < this.counties.size(); i++)
			{
				if (this.counties.get(i).getName().equals(county))
				{
					for (int j = 0; j < this.counties.get(i).getStorms()
					.size(); j++)
					{
						if (this.counties.get(i).getStorms().get(j).getName()
						.equals(storm))
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Return the list of storms with damage amount amount. If no 
	 * storms in the database have damage amount amount, return 
	 * null. 
	 *
	 * @param amount
	 * @return List<Storm>
	 */
	List<Storm> getStormsWithDamageAmount(Integer amount) 
	{
		//Variable used to store the array list of storms with the 
		//specified damage amount
		List<Storm> specificDamageStormList = new ArrayList<Storm>();
		for (int i = 0; i < this.counties.size(); i++)
		{
			for (int j = 0; j < this.counties.get(i).getStorms().size(); j++)
			{
				if (this.counties.get(i).getStorms().get(j).getDamageAmount()
				.equals(amount))
				{
					specificDamageStormList.add(this.counties.get(i).
					getStorms().get(j));
				}
			}
		}
		return specificDamageStormList;		
	}
	
	/**
	 *  Return a list of storms that have the date date. If 
	 *  no storms in the database occurred on date date, 
	 *  return null. 
	 *
	 * @param date
	 * @return List<Storm>
	 */
	List<Storm> getStormsWithDate(String date) 
	{
		//Variable used to store the array list of storms with the specified
		//date
		List<Storm> specificDateStormList = new ArrayList<Storm>();
		for (int i = 0; i < this.counties.size(); i++)
		{
			for (int j = 0; j < this.counties.get(i).getStorms().size(); j++)
			{
				if (this.counties.get(i).getStorms().get(j).getDate().equals(
				date))
				{
					specificDateStormList.add(this.counties.get(i).getStorms()
					.get(j));
				}
			}
		}
		return specificDateStormList;	
	}
	
	/**
	 * Return the list of storms with name name. If no storms in the 
	 * database have name name, return null. 
	 *
	 * @param name
	 * @return List<Storm>
	 */
	List<Storm> getStormsWithName(String name) 
	{
		//Variable used to store the array list of storms with the specified 
		//name
		List<Storm> specificNameStormList = new ArrayList<Storm>();
		for (int i = 0; i < this.counties.size(); i++)
		{
			for (int j = 0; j < this.counties.get(i).getStorms().size(); j++)
			{
				if (this.counties.get(i).getStorms().get(j).getName().equals(
				name))
				{
					specificNameStormList.add(this.counties.get(i).getStorms().
					get(j));
				}
			}
		}
		return specificNameStormList;	
	}
	
	/**
	 * Return the list of storms for the county county. 
	 * If a county county is not in the database, return null. 
	 *
	 * @param county
	 * @return List<Storm>
	 */
	List<Storm> getStormsFromCounty(String county) 
	{
		for (int i = 0; i < this.counties.size(); i++)
		{
			if (this.counties.get(i).getName().equals(county))
			{
				return this.counties.get(i).getStorms();
			}
		}
		return null;
	}
	
	/**
	 * Get the percentage of storms in the database that have a damage amount
	 * of 0. There are multiple ways to implement this method, some more
	 * efficient than others. Can you think of an implementation that avoids
	 * searching through the entire database each time this method is called?
	 *
	 * @return double
	 */
	double getPercentageOfStormsNoDamage() 
	{
		//Variable used to store the percentage of storms with no damage
		double percentage = 0;
		//Variable used to store the array list of storms with the no damage
		List<Storm> noDamageStormList = new ArrayList<Storm>();
		noDamageStormList = this.getStormsWithDamageAmount(0);
		
		//Variable used to store the total number of storms in the county 
		//database
		int totalStorms = 0;
		for (int i = 0; i < this.counties.size(); i++)
		{
			totalStorms += this.counties.get(i).getStorms().size();
		}
		percentage = ((double)noDamageStormList.size() / totalStorms) * 100;
		
		return percentage;
	}
	
	/**
	 * Return an Iterator over the County objects in the database. 
	 * The counties should be returned in the order they were added 
	 * to the database (resulting from the order in which they are in 
	 * the text file). 
	 *
	 * @return Iterator<County>
	 */
	Iterator<County> iterator() 
	{
		//Variable used to iterate through the county database
		Iterator<County> iterator = this.counties.iterator();
		return iterator;
	}
	
	/**
	 * Print the names, dates, and damage amounts of the three storms that have
	 * the largest damage amounts, 1 storm per line. You must find the 3 most 
	 * expensive storms and print out the information previously stated for 
	 * those 3 storms. The storms should be printed in descending order (i.e.
	 * most expensive storm(s) first). DO NOT worry about handling ties (i.e.
	 * storms that have the same damage amount) as we will not test for this.
	 * It is fine to use Collections.sort() to sort a List of Integers. 
	 * However, (hint hint) there may be a better/easier way to keep track of
	 * the 3 most expensive storms instead of sorting through the entire 
	 * database.
	 */
	void printThreeMostExpensiveStorms() 
	{
		//Variable used to store the damage amounts of all storms, to figure 
		//out the three most expensive storms
		ArrayList<Integer> allStormDamageAmounts = new ArrayList<Integer>();
		//Variable used to iterate through the list of counties in the county
		//database
		Iterator<County> iterator = this.iterator();
		while (iterator.hasNext())
		{
			//Variable used to store the county that the iterator is pointing 
			//to
			County currentCounty = iterator.next();
			List<Storm> currentCountyStorms = this.getStormsFromCounty(
			currentCounty.getName());
			for (int i = 0; i < currentCountyStorms.size(); i++)
			{
				allStormDamageAmounts.add(currentCountyStorms.get(i).
				getDamageAmount());
			}
		}
		
		Collections.sort(allStormDamageAmounts);
		Collections.reverse(allStormDamageAmounts);
		
		System.out.println(this.getStormsWithDamageAmount(
				allStormDamageAmounts.get(0)).get(0).getName() + 
				", " + this.getStormsWithDamageAmount(
				allStormDamageAmounts.get(0)).get(0).getDate() + ", " +
				this.getStormsWithDamageAmount(
				allStormDamageAmounts.get(0)).get(0).getDamageAmount());
		System.out.println(this.getStormsWithDamageAmount(
				allStormDamageAmounts.get(1)).get(0).getName() + 
				", " + this.getStormsWithDamageAmount(
				allStormDamageAmounts.get(1)).get(0).getDate() + ", " +
				this.getStormsWithDamageAmount(
				allStormDamageAmounts.get(1)).get(0).getDamageAmount());
		System.out.println(this.getStormsWithDamageAmount(
				allStormDamageAmounts.get(2)).get(0).getName() + 
				", " + this.getStormsWithDamageAmount(
				allStormDamageAmounts.get(2)).get(0).getDate() + ", " +
				this.getStormsWithDamageAmount(
				allStormDamageAmounts.get(2)).get(0).getDamageAmount());
	}
	
	/**
	 * Remove county county from the database. If county county is not in the
	 * database, return false; otherwise (i.e., the removal is successful) 
	 * return true. 
	 *
	 * @param county
	 * @return boolean
	 */
	boolean removeCounty(String county) 
	{
		if (this.containsCounty(county))
		{
			for (int i = 0; i < this.counties.size(); i++)
			{
				if (this.counties.get(i).getName().equals(county))
				{
					this.counties.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Remove all storms with the name storm from the database. If 
	 * there are no storms with the name storm in the database, return 
	 * false; otherwise (i.e., the removal is successful) return true.
	 *
	 * @param storm
	 * @return boolean
	 */
	boolean removeStormsWithName(String storm) 
	{
		//Variable used to check whether the storm with the given name has been
		//removed
		boolean removed = false;
		
		if (!this.containsStorm(storm))
		{
			return removed;
		}
		//Variable used to iterate through the list of counties in the county 
		//database
		Iterator<County> iterator = this.iterator();
		while (iterator.hasNext())
		{
			//Variable used to store the county that the iterator is pointing 
			//to
			County currentCounty = iterator.next();
			//Variable used to store the array list of storms of the current
			//county
			List<Storm> currentCountyStorms = this.getStormsFromCounty(
			currentCounty.getName());
			for (int i = 0; i < currentCountyStorms.size(); i++)
			{
				if (currentCountyStorms.get(i).getName().equals(storm))
				{
					currentCountyStorms.remove(i);
					removed = true;
				}
			}
		}
		return removed;
	}
	
	/**
	 *  Remove storms with damage amount damage from the database, i.e.,
	 *  remove ALL storms that have the damage amount damage from the 
	 *  database. If storms with the damage amount damage are not in 
	 *  the database, return false; otherwise (i.e., the removal
	 *  is successful) return true. 
	 *
	 * @param damage
	 * @return boolean
	 */
	boolean removeStormsWithDamageAmount(Integer damage)
	{
		//Variable used to check whether the storm with the given damage
		//has been removed
		boolean removed = false;
		//Variable used to store the array list of storms with the given
		//damage amount
		List<Storm> storms = new ArrayList<Storm>(); 
		storms = this.getStormsWithDamageAmount(damage);
		
		for (int i = 0; i < this.counties.size(); i++)
		{
			for (int j = 0; j < this.counties.get(i).getStorms().size(); j++)
			{
				for (int x = 0; x < storms.size(); x++)
				{
					if (this.counties.get(i).getStorms().get(j).equals(
					storms.get(x)))
					{
						this.counties.get(i).getStorms().remove(j);
						removed = true;
					}
				}
			}
		}		
		return removed;
	}
	
	/**
	 * Return the number of counties in this database.
	 *
	 * @return boolean
	 */
	int size() 
	{
		return this.counties.size();
	}
}
