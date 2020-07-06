/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 **/

import java.util.*;
import edu.duke.*;

public class LogAnalyzer{
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }

    //reads file through file resource, converts each line into a LogEntry object
    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for(String line: fr.lines()) {
            records.add(WebLogParser.parseEntry(line));
        }
    }

    //returns total unique ip addresses in our log entries
    public int countUniqueIp() {
        ArrayList<String> unique = new ArrayList<>();
        for(LogEntry le: records) {
            String ipAddress = le.getIpAddress();
            if(!unique.contains(ipAddress)) {
                unique.add(ipAddress);
            }
        }
        return unique.size();
        
        //*this works cuz the hashmap will only have 1 key for each ip address, therefore having only unique ones*//
        //HashMap<String, Integer> counts = countVisitsPerIP();
        //return counts.size();     
    }

    //prints log entries that have the status code equal to input
    public void printAllHigherNum(int num){
        ArrayList<LogEntry> higherStatus = new ArrayList<>();
        for(LogEntry ie: records) {
            int statusCode = ie.getStatusCode();
            if(statusCode > num) higherStatus.add(ie);
        }
        for(LogEntry ie: higherStatus) System.out.println(ie);
    }

    //returns number of unique visits per day depending on different ip's
    public int uniqueIpVisitsOnDay(String someday) {
        ArrayList<String> unique = new ArrayList<>();
        
        for(LogEntry ie: records) {
            String ip = ie.getIpAddress();
            String accessTime = ie.getAccessTime().toString();
            if(accessTime.indexOf(someday) != -1) {
                if(!unique.contains(ip)) unique.add(ip);
            }
        }
        return unique.size();
    }
    
    //return number of unique ip addresses with status code within a range
    public int countUniqueIPsInRange(int start, int end) {
        ArrayList<String> unique = new ArrayList<>();
        
        for(LogEntry ie: records) {
            String ip = ie.getIpAddress();
            if(!unique.contains(ip)) {
                if(ie.getStatusCode() >= start && ie.getStatusCode() <= end) {
                    unique.add(ip);
                }
            }
        }
        return unique.size();
    }
    
    //returns a hashmap that holds the amount of times a specific ip visits
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> map = new HashMap<>();
        
        for(LogEntry le: records) {
            String ip = le.getIpAddress();
            if(!map.keySet().contains(ip)) {
                map.put(ip, 1);
            } else {
                map.put(ip, map.get(ip)+1);
            }
        }
        
        return map;
    }
    
     /**
      * Helper method.
      * Maps an IP address to the number of times that IP address appears in 
      * the web log file, but for a given day only.
      * @param	day	day in "MMM DD" format, limiting output
      * @return map of IP to visit counts for a given day
      */
     private HashMap<String, Integer> countVisitsPerIP(String day) {
    	 HashMap<String, Integer> map = new HashMap<String, Integer>();
    	 
    	 for (LogEntry le : records) {
    		 if (!le.getAccessTime().toString().substring(4, 10).equals(day)) continue; 
    		 String ip = le.getIpAddress();
    		 if (!map.keySet().contains(ip)) map.put(ip, 1);
    		 else map.put(ip, map.get(ip)+1);
    	 }
    	 return map;
     }

    //gets most visits by an ip address
    public int mostNumberVisitsByIP(HashMap<String, Integer> map) {
        int maxCount = 0;
        
        for(int count: map.values()) {
            if(count>maxCount) maxCount = count;
        }
        return maxCount;
    }
    
    //gets an arraylist of ip addresses that have visited the most times
    public ArrayList<String> IPsMostVisits(HashMap<String, Integer> map){
        ArrayList<String> IPs = new ArrayList<>();
        int mostVisits = mostNumberVisitsByIP(map);
        
        for(String string: map.keySet()) {
            if(map.get(string) == mostVisits) {
                IPs.add(string);
            }
        }
        return IPs;
    }
    
    //returns each date mapped to all ip addresses that visited, considering duplicates
    public HashMap<String, ArrayList<String>> IPsForDays() {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        
        for(LogEntry le: records) {
            String date = le.getAccessTime().toString().substring(4, 10);
            String ip = le.getIpAddress();
            
            if(!map.keySet().contains(date)) {
               ArrayList<String> list = new ArrayList<>();
               list.add(ip);
               map.put(date, list); 
            } else {
                ArrayList<String> list = map.get(date);
                list.add(ip);
                map.put(date, list);
            }
        }
        return map;
    }
    
    //returns the day at which most ip addresses have visited
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
        map = IPsForDays();
        String maxDay = "";
        int maxCount = 0;
        
        for(String string: map.keySet()) {
            if(map.get(string).size() > maxCount) {
                maxCount = map.get(string).size();
                maxDay = string;
            }
        }
        return maxDay;
    }
    
    //returns list of ip addresses that visited the most at a particular day
    public ArrayList<String> IPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day) {
    	 ArrayList<String> list = new ArrayList<String>();
    	 HashMap<String, Integer> visits = countVisitsPerIP(day);
    	 
    	 int maxCount = 0;
    	 for (int count : visits.values()) 
    		 if (count > maxCount) maxCount = count;
    	 
    	 for (String ip : visits.keySet())
    		 if (visits.get(ip) == maxCount) list.add(ip);
    	 
    	 return list;
    }
    
    //prints all log entries
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
}
