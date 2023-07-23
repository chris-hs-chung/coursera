
/**
 * Part 3
 * Find gene with different stop codons.
 * And adding short circuit evaluations if there are no valid stop codons.
 * In the findStopCodon, change return to -1, not dna.length()
 * 
 * @author (chris) 
 * @version 7/26/2023
 */
public class AllCodons {
    //
    //
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        // Return index of the stop codon.

        int currIndex = dna.indexOf(stopCodon, startIndex + 3);

        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }

        return -1;
    }
    //
    //
    public String findGene (String dna) {
        // Find a valid gene that starts with ATG and ends with TAA.
        // The length must be a multiple of three.

        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        // int temp = Math.min(taaIndex, tagIndex);
        // int minIndex = Math.min(temp, tgaIndex); This can't be used because -1 means there is no stop codon.

        // Find the minIndex
        int minIndex == -1;
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }

        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }

        // If there is no stop codon, then return empty string. Else, return gene.
        if (minIndex == -1) {
            return "";
        } else {
            return dna.substring(startIndex, minIndex + 3);
        }
    }
    //
    //
    public void testGene() {
        String dna;
        dna = "ATGTAA";
        System.out.println("DNA= " + dna);
        System.out.println("Gene= " + findGene(dna) + "\n");

        dna = "ATGTGA";
        System.out.println("DNA= " + dna);
        System.out.println("Gene= " + findGene(dna) + "\n");

        dna = "ATGTAG";
        System.out.println("DNA= " + dna);
        System.out.println("Gene= " + findGene(dna) + "\n");

        dna = "ZZZATGZZZTAG";
        System.out.println("DNA= " + dna);
        System.out.println("Gene= " + findGene(dna) + "\n");
        
        dna = "ZATGAAAGGGTTTTGA";
        System.out.println("DNA= " + dna);
        System.out.println("Gene= " + findGene(dna) + "\n");
    }

    
}
