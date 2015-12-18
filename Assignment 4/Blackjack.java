/**
 *
 * @author Ali Murtaza Sharif
 */
import java.util.Scanner;
import java.util.InputMismatchException;

public class Blackjack {

  public enum Results {
    DEALER_WINS,
    PLAYER_WINS,
    TIE,
    BLACKJACK
  }

  public static void main(String args[]) {
    int chips = -1;
    // Just making sure my input is valid
    // You shouldn't be able to execute this program with invalid arguments. That's why I return
    try {
      chips = Integer.parseInt(args[0]);
    } catch(NumberFormatException e) {
      System.out.println("Please pass a valid integer");
      System.out.println("Please pass the number of chips you wish to start the game with");
      return;
    } catch(ArrayIndexOutOfBoundsException e) {
      System.out.println("Please pass the number of chips you wish to start the game with");
      return;
    }
    if(chips <= 0) {
      System.out.println("Please pass an integer greater than zero");
      System.out.println("Please pass the number of chips you wish to start the game with");
      return;
    }

    // Just to make sure the player knows what game this is
    // It also looks really cool
    System.out.println();
    System.out.println("______ _      ___  _____ _   __   ___  ___  _____ _   __");
    System.out.println("| ___ \\ |    / _ \\/  __ \\ | / /  |_  |/ _ \\/  __ \\ | / /");
    System.out.println("| |_/ / |   / /_\\ \\ /  \\/ |/ /     | / /_\\ \\ /  \\/ |/ / ");
    System.out.println("| ___ \\ |   |  _  | |   |    \\     | |  _  | |   |    \\ ");
    System.out.println("| |_/ / |___| | | | \\__/\\ |\\  \\/\\__/ / | | | \\__/\\ |\\  \\");
    System.out.println("\\____/\\_____|_| |_/\\____|_| \\_/\\____/\\_| |_/\\____|_| \\_/");
    System.out.println("--------------------------------------------------------");

    // Initialization
    CardPile gameDeck = CardPile.makeFullDeck(4);
    Scanner betReader = new Scanner(System.in);
    int playerBet = 0;

    // Main game loop
    while(gameDeck.getNumCards() > 10 && chips > 0) {
      System.out.println("\n-----------------------------\n\t~ ROUND START ~\n-----------------------------");
      boolean err = true;
      while(err) {
        System.out.print("You can pass a negative integer to quit\nHow much would you like to bet on this round?: ");
        // Just making sure exceptions don't go unhandled
        try {
          playerBet = betReader.nextInt();
          err = false;
        } catch(InputMismatchException e) {
          System.out.println("\n---Please enter a valid integer---\n");
          // The scanner needs to be reset so it doesn't keep trying to read the invalid integer
          betReader = new Scanner(System.in);
        }
      }

      // Keep asking the player to bet within his or her means
      while(playerBet > chips) {
        System.out.print("\n---You cannot bet outside your means. Please choose a value less than or equal to your current number of chips---\n\nHow much would you like to be on this round?: ");
        playerBet = betReader.nextInt();
      }

      if(playerBet < 0) {
        System.out.println("\nGoodbye");
        return;
      }

      Results result = playRound(gameDeck);
      if(result == Results.PLAYER_WINS) chips += playerBet;
      if(result == Results.DEALER_WINS) chips -= playerBet;
      if(result == Results.BLACKJACK) chips += (int)(playerBet * 1.5);
      System.out.println("You now have " + chips + " chips");
    }
    System.out.print("GAME OVER");
    betReader.close();
  }

  // This method requires heavy refactoring
  // I am not pleased with the code quality
  public static Results playRound(CardPile startingDeck) {
    // Initialization of the round
    Scanner kbInput = new Scanner(System.in);
    CardPile playersCards, dealersCards;

    playersCards = new CardPile();
    playersCards.addToBottom(startingDeck.remove(0));
    playersCards.addToBottom(startingDeck.remove(0));

    dealersCards = new CardPile();
    dealersCards.addToBottom(startingDeck.remove(0));
    dealersCards.addToBottom(startingDeck.remove(0));

    String move = "";
    // Initialization over

    // If the player gets 21 through the first 2 cards then check the dealer's hand
    // If they both have 21, then tie
    // Otherwise, the player wins
    if(countValues(playersCards) == 21) {
      if(countValues(dealersCards) == 21) {
        System.out.println("Both you and the dealer received blackjack. It's a tie");
        return Results.TIE;
      }
      System.out.println("BLACKJACK!\nYou win the game!");
      return Results.BLACKJACK;
    }

    // The player goes first
    while(true) {
      System.out.println("-------------------------");
      System.out.println("Current status of game:");
      System.out.println("Dealer's second card: " + dealersCards.get(1).toString());
      System.out.println("Your hand: " + playersCards.toString() + "(Total: " + countValues(playersCards) + ")");
      System.out.print("What would you like to do? (hit/stay):");
      // Get the initial input from the player
      move = kbInput.next();
      // Prevents the player from making invalid moves
      while(!(move.equals("hit") || move.equals("stay"))) move = kbInput.next();
      if(move.equals("hit")) {
        System.out.print("The card you have received is " + startingDeck.get(0).toString());
        playersCards.addToBottom(startingDeck.remove(0));
        System.out.println(". Now your score is " + countValues(playersCards));
        // If the player is unlucky and scores over 21, end the game immediately
        if(countValues(playersCards) > 21) {
          System.out.println("Your score is over 21. You lose");
          return Results.DEALER_WINS;
        }
      }
      // End the player's loop
      if(move.equals("stay")) break;
    }

    // The dealer's turn
    System.out.println("It is now the dealer's turn");
    while(countValues(dealersCards) < 18) {
      dealersCards.addToBottom(startingDeck.remove(0));
    }
    System.out.println("The dealer has the following cards: " + dealersCards.toString() + "(Total: " + countValues(dealersCards) + ")");

    // Check the results of the round
    if(countValues(dealersCards) > 21) {
      System.out.println("The dealer busted!\nYou win!");
      return Results.PLAYER_WINS;
    }

    if(countValues(dealersCards) == countValues(playersCards)) {
      System.out.println("It's a tie");
      return Results.TIE;
    }

    // At this point, I'm 100% sure that the player's hand's score and dealer's hand's score are less than 21
    if(21 - countValues(dealersCards) < 21 - countValues(playersCards)) {
      System.out.println("Dealer wins");
      return Results.DEALER_WINS;
    } else {
      System.out.println("You win");
      return Results.PLAYER_WINS;
    }
  }

  public static int countValues(CardPile deck) {
    int numberOfAces = 0;
    int score = 0;
    if(deck.isEmpty()) return 0;
    for(int i = 0;i < deck.getNumCards();i++) {
      if(deck.get(i).getValue() == Value.ACE) numberOfAces++;
      score += getScore(deck.get(i));
    }
    while(score > 21 && numberOfAces > 0)
    {
      score -= 10;
      numberOfAces--;
    }
    return score;
  }

  public static int getScore(Card card) {
    int score = 0;
    if(card.getValue() == Value.TEN || card.getValue() == Value.JACK || card.getValue() == Value.QUEEN || card.getValue() == Value.KING) {
      return 10;
    }
    if(card.getValue() == Value.ACE) return 11;
    int i = 2;
    for(Value v : Value.values()) {
      if(card.getValue() == v) return i;
      i++;
    }
    return 0;
  }
}
