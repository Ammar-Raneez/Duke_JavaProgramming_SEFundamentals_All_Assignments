
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class BabyBirths {
    public void printNames() {
        FileResource fr = new FileResource();
        
        //ignore header row - cuz it ain't there on the csv file...
        for(CSVRecord rec: fr.getCSVParser(false)) {
            System.out.println("Name " + rec.get(0)
                + " Gender " + rec.get(1) + " Num Born " + 
                rec.get(2));
        }
    }
    
    public void totalBirths(FileResource fr) {
        int totalBirths = 0, totalBoys = 0, totalGirls = 0;
        for(CSVRecord rec: fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if(rec.get(1).equals("M")) totalBoys += numBorn;
            else totalGirls += numBorn;
        }
        System.out.println(totalBirths + " " + totalBoys + " " + totalGirls);
    }
    
    public void test() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
}
