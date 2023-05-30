import java.util.HashMap;
import java.util.Map;

public class LongestPalindrome {
    /*
    https://leetcode.com/study-plan/leetcode-75/?progress=xdlhlco7 Level 1
    Given a string s which consists of lowercase or uppercase letters, return the length of the longest
    palindrome that can be built with those letters.

    Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
     */

    public int longestPalindrome(String s) { // 10ms 42.5 mb via leetcode
        int[] count = new int[128]; // uses int array because ASCII for lower/uppercase letters fall in that range.
        for (char c: s.toCharArray()) // char value defaults to int, and can be used as unique index.
            count[c]++; // int defaults to 0 for each index

        int ans = 0;
        for (int v: count) {
            ans += v / 2 * 2;
            if (ans % 2 == 0 && v % 2 == 1) // add unique center only once.
                ans++;
        }
        return ans;
    }


    public int longestPalindromeFirstAttempt(String s) {  //(21ms) (42.6mb) via leetcode.
        HashMap<Character,Integer> charOccurrence = new HashMap<Character,Integer>(); // map Adds a lot of memory
        for (int i=0; i<s.length(); i++){ // Map character to number of occurrences
            Character c = s.charAt(i);
            if(charOccurrence.containsKey(c)){
                charOccurrence.replace(c,charOccurrence.get(c)+1);
            }else charOccurrence.put(c,1);
        }
        int odd = 0; // keep track of the existence of a middle character
        int num = 0;
        for(Map.Entry<Character,Integer> map: charOccurrence.entrySet()){
            int numChars = map.getValue(); // this may be where the extra time was added.
            if(numChars%2==1) {// odd occurrence means there will be a middle char in palindrome
                odd=1;
                numChars-=odd;// there can only be one middle number, so we tack it on at the end
            }
            num+=numChars; // Add even number

        }
        return num+odd; // Add the middle character to the rest of the palindrom
    }
}
