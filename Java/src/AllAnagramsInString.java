import java.util.*;

public class AllAnagramsInString {
    /*
    Given two strings s and p, return an array of all the start indices of p's anagrams in s.
    You may return the answer in any order.

    An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
    typically using all the original letters exactly once.

    Constraints:

    1 <= s.length, p.length <= 3 * 104
    s and p consist of lowercase English letters.
     */


    /* Example 1:

    Input: s = "cbaebabacd", p = "abc"
    Output: [0,6]
    Explanation:
    The substring with start index = 0 is "cba", which is an anagram of "abc".
    The substring with start index = 6 is "bac", which is an anagram of "abc".
    */
    private static void example1(){
        String s = "cbaebabacd", p = "abc";
        System.out.println("Example1: "+findAnagrams(s,p));
    }


    /* Example 2:

    Input: s = "abab", p = "ab"
    Output: [0,1,2]
    Explanation:
    The substring with start index = 0 is "ab", which is an anagram of "ab".
    The substring with start index = 1 is "ba", which is an anagram of "ab".
    The substring with start index = 2 is "ab", which is an anagram of "ab".
     */
    private static void example2(){
        String s = "abab", p = "ab";
        System.out.println("Example2: "+findAnagrams(s,p));

    }

    public static void main(String[] args){
        example1();
        example2();
    }


    // ###############  FIRST ATTEMPT ##################
    // This attempt passed, but just barely. It was so slow compared to the rest of the submissions.
    // Time: 2157ms, in the slowest 5.1% to pass leetcode.com's test
    // Space: 43.15mb, 85.86%
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> anagramOffset = new ArrayList<>();
        if(p.length() > s.length())
            return anagramOffset;

        char[] pTemp = p.toCharArray();
        Arrays.sort(pTemp);

        for(int i = p.length(); i<s.length()+1; i++){
            char[] temp = s.substring(i-p.length(),i).toCharArray();
            Arrays.sort(temp);
            if(Arrays.equals(temp, pTemp))
                anagramOffset.add(i-p.length());
        }
        return anagramOffset;
    }

    /*
    This is a problem of multiple pattern search in a string.
    All such problems usually could be solved by sliding window approach in a linear time.
    The challenge here is how to implement constant-time slice to fit into this linear time.

    If the patterns are not known in advance, i.e. it's "find duplicates" problem,
    one could use one of two ways to implement constant-time slice: Bitmasks or Rabin-Karp.
    Please check article Repeated DNA Sequences for the detailed comparison of these two algorithms.
    https://leetcode.com/articles/repeated-dna-sequences/

    Here the situation is more simple: patterns are known in advance,
    and the set of characters in the patterns is very limited as well: 26 lowercase English letters.
    Hence one could allocate array or hashmap with 26 elements and use it as a letter counter in the sliding window.

     */
    /**
     *  Approach 1: Sliding Window with HashMap
     *     Let's start from the simplest approach: sliding window + two counter hashmaps letter -> its count.
     *     The first hashmap is a reference counter pCount for string p, and the second one is a counter
     *     sCount for string in the sliding window.
     *     The idea is to move sliding window along the string s, recompute the second hashmap sCount in a
     *     constant time and compare it with the first hashmap pCount. If sCount == pCount,
     *     then the string in the sliding window is a permutation of string p, and one could add its start
     *     position in the output list.
     *
     *     Algorithm
     *     Build reference counter pCount for string p.
     *     Move sliding window along the string s:
     *     Recompute sliding window counter sCount at each step by adding one letter on the right
     *     and removing one letter on the left.
     *     If sCount == pCount, update the output list.
     *     Return output list.
     *     Implementation
     */

    // 72ms, 43.1mb usage via leetcode.com
    public List<Integer> findAnagramsSlidingWindowHashMap(String s, String p) {
        int ns = s.length(), np = p.length();
        if (ns < np) return new ArrayList();

        Map<Character, Integer> pCount = new HashMap();
        Map<Character, Integer> sCount = new HashMap();
        // build reference hashmap using string p
        for (char ch : p.toCharArray()) {
            if (pCount.containsKey(ch))
                pCount.put(ch, pCount.get(ch) + 1);
            else
                pCount.put(ch, 1);
        }

        List<Integer> output = new ArrayList();
        // sliding window on the string s
        for (int i = 0; i < ns; ++i) {
            // add one more letter on the right side of the window
            char ch = s.charAt(i);
            if (sCount.containsKey(ch))
                sCount.put(ch, sCount.get(ch) + 1);
            else
                sCount.put(ch, 1);
            // remove one letter from the left side of the window
            if (i >= np) {
                ch = s.charAt(i - np);
                if (sCount.get(ch) == 1)
                    sCount.remove(ch);
                else
                    sCount.put(ch, sCount.get(ch) - 1);
            }
            // compare hashmap in the sliding window with the reference hashmap
            if (pCount.equals(sCount))
                output.add(i - np + 1);
        }
        return output;
    }

    /**
     * Here's a similar implementation of the above, using an int[] instead of a
     * HashMap to keep track of char occurrence.
     */
    // 8ms, 43mb leetcode.com, with fewer lines of code.
    public List<Integer> findAnagramsIntArray(String s, String p) {
        int ns = s.length(), np = p.length();
        if (ns < np) return new ArrayList();

        int[] pCount = new int[128];
        int[] sCount = new int[128];
        for (char ch : p.toCharArray())
            pCount[ch]++;

        List<Integer> output = new ArrayList();
        for (int i = 0; i < ns; ++i) {
            char ch = s.charAt(i);
            sCount[ch]++;

            if (i >= np) {
                ch = s.charAt(i - np);
                if (sCount[ch] > 0)
                    sCount[ch]--;
            }

            if (Arrays.equals(pCount,sCount))
                output.add(i - np + 1);
        }
        return output;
    }


    /**
     * Reduce the size of int[] to 26
     */
    // 8ms, 43.1mb - basically the same as above,
    public List<Integer> findAnagramsSmallerIntArray(String s, String p) {
        int ns = s.length(), np = p.length();
        if (ns < np) return new ArrayList();

        int [] pCount = new int[26];
        int [] sCount = new int[26];

        for (char ch : p.toCharArray())
            pCount[(int)(ch - 'a')]++;

        List<Integer> output = new ArrayList();

        for (int i = 0; i < ns; ++i) {
            sCount[(int)(s.charAt(i) - 'a')]++;
            if (i >= np)
                sCount[(int)(s.charAt(i - np) - 'a')]--;

            if (Arrays.equals(pCount, sCount))
                output.add(i - np + 1);
        }
        return output;
    }
}
