
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movies = new ArrayList<>();
        FileResource fr = new FileResource(filename);
        
        for(CSVRecord re: fr.getCSVParser()) {
            Movie movie = new Movie(re.get("id"), re.get("title"), re.get("year"), re.get("genre"), 
            re.get("director"), re.get("country"), re.get("poster"), Integer.parseInt(re.get("minutes")));
            movies.add(movie);
        }
        
        return movies;
    }
    
    public void testLoadMovies() {
        ArrayList<Movie> allMovies = loadMovies("data/ratedmoviesfull.csv");
        Map<String, Integer> directorMovies = new HashMap<>();
        
        //for(Movie m: allMovies) System.out.println(m);
        
        //**comedy genre movies**//
        /**int genreCount = 0;
        for(Movie m: allMovies) {
            if(m.getGenres().contains("Comedy")) {
                System.out.println(m);
                genreCount++;
            }
        }
        System.out.println(genreCount);**/
        
        //**movies longer than 150 minutes**//
        /**int longerThanCount = 0;
        for(Movie m: allMovies) {
            if(m.getMinutes() > 150) {
                System.out.println(m);
                longerThanCount++;
            }
        }
        System.out.println(longerThanCount);**/
        
        //**directors and their movie counts**//
        /**for(Movie m: allMovies) {
            String dir = m.getDirector();
            if(!directorMovies.containsKey(dir)) {
                directorMovies.put(dir, 1);
            } else {
                directorMovies.put(dir, directorMovies.get(dir) + 1);
            }
        }**/
        
        //**Max movie count directed, and directors that directed that many**//
        /**int maxMovieCount = 0;
        for(String dir: directorMovies.keySet()) {
            if(directorMovies.get(dir) > maxMovieCount) maxMovieCount = directorMovies.get(dir); 
        }
        System.out.println("Max movie count " + maxMovieCount);
        for(String dir: directorMovies.keySet()) {
            if(directorMovies.get(dir) == maxMovieCount) {
                System.out.println(dir + " directed " + maxMovieCount);
            }
        }**/
    }
    
    //**load all the raters from csv file**//
    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> raters = new ArrayList<>();
        FileResource fr = new FileResource(filename);
        
        for(CSVRecord re: fr.getCSVParser()) {
            Rater r = new EfficientRater(re.get("rater_id"));
            boolean found = false;
            int indexFound = 0;
            
            //we check thru the entire arraylist of raters to see
            //whether theres a rater with the same id
            for(int i=0; i<raters.size(); i++){
                if(r.getID().equals(raters.get(i).getID()))  {
                    found = true;
                    indexFound = i;
                } 
            }
            
            //if we did not find any, we add a new rater with their corresponding rating and movie id they've rated
            if(!found) {
                r.addRating(re.get("movie_id"), Double.parseDouble(re.get("rating")));
                raters.add(r);
            //else we get that specific rater, update them and update the list of raters    
            } else {
                Rater rt = raters.get(indexFound);
                rt.addRating(re.get("movie_id"), Double.parseDouble(re.get("rating")));
                raters.set(indexFound, rt);
            }      
        }
  
        return raters;
    }
    
    public void testLoadRaters() {
        ArrayList<Rater> allRaters = loadRaters("data/ratings.csv");
        //for(Rater r: allRaters) System.out.println(r);
        //System.out.println(allRaters.size());
        
        //**all ratings for rater with id 193**//
        /**int rater_id = 193;
        for(Rater rater: allRaters) {
            if(rater.getID().equals(String.valueOf(rater_id))) {
                System.out.println(rater.getItemsRated());
                System.out.println(rater.getItemsRated().size());
            }
        }**/
        
        //**Max rating count by a rater**//
        /**int maxRatingCount = 0;
        for(Rater rater: allRaters) {
            if(rater.getItemsRated().size() > maxRatingCount) {
                maxRatingCount = rater.getItemsRated().size();
            }
        }
        System.out.println(maxRatingCount);
        for(Rater rater: allRaters) {
            if(rater.getItemsRated().size() == maxRatingCount) System.out.println(rater);
        }**/
        
        //**all raters that rated a movie**//
        /**String movieId = "1798709";
        int movieRatingCount = 0;
        for(Rater rater: allRaters) {
            if(rater.hasRating(movieId)) {
                System.out.println(rater);
                movieRatingCount++;
            }
        }
        System.out.println(movieRatingCount);**/
        
        //**all unique movies rated**//
        /**ArrayList<String> uniqueMovies = new ArrayList<>();
        for(Rater rater: allRaters) {
            for(String eachRating: rater.getItemsRated()) {
                if(!uniqueMovies.contains(eachRating)) {
                    uniqueMovies.add(eachRating);
                }
            }
        }
        System.out.println(uniqueMovies);
        System.out.println(uniqueMovies.size());**/
    }
}
