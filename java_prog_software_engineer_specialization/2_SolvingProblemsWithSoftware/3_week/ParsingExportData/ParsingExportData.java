import edu.duke.*;
import org.apache.commons.csv.*;

/**
 * Parse a CSV file with country,export and value data.
 * 
 * @author chris
 * @version 7/26
 */
public class ParsingExportData {
    // Part 5
    public void bigExporters (CSVParser parser, String amount){
        // Print countries along with their amounts that are greater than the length of the invoked amount.
        String country = "";
        String a = "";
        int amountLength = amount.length();
        int aLength = 0;
        for (CSVRecord record : parser) {
            country = record.get("Country");
            a = record.get("Value (dollars)");
            aLength = a.length();
            if (aLength > amountLength){
                System.out.println(country + " " + a);
            }
            
        }        
    }
    
    // Part 4
    public int numberOfExporters (CSVParser parser, String exportItem) {
        // Return the # of countries that export a certain item.
        int count = 0;
        for (CSVRecord record : parser) {
            String item = record.get("Exports");
            if (item.contains(exportItem)) {
                count++;
            }
        }
        return count;
    }
    
    // Part 3
    public void listExportersTwoProducts (CSVParser parser, String exportItem1 , String exportItem2){
        // Prints the countries with certain exports.
        
        for (CSVRecord record : parser) {
            String item = record.get("Exports");
            if ( item.contains(exportItem1) && item.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    // Part 2
    public String countryInfo (CSVParser parser, String country) {
        // Return info about a country.
        String info = "";
        
        for (CSVRecord record : parser) {
            String getCountry = record.get("Country");
            if (getCountry.contains(country)) {
                info = info + getCountry + ": ";
                info = info + record.get("Exports") + ": ";
                info = info + record.get("Value (dollars)");
                return info;
            }
        }
        return "NOT FOUND";
    }
    
    // Part 1 : Test
    public void tester() {
            FileResource fr = new FileResource();
            CSVParser parser = fr.getCSVParser();
            String country = "Nauru";
            
            System.out.println("part 2");
            System.out.println(countryInfo(parser, country));
            
            System.out.println("part 3");
            parser = fr.getCSVParser();
            listExportersTwoProducts(parser, "cotton", "flowers");
            
            System.out.println("part 4");
            parser = fr.getCSVParser();
            System.out.println("Count= " + numberOfExporters(parser, "cocoa"));
            
            System.out.println("part 5");
            parser = fr.getCSVParser();
            bigExporters(parser, "$999,999,999,999");
    }
}
