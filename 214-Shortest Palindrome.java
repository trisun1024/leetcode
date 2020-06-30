class Solution {

    // Brute Force. Time = O(N^2); Space = O(N)
    public String shortestPalindromeI(String s) {
        int n = s.length();
        String rev = new StringBuilder(s).reverse().toString();
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (s.substring(0, n - i).equals(rev.substring(i))) {
                return rev.substring(0, i) + s;
            }
        }
        return "";
    }

    // Time = O(N^2); Space = O(N);
    public String shortestPalindromeII(String s) {
        int n = s.length();
        int i = 0;
        for (int j = n - 1; j >= 0; j--) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
            }
        }
        if (i == n) {
            return s;
        }
        String rem = s.substring(i, n);
        rem = new StringBuilder(rem).reverse().toString();
        return rem + shortestPalindromeII(s.substring(0, i)) + s.substring(i);
    }

    // similar two pointers
    public String shortestPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        int end = s.length() - 1;
        char[] arr = s.toCharArray();
        while (i < j) {
            if (arr[i] == arr[j]) {
                i++;
                j--;
            } else {
                i = 0;
                end--;
                j = end;
            }
        }
        return new StringBuilder(s.substring(end + 1)).reverse().toString() + s;
    }

    // KMP
    public String shortestPalindromeIII(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        // merge s and rev_s, separate by #;
        String ns = s + "#" + rev;
        int len = ns.length();
        int[] kmp = new int[len];
        for (int i = 1; i < len; i++) {
            int j = kmp[i - 1];
            while (j > 0 && ns.charAt(i) != ns.charAt(j)) {
                j = kmp[j - 1];
            }
            if (ns.charAt(i) == ns.charAt(j)) {
                ++j;
            }
            kmp[i] = j;
        }
        return rev.substring(0, s.length() - kmp[len - 1]) + s;
    }
}