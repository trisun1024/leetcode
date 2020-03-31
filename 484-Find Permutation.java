import java.util.*;

/*
*
*/

class Solution {

    // Stack
    public int[] findPermutationIII(String s) {
        int[] res = new int[s.length() + 1];
        Deque<Integer> stack = new ArrayDeque<>();
        int j = 0;
        for (int i = 1; i <= s.length(); i++) {
            if (s.charAt(i - 1) == 'I') {
                stack.push(i);
                while (!stack.isEmpty())
                    res[j++] = stack.pop();
            } else
                stack.push(i);
        }
        stack.push(s.length() + 1);
        while (!stack.isEmpty())
            res[j++] = stack.pop();
        return res;
    }

    // reverse subarray T = O(N) S = O(1)
    public int[] findPermutation(String s) {
        int[] res = new int[s.length() + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = i + 1;
        }
        int i = 1;
        while (i <= s.length()) {
            int j = i;
            while (i <= s.length() && s.charAt(i - 1) == 'D') {
                i++;
            }
            reverse(res, j - 1, i - 1);
            i++;
        }
        return res;
    }

    private void reverse(int[] res, int i, int j) {
        while (i < j) {
            swap(res, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // two pointers
    public int[] findPermutationII(String s) {
        int[] res = new int[s.length() + 1];
        res[0] = 1;
        int i = 1;
        while (i <= s.length()) {
            res[i] = i + 1;
            int j = i;
            if (s.charAt(i - 1) == 'D') {
                while (i <= s.length() && s.charAt(i - 1) == 'D')
                    i++;
                for (int k = j - 1, c = i; k <= i - 1; k++, c--) {
                    res[k] = c;
                }
            } else
                i++;
        }
        return res;
    }
}
