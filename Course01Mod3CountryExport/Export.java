 


/**
 * Write a description of Csv here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Export {
    public void readFood() {
        FileResource fr = new FileResource();   //makes us to chose a file
        CSVParser parser = fr.getCSVParser();   //give me the csv parser of our file
        
        //csv record has a get method to get a line from a csv file
        for(CSVRecord record: parser) {
            System.out.println(record);     //gives entire record
            System.out.println(record.get("Favorite Food"));    //choosing a single column
            System.out.println(record.get("Favorite Color"));   //from the excel spreadsheet
            System.out.println(record.get("Name"));
        }
    }
    
    //method to get countries that contain our specific need of export
    public void listExporters(CSVParser parser, String exportInterest) {
        for(CSVRecord record: parser) {
            String export = record.get("Exports");
            if(export.contains(exportInterest)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public void whoExports() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    
    public void countryInfo(CSVParser parser, String country) {
        //country info for a specified country
        boolean flag = false;
        for(CSVRecord record: parser) {
            String countryy = record.get("Country");
            if(countryy.equals(country)) {
                String export = record.get("Exports");
                String cash = record.get("Value (dollars)");
                System.out.println(countryy +  ": " + export + ": " + cash);
                break;
            } else {
                flag = true;
            }
        }
        if(flag)System.out.println("ERROR");
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        //exporters that export both the specified items
        for(CSVRecord record: parser) {
            String countryy = record.get("Country");
            String export = record.get("Exports");
            if(export.contains(exportItem1) && export.contains(exportItem2)) {
                System.out.println(countryy);
            }
        }
    }
    
    public void numberOfExporters(CSVParser parser, String exportItem) {
        //total number of exporters for a specified item
        int count = 0;
        for(CSVRecord record: parser) {
            String export = record.get("Exports");
            if(export.contains(exportItem)) {
                System.out.println(record.get("Country"));
                count++;
            }
        }      
        System.out.println(count);
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        //export values greater than specified value
        for(CSVRecord record: parser) {
            String value = record.get("Value (dollars)");
            String a = amount.substring(1, amount.length());
            String b = value.substring(1, value.length());
            if(b.length()>a.length()) {
                System.out.println(value + " " + record.get("Country"));
            }
        }           
    }
    
    public void test() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //countryInfo(parser, "Nauru");
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //numberOfExporters(parser, "cocoa");
        bigExporters(parser, "$999,999,999, 999");
    }
    
}
