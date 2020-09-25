import java.util.*;

class FindDifference {

    // Sort. Time = O(N*log(N)); Space = O(N);
    public char findTheDifference(String s, String t) {
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();
        Arrays.sort(sa);
        Arrays.sort(ta);
        int i = 0;
        while (i < s.length()) {
            if (sa[i] != ta[i]) {
                return ta[i];
            }
            i++;
        }
        return ta[i];
    }

    // HashMap. Time = O(N); Space = O(1);
    public char findTheDifferenceI(String s, String t) {
        char extraChar = '\0';
        // Prepare a counter for string s.
        // This hash map holds the characters as keys and respective frequency as value.
        HashMap<Character, Integer> counterS = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            counterS.put(ch, counterS.getOrDefault(ch, 0) + 1);
        }

        // Iterate through string t and find the character which is not in s.
        for (int i = 0; i < t.length(); i += 1) {
            char ch = t.charAt(i);
            int countOfChar = counterS.getOrDefault(ch, 0);
            if (countOfChar == 0) {
                extraChar = ch;
                break;
            } else {

                // Once a match is found we reduce frequency left.
                // This eliminates the possibility of a false match later.
                counterS.put(ch, countOfChar - 1);
            }
        }
        return extraChar;
    }

    // Array. Time = O(N); Space = O(1);
    public char findTheDifferenceII(String s, String t) {
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c - 'a']--;
        }
        for (char c : t.toCharArray()) {
            arr[c - 'a']++;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                return (char) (i + 'a');
            }
        }
        return '\0';
    }

    // Bit Manipulation. Time = O(N); Space = O(1);
    public char findTheDifferenceIII(String s, String t) {
        // Initialize ch with 0, because 0 ^ X = X
        // 0 when XORed with any bit would not change the bits value.
        char ch = 0;
        // XOR all the characters of both s and t.
        for (int i = 0; i < s.length(); i += 1) {
            ch ^= s.charAt(i);
        }
        for (int i = 0; i < t.length(); i += 1) {
            ch ^= t.charAt(i);
        }
        // What is left after XORing everything is the difference.
        return ch;
    }
}