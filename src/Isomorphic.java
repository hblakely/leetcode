import java.util.HashMap;

public class Isomorphic {
    /*
    https://leetcode.com/study-plan/leetcode-75/?progress=xdlhlco7 Level 1
    Given two strings s and t, determine if they are isomorphic.

    Two strings s and t are isomorphic if the characters in s can be replaced to get t.

    All occurrences of a character must be replaced with another character while preserving the order of characters.
    No two characters may map to the same character, but a character may map to itself.
     */
    public boolean isIsomorphic(String s, String t) {
        // invalid input
        if(s.length() != t.length())
            return false;
        if(s.length()<1 || s.length()> 50000)
            return false;

        HashMap sMap = new HashMap<Character, Character>();
        HashMap tMap = new HashMap<Character, Character>();

        for (int i =0; i<s.length(); i++){
            Character sChar = (Character)s.charAt(i);
            Character tChar = (Character)t.charAt(i);
            Character sMapped=(Character)sMap.get(sChar);
            Character tMapped=(Character)tMap.get(tChar);

            if(sMapped==null)
                sMap.put(sChar,tChar);
            else if (sMapped.charValue()!=tChar.charValue())
                return false;

            if(tMapped==null)
                tMap.put(tChar,sChar);
            else if (tMapped.charValue()!=sChar.charValue())
                return false;
        }
        return true;
    }
}
