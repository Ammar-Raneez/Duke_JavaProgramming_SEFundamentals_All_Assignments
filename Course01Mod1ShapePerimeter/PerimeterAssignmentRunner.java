import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        System.out.println(totalPerim);
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        //returns the total number of points
        int numPoints = 0;
        for(Point point : s.getPoints()) {
            numPoints++;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        //average length of shape
        int points = getNumPoints(s);
        double distance = getPerimeter(s);
        double avgLength = distance/points;
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        //largest side of a shape --doesn't work for some reason
        double largestSide = 0;
        Point prevPoint = s.getLastPoint();
        for(Point point : s.getPoints()) {
            if(point.distance(prevPoint) > largestSide) {
                largestSide = point.distance(prevPoint);
            }
 
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        //largest x-coordinate
        double largestX = 0;
        Point prevPoint = s.getLastPoint();
        for(Point point : s.getPoints()) {
            if(point.getX() > largestX) {
                largestX = point.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        //largest perimeter outta a selected number of files
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double getPerimeter = getPerimeter(s);
            if(getPerimeter > largestPerimeter) {
                largestPerimeter = getPerimeter;
            }
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        //file name containing shape with largest perimeter
        DirectoryResource dr = new DirectoryResource();
        File temp = null;
        double largestPerimeter = 0;
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double getPerimeter = getPerimeter(s);
            if(getPerimeter > largestPerimeter) {
                temp = f;
                largestPerimeter = getPerimeter;
            }
        }
        System.out.println(largestPerimeter);
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int points = getNumPoints(s);
        double avgLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("Points = " + points);
        System.out.println("Largest Side = " + largestSide);
        System.out.println("largest X = " + largestX);
        System.out.println("AVG Length = " + avgLength);
        System.out.println("perimeter = " + length);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest Perimeter = " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        String largestPerimeterFile = getFileWithLargestPerimeter();
        System.out.println("largest Perimeter File = " + largestPerimeterFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
