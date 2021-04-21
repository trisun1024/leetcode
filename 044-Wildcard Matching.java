
class WildcardMatching {

    // DP. Time = O(S*P); Space = O(S*P);
    public boolean isMatch(String s, String p) {
        // base case
        if (p.equals(s) || p.equals("*") || (p.length() > 0 && allStar(p))) {
            return true;
        }
        if (p.length() == 0 || s.length() == 0) {
            return false;
        }
        int sLen = s.length();
        int pLen = p.length();

        // init all matrix except [0][0] element as False
        boolean[][] dp = new boolean[pLen + 1][sLen + 1];
        dp[0][0] = true;

        // DP compute
        for (int pi = 1; pi <= pLen; pi++) {
            // the current character in the pattern is '*'
            if (p.charAt(pi - 1) == '*') {
                int si = 1;
                // d[p_idx - 1][s_idx - 1] is a string-pattern match
                // on the previous step, i.e. one character before.
                // Find the first idx in string with the previous math.
                while (!dp[pi - 1][si - 1] && (si <= sLen)) {
                    si++;
                }
                // If (string) matches (pattern),
                // when (string) matches (pattern)* as well
                dp[pi][si - 1] = dp[pi - 1][si - 1];
                // If (string) matches (pattern),
                // when (string)(whatever_characters) matches (pattern)* as well
                while (si <= sLen) {
                    dp[pi][si++] = true;
                }
            }
            // the current character in the pattern is '?'
            else if (p.charAt(pi - 1) == '?') {
                for (int si = 1; si <= sLen; si++) {
                    dp[pi][si] = dp[pi - 1][si - 1];
                }
            }
            // the current character in the pattern is not '*' or '?'
            else {
                for (int si = 1; si <= sLen; si++) {
                    // Match is possible if there is a previous match
                    // and current characters are the same
                    dp[pi][si] = dp[pi - 1][si - 1] && (p.charAt(pi - 1) == s.charAt(si - 1));
                }
            }
        }
        return dp[pLen][sLen];
    }

    private boolean allStar(String p) {
        for (char c : p.toCharArray()) {
            if (c != '*') {
                return false;
            }
        }
        return true;
    }

    // Two Pointers.
    public boolean isMatchI(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        int sIdx = 0, pIdx = 0;
        int starIdx = -1, sTmpIdx = -1;

        while (sIdx < sLen) {
            // If the pattern caracter = string character
            // or pattern character = '?'
            if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                ++sIdx;
                ++pIdx;
            }
            // If pattern character = '*'
            else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                // Check the situation
                // when '*' matches no characters
                starIdx = pIdx;
                sTmpIdx = sIdx;
                ++pIdx;
            }
            // If pattern character != string character
            // or pattern is used up
            // and there was no '*' character in pattern
            else if (starIdx == -1) {
                return false;
            }
            // If pattern character != string character
            // or pattern is used up
            // and there was '*' character in pattern before
            else {
                // Backtrack: check the situation
                // when '*' matches one more character
                pIdx = starIdx + 1;
                sIdx = sTmpIdx + 1;
                sTmpIdx = sIdx;
            }
        }

        // The remaining characters in the pattern should all be '*' characters
        for (int i = pIdx; i < pLen; i++) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }

    public boolean isMatchII(String s, String p) {
        int i = 0;
        int j = 0;
        int starIndex = -1;
        int iIndex = -1;

        while (i < s.length()) {
            if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                ++i;
                ++j;
            } else if (j < p.length() && p.charAt(j) == '*') {
                starIndex = j;
                iIndex = i;
                j++;
            } else if (starIndex != -1) {
                j = starIndex + 1;
                i = iIndex + 1;
                iIndex++;
            } else {
                return false;
            }
        }

        while (j < p.length() && p.charAt(j) == '*') {
            ++j;
        }

        return j == p.length();
    }
}