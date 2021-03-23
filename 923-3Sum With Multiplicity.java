import java.util.*;

class ThreeSumWithMultiplicity {

    // Three Pointers. Time = O(N^3); Space = O(1);
    public int threeSumMultiI(int[] arr, int target) {
        int MOD = 1_000_000_007;
        long ans = 0;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            // find the number of i < j < k with A[j] + A[k] == T, where T = target - A[i].
            int subTarget = target - arr[i];
            int j = i + 1;
            int k = arr.length - 1;
            while (j < k) {
                if (arr[j] + arr[k] < subTarget) {
                    j++;
                } else if (arr[j] + arr[k] > subTarget) {
                    k--;
                } else if (arr[j] != arr[k]) {
                    // We have A[j] + A[k] == T. Let's count "left": the number of A[j] == A[j+1] ==
                    // A[j+2] == ... And similarly for "right".
                    int left = 1;
                    int right = 1;
                    while (j + 1 < k && arr[j] == arr[j + 1]) {
                        left++;
                        j++;
                    }
                    while (k - 1 > j && arr[k] == arr[k - 1]) {
                        right++;
                        k--;
                    }
                    ans += left * right;
                    ans %= MOD;
                    j++;
                    k--;
                } else {
                    ans += (k - j + 1) * (k - j) / 2;
                    ans %= MOD;
                    break;
                }
            }
        }
        return (int) ans;
    }

    // Adapt with Three Sum. Time = O(N^2); Space = O(N);
    public int threeSumMulti(int[] A, int target) {
        int MOD = 1_000_000_007;

        // Initializing as long saves us the trouble of
        // managing count[x] * count[y] * count[z] overflowing later.
        long[] count = new long[101];
        int uniq = 0;
        for (int x : A) {
            count[x]++;
            if (count[x] == 1) {
                uniq++;
            }
        }

        int[] keys = new int[uniq];
        int t = 0;
        for (int i = 0; i <= 100; ++i) {
            if (count[i] > 0) {
                keys[t++] = i;
            }
        }

        long ans = 0;
        // Now, let's do a 3sum on "keys", for i <= j <= k.
        // We will use count to add the correct contribution to ans.

        for (int i = 0; i < keys.length; ++i) {
            int x = keys[i];
            int T = target - x;
            int j = i, k = keys.length - 1;
            while (j <= k) {
                int y = keys[j], z = keys[k];
                if (y + z < T) {
                    j++;
                } else if (y + z > T) {
                    k--;
                } else { // # x+y+z == T, now calc the size of the contribution
                    if (i < j && j < k) {
                        ans += count[x] * count[y] * count[z];
                    } else if (i == j && j < k) {
                        ans += count[x] * (count[x] - 1) / 2 * count[z];
                    } else if (i < j && j == k) {
                        ans += count[x] * count[y] * (count[y] - 1) / 2;
                    } else { // i == j == k
                        ans += count[x] * (count[x] - 1) * (count[x] - 2) / 6;
                    }

                    ans %= MOD;
                    j++;
                    k--;
                }
            }
        }

        return (int) ans;
    }

}