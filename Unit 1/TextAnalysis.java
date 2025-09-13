import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A simple text analysis tool that calculates character count, word count,
 * and analyzes character and word frequency in a given text input.
 */
public class TextAnalysis {
    public static void main(String[] args) {

        // ============================
        // 1. USER INPUT AND INITIAL SETUP
        // ============================

        // Create a Scanner object to read input from the user.
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a paragraph or a lengthy text.
        System.out.println("please enter a paragraph or a lengthy text, and press Enter when you are done");

        // Read the entire line of text entered by the user and store it in the 'input' variable.
        String input = scanner.nextLine();

        // Display the user's input to confirm it has been read.
        System.out.println("The text you entered is: " + input);

        // ============================
        // 2. CHARACTER ANALYSIS
        // ============================

        // Calculate the total number of characters in the input text using the length() method.
        int charCount = input.length();

        // Display the total character count.
        System.out.println("The total number of character is: " + charCount);

        // ============================
        // 3. WORD ANALYSIS
        // ============================

        // Use a powerful regular expression to split the string into words.
        // [\\s\\p{Punct}]+ matches one or more whitespace characters (\\s) or
        // any punctuation characters (\\p{Punct}). This ensures accurate word splitting
        // by treating punctuation and multiple spaces as delimiters.
        String [] words = input.split("[\\s\\p{Punct}]+");

        // A HashMap is used to store word counts. The key is the word (String),
        // and the value is its frequency (Integer).
        Map<String, Integer> wordCountMap = new HashMap<>();

        // Loop through each word from the split array to populate the map.
        for (String word : words) {
            // Check if the word is not an empty string. This handles cases where
            // the split operation might create empty elements due to leading/trailing
            // delimiters in the input.
            if (!word.isEmpty()) {
                // Convert the word to lowercase to ensure case-insensitive counting.
                word = word.toLowerCase();
                // Get the current count of the word, or 0 if it's the first time
                // we've seen it. Then increment the count and store it back in the map.
                wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
            }
        }

        // The total number of words is the size of the array after splitting.
        int wordCount = words.length;

        System.out.println("The total number of words is: " + wordCount);

        // ============================
        // 4. FINDING THE MOST COMMON CHARACTER
        // ============================

        // Create a HashMap to store the frequency of each character.
        // We first convert the entire input string to lowercase to handle
        // case-insensitive counting.
        input = input.toLowerCase();
        Map<Character, Integer> charCountMap = new HashMap<>();

        // Iterate over each character in the input string's character array.
        for (char c : input.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        // Initialize variables to track the most common character and its count.
        char mostCommonChar = ' ';
        int maxCount = 0;

        // Iterate through each entry in the character count map to find the max count.
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            // If the current character's count is greater than the current maxCount,
            // update both the maxCount and the mostCommonChar.
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostCommonChar = entry.getKey();
            }
        }

        // Display the result.
        System.out.println("The most common character is: '" + mostCommonChar + "' with a count of " + maxCount);

        // ============================
        // 5. USER INTERACTION: CHARACTER FREQUENCY
        // ============================

        // Prompt the user for the character they want to count.
        System.out.println("Enter the character you want to count:");
        String charInput = scanner.nextLine();

        // Validate the input to ensure it's a single character.
        if (charInput.length() !=1) {
            System.out.println("Error: Please enter only one character.");
        } else {
            // Get the character from the user input and convert it to lowercase for
            // case-insensitive lookup.
            char charToCount = Character.toLowerCase(charInput.charAt(0));

            // Look up the character's count directly from the map.
            int count = charCountMap.getOrDefault(charToCount,0);

            // Display the final result to the user.
            System.out.println("The character '" + charToCount + "' appears " + count + " times.");
        }

        // ============================
        // 6. USER INTERACTION: WORD FREQUENCY
        // ============================

        // Prompt the user for the word they want to count.
        System.out.println("Enter the word you want to count:");
        // Read the user input and convert it to lowercase.
        String wordToCount = scanner.nextLine().toLowerCase();

        // Look up the word's count directly from the map.
        int count = wordCountMap.getOrDefault(wordToCount, 0);

        // Display the final result.
        System.out.println("The word '" + wordToCount + "' appears " + count + " times.");

        // ============================
        // 7. UNIQUE WORD COUNT AND CLEANUP
        // ============================

        // The number of unique words is simply the size of the wordCountMap.
        int uniqueWordCount = wordCountMap.size();
        System.out.println("The total number of unique words is: " + uniqueWordCount);


        // Close the Scanner object to release system resources. This is an important
        // step to prevent resource leaks.
        scanner.close();
    }
}
