
/**
 * Part 3: Problem Solving with Strings
 * 
 * @author (chris) 
 * @version (7/21/2023)
 */
public class Part3 {
    //
    public boolean twoOccurrences (String stringa, String stringb) {
    // return true if stringa occurs atleast twice in stringb
        int count = 0;
        int bLength =stringb.length();
        
        for (int i = 0; i < bLength; i++) {
            if (stringb.indexOf(stringa, i) > -1) {
                count++;
            }
        }
        
        if (count > 1) {
            return true;
        } else {
            return false;
        }
    }
    
    //
    public void testing () {
        System.out.println("a in banana"+ twoOccurrences("a", "banana ") + "\n"); // true
        System.out.println("an in banana"+ twoOccurrences("an", "banana ") + "\n"); // true
        System.out.println("c in banana"+ twoOccurrences("c", "banana ") + "\n"); // false
        
        System.out.println(("bro in broccoli is = ") + lastPart("bro", "broccoli"));
        System.out.println(("car in truck is = ") + lastPart("car", "truck"));
    }
    
    //
    public String lastPart (String stringa, String stringb) {
        // Find 1st occurs of stringa in stringb. Then return what follows it.
        // If no occurence of stringa, then return stringb.
        int aIndex = stringb.indexOf(stringa);
        int aLength = stringa.length();
        
        if (aIndex == -1) {
            System.out.println("no occurence");
            return stringb;
        } else {
            System.out.println("occurence");
            return stringb.substring(aIndex + aLength);
        }
        
    }
}

