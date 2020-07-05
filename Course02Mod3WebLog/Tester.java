package WebLogProgram;


/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer analyzer = new LogAnalyzer();
        
        System.out.println("All Log Files ");
        analyzer.readFile("WebLogProgram/weblog2_log.txt");
        analyzer.printAll();
        System.out.println();
        
        int uniqueIp = analyzer.countUniqueIp();
        System.out.println("Number of unique IP addresses " + uniqueIp);
        System.out.println();
        
        System.out.println("\nLog Files With Status above 200 ");
        analyzer.printAllHigherNum(200);
        System.out.println();
        
        int uniqueVisits = analyzer.uniqueIpVisitsOnDay("Sep 27");
        System.out.println("Unique Visits on day sep 27 " + uniqueVisits);
        System.out.println();
        
        int uniqueWithinStatusRange = analyzer.countUniqueIPsInRange(400, 499);
        System.out.println("Unique Within Status range " + uniqueWithinStatusRange);
        System.out.println();
        
        HashMap<String, Integer> mapCounts = analyzer.countVisitsPerIP();
        System.out.println("count visits per ip " + mapCounts);
        System.out.println();  
        
        int mostNumberVisits = analyzer.mostNumberVisitsByIP(mapCounts);
        System.out.println("ip address most visit times " + mostNumberVisits);
        System.out.println(); 
        
        ArrayList<String> ipMostVisit = analyzer.IPsMostVisits(mapCounts);
        System.out.println("Unique ip addresses most visit " + ipMostVisit);
        System.out.println(); 
        
        HashMap<String, ArrayList<String>> mapDays = analyzer.IPsForDays();
        System.out.println("Unique day ip addresses visits " + mapDays);
        System.out.println();  
        
        String dayWithMostIPs = analyzer.dayWithMostIPVisits(mapDays);
        System.out.println("Day with most IP addresses " + dayWithMostIPs);
        System.out.println();  
        
        ArrayList<String> mostIPsOfADay = analyzer.IPsWithMostVisitsOnDay(mapDays, "Sep 29");
        System.out.println("IP Addresses with Most visit on a day " + mostIPsOfADay);
        System.out.println();  
    }
}
