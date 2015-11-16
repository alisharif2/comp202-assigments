/**
 *
 * @author Ali Murtaza Sharif
 */
public class MakingChange {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int money = 0;
        try {
            money = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please pass an argument");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.out.println("Please pass a non-negative integer argument");
            System.exit(1);
        }
        
        int coinCount = 0;
        
        System.out.println("Change for "+money+" cents is:");
        int currentDenominator = 200;
        while(money >= currentDenominator) {
            coinCount += 1;
            money -= currentDenominator;
        }
        System.out.println("Number of toonies: " + coinCount);
        coinCount = 0;
        
        currentDenominator = 100;
        while(money >= currentDenominator) {
            coinCount += 1;
            money -= currentDenominator;
        }
        System.out.println("Number of loonies: " + coinCount);
        coinCount = 0;
        
        currentDenominator = 25;
        while(money >= currentDenominator) {
            coinCount += 1;
            money -= currentDenominator;
        }
        System.out.println("Number of quarters: " + coinCount);
        coinCount = 0;
        
        currentDenominator = 10;
        while(money >= currentDenominator) {
            coinCount += 1;
            money -= currentDenominator;
        }
        System.out.println("Number of dimes: " + coinCount);
        coinCount = 0;
        
        currentDenominator = 5;
        while(money >= currentDenominator) {
            coinCount += 1;
            money -= currentDenominator;
        }
        System.out.println("Number of nickels: " + coinCount);
        coinCount = 0;
        
        System.out.println("Number of pennies: " + money);
        /*
        currentDenominator = 1;
        while(money >= currentDenominator) {
            coinCount += 1;
            money -= currentDenominator;
        }
        System.out.println("Number of pennies: " + coinCount);
        coinCount = 0;*/
    }
    
}
