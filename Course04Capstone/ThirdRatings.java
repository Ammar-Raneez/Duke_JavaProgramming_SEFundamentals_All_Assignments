/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("data/ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    //returns list of Rating's that have an avg rating > minimalRating
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> avgRate = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for(String movieId: movies) {
            double average = getAverageByID(movieId, minimalRaters);
            
            if(average != 0.0) {
                Rating rating = new Rating(movieId, average);
                avgRate.add(rating);
            }
        }
        
        return avgRate;
    }
    
    //returns average rating for a movie id, if it has sufficient amount of ratings
    public double getAverageByID(String id, int minimalRatings) {
        double rating = 0.0;
        int countForId = 0;
        
        for(Rater r: myRaters) {
            if(r.getItemsRated().contains(id)) {
                rating += r.getRating(id);
                countForId++;
            }
        }
        
        if(countForId >= minimalRatings) return rating/countForId;
        return 0.0;
    }
    
    //returns a list of Rating objects for each movie, with the avg rating for the movie (calculated by each individual rating for that movie)
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> ratings = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        for(String id: movies) {
            double avg = getAverageByID(id, minimalRaters);
            if(avg != 0.0) {
                Rating rating = new Rating(id, avg);
                ratings.add(rating);
            }
        }
        
        return ratings;
    }
}