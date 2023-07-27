import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

/**
 * A prorgram to find the hottest temp from CSV files
 */ 
 */

public class MaxTemp {
    public CSVRecord hottestHourInDay (CSVParser parser){
        // returns the CSV row with the hottest temp from a single file.
        CSVRecord hottestRow = null;
        double currentTemp = 0.0;
        double hottestTemp = 0.0;
        
        for (CSVRecord currentRow : parser){
            if (hottestRow == null){ 
                hottestRow = currentRow;
            }
            currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            hottestTemp = Double.parseDouble(hottestRow.get("TemperatureF"));
            
            if (currentTemp > hottestTemp){
                hottestRow = currentRow;
            }
        }
        return hottestRow;
    }
    
    public void testHottestHourDay(){
        // test hottestHourInDay
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord hottestRow = hottestHourInDay(parser);
        System.out.println( "The hottest day= " + hottestRow.get("DateUTC"));
        System.out.println( "time= " + hottestRow.get("TimeEST"));
        System.out.println( "temp= " + hottestRow.get("TemperatureF") + " F");
        System.out.println( "humidity= " + hottestRow.get("Humidity"));
        System.out.println( "conditions= " + hottestRow.get("Conditions"));
    }
    
    
}
