
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
*/
import java.util.*;

public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> items = new ArrayList<>();
        ArrayList<String> allMovies = MovieDatabase.filterBy(new TrueFilter());
        
        //pick a random movie from the list of movies
        Random randomIndex = new Random();
        
        //for 15 movies
        for(int i=0; i<15; i++) {
            int randIndex = randomIndex.nextInt(allMovies.size());
            
            String movieID = allMovies.get(randIndex);
            items.add(movieID);
            
            //remove picked item to prevent movie duplicates
            allMovies.remove(randIndex);
        }
     
        return items;
    }
    
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("data/ratings.csv");
        
        ArrayList<String> moviesRatedIDs = getItemsToRate();
        try {
            //recomendations for 100 similar raters with a minimum amount of 10 ratings
            ArrayList<Rating> similarRatings = fr.getSimilarRatings(webRaterID, 100, 10);
            //a counter to display only 10 movies, instead of em all
            int count = 0;
            
            //display table
            System.out.println("<html><body><table style='width: 100%; border: 1px solid black'>");
            System.out.println("<tr>");
            System.out.println("<th style='width: 40%; border: 1px solid black'>T I T L E</th>");
            System.out.println("<th style='width: 25%; border: 1px solid black'>G E N R E S</th>");
            System.out.println("<th style='width: 25%; border: 1px solid black'>P R O D U C T I O N</th>");
            System.out.println("<th style='width: 10%; border: 1px solid black'>D U R A T I O N</th>");
            System.out.println("</tr>");
            for(Rating r: similarRatings) {
                if(count >= 10) break;
                count++;
                //only movies that haven't been rated yet
                if(!moviesRatedIDs.contains(r.getItem())) {
                        System.out.println("<tr>");
                            System.out.println("<th style='width: 40%; border: 1px solid black'>" + MovieDatabase.getTitle(r.getItem()) + "</th>");
                            System.out.println("<td style='width: 25%; border: 1px solid black'>" + MovieDatabase.getGenres(r.getItem()) + "</td>");
                            System.out.println("<td style='width: 25%; border: 1px solid black'>" + MovieDatabase.getYear(r.getItem()) + "</td>");
                            System.out.println("<td style='width: 10%; border: 1px solid black'>" + MovieDatabase.getMinutes(r.getItem()) + "</td>");
                        System.out.println("</tr>");
                }
            }
            System.out.println("</table></body></html>");
        } catch(Exception e) {
            System.out.println("No Movies to Recommend");
        }
    }
    
    public void testRecommendations() {
        printRecommendationsFor("100");
    }
}
