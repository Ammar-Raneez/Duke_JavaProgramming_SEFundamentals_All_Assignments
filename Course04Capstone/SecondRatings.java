/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    //returns list of Rating's that have a minimal number of ratings >= minimalRaters
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> avgRate = new ArrayList<>();
        
        for(Movie m: myMovies) {
            String movieId = m.getID();
            double average = getAverageByID(movieId, minimalRaters);
            
            if(average != 0.0) {
                Rating rating = new Rating(movieId, average);
                avgRate.add(rating);
            }
        }
        System.out.println(avgRate.size());
        
        return avgRate;
    }
    
    //returns title of movie on passed movie id
    public String getTitle(String id) {
        for(Movie m: myMovies) {
            if(m.getID().equals(id)) {
                return m.getTitle();
            }
        }
        return "ID was not found";
    }
    
    //returns movie id on passed title
    public String getID(String title) {
        for(Movie m: myMovies) {
            if(m.getTitle().equals(title)) {
                return m.getID();
            }
        }
        return "Title was not found";
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
}