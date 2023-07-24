
/**
 * Find all genes in DNA with while loop and break.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllGenes {
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
    public String findGene (String dna, int where) {
        // Find a valid gene that starts with ATG and ends with TAA.
        // The length must be a multiple of three.

        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        // int temp = Math.min(taaIndex, tagIndex);
        // int minIndex = Math.min(temp, tgaIndex); This can't be used because -1 means there is no stop codon.

        // Find the minIndex
        int minIndex;
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
    public void printAllGenes(String dna) {
        int startIndex = 0;
        String gene;

        while (true) {
            gene = findGene(dna, startIndex);
            if (gene == "") {
                break;
            }
            System.out.println(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }

}
