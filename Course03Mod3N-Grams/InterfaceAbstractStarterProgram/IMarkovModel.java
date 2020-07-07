
/**
 * Write a description of interface IMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

//interface impemented by the abstract class to define the common methods
public interface IMarkovModel {
    public void setTraining(String text);
    public void setRandom(int seed);
    public String getRandomText(int numChars);
    
}
