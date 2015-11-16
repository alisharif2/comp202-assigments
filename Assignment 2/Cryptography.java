/*
 * @author: Ali Murtaza Sharif
 */

/*
 * Spiritual Growth Question:
 * In the first encryption scheme using the caesar shift there are only 25 possible encryptions. This is because each shift from 1 - 25
 * generates a unique string and everything after either does nothing or simply repeats.
 *
 * In the second ecryption scheme, the string to be encoded is remapped using a 26 character array. Since the encryption depends on only
 * the charMap, the number of encryption possibilities is equal to the number of permutations of the charMap.
 * This is equal to 26! - 1 or 403291461126605635583999999
 * One of the permutations is the alphabet which does nothing.
 */

import java.util.Random;

public class Cryptography {
	
	/*
	 * a : 97
	 * z : 122
	 * A : 65
	 * Z : 90
	 */

	// Honestly, I'd prefer magic numbers to having every single significant number stored in a variable
	public static final int numberOfLetters = 26;
	public static final int randSeed = 12345;
	
	public static void main(String args[]) {
		System.out.println("Your text message: '" + args[0] + "' has been encoded using a " + args[1] + " shift caesar cipher");
		//System.out.println(generateAlphabetCharArray());	
		//System.out.println(generatePermutation());
		//System.out.println(permuteEncrypt(args[0]));
		String code = caesarEncrypt(args[0], Integer.parseInt(args[1]));
		System.out.println(code);
		System.out.println("Your code will now be run through a decryption algorithm");
		System.out.println(code + " -> " + crackCipher(code, 25));
	}
	
	public static char[] generateAlphabetCharArray() {
		char[] charList = new char[numberOfLetters];
		for(int i = 0;i < charList.length;i++) {
			charList[i] = (char)('A' + i);
		}
		return charList;
	}
	
	public static String caesarEncrypt(String originalMessage, int shift) {
		while(shift >= numberOfLetters) shift -= numberOfLetters;
		if(shift <= 0) return originalMessage;
		String encryptedMessage = "";
		char tmpChar;
		for(int i = 0;i < originalMessage.length();i++) {
			// There are two seperate blocks of code for for lowercase and uppercase characters
			// This block is for the lowercase characters
			if((int)originalMessage.charAt(i) <= 'z' && (int)originalMessage.charAt(i) >= 'a') {
				tmpChar = (char)(originalMessage.charAt(i) + (char)shift);
				// Checking if the shifted character is outside the alphabet
				// If it is, then just wrap around by using the 'distance' from 'z' and adding to 'a'-1
				if((int)tmpChar > 'z') {
					tmpChar = (char)('a' + tmpChar - 'z' - (char)1);
				}
				encryptedMessage = encryptedMessage + tmpChar;
			}
			// This block is for the uppercase characters
			// The structure of this block is identical to the one above
			else if((int)originalMessage.charAt(i) <= 'Z' && (int)originalMessage.charAt(i) >= 'A') {
				tmpChar = (char)(originalMessage.charAt(i) + (char)shift);
				if((int)tmpChar > 'Z') {
					tmpChar = (char)('A' + tmpChar - 'Z' - (char)1);
				}
				encryptedMessage = encryptedMessage + tmpChar;
			}
			// If none of the characters belong to the alphabet, then just copy them without shifting
			else {
				encryptedMessage = encryptedMessage + originalMessage.charAt(i);
			}
		}
		return encryptedMessage;
	}
	
	public static String caesarDecrypt(String encoded, int shift) {
		while(shift >= numberOfLetters) shift -= numberOfLetters;
		return caesarEncrypt(encoded, numberOfLetters - shift);
	}
	
	public static String crackCipher(String encoded, int numberLetters) {
		String bestDecryption = "";
		String decoded = "";
		int englishWordCount = 0;
		// Iterate through each possible caesar decryption possibility and count the number of english words
		// If the current count of english words is greater than the previous count then update 'bestDecryption' and 'englishWordCount'
		// If not, then do nothing
		for(int i = 0;i < numberLetters;i++) {
			decoded = caesarDecrypt(encoded, i);
			if(SentenceChecker.countEnglishWords(decoded) > englishWordCount) {
				bestDecryption = decoded;
				englishWordCount = SentenceChecker.countEnglishWords(decoded);
			}
		}
		return bestDecryption;
	}

	public static void shuffle(char[] charArray) {
		char tmp = 0;
		int positionOne, positionTwo;
		Random generator = new Random(randSeed);
		for(int i = 0;i < Math.pow(charArray.length, 4);i++) {
			positionOne = generator.nextInt(charArray.length);
			positionTwo = generator.nextInt(charArray.length);
			tmp = charArray[positionOne];
			charArray[positionOne] = charArray[positionTwo];
			charArray[positionTwo] = tmp;
		}
	}

	public static char[] generatePermutation() {
		char[] charList = generateAlphabetCharArray();
		shuffle(charList);
		return charList;
	}

	public static String permuteEncrypt(String input) {
		char[] charMap = generatePermutation();
		String encoded = "";
		for(int i = 0;i < input.length();i++) {
			// This the part of the code that does the remapping
			// Substracting 'a' or 'A' from each char allows me to use it as an index for the charMap
			if(input.charAt(i) <= 'Z' && input.charAt(i) >= 'A') encoded = encoded + charMap[input.charAt(i) - 'A'];
			// After getting the appropriate character from charMap, I add 32 to it
			// This is because the difference between each form of a letter is 32: 'a' - 'A' = 32
			else if(input.charAt(i) <= 'z' && input.charAt(i) >= 'a') encoded = encoded + (char)(charMap[input.charAt(i) - 'a'] + 'a' - 'A');
			else encoded = encoded + input.charAt(i);
		}
		return encoded;
	}
}	
