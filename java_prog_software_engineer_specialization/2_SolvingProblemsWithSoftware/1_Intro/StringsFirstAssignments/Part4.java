
/**
 * Part 4: Finding Web Links
 * 
 * @author (chris) 
 * @version (7/21/2023)
 */

import edu.duke.*;
import java.io.File;

public class Part4 {
    //
    public void linkFinder(URLResource ur, String linkOfInterest) {
        String url = "";
        String lowerCasedS = "";
        int linkIndex, start, end;
        
        // for each line
        for (String s : ur.lines()) {
            // System.out.println(url);
            lowerCasedS = s.toLowerCase();
            linkIndex = lowerCasedS.indexOf(linkOfInterest);
            if (linkIndex != -1) {
                start = s.lastIndexOf("\"", linkIndex);
                end = s.indexOf("\"", linkIndex);
                url = s.substring(start, end + 1);
                System.out.println(url);
            }
        }
        
    }
    //
    public void test() {
        String linkOfInterest = "";
        String url = "";
        
        linkOfInterest = "youtube.com";
        url = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        
        URLResource ur = new URLResource(url);
        linkFinder(ur, linkOfInterest);
    }

}
