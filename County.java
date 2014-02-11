///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  CountyDBMain.java
// File:             CountyDatabase.java
// Semester:         CS302 Spring 2014
//
// Author:           Alejandro Puente apuente@wisc.edu
// CS Login:         alejandr
// Lecturer's Name:  Jim Skrentny
// Lab Section:      Lecture 1
//////////////////////////// 80 columns wide //////////////////////////////////


/** The following class represents the County class and holds
 *  the county name and a list of storms that occurred in that county.
 ************ DO NOT MODIFY THIS CLASS *************/
import java.util.List;
import java.util.ArrayList;;

public class County {
	private String name;
	private List<Storm> storms;
	public County(String name) {
		this.name = name;
		storms = new ArrayList<Storm>();
	}
	public String getName() {
		return name;
	}
	public List<Storm> getStorms() {
		return storms;
	}
}
