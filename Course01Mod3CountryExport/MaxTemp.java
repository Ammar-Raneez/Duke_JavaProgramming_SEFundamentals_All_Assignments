
/**
 * Write a description of MaxTemp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class MaxTemp {
    public CSVRecord getLargest(CSVRecord largestSoFar, CSVRecord current) {
        //returns the csv record that holds the max temperature
        //parsing due to a string being returned
        if(largestSoFar == null) {
            largestSoFar = current;
        } else {
            double temp = Double.parseDouble(current.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF")); 
            if(temp>largestTemp) {
                largestSoFar = current;
            }                
        }
        return largestSoFar;
    }
    
    public CSVRecord hottest(CSVParser parser) {
        //method for a single file
        CSVRecord largestSoFar = null;
        
        for(CSVRecord current: parser) {
            largestSoFar = getLargest(largestSoFar, current);
        }
        return largestSoFar;
    }
    
    public CSVRecord multiple() {
        //checking for multiple selected files
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = hottest(fr.getCSVParser());
            largestSoFar = getLargest(largestSoFar, current);
        }
        
        return largestSoFar;
    }
    
    public void test() {
        FileResource fr = new FileResource();
        CSVRecord largest = hottest(fr.getCSVParser());
        System.out.println("Hottest temp was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
    }
    
    public void testMany() {
        CSVRecord largest = multiple();
        System.out.println("Hottest temp was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
    }
}
