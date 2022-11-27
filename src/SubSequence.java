public class SubSequence {
    /*
    A subsequence of a string is a new string that is formed from the original string by deleting some
    (can be none) of the characters without disturbing the relative positions of the remaining characters.
    (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
     */
    public boolean isSubsequence(String s, String t) {

        // invalid input
        if(s.length()>t.length())
            return false;
        // edge cases
        if(s.isEmpty())
            return true;
        if(s.length()==1)
            if(t.contains(s))
                return true;
            else
                return false;

        for(int i=1; i<s.length(); i++){
            char next = s.charAt(i);
            char current = s.charAt(i-1);

            t = t.substring(t.indexOf(current)+1);

            if(s.length()-(i)>t.length() || t.indexOf(next)==-1)
                return false;
        }

        return true;
    }
}
