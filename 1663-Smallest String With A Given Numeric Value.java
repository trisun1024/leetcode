import java.util.*;

class SmallestStringWithGivenNumericValue {

    // Build Number From Left. Time = O(N); Space = O(1);
    public String getSmallestStringI(int n, int k) {
        char[] res = new char[n];
        for (int pos = 0; pos < n; pos++) {
            int posLeft = (n - pos - 1);
            if (k > posLeft * 26) {
                int add = k - (posLeft * 26);
                res[pos] = (char) ('a' + add - 1);
                k -= add;
            } else {
                res[pos] = 'a';
                k--;
            }
        }
        return new String(res);
    }

    // Build Number From Right.
    public String getSmallestStringII(int n, int k) {
        char[] result = new char[n];
        Arrays.fill(result, 'a');
        k -= n;
        for (int position = n - 1; position >= 0 && k > 0; position--) {
            int add = Math.min(k, 25);
            result[position] = (char) (result[position] + add);
            k -= add;
        }
        return new String(result);
    }

    // Optimized.
    public String getSmallestString(int n, int k) {
        char[] result = new char[n];
        for (int position = n - 1; position >= 0; position--) {
            int add = Math.min(k - position, 26);
            result[position] = (char) (add + 'a' - 1);
            k -= add;
        }
        return new String(result);
    }
}