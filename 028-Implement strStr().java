class ImplementString {
    public int strStr(String haystack, String needle) {
        int i = haystack.length();
        int j = needle.length();
        if (j == 0)
            return 0;
        if (j > i)
            return -1;
        for (int m = 0; m < i - j + 1; m++) {
            if (haystack.substring(m, m + j).equals(needle))
                return m;
        }
        return -1;
    }
}