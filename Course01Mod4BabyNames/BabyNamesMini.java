
/**
 * Write a description of BabyNamesMini here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyNamesMini {
    public void totalBirths(FileResource fr) {
        int totalBirths = 0, totalBoys = 0, totalGirls = 0;
        int totalNames = 0, boyNames = 0, girlNames = 0;
        
        for(CSVRecord rec: fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNames++;
            if(rec.get(1).equals("M")) totalBoys += numBorn;
            else totalGirls += numBorn;
        }
        
        System.out.println("Total Names - " + totalBirths);
        System.out.println("Total Girls - " + totalGirls);
        System.out.println("Total Boys - "  + totalBoys);
    }
    
    public int getRank(int year, String name, String gender) {
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        int girlRank = 0, boyRank = 0;
        
        for(CSVRecord rec: fr.getCSVParser(false)) {
            if(rec.get(1).equals("F")) {
                girlRank++;
            } else {
                boyRank++;
            }
            if(rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                if(gender.equals("M")) return boyRank;
                return girlRank;
            }
        }
        
        return -1;
    }
    
    public String getName(int year, int rank, String gender) {
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        int girlRank = 0, boyRank = 0;
        
        for(CSVRecord rec: fr.getCSVParser(false)) {
            if(rec.get(1).equals("F")) {
                girlRank++;
            } else {
                boyRank++;
            }
            if(gender.equals("M") && boyRank==rank) {
                return rec.get(0);
            } else if(gender.equals("F") && girlRank==rank) {
                return rec.get(0);
            }
        }
        
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int nameRank = getRank(year, name, gender);
        String newName = getName(newYear, nameRank, gender);
        
        System.out.println(name + " born in " + year + " would be " + newName + 
        " if she was born in " + newYear + ".");
    }
    
    //public String yearOfHighestRank(String name, String gender) {
        //DirectoryResource dr = new DirectoryResource();
        //int highestRank = 0;
        //String fileHighest = "";
        
        //for(File f: dr.selectedFiles()) {
        //    System.out.println(f.getName());
        //    int currentRank = getRank("Mason", "M");
        //    if(highestRank == 0) highestRank = currentRank;
        //    else {
        //        if(currentRank<highestRank) {
        //            highestRank = currentRank;
        //       }
        //    }
        //}
        
        //return highestRank;
    //}
    public int getRankFile(FileResource fr, String name , String gender) {
        int girlRank = 0, boyRank = 0;
        
        for(CSVRecord rec: fr.getCSVParser(false)) {
            if(rec.get(1).equals("F")) {
                girlRank++;
            } else {
                boyRank++;
            }
            if(rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                if(gender.equals("M")) return boyRank;
                return girlRank;
            }
        }
        
        return -1;
    }
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double totalRank = 0.0;
        int count = 0;
        for(File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            count++;
            totalRank += getRankFile(fr, name, gender);
        }
        double average = totalRank/count;
        return average;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob"+year+".csv");
        int nameRank = getRank(year, name, gender);
        int totalHigher = 0;
        
        for(CSVRecord record: fr.getCSVParser(false)) {
            int currentRank = getRank(year, record.get(0), record.get(1)); 
            if(currentRank < nameRank && record.get(1).equals(gender)) {
                totalHigher += Integer.parseInt(record.get(2));
            }
        }
        return totalHigher;
    }
    
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public void testRank() {
        int rank = getRank(1971, "Frank", "M");
        System.out.println(rank);
    }
    
    public void testName() {
        String name = getName(1982, 450, "M");
        System.out.println(name);
    }
    
    public void testNameInYear() {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    //public void testHighestRank() {
    //    int rank = yearOfHighestRank("Mason", "M");
    //    System.out.println(rank);
    //}
    public void testAverageRank() {
        double averageRank = getAverageRank("Robert", "M");
        System.out.println(averageRank);
    }
    
    public void testHigherBirths() {
        int total = getTotalBirthsRankedHigher(1991, "Drew", "M");
        System.out.println(total);
    }
}
