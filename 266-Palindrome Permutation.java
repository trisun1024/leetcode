import java.util.*;

class PaldinromePermutation {

    // Singe Pass. Time = O(N); Space = O(1);
    public boolean canPermutePalindrome(String s) {
        int[] charCount = new int[128];
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++)
            charCount[str[i] - 1]++;
        int oddCount = 0;
        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] % 2 != 0)
                oddCount++;
        }
        if (str.length % 2 == 0)
            return oddCount == 0;
        else
            return oddCount == 1;
    }

    // HashSet. Time = O(N); Space = O(1);
    public boolean canPermutePalindromeI(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (!set.add(s.charAt(i))) {
                set.remove(s.charAt(i));
            }
        }
        return set.size() <= 1;
    }
}