
/**
 * Find gene with different stop codons.
 * 
 * @author (chris) 
 * @version (a version number or a date)
 */
public class AllCodons {
    //
    //
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        // Return index of the stop codon.

        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        if (currIndex == -1) {
            return currIndex;
        }

        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }

        return dna.length();
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

        // find the min among the stop codons = minIndex
        int temp = Math.min(taaIndex, tagIndex);
        int minIndex = Math.min(temp, tgaIndex);

        if (minIndex == dna.length()) {
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
