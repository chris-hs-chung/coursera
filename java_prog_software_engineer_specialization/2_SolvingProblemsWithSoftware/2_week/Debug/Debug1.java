
/**
 * Write a description of Debug1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Debug1 {
   public void findAbc(String input) {
        int index = input.indexOf("abc");
        System.out.println(input);
        
        while (true) {
            if (index == -1 || index >= input.length() - 3) {
                break;
            }
            
            // System.out.println((index+1) +" "+ (index+4) );
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            System.out.println("index " + index);
            index = input.indexOf("abc", index+3);
            System.out.println("index after updating " + index);
        }
   }
   
   
   public void test() {
       findAbc("abcabcabcabca");
       System.out.println("\n");
       findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
       System.out.println("\n");
       findAbc("abcbbbabcdddabc");
       System.out.println("\n");
       findAbc("yabcyabc");
       System.out.println("\n");
    }
}
