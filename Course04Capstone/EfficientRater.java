
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientRater implements Rater{
    private String myID;
    private Map<String, Rating> movieMap;   //id:rating

    public EfficientRater(String id) {
        myID = id;
        movieMap = new HashMap<>();
    }

    public void addRating(String item, double rating) {
        movieMap.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        return movieMap.containsKey(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        return movieMap.get(item).getValue();
    }

    public int numRatings() {
        return movieMap.size();
    }

    public ArrayList<String> getItemsRated() {
        return new ArrayList<>(movieMap.keySet());
    }
    
    public String toString() {
        return "Id - " + myID + ", rating - " + movieMap;
    }
}
