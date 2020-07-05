
/**
 * Write a description of MinTemp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class MinTemp {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        //returns csvrecord that has the lowest temperature
        CSVRecord lowestSoFar = null;
        
        for(CSVRecord current: parser) {
            if(lowestSoFar == null) {
                lowestSoFar = current;
            } else {
                double temp = Double.parseDouble(current.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));  
                if(temp<lowestTemp) {
                    lowestSoFar = current;
                }  
            }
        }
        return lowestSoFar;
    }
    
    public String fileWithColdestTemperature() {
        //file name holding the csv record that has the lowest temperature
        CSVRecord lowestSoFar = null;
        CSVParser lowestDay = null;
        String file = "";
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            if(lowestSoFar == null) {
                lowestSoFar = current;
            } else {
                double temp = Double.parseDouble(current.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));  
                if(temp<lowestTemp) {
                    lowestSoFar = current;
                    file = f.getName();
                    lowestDay = fr.getCSVParser();
                }  
            }
        }
        System.out.println("Coldest temperature on that day was " + lowestSoFar.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        for(CSVRecord rec: lowestDay) {
            System.out.println(rec.get("DateUTC") + " " + rec.get("TemperatureF"));
        }
        return file;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        //lowest humidity recorded, for a single file
        CSVRecord lowest = null;
        
        for(CSVRecord current: parser) {
            if(lowest == null) {
                lowest = current;
            } else {
                if(!current.get("Humidity").equals("N/A") && !lowest.get("Humidity").equals("N/A")){
                    double humidity = Double.parseDouble(current.get("Humidity"));
                    double lowestHumidity = Double.parseDouble(lowest.get("Humidity"));  
                    
                    if(humidity<lowestHumidity) {
                        lowest = current;
                    }  
                }
            }
        }
        return lowest;
    }
    
    public CSVRecord lowestHumidityMultipleFiles() {
        //lowest humidity recorded for multiple files
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            if(lowestSoFar == null) {
                lowestSoFar = current;
            } else {
                double temp = Double.parseDouble(current.get("Humidity"));
                double lowestTemp = Double.parseDouble(lowestSoFar.get("Humidity"));  
                if(temp<lowestTemp) {
                    lowestSoFar = current;
                }  
            }
        }
        
        return lowestSoFar;
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        //average temperature recorded for a file
        double totalTemp = 0.0;
        int count = 0;
        for(CSVRecord current: parser) {
            totalTemp += Double.parseDouble(current.get("TemperatureF"));
            count++;
        }
        double avg = totalTemp/count;
        return avg;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        //average temperature for a file that has a largest humidity
        double totalTemp = 0.0;
        int count = 0;
        for(CSVRecord current: parser) {
            if(Integer.parseInt(current.get("Humidity")) >= value) {
                    totalTemp += Double.parseDouble(current.get("TemperatureF"));
                    count++;
            }
        }
        
        if(count==0) return 0.0;
        double avg = totalTemp/count;
        return avg;
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Lowest temp was " + lowest.get("TemperatureF") + " at " + lowest.get("DateUTC"));
    }
    
    public void testFileWithColdestTemperature() {
        String lowest = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + lowest);
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVRecord lowest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord lowest = lowestHumidityMultipleFiles();
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double avg = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("AVG temp in file is " + avg);
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double avg = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if(avg==0.0) System.out.println("No temperatures with that humidity");
        else System.out.println("AVG temp when High Humidity is " + avg);
    }
}
