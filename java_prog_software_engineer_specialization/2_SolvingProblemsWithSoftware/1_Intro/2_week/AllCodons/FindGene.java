
/**
 * Part 1
 * A simple version of finding a gene in a dna sequence.
 * 
 * @author chris
 * @version 7/22/2023
 */
import edu.duke.*;

public class FindGene {
    public String findGene (String dna) {
        // Find a valid gene that starts with ATG and ends with TAA.
        // The length must be a multiple of three.

        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }

        int currIndex = dna.indexOf("TAA", startIndex + 3);

        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return dna.substring(startIndex, currIndex + 3);
            } else {
                currIndex = dna.indexOf("TAA", currIndex + 1);
            }
        }
        
        return "";
    }

    public void testGene() {
        String dna;
        dna = "ATGTAA";
        System.out.println("DNA= " + dna);
        System.out.println("Gene= " + findGene(dna) + "\n");

        dna = "ATGZTAA";
        System.out.println("DNA= " + dna);
        System.out.println("Gene= " + findGene(dna) + "\n");

        dna = "ATGTATA";
        System.out.println("DNA= " + dna);
        System.out.println("Gene= " + findGene(dna) + "\n");

        dna = "ATGZZTAA";
        System.out.println("DNA= " + dna);
        System.out.println("Gene= " + findGene(dna) + "\n");

        dna = "ZATGAAAGGGTTTTAA";
        System.out.println("DNA= " + dna);
        System.out.println("Gene= " + findGene(dna) + "\n");
    }
}
