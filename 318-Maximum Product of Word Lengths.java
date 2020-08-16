class MaximumProductOfWordLengths {

    // product 
    public int maxProduct(String[] words) {
        int max = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (!match(words[i], words[j])) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }

    private boolean match(String a, String b) {
        int[] chars = new int[26];
        for (char c : a.toCharArray()) {
            chars[c - 'a']++;
        }
        for (char c : b.toCharArray()) {
            if (chars[c - 'a'] != 0) {
                return true;
            }
        }
        return false;
    }

    // bit operator
    public int maxProductI(String[] words) {
        int l = words.length;
        int[] mask = new int[l];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                mask[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }

        int ans = 0;
        for (int i = 0; i < l - 1; i++) {
            for (int j = i + 1; j < l; j++) {
                if ((mask[i] & mask[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }
}