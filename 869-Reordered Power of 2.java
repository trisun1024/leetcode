import java.util.*;

class ReorderedPowerOf2 {

    // Permutation. Time = O((log(N)! * log(N));
    public boolean reorderedPowerOf2(int N) {
        String s = String.valueOf(N);
        return helper(s.toCharArray(), 0, 0);
    }

    private boolean helper(char[] arr, int index, int cur) {
        if (index == arr.length) {
            // System.out.println(cur);
            return isPowerOfTwo(arr, cur);
        }
        for (int i = index; i < arr.length; i++) {
            cur = cur * 10 + (arr[i] - '0');
            swap(arr, index, i);
            if (helper(arr, index + 1, cur)) {
                return true;
            }
            swap(arr, index, i);
            cur = cur / 10;
        }
        return false;
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private boolean isPowerOfTwo(char[] arr, int x) {
        if (arr[0] == '0') {
            return false;
        }
        while (x > 0 && ((x & 1) == 0)) {
            x >>= 1;
        }
        return x == 1;
    }

    // Counting. Time = O(log(N)^2); Space = O(log(N));
    public boolean reorderedPowerOf2I(int N) {
        int[] arr = count(N);
        for (int i = 0; i < 31; ++i) {
            if (Arrays.equals(arr, count(1 << i))) {
                return true;
            }
        }
        return false;
    }

    private int[] count(int N) {
        int[] ans = new int[10];
        while (N > 0) {
            ans[N % 10]++;
            N /= 10;
        }
        return ans;
    }

}