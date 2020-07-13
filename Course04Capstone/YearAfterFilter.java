//returns all movies that had been created after/ on a specified year
public class YearAfterFilter implements Filter {
    private int myYear;

    public YearAfterFilter(int year) {
        myYear = year;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getYear(id) >= myYear;
    }

}
