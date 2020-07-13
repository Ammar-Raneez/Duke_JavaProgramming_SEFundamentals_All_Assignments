/*
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {
    public FourthRatings() {
        // default constructor
        this("data/ratings.csv");
    }
    
    public FourthRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
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
        
        for(Rater r: RaterDatabase.getRaters()) {
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
    
    //returns total dot product of ratings between me and another rater
    private double dotProduct(Rater me, Rater r) {
        double product = 0.0;
        ArrayList<String> mIDs = me.getItemsRated();
        ArrayList<String> rIDs = r.getItemsRated();
        
        for(String mID: mIDs) {
            if(rIDs.contains(mID)) {
                product += (me.getRating(mID) - 5) * (r.getRating(mID) - 5);
            }
        }
        return product;
    }
    
    //similarities between id's rating and everything else
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> similar = new ArrayList<>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        
        //loop thru all the raters, for everyone obv except ourselves, we get similar dot product and add Rating obj w that dot product
        for(Rater rt: raters) {
            if(!rt.getID().equals(id)) {
                double product = dotProduct(me, rt);
                if(product >= 0) {
                    Rating rating = new Rating(rt.getID(), product);
                    similar.add(rating);
                }
            }
        }
        //sort in descending order
        Collections.sort(similar, Collections.reverseOrder());
        return similar;
    }
    
    //returns ratings of movies with their weighted average ratings
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> similarRatings = new ArrayList<>();
        ArrayList<Rating> similar = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for(String movie: movies) {
            double finalRating = 0.0;
            int count = 0;
            //top numSimilarRatings of similar list
            for(int i=0; i<numSimilarRaters; i++) {
                String raterId = similar.get(i).getItem();
                double similarityRating = similar.get(i).getValue();
                double movieGivenRating = 0.0;
                try{
                    movieGivenRating = RaterDatabase.getRater(raterId).getRating(movie);
                } catch(NullPointerException e){
                    continue;
                }
                
                //multiply rater rating and similarity rating for each rater
                //so a greater product means a closer similiarity
                finalRating += similarityRating * movieGivenRating;
                count++;
            }  
            //weighted avg is rating/ating count
            if(count >= minimalRaters) similarRatings.add(new Rating(movie, finalRating/count));
        }
        
        Collections.sort(similarRatings, Collections.reverseOrder());
        return similarRatings;
    }

    //same with a filter
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> similarRatings = new ArrayList<>();
        ArrayList<Rating> similar = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        for(String movie: movies) {
            double finalRating = 0.0;
            int count = 0;
            //top numSimilarRatings of similar list
            for(int i=0; i<numSimilarRaters; i++) {
                String raterId = similar.get(i).getItem();
                double similarityRating = similar.get(i).getValue();
                double movieGivenRating = 0.0;
                try{
                    movieGivenRating = RaterDatabase.getRater(raterId).getRating(movie);
                } catch(NullPointerException e){
                    continue;
                }
                //multiply rater rating and similarity rating for each rater
                //so a greater product means a closer similiarity
                finalRating += similarityRating * movieGivenRating;
                count++;
            }  
            //weighted avg is rating/ating count
            if(count >= minimalRaters) similarRatings.add(new Rating(movie, finalRating/count));
        }
        
        Collections.sort(similarRatings, Collections.reverseOrder());
        return similarRatings;
    }    
}