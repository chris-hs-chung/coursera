
/**
 * Part 2: Finding a Gene - Using the Simplified Algorithm Reorganized

 * 
 * @author (chris) 
 * @version (7/21/2023)
 */
public class Part2 {

    public String findSimpleGene (String dna, String startCodon, String stopCodon) {
        // check if dna is lower case
        if (dna.charAt(0) > 96) {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        
        // find index of "ATG". Else, return empty string.
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) {
            return "";
        }
        
        // find index of "TAA". Else, return empty string.
        int stopIndex = dna.indexOf(stopCodon, startIndex);
        if (stopIndex == -1) {
            return "";
        }
        
        String gene;
        if ((stopIndex - startIndex) % 3 == 0) {
            gene = dna.substring(startIndex, stopIndex +3);
            return gene;
        } else {
            return "";
        }
    }
    
    public void testSimpleGene () {
        String startCodon = "ATG";
        String stopCodon = "TAA";
        
        String a = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("Test a");
        System.out.println(a);
        System.out.println(findSimpleGene(a, startCodon, stopCodon));
        
        String b = "ATATATAGTAA";
        System.out.println("Test b");
        System.out.println(b);
        System.out.println(findSimpleGene(b, startCodon, stopCodon));
        
        String c = "GATATGAAGT";
        System.out.println("Test c");
        System.out.println(c);
        System.out.println(findSimpleGene(c, startCodon, stopCodon));
        
        String d = "ATATAGATAT";
        System.out.println("Test d");
        System.out.println(d);
        System.out.println(findSimpleGene(d, startCodon, stopCodon));
        
        String e = "GTGTATGAAAGTTTAAAGTT";
        System.out.println("Test e");
        System.out.println(e);
        System.out.println(findSimpleGene(e, startCodon, stopCodon));
        
        String f = "agtatatggggggataaggg";
        System.out.println("Test f");
        System.out.println(f);
        System.out.println(findSimpleGene(f, startCodon, stopCodon));
    }
}
