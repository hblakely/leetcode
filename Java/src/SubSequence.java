import java.util.HashMap;
import java.util.Map;

public class SubSequence {
    /*
    https://leetcode.com/study-plan/leetcode-75/?progress=xdlhlco7 Level 1
    A subsequence of a string is a new string that is formed from the original string by deleting some
    (can be none) of the characters without disturbing the relative positions of the remaining characters.
    (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

            Constraints:

        0 <= s.length <= 100
        0 <= t.length <= 104
        s and t consist only of lowercase English letters.

        Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109,
        and you want to check one by one to see if t has its subsequence. In this scenario,
        how would you change your code? -

        ANSWER: for (String s : Ss){code}, would return mapped String to boolean
     */



    /**
     * First attempt.
     */
    public static boolean isSubsequence(String s, String t) { // 1ms, 42.7 mb leetcode.com
        if(s.length()>t.length())
            return false;

        for(int i = 0; i<s.length(); i++){
            char sChar = s.charAt(i);
            if(t.contains(""+sChar))
                t=t.substring(t.indexOf(sChar)+1);
            else
                return false;
        }
        return true;
    }

    /**
     * Answer to the followup question: handle String[] s
     */
    public static Map<String,Boolean> isSubsequenceFollowUp(String[] sS, String t) { // 1ms, 42.7 mb leetcode.com
        Map<String,Boolean> answers = new HashMap<>();
        outer: for(String s: sS) {
            if (s.length() > t.length()) {
                answers.put(s, false);
                continue outer;
            }

            String tTemp = t;
            for (int i = 0; i < s.length(); i++) {
                char sChar = s.charAt(i);
                if (tTemp.contains("" + sChar))
                    tTemp = tTemp.substring(tTemp.indexOf(sChar) + 1);
                else {
                    answers.put(s, false);
                    continue outer;
                }
            }
            answers.put(s,true);
        }
        return answers;
    }

    public static void main(String[] args){
        example1();
        example2();
        example3();
    }

    /**
     * Example 1:
     *
     * Input: s = "abc", t = "ahbgdc"
     * Output: true
     */
    private static void example1(){
        System.out.println("Example1: ");
        String s = "abc", t = "ahbgdc";
        System.out.println(s+" is a subsequence of "+t+"? : "+isSubsequence(s,t)+"\n");
    }

    /**
     * Example 2:
     *
     * Input: s = "axc", t = "ahbgdc"
     * Output: false
     */
    private static void example2(){
        System.out.println("Example2: ");
        String s = "axc", t = "ahbgdc";
        System.out.println(s+" is a subsequence of "+t+"? : "+isSubsequence(s,t)+"\n");
    }

    /**
     * Example 3: FOR THE FOLLOWUP
     *
     * Input: s = "axc", t = "ahbgdc"
     * Output: false
     */
    private static void example3(){
        System.out.println("Example3: s[] input");
        String[] sS = {"axc","abc"};
        String t = "ahbgdc";
        Map<String,Boolean> mapped = isSubsequenceFollowUp(sS,t);
        for(String s: sS){
            System.out.println(s+" is subsequence of "+t+"? : "+mapped.get(s));
        }
    }



    /*
    LEETCODE solutions:
     */


    /**
     * Approach 1: Divide and Conquer with Greedy
     * Intuition
     *
     * The problem concerns the string matching issues, for which often one can apply a
     * technique called divide and conquer.
     */
    public static String source, target;
    public static Integer leftBound, rightBound;
    private static boolean rec_isSubsequence(int leftIndex, int rightIndex) {
        // base cases
        if (leftIndex == leftBound)
            return true;
        if (rightIndex == rightBound)
            return false;

        // consume both strings or just the target string
        if (source.charAt(leftIndex) == target.charAt(rightIndex))
            ++leftIndex;
        ++rightIndex;

        return rec_isSubsequence(leftIndex, rightIndex);
    }

    public static boolean isSubsequenceDivideAndConquer(String s, String t) {
        source = s;
        target = t;
        leftBound = s.length();
        rightBound = t.length();

        return rec_isSubsequence(0, 0);
    }


}
