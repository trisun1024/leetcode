import java.util.*;

class CheckIfStringContainsAllBinaryCodesOfSizeK {

    public boolean hasAllCodes(String s, int k) {
        int need = 1 << k;
        Set<String> set = new HashSet<>();
        for (int i = k; i <= s.length(); i++) {
            String a = s.substring(i - k, i);
            if (!set.contains(a)) {
                set.add(a);
                need--;
                if (need == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    // Hash.
    public static boolean hasAllCodesI(String s, int k) {
        int need = 1 << k;
        boolean[] got = new boolean[need];
        int allOne = need - 1;
        int hashVal = 0;

        for (int i = 0; i < s.length(); i++) {
            // calculate hash for s.substr(i-k+1,i+1)
            hashVal = ((hashVal << 1) & allOne) | (s.charAt(i) - '0');
            // hash only available when i-k+1 > 0
            if (i >= k - 1 && !got[hashVal]) {
                got[hashVal] = true;
                need--;
                if (need == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
