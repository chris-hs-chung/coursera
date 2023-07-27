import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class MaxTemp {
    public CSVRecord hottestHourInDay (CSVParser parser){
        // returns the CSV row with the hottest temp from a single file.
        CSVRecord hottestRow = null;
        
        for (CSVRecord currentRow : parser){
            hottestRow = getLargestOfTwo(currentRow,hottestRow);
        }
        return hottestRow;
    }
    
    public CSVRecord hottestInManyDays(){
        // return a CSVRecord row with the hottest temp amoung multiple rows
        DirectoryResource dr = new DirectoryResource();
        CSVRecord hottestRow = null;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            
            CSVRecord currentRow = hottestHourInDay(parser);
            hottestRow = getLargestOfTwo(currentRow, hottestRow);

        }
        return hottestRow;
    }
    
    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord hottestRow){
        // Compare 2 rows, and return the highest
        if (hottestRow == null) {
            hottestRow = currentRow;
        }
        double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
        double hottestTemp = Double.parseDouble(hottestRow.get("TemperatureF"));
        if (currentTemp > hottestTemp) {
            hottestRow = currentRow;
        }
        return hottestRow;
    }
    
    public void testHottestManyDays(){
        // test hottestInManyDays
        CSVRecord hottestRow = hottestInManyDays();
        System.out.println("Hottest day among many files= " + hottestRow.get("DateUTC"));
        // System.out.println( "time= " + hottestRow.get("TimeEST"));
        System.out.println( "temp= " + hottestRow.get("TemperatureF") + " F \n");
    }
}
