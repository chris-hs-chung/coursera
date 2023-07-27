
/**
 * Part 1 of assignment
 * 
 * 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    // findStopCodon
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        // The stop codon of interest is stopCodon (TAA, TAG, or TGA).
        // Return the index of a valid stop codon. The gene has to be a multiple of three.

        int currIndex = dna.indexOf(stopCodon, startIndex);

        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 3);
            }
        }

         return dna.length();
    }
    
    // find a gene
    public String findGene (String dna) {
        // Find first occurence of ATG.
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        
        // stop codons = TAA, TAG, TGA
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        
        // find lowest valid stop codon index
        int temp = Math.min(taaIndex, tagIndex);
        int minIndex = Math.min(temp, tgaIndex);
        
        if (minIndex != dna.length()) {
            return dna.substring(startIndex, minIndex + 3);
        }
        
        return "";
    }
    
    // test findGene
    public void testFindGene () {
        String dna = "ATGTAA";
        int startIndex = dna.indexOf("ATG");
        String gene = findGene(dna);
        System.out.println("dna= " + dna);
        System.out.println("gene= " + gene);
        System.out.println("* * *");
        
        dna = "ATGzzTAA";
        startIndex = dna.indexOf("ATG");
        gene = findGene(dna);
        System.out.println("dna= " + dna);
        System.out.println("gene= " + gene);
        System.out.println("* * *");
        
        dna = "zzzATGzzzTAAzz";
        startIndex = dna.indexOf("ATG");
        gene = findGene(dna);
        System.out.println("dna= " + dna);
        System.out.println("gene= " + gene);
        System.out.println("* * *");
        
        dna = "zzzAGzzzTAAzz";
        startIndex = dna.indexOf("ATG");
        gene = findGene(dna);
        System.out.println("dna= " + dna);
        System.out.println("gene= " + gene);
        System.out.println("* * *");
        
        dna = "zzzATGzzTAAzZzzTGA";
        startIndex = dna.indexOf("ATG");
        gene = findGene(dna);
        System.out.println("dna= " + dna);
        System.out.println("gene= " + gene);
        System.out.println("* * *");

    }
}
