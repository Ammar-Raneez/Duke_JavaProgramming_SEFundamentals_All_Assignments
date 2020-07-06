/**
 * Write a description of class DifferentSorters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class DifferentSorters {
    public void sortWithCompareTo() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        //Collections.sort() - prolly most efficient way of sorting
        Collections.sort(list);
        //for(QuakeEntry qe: list) {
        //    System.out.println(qe);
        //}
        int quakeNumber = 600;
        System.out.println("Quake Entry Position - " + quakeNumber);
        System.out.println(list.get(quakeNumber));
    }    

    public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new MagnitudeComparator());  //passing our compare() method - this makes Collections.sort() to sort based on magnitude
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }

    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));  //based on distance
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }
    
    public void sortByTitleAndDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        Collections.sort(list, new TitleAndDepthComparator());      //based on title and depth
        int quakeNumber = 500;
        System.out.println("Quake Entry Position - " + quakeNumber);
        System.out.println(list.get(quakeNumber));        
    }
    
    public void sortByLastWordInTitleThenByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        Collections.sort(list, new TitleLastAndMagnitudeComparator());
        int quakeNumber = 500;
        System.out.println("Quake Entry Position - " + quakeNumber);
        System.out.println(list.get(quakeNumber));           
    }
}
