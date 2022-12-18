import java.util.HashSet;

public class LongestRepeatingCharacterReplacement {
    /*
    You are given a string s and an integer k. You can choose any character of
    the string and change it to any other uppercase English character.
    You can perform this operation at most k times.

    Return the length of the longest substring containing the same letter you
    can get after performing the above operations.

    CONSTRAINTS
        1 <= s.length <= 105
        s consists of only uppercase English letters.
        0 <= k <= s.length
     */

    /**
     * Example 1:
     *
     * Input: s = "ABAB", k = 2
     * Output: 4
     * Explanation: Replace the two 'A's with two 'B's or vice versa.
     */
    private static void example1(){

    }

    /**
     * Example 2:
     *
     * Input: s = "AABABBA", k = 1
     * Output: 4
     * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
     * The substring "BBBB" has the longest repeating letters, which is 4.
     */
    private static void example2(){

    }

    public static void main(String[] args){
        example1();
        example1();
    }
    /**
     * Approach 3: Sliding Window (Fast)
     */
    public static int characterReplacement(String s, int k) {
        int[] frequencyMap = new int[26];
        int start = 0, maxFrequency = 0, longestRun = 0;

        for (int end = 0; end < s.length(); end++) {
            int currentChar = s.charAt(end) - 'A';
            frequencyMap[currentChar]++;
            maxFrequency = Math.max(maxFrequency, frequencyMap[currentChar]);
            Boolean isValid = (end + 1 - start - maxFrequency <= k);
            if (!isValid) {
                int outgoingChar = s.charAt(start) - 'A';
                frequencyMap[outgoingChar]--;
                start++;
            }
            longestRun = end + 1 - start;
        }
        return longestRun;
    }


    /*
    In this problem, we are given a string s consisting of uppercase English alphabets.
    We are allowed to replace any letter of this string with any other uppercase English letter
    in one operation. A maximum of k such operations are permitted.

    We need to find the longest substring after at most k operations such that all the letters
    are identical and return its length.

    There are several ways to solve this problem. We start with the brute force solution and improve
    it with a binary-search-based approach. We build on the understanding developed from the previous
    methods and present an optimized O(n) solution which uses an expanding sliding window.
     */

    /**
    Approach 1: Sliding Window + Binary Search
    */
    public static int characterReplacementBinarySearchWindow(String s, int k) {
        // binary search over the length of substring
        // lo contains the valid value, and hi contains the
        // invalid value
        int lo = 1;
        int hi = s.length() + 1;

        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;

            // can we make a valid substring of length `mid`?
            if (canMakeValidSubstring(s, mid, k)) {
                // explore the right half
                lo = mid;
            } else {
                // explore the left half
                hi = mid;
            }
        }

        // length of the longest substring that satisfies
        // the given condition
        return lo;
    }

    private static Boolean canMakeValidSubstring(String s, int substringLength, int k) {
        // take a window of length `substringLength` on the given
        // string, and move it from left to right. If this window
        // satisfies the condition of a valid string, then we return
        // true
        int[] freqMap = new int[26];
        int maxFrequency = 0;
        int start = 0;
        for (int end = 0; end < s.length(); end += 1) {
            freqMap[s.charAt(end) - 'A'] += 1;
            // if the window [start, end] exceeds substringLength
            // then move the start pointer one step toward right
            if (end + 1 - start > substringLength) {
                // before moving the pointer toward right, decrease
                // the frequency of the corresponding character
                freqMap[s.charAt(start) - 'A'] -= 1;
                start += 1;
            }
            // record the maximum frequency seen so far
            maxFrequency = Math.max(maxFrequency, freqMap[s.charAt(end) - 'A']);
            if (substringLength - maxFrequency <= k) {
                return true;
            }
        }
        // we didn't find a valid substring of the given size
        return false;
    }

    /**
     * Approach 2: Sliding Window (Slow)
     */
    public static int characterReplacementSlidingWindowSlow(String s, int k) {
        HashSet<Character> allLetters = new HashSet();
        // collect all unique letters
        for (int i = 0; i < s.length(); i++)
            allLetters.add(s.charAt(i));

        int maxLength = 0;
        for (Character letter : allLetters) {
            int start = 0, count = 0;
            // initialize a sliding window for each unique letter
            for (int end = 0; end < s.length(); end += 1) {
                if (s.charAt(end) == letter)
                    count += 1;// if the letter matches, increase the count

                // bring start forward until the window is valid again
                while (!isWindowValid(start, end, count, k)) {
                    if (s.charAt(start) == letter)
                        count -= 1;// if the letter matches, decrease the count

                    start += 1;
                }
                // at this point the window is valid, update maxLength
                maxLength = Math.max(maxLength, end + 1 - start);
            }
        }
        return maxLength;
    }
    private static Boolean isWindowValid(int start, int end, int count, int k) {
        return end + 1 - start - count <= k;
    }



}
