import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        double totalPerim = 0.0;            // Put code here
        Point prevPt = s.getLastPoint();

        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }

        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // part 1
        int count = 0;
        for (Point pt : s.getPoints()) {
            count = count + 1;
        }
        return count;       // return the number of points in Shape s.
    }

    public double getAverageLength(Shape s) {
        // part 1
        double a = getPerimeter(s);
        double b = getNumPoints(s);
        
        return a / b;     // return the average side length.
    }

    public double getLargestSide(Shape s) {
        // part 2
        double largest = 0;
        Point prevPt = s.getLastPoint();
        
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > largest) {
                largest = currDist;
            }
            prevPt = currPt;
        }
        
        return largest;     // return longest side of Shape.
    }

    public double getLargestX(Shape s) {
        // part 2
        double largestX = 0;
        
        for (Point pt : s.getPoints()) {
            int a = pt.getX();
            if (a > largestX) {
                largestX = a;
            }
        }
        return largestX;     // return largest x value out of all the points.
    }

    public double getLargestPerimeterMultipleFiles() {
        // Part 3 
        double largestPerimeter = 0.0;
       
        DirectoryResource dr = new DirectoryResource(); // select files from dialog box
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape a = new Shape(fr);
            double perm = getPerimeter(a);
            if (perm > largestPerimeter) {
                largestPerimeter = perm;
            }
        }
        
        return largestPerimeter;     // return the largest permiter out of the selected files.
    }

    public String getFileWithLargestPerimeter() {
        // Part 3
        File temp = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape a = new Shape(fr);
            double perm = getPerimeter(a);
            if (perm > largestPerimeter) {
                largestPerimeter = perm;
                temp = f;
            }
        }
        
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();  
        Shape s = new Shape(fr);    // new Shape s object
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        
        // part 1 of assignment
        int numPoints = getNumPoints(s);
        System.out.println("Number of points= " + numPoints);
        double avgSideLength = getAverageLength(s);
        System.out.println("Average side length= " + avgSideLength);
        
        // part 2
        double largestSide = getLargestSide(s);
        System.out.println("Longest side= " + largestSide);
        double largestX = getLargestX(s);
        System.out.println("Largest X value= " + largestX + "\n");
    }
    
    public void testPerimeterMultipleFiles() {
        // Part 3
        double a = getLargestPerimeterMultipleFiles();
        System.out.println("test: Largest perimeter= " + a);
    }

    public void testFileWithLargestPerimeter() {
        // Part 3
        String a = getFileWithLargestPerimeter();
        System.out.println("test: file with largest perm= " + a);
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
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
