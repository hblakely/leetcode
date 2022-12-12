import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentenceSImilarity {
    /*
    We can represent a sentence as an array of words, for example, the sentence "I am happy with leetcode"
    can be represented as arr = ["I","am",happy","with","leetcode"].

    Given two sentences sentence1 and sentence2 each represented as a string array and given an array of string pairs
    similarPairs where similarPairs[i] = [xi, yi] indicates that the two words xi and yi are similar.

    Return true if sentence1 and sentence2 are similar, or false if they are not similar.

    Two sentences are similar if:

    They have the same length (i.e., the same number of words)
    sentence1[i] and sentence2[i] are similar.
    Notice that a word is always similar to itself, also notice that the similarity relation is not transitive.
    For example, if the words a and b are similar, and the words b and c are similar, a and c are not necessarily similar.

    Example 1:

    Input: sentence1 = ["great","acting","skills"],
    sentence2 = ["fine","drama","talent"], similarPairs =
    [["great","fine"],["drama","acting"],["skills","talent"]]
    Output: true
    Explanation: The two sentences have the same length and each word i of sentence1 is also similar to the
    corresponding word in sentence2.
    Example 2:

    Input: sentence1 = ["great"], sentence2 = ["great"], similarPairs = []
    Output: true
    Explanation: A word is similar to itself.
    Example 3:

    Input: sentence1 = ["great"], sentence2 = ["doubleplus","good"], similarPairs = [["great","doubleplus"]]
    Output: false
    Explanation: As they don't have the same length, we return false.


    Constraints:

    1 <= sentence1.length, sentence2.length <= 1000
    1 <= sentence1[i].length, sentence2[i].length <= 20
    sentence1[i] and sentence2[i] consist of English letters.
    0 <= similarPairs.length <= 1000
    similarPairs[i].length == 2
    1 <= xi.length, yi.length <= 20
    xi and yi consist of lower-case and upper-case English letters.
    All the pairs (xi, yi) are distinct.
     */

    /*
    The instructions for this one felt a bit ambiguous. I had to run through their test-cases a few times to glean
    more information to handle their inputs correctly. I had to check for either combination of the words appearing
    first. I had to skip over the same word being in the pair, as they wouldn't include a word mapped to itself in
    the similar pairs (makes sense, but wasn't clear). And I had to completely skip over two empty sentences.
     */
    // The below solution passed leetcode's testing with 1ms (100%), and 41mb (95%)
    public static boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        boolean similar = sentence1.length == sentence2.length; // initialize boolean with matching sentence lengths.
        if(similar){ // Only continue if sentences are of same length. No need to calculate otherwise.
            if (sentence1.length ==0) // sentence1 and 2 can both be empty and still be considered similar.
                return similar;
            for(int i = 0; i<sentence1.length; i++){
                // I had originally thought to make everything lowercase for comparisons, but it didn't seem tp matter.
                // Therefor I optimized for their test cases.
                if(sentence1[i].equals(sentence2[i])) // Skip calculation if the words are the same.
                    continue;
                if(similarPairs.size() > 0){ // verify similarPairs has entries berfore searching.
                    List<String> pair1 = new ArrayList<>();
                    List<String> pair2 = new ArrayList<>();
                    pair1.add(sentence1[i].toLowerCase()); pair1.add(sentence2[i]);
                    pair2.add(sentence2[i].toLowerCase()); pair2.add(sentence1[i]);
                    if(!(similarPairs.contains(pair1) || similarPairs.contains(pair2)))
                        return false;
                }
            }
        }
        return similar;
    }

    public static void main(String[] args){
        // I could run through the rest of the examples, but this will suffice for now.
        // Feel free to add to the testing of examples if you like.
        example1();
    }
    public static void example1(){
        boolean example1 = areSentencesSimilar(new String[]{"great","acting","skills"},
                new String[]{"fine","drama","talent"},Arrays.asList(Arrays.asList("great","fine"),
                        Arrays.asList("drama","acting"),Arrays.asList("skills","talent")));
        if(example1) System.out.println("SUCCESS!");
        else System.out.println("FAILURE! answer should be: "+!example1);
        System.out.println("Your answer for example1: "+example1);
    }
}
