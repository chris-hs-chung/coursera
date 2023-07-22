
/**
 * Simplified algorithm of finding a gene
 * 
 * chris
 * 7/21/2023
 */
public class Part1 {

    public String findSimpleGene (String dna) {
        // find index of "ATG". Else, return empty string.
        int startCodon = dna.indexOf("ATG");
        if (startCodon == -1) {
            return "";
        }
        
        // find index of "TAA". Else, return empty string.
        int stopCodon = dna.indexOf("TAA", startCodon);
        if (stopCodon == -1) {
            return "";
        }
        
        if ((stopCodon - startCodon) % 3 == 0) {
            // Return the gene
            return dna.substring(startCodon, stopCodon +3);
        } else {
            return "";
        }
    }
    
    public void testSimpleGene () {
        String a = "ATATATGGGGGGAAATAAGGGGAAA";
        System.out.println("Test a");
        System.out.println(a);
        System.out.println(findSimpleGene(a));
        
        String b = "ATATATAGTAA";
        System.out.println("Test b");
        System.out.println(b);
        System.out.println(findSimpleGene(b));
        
        String c = "GATATGAAGT";
        System.out.println("Test c");
        System.out.println(c);
        System.out.println(findSimpleGene(c));
        
        String d = "ATATAGATAT";
        System.out.println("Test d");
        System.out.println(d);
        System.out.println(findSimpleGene(d));
        
        String e = "GTGTATGAAAGTTTAAAGTT";
        System.out.println("Test e");
        System.out.println(e);
        System.out.println(findSimpleGene(e));
    }
}