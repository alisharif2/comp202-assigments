/**
 *
 * @author Ali Murtaza Sharif
 */
public class DnaUtilities {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(watsonCrickTripletComplement("TGC"));
        System.out.println(watsonCrickTripletComplement("TGF"));
        System.out.println(watsonCrickTripletComplement("AAA"));
        System.out.println(watsonCrickTripletComplement("CCC"));
        System.out.println(watsonCrickTripletComplement("TG3GSC"));
        System.out.println(watsonCrickTripletComplement("343"));
        
    }
    
    public static boolean isValidBase(char base) {
        return base == 'A' | base == 'C' | base == 'G' | base == 'T';
    }
    
    public static char watsonCrickComplement(char base) {
        if(isValidBase(base)) {
            if(base == 'A') return 'T';
            if(base == 'T') return 'A';
            if(base == 'C') return 'G';
            if(base == 'G') return 'C';
        }
        return base;
    }
    
    public static String watsonCrickTripletComplement(String dnaSequence) {
        String dnaComplement = "";
        if(dnaSequence.length() != 3) return "";
        // Loop on each char of the string. If the char is not a valid base return an empty string
        // If the char is a valid base take its complement and concatenate it onto dnaComplement
        for(int i = 0; i < 3;i++) {
            if(!isValidBase(dnaSequence.charAt(i))) return "";
            else {
                dnaComplement = dnaComplement + watsonCrickComplement(dnaSequence.charAt(i));
            }
        }
        return dnaComplement;
    }
}
