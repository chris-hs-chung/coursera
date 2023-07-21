
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
    }
    
    //
    public String lastPart (String stringa, String stringb) {
        //
    }
}

