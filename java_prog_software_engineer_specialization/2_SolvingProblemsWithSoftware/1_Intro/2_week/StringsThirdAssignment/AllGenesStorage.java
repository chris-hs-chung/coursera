import edu.duke.*;
/**
 * Strings Third Assignment
 * 
 * Using StorageResource
 * 
 * @author chris
 * @version 7/25/2023
 */
public class AllGenesStorage {
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
    // this method is replaced by getAllGenes
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
    //
    // Part 1: find all genes and put them in storage
    public StorageResource getAllGenes(String dna) {
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        String gene;

        while (true) {
            gene = findGene(dna, startIndex);
            if (gene == "") {
                break;
            } else {
                geneList.add(gene);
            }
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }

        return geneList;
    }
    //
    // Part 1: test getAllGenes
    public void testGetAllGenes() {
        String dna;
        StorageResource sr = new StorageResource();

        dna = "ZZATGZZZZZZTAGZZZATGTAGZZATGZZZTGAZZ";
        sr = getAllGenes(dna);
        for (String s : sr.data()) {
            System.out.println(s);
        }
    }
    
    //-------------------------------------------------------
    // Part 2: find the ratio of Cs and Gs in the entire DNA.
    public double cgRatio (String dna) {
        //
        int count = 0;
        double dnaLength = dna.length();

        for (int i = 0; i < dnaLength; i++) {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                count++;
            }
        }
        
        double ratio = count/dnaLength;
        return ratio;
    }
    //
    public void testCgRatio () {
        String dna;
        dna = "ATGCCATAG";
        System.out.println(cgRatio(dna));
    }
    //
    // Part 2: return the number of times the codon CTG appears in DNA.
    public int countCTG (String dna) {
        //
        int startIndex = 0;
        int ctgIndex;
        int count = 0;
        
        while (true) {
            ctgIndex = dna.indexOf("CTG", startIndex);
            if (ctgIndex == -1) {
                break;
            } else {
                count++;
            }
            startIndex = dna.indexOf("CTG", startIndex) + 3;
        }
        return count;
    }
    // 
    public void testCountCTG (){
        String dna = "CTGCTGCCC";
        System.out.println(countCTG(dna));
    }
    
    // ---------------------------------------------------
    // Part 3: Process all the genes in a storage resource
    public void processGenes(StorageResource sr) {
        int countLonger9 =0;
        int countCG = 0;
        String longestGene = "";
        int longestLength = 0;
        int countLongerThan60 = 0;
        int sLength = 0;
        
        for (String s : sr.data()) {
            sLength = s.length(); 
            if (sLength > 9) {
                System.out.println("Longer than 9 char: )" + s);
                countLonger9 ++;
            }
            if (cgRatio(s) > 0.35) {
                System.out.println("C-G ratio higher than .35: )" + s);
                countCG++;
            }
            if (sLength > longestLength){
                longestGene = s;
                longestLength = sLength;
            }
            if (sLength > 60) {
                System.out.println("Longer than 60: )" + s);
                countLongerThan60++;
            }
        }
        System.out.println("Count longer than 9: " + countLonger9);
        System.out.println("Count C-G ratio higher than .35: " + countCG);
        System.out.println("Count longer than 60: " + countLongerThan60);
    }
    //
    public void testProcessGenes() {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        StorageResource sr = new StorageResource();
        sr = getAllGenes(dna);
        
        processGenes(sr);
    }
}
