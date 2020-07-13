
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings() {
        FourthRatings fr = new FourthRatings("data/ratings.csv");
        RaterDatabase.initialize("data/ratings.csv");
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        ArrayList<Rating> ratings = fr.getAverageRatings(1);
        Collections.sort(ratings);
        System.out.println("Found " + ratings.size() + " movies");
        
        for(Rating rt: ratings) {
            System.out.println("Rating - " + rt.getValue() + " Title - " + MovieDatabase.getTitle(rt.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        FourthRatings fr = new FourthRatings("data/ratings.csv");
        RaterDatabase.initialize("data/ratings.csv");
        
        YearAfterFilter yaf = new YearAfterFilter(1980);
        GenreFilter gf = new GenreFilter("Romance");
        AllFilters af = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(gf);        
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");    
        
        ArrayList<Rating> ratings = fr.getAverageRatingsByFilter(1, af);
        Collections.sort(ratings);
        System.out.println("Found " + ratings.size() + " movies");
        
        for(Rating rt: ratings) {
            System.out.println("Rating - " + rt.getValue() + " Year - " + MovieDatabase.getYear(rt.getItem()) + " Genre - " + MovieDatabase.getGenres(rt.getItem()) + " Title - " + MovieDatabase.getTitle(rt.getItem()));
        }    
    }
    
    
    
    public void printSimilarRatings() {
        FourthRatings fr = new FourthRatings("data/ratings.csv");
        RaterDatabase.initialize("data/ratings.csv");

        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");   
        
        ArrayList<Rating> ratings = fr.getSimilarRatings("71", 20, 5);
        
        for(Rating rt: ratings) {
            System.out.println("Rating - " + rt.getValue() + " Year - " + MovieDatabase.getYear(rt.getItem()) + " Genre - " + MovieDatabase.getGenres(rt.getItem()) + " Title - " + MovieDatabase.getTitle(rt.getItem()));
        }  
    }
    
    public void printSimilarRatingsByGenre() {
        FourthRatings fr = new FourthRatings("data/ratings.csv");
        RaterDatabase.initialize("data/ratings.csv");

        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");   
        
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("964", 20, 5, new GenreFilter("Mystery"));
        
        for(Rating rt: ratings) {
            System.out.println("Rating - " + rt.getValue() + " Year - " + MovieDatabase.getYear(rt.getItem()) + " Genre - " + MovieDatabase.getGenres(rt.getItem()) + " Title - " + MovieDatabase.getTitle(rt.getItem()));
        }  
    }
    
    public void printSimilarRatingsByDirector () {
        FourthRatings fr = new FourthRatings("data/ratings.csv");
        RaterDatabase.initialize("data/ratings.csv");

        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");   
        
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("120", 10, 2, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        
        for(Rating rt: ratings) {
            System.out.println("Rating - " + rt.getValue() + " Year - " + MovieDatabase.getYear(rt.getItem()) + " Genre - " + MovieDatabase.getGenres(rt.getItem()) + " Title - " + MovieDatabase.getTitle(rt.getItem()));
        }  
    }
    
    public void printSimilarRatingsByGenreAndMinutes () {
        FourthRatings fr = new FourthRatings("data/ratings.csv");
        RaterDatabase.initialize("data/ratings.csv");
        AllFilters f = new AllFilters();
        f.addFilter(new GenreFilter("Drama"));
        f.addFilter(new MinutesFilter(80,160));
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");   
        
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("168", 10, 3, f);
        
        for(Rating rt: ratings) {
            System.out.println("Rating - " + rt.getValue() + " Year - " + MovieDatabase.getYear(rt.getItem()) + " Genre - " + MovieDatabase.getGenres(rt.getItem()) + " Title - " + MovieDatabase.getTitle(rt.getItem()));
        }  
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes () {
        FourthRatings fr = new FourthRatings("data/ratings.csv");
        RaterDatabase.initialize("data/ratings.csv");
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(1975));
        f.addFilter(new MinutesFilter(70,200));
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");   
        
        ArrayList<Rating> ratings = fr.getSimilarRatingsByFilter("314", 10, 5, f);
        
        for(Rating rt: ratings) {
            System.out.println("Rating - " + rt.getValue() + " Year - " + MovieDatabase.getYear(rt.getItem()) + " Genre - " + MovieDatabase.getGenres(rt.getItem()) + " Title - " + MovieDatabase.getTitle(rt.getItem()));
        }  
    }
}
