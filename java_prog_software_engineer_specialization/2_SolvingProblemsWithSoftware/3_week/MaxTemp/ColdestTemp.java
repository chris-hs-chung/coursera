import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
// Programming exercise: Parsing Weather Data
public class ColdestTemp {
    public void test_Ave_Temp_High_Humidity_InFile(){
        // part 6. test averageTempWithHighHumidityInFile
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        double average = averageTempWithHighHumidityInFile(parser, value);
        
        if (average == -1.0) {
            System.out.println("No temperatures with that humidity");
        }
        System.out.println("\n Average with high humidity= " + average + "\n");
    }
    
    public double averageTempWithHighHumidityInFile(CSVParser parser, int value){
        // part 6. return average temp for humidities greater or equal to value.
        double sum = 0.0;
        int count = 0;
        int humidity = 0;
        
        for (CSVRecord currentRow : parser){
            humidity = Integer.parseInt(currentRow.get("Humidity"));
            if (humidity >= value){
                sum += Double.parseDouble(currentRow.get("TemperatureF"));
                count++;
            }
        }
        if (count == 0) {
            return -1;
        } else {
            return sum/count;
        }
    }
    
    public void test_Ave_Temp_In_File(){
        // part 5. test averageTemperatureInFile
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.println("\n Average= " + average + "\n");
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        // part 5. return the average temperature in a file
        double sum = 0.0;
        int count = 0;
        
        for (CSVRecord currentRow : parser){
            sum += Double.parseDouble(currentRow.get("TemperatureF"));
            count++;
        }
        return sum/count;
    }
    
    public void test_Humidity_Many_Files() {
        // part 4. test the humidity among many files
        CSVRecord humidityRow = lowestHumidityInManyFiles();
        System.out.println("\nLowest humidity in the year= " + humidityRow.get("Humidity"));
        System.out.println("date= " + humidityRow.get("DateUTC"));
        System.out.println("time= " + humidityRow.get("TimeEST") + "\n");
    }
    
    public CSVRecord lowestHumiditySoFar(CSVRecord currentRow, CSVRecord humidityRow){
        // comparison of humidities
        if (humidityRow == null) {
            humidityRow = currentRow;
        }
        System.out.println(currentRow.get("DateUTC"));
        System.out.println(currentRow.get("Humidity"));
        if (currentRow.get("Humidity").equals("N/A")) {
            return humidityRow;
        }
        int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
        int lowestHumidity = Integer.parseInt(humidityRow.get("Humidity"));
        if (currentHumidity < lowestHumidity) {
            humidityRow = currentRow;
        }
        return humidityRow;
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        // part 4. get row with lowest humidity amond many files
        CSVRecord humidityRow = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f.getName());
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRow = lowestHumidityInFile(parser);
            humidityRow = lowestHumiditySoFar(currentRow, humidityRow);
            
        }
        return humidityRow;
    }
    
    public CSVRecord lowestHumidityInFile (CSVParser parser){
        // part 3. return row with lowest humidity
        CSVRecord humidityRow = null;
        for (CSVRecord currentRow : parser) {
            humidityRow = lowestHumiditySoFar(currentRow, humidityRow);
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
