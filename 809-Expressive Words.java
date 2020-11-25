class ExpressiveWords {
    public int expressiveWords(String S, String[] words) {
        int count = 0;
        for (String word : words) {
            if (check(S, word))
                count++;
        }
        return count;
    }

    public boolean check(String S, String W) {
        int n = S.length(), m = W.length(), j = 0;
        for (int i = 0; i < n; i++)
            if (j < m && S.charAt(i) == W.charAt(j))
                j++;
            else if (i > 1 && S.charAt(i) == S.charAt(i - 1) && S.charAt(i - 1) == S.charAt(i - 2))
                continue;
            else if (0 < i && i < n - 1 && S.charAt(i - 1) == S.charAt(i) && S.charAt(i) == S.charAt(i + 1))
                continue;
            else
                return false;
        return j == m;
    }
}