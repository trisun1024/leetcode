import java.util.*;

class JewelsAndStones {

    // brute force
    public int numJewelsInStones(String J, String S) {
        int ans = 0;
        for (char s : S.toCharArray()) // For each stone...
            for (char j : J.toCharArray()) // For each jewel...
                if (j == s) { // If the stone is a jewel...
                    ans++;
                    break; // Stop searching whether this stone 's' is a jewel
                }
        return ans;
    }

    // HashSet
    public int numJewelsInStonesII(String J, String S) {
        Set<Character> set = new HashSet<>();
        for (char c : J.toCharArray()) {
            set.add(c);
        }
        int count = 0;
        for (char c : S.toCharArray()) {
            if (set.contains(c)) {
                count++;
            }
        }
        return count;
    }
}