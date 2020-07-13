
/**
 * Write a description of MovieRunnerWithFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilter {
    public void printAverageRatings() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        
        System.out.println("Read data for " + sr.getRaterSize() + " raters");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        ArrayList<Rating> ratings = sr.getAverageRatings(35);
        Collections.sort(ratings);
        System.out.println("Found " + ratings.size() + " movies");
        
        for(Rating rt: ratings) {
            System.out.println("Rating - " + rt.getValue() + " Title - " + MovieDatabase.getTitle(rt.getItem()));
        }
    }
    
    public void printAverageRatingsByYear() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        YearAfterFilter yearFilter = new YearAfterFilter(2000);
        
        System.out.println("Read data for " + sr.getRaterSize() + " raters");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(20, yearFilter);
        Collections.sort(ratings);
        System.out.println("Found " + ratings.size() + " movies");
        
        for(Rating rt: ratings) {
            System.out.println("Rating - " + rt.getValue() + " Year - " + MovieDatabase.getYear(rt.getItem()) + " Title - " +  MovieDatabase.getTitle(rt.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        GenreFilter genreFilter = new GenreFilter("Comedy");
        
        System.out.println("Read data for " + sr.getRaterSize() + " raters");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");       
        
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(20, genreFilter);
        Collections.sort(ratings);
        System.out.println("Found " + ratings.size() + " movies");
        
        for(Rating rt: ratings) {
            System.out.println("Rating - " + rt.getValue() + " Genres - " + MovieDatabase.getGenres(rt.getItem()) + " Title - " + MovieDatabase.getTitle(rt.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        MinutesFilter minFilter = new MinutesFilter(105, 135);
        
        System.out.println("Read data for " + sr.getRaterSize() + " raters");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");       
        
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(5, minFilter);
        Collections.sort(ratings);
        System.out.println("Found " + ratings.size() + " movies");
        
        /*for(Rating rt: ratings) {
            System.out.println("Rating - " + rt.getValue() + " Minutes - " + MovieDatabase.getMinutes(rt.getItem()) + " Title - " + MovieDatabase.getTitle(rt.getItem()));
        }*/
    }
    
    public void printAverageRatingsByDirectors() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        DirectorsFilter dirFilter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        
        System.out.println("Read data for " + sr.getRaterSize() + " raters");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");       
        
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(4, dirFilter);
        Collections.sort(ratings);
        System.out.println("Found " + ratings.size() + " movies");
        
        /*for(Rating rt: ratings) {
            System.out.println("Rating - " + rt.getValue() + " Directors - " + MovieDatabase.getDirector(rt.getItem()) + " Title -" + MovieDatabase.getTitle(rt.getItem()));
        }*/    
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        YearAfterFilter yaf = new YearAfterFilter(1990);
        GenreFilter gf = new GenreFilter("Drama");
        AllFilters af = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(gf);        
        
        System.out.println("Read data for " + sr.getRaterSize() + " raters");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");    
        
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(8, af);
        Collections.sort(ratings);
        System.out.println("Found " + ratings.size() + " movies");
        
        /*for(Rating rt: ratings) {
            System.out.println("Rating - " + rt.getValue() + " Year - " + MovieDatabase.getYear(rt.getItem()) + " Genre - " + MovieDatabase.getGenres(rt.getItem()) + " Title - " + MovieDatabase.getTitle(rt.getItem()));
        }*/    
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings sr = new ThirdRatings("data/ratings.csv");
        MinutesFilter mf = new MinutesFilter(90, 180);
        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        AllFilters af = new AllFilters();
        af.addFilter(mf);
        af.addFilter(df);        
        
        System.out.println("Read data for " + sr.getRaterSize() + " raters");
        MovieDatabase.initialize("data/ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");    
        
        ArrayList<Rating> ratings = sr.getAverageRatingsByFilter(3, af);
        Collections.sort(ratings);
        System.out.println("Found " + ratings.size() + " movies");
        
        /*for(Rating rt: ratings) {
            System.out.println("Rating - " + rt.getValue() + " Minutes - " + MovieDatabase.getMinutes(rt.getItem()) + " Directors - " + MovieDatabase.getDirector(rt.getItem()) + " Title - " + MovieDatabase.getTitle(rt.getItem()));
        }*/    
    }    
}
