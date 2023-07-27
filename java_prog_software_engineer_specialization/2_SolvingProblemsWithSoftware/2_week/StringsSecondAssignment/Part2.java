
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {

    public int howMany (String stringa, String stringb) {
        // return how many times stringa appears in stringb
        // no overlaps
        int count = 0;
        int aLength = stringa.length();
        int aIndex = stringb.indexOf(stringa);
        if (aIndex == -1) { return 0; }    // check if stringa occurs.
        
        while (aIndex != -1) {
            count = count +1;
            aIndex = stringb.indexOf(stringa, aIndex + aLength);
        }
        return count;
    }
    
    public void testHowMany () {
        String stringa = "aa";
        String stringb = "aaabaaab";
        System.out.println("count =" + howMany(stringa, stringb));
        
        stringa = "ab";
        stringb = "aaabaaab";
        System.out.println("count =" + howMany(stringa, stringb));        
        
        stringa = "aaa";
        stringb = "aaabaaabaaacaaaaac";
        System.out.println("count =" + howMany(stringa, stringb));            
    }
}
