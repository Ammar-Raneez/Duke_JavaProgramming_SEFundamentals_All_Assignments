/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        //returns index of smallest quake entry magnitude, from a specified location
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       //insertion sort - from a specific location, find the smallest value
       //starts looking from the beginning, brings smallest to the front, then starts
       //looking from the index ahead from that - since that value is already sorted
       for (int i=0; i< in.size(); i++) {
            if(checkInSortedOrder(in)) break;
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
       }
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIndex = from;
        
        for(int i=from+1; i<quakeData.size(); i++) {
            if(quakeData.get(i).getDepth() > quakeData.get(maxIndex).getDepth()) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> quakeData) {
        //i<50 means, 50 passes
        int count = 0;
        for(int i=0; i<70; i++) {
           int maxIndex = getLargestDepth(quakeData, i);
           QuakeEntry max = quakeData.get(maxIndex);
           QuakeEntry temp = quakeData.get(i);
           quakeData.set(i, max);
           quakeData.set(maxIndex, temp);
           count++; 
            if(checkInSortedOrder(quakeData)){
                //System.out.println("in checkInSortedOrder "+i);
                break;
            }
            //System.out.println("after checkInSortedOrder "+i);
        }
        System.out.println("Sorted with "+ count+ " passes");
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for(int i=0; i<quakeData.size()-numSorted-1; i++) {
            if(quakeData.get(i).getMagnitude() > quakeData.get(i+1).getMagnitude()) {
                QuakeEntry temp = quakeData.get(i);
                quakeData.set(i, quakeData.get(i+1));
                quakeData.set(i+1, temp);
            }
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> quakeData) {
        //sort based on magnitude using bubble sort
        for(int i=0; i<quakeData.size(); i++) {
            onePassBubbleSort(quakeData, i);
            System.out.println("Printing quakes after pass " + i);
            
            for(QuakeEntry qe: quakeData){
                System.out.println(qe);
            }
        }
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        //method that determines whether to stop the sorting or not, to increase efficiency, as if the list is already sorted
        //before all passes, it'd be a waste of time
        for(int i=0; i<quakes.size()-1; i++) {
            if(quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()) return false;            
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int count = 0;
        for(int i=0; i<in.size(); i++) {
            onePassBubbleSort(in, i);
            count = i+1;
            if(checkInSortedOrder(in)) {
                break;
            }
        }
        System.out.println("Sorted with " + count+ " passes");
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        for(int i=0; i<in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx, qi);
            if(checkInSortedOrder(in)) {
                System.out.println("Sorted with "+ (i+1)+ " passes");
                break;
            }        
        }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataDec6sample2.atom.";
        //String source = "EarthquakeSortStarterProgram/data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        sortByLargestDepth(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",qe.getLocation().getLatitude(),qe.getLocation().getLongitude(),qe.getMagnitude(),qe.getInfo());
        }
    }
}
