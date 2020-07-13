
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        SecondRatings sr = new SecondRatings();
        
        System.out.println(sr.getMovieSize());
        System.out.println(sr.getRaterSize());
        
        ArrayList<Rating> ratings = sr.getAverageRatings(12);
        Collections.sort(ratings);
        for(Rating rt: ratings) {
            System.out.println(rt.getValue() + " " + sr.getTitle(rt.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie() {
        SecondRatings sr = new SecondRatings();
        
        String movie = "The Maze Runner";
        String id = sr.getID(movie);
        double avgRating = sr.getAverageByID(id, 3);
        System.out.println(movie + " " + avgRating);
    }
}
