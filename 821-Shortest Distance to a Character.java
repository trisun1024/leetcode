
class ShortestDistanceToCharacter {

    // Min Array. Time = O(N);
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] res = new int[n];
        int prev = Integer.MIN_VALUE / 2;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                prev = i;
            }
            res[i] = i - prev;
        }
        prev = Integer.MAX_VALUE / 2;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                res[i] = Math.min(res[i], prev - i);
            }
        }
        return res;
    }

    // Two Pointers.
    public int[] shortestToCharI(String S, char C) {
        int[] result = new int[S.length()];
        int prev = Integer.MIN_VALUE;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == C) {
                prev = i;
            }
            if (prev >= 0) {
                result[i] = i - prev;
            }
        }
        prev = 0;
        for (int i = S.length() - 1; i >= 0; i--) {
            if (S.charAt(i) == C) {
                prev = i;
            }
            if (prev > 0) {
                if (result[i] > prev - i || result[i] == 0) {
                    result[i] = prev - i;
                }
            }
        }
        return result;
    }
}