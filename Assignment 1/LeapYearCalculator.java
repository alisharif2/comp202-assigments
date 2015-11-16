/**
 *
 * @author Ali Murtaza Sharif
 */
public class LeapYearCalculator {

    public static void printIsLeapYear(int year) {
        if(year < 1) System.out.println("Please enter a non-negative number");
        if(year <= 1581) System.out.println("Please enter a year before 1581");
        if(year % 4 != 0) System.out.println(year + " is not a leap year");
        else if(year % 100 != 0) System.out.println(year + " is a leap year");
        else if(year % 400 != 0) System.out.println(year + " is not a leap year");
        else System.out.println(year + " is a leap year");
    }
    
    public static boolean isLeapYear(int year) {
        if(year < 1) return false;
        if(year <= 1581) return false;
        boolean isLeapYear;
        if(year % 4 != 0) isLeapYear = false;
        else if(year % 100 != 0) isLeapYear = true;
        else if(year % 400 != 0) isLeapYear = false;
        else isLeapYear = true;
        return isLeapYear;
    }
    
    // Keep adding 4 years to the currentYear parameter until it becomes a leap year
    // The method will return 0 if years that don't make sense are passed to it, eg 0 and -34.
    public static int subsequentLeapYear(int currentYear) {
        if(currentYear < 1) return 0;
        int subsequentYear = currentYear + 1;
        while(!isLeapYear(subsequentYear)) {
            subsequentYear += 1;
        }
        return subsequentYear;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO: Insert application logic and code
        System.out.println(subsequentLeapYear(-2096));
        System.out.println(subsequentLeapYear(-1000));
        System.out.println(subsequentLeapYear(1));
        System.out.println(subsequentLeapYear(1532));
        System.out.println(subsequentLeapYear(-3012));
        System.out.println(subsequentLeapYear(30));
        System.out.println(subsequentLeapYear(-3013));
        System.out.println(subsequentLeapYear(6666));
    }
}
