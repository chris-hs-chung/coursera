
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
            if ({currIndex - startIndex} % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 3)
            }
        }

        if (currIndex == -1) {
            return dna.length();
        }

    }
}
