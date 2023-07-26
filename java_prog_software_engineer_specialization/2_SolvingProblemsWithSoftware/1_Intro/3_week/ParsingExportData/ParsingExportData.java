import edu.duke.*;
import org.apache.commons.csv.*;

/**
 * Parse a CSV file with country,export and value data.
 * 
 * @author chris
 * @version 7/26
 */
public class ParsingExportData {
    
    public String countryInfo (CSVParser parser, String country) {
        // Return info about a country.
        String info = "";
        
        for (CSVRecord record : parser) {
            //System.out.println(record.get("Country"));
            String line = record.toString();
            if (line.contains(country)) {
                info = info + record.get("Country") + ": ";
                info = info + record.get("Exports") + ": ";
                info = info + record.get("Value (dollars)");
                return info;
            }
        }
        return "NOT FOUND";
    }
    
    // Test
    public void tester() {
            FileResource fr = new FileResource();
            CSVParser parser = fr.getCSVParser();
            String country = "Germany";
            
            System.out.println(countryInfo(parser, country));
            
    }
}
