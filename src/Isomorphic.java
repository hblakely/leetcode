import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Isomorphic {
    /*
    https://leetcode.com/study-plan/leetcode-75/?progress=xdlhlco7 Level 1
    Given two strings s and t, determine if they are isomorphic.

    Two strings s and t are isomorphic if the characters in s can be replaced to get t.

    All occurrences of a character must be replaced with another character while preserving the order of characters.
    No two characters may map to the same character, but a character may map to itself.

            Constraints:

        1 <= s.length <= 5 * 104
        t.length == s.length
        s and t consist of any valid ascii character.
     */

    // First attempt, HashMap single loop.
    public static boolean isIsomorphicHash(String s, String t) {  // 31ms, 42mb leetcode.com
        boolean isIsomorphic = true;
        Map<Character, Character> sMap = new HashMap<>();
        Map<Character, Character> tMap = new HashMap<>();

        for(int i=0; i<s.length();i++){
            Character sChar = s.charAt(i);
            Character tChar = t.charAt(i);

            Character sMapped = sMap.get(sChar);
            if(sMapped==null) sMap.put(sChar,tChar);
            else if(sMapped!=tChar) return false;

            Character tMapped = tMap.get(tChar);
            if(tMapped==null) tMap.put(tChar, sChar);
            else if(tMapped!=sChar) return false;
        }
        return isIsomorphic;
    }


    public static boolean isIsomorphic(String s, String t) { // 7ms, 43.2mb
        boolean isIsomorphic = true;
        int[] sMap = new int[256], tMap = new int[256];
        Arrays.fill(sMap, -1); // Mark with invalid char to know we haven't visited.
        Arrays.fill(tMap,-1);

        for(int i=0; i<s.length();i++){
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);

            int sMapped =  sMap[sChar];
            if(sMapped==-1) sMap[sChar]=tChar;
            else if(sMapped!=tChar) return false;

            int tMapped = tMap[tChar];
            if(tMapped==-1) tMap[tChar]=sChar;
            else if(tMapped!=sChar) return false;
        }
        return isIsomorphic;
    }


    public static void main(String[] args){
        example1();
        example2();
        example3();
    }

    /**
     * Example 1:
     *
     * Input: s = "egg", t = "add"
     * Output: true
     */
    private static void example1(){
        String s = "egg", t = "add";
        System.out.println("Input: "+s+", "+t+"; is isomorphic: "+isIsomorphicHash(s,t));
    }

    /**
     * Example 2:
     *
     * Input: s = "foo", t = "bar"
     * Output: false
     *
     */
    private static void example2(){
        String s = "foo", t = "bar";
        System.out.println("Input: "+s+", "+t+"; is isomorphic: "+isIsomorphic(s,t));
    }

    /**
     * Example 3:
     *
     * Input: s = "paper", t = "title"
     * Output: true
     */
    private static void example3(){
        String s = "paper", t = "title";
        System.out.println("Input: "+s+", "+t+"; is isomorphic: "+isIsomorphic(s,t));
    }
}
