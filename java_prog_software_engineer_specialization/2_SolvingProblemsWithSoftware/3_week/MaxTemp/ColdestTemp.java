import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
// Programming exercise: Parsing Weather Data
public class ColdestTemp {
    public void lowestHumidityInManyFiles(){
        // part 4. get row with lowest humidity amond many files
        
    }
    
    public CSVRecord lowestHumidityInFile (CSVParser parser){
        // part 3. return row with lowest humidity
        CSVRecord humidityRow = null;
        for (CSVRecord currentRow : parser) {
            if (humidityRow == null) {
                humidityRow = currentRow;
            }
            
            int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
            int lowestHumidity = Integer.parseInt(humidityRow.get("Humidity"));
            if (currentHumidity < lowestHumidity) {
                humidityRow = currentRow;
            }
        }
        
        return humidityRow;
    }
    
    public void testLowestHumidityInFile() {
        // part 3. test
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        
        System.out.println("\n    Lowest humidity= " + csv.get("Humidity"));
        System.out.println("date= " + csv.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature (){
        // part 2. return name of the file with the coldest temp
        String fileName = null;
        CSVRecord coldestRow = null;
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = coldestHourInFile(parser);
            
            coldestRow = coldestSoFar(currentRow, coldestRow);
            if (currentRow == coldestRow){
                fileName = f.getName();
            }
            
        }
        
        return fileName;
    }
    
    public void testFileWithColdestTemperature (){
        // part 2. test
        String fileName = fileWithColdestTemperature();
        
        int year = Integer.parseInt(fileName.substring(8, 12));
        String filePath = "nc_weather/" + year +"/" + fileName;
        FileResource fr = new FileResource(filePath);
        
        CSVRecord coldestRow = coldestHourInFile(fr.getCSVParser());
        
        System.out.println("File name=    " + fileName);
        System.out.println("Coldest temp=    " + coldestRow.get("TemperatureF"));
        System.out.println("All the temperatures:");
        for (CSVRecord record : fr.getCSVParser()){
            String date = record.get("DateUTC");
            String temp = record.get("TemperatureF");
            System.out.println(date + " || " + temp); 
        }
        // print temp of coldest day with coldestHourInFile
    }
    
    public CSVRecord coldestHourInFile (CSVParser parser){
        // part 1. return the row with coldest temperature
        CSVRecord coldestRow = null;
        
        for (CSVRecord currentRow : parser){
            coldestRow = coldestSoFar(currentRow, coldestRow);
        }
        return coldestRow;
    }
    
    public CSVRecord coldestSoFar(CSVRecord currentRow, CSVRecord coldestRow){
        // comparison b/w currentRow and coldestRow
        if (coldestRow == null){
            coldestRow = currentRow;
        }
        
        double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
        double coldestTemp = Double.parseDouble(coldestRow.get("TemperatureF"));
        
        if (currentTemp == -9999) {
            return coldestRow;
        }
        if (currentTemp < coldestTemp){
            coldestRow = currentRow;
        }
        
        return coldestRow;
    }
    
    public void testColdestHourInFile(){
        // part 1. test
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestRow = coldestHourInFile(parser);
        
        System.out.println("temp= " + coldestRow.get("TemperatureF"));
        System.out.println("date= " + coldestRow.get("DateUTC"));
        System.out.println("humidity= " + coldestRow.get("Humidity"));
    }
}
