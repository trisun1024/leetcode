
class CreateSortedArrayThroughInstructions {

    // Segment Tree. Time = O(N*log(M)); Space = O(M);
    public int createSortedArray(int[] instructions) {
        final int K = (int) 1e5 + 1;
        int[] tree = new int[K * 2];

        long cost = 0;
        long MOD = (int) 1e9 + 7;
        for (int x : instructions) {
            cost += Math.min(query(0, x, tree, K), query(x + 1, K, tree, K));
            updateTree(x, 1, tree, K);
        }
        return (int) (cost % MOD);
    }

    // implement Segment Tree
    private void updateTree(int index, int value, int[] tree, int m) {
        index += m;
        tree[index] += value;
        for (index >>= 1; index > 0; index >>= 1)
            tree[index] = tree[index << 1] + tree[(index << 1) + 1];
    }

    private int query(int left, int right, int[] tree, int m) {
        int result = 0;
        for (left += m, right += m; left < right; left >>= 1, right >>= 1) {
            if ((left & 1) == 1)
                result += tree[left++];
            if ((right & 1) == 1)
                result += tree[--right];
        }
        return result;
    }

    // Binary Indexed Tree (BIT). Time = O(N*log(M)); Space = O(M);
    public int createSortedArrayI(int[] instructions) {
        int m = 100002;
        int[] bit = new int[m];
        long cost = 0;
        long MOD = 1000000007;

        for (int i = 0; i < instructions.length; i++) {
            int leftCost = query(instructions[i] - 1, bit);
            int rightCost = i - query(instructions[i], bit);
            cost += Math.min(leftCost, rightCost);
            update(instructions[i], 1, bit, m);
        }
        return (int) (cost % MOD);
    }

    // implement Binary Index Tree
    private void update(int index, int value, int[] bit, int m) {
        index++;
        while (index < m) {
            bit[index] += value;
            index += index & -index;
        }
    }

    private int query(int index, int[] bit) {
        index++;
        int result = 0;
        while (index >= 1) {
            result += bit[index];
            index -= index & -index;
        }
        return result;
    }

    // Merge Sort. Time = O(N*log(N)); Space = O(N);
    int[] smaller;
    int[] larger;
    int[][] temp; // record some temporal information

    public int createSortedArrayII(int[] instructions) {
        int n = instructions.length;
        smaller = new int[n];
        larger = new int[n];
        temp = new int[n][];
        long cost = 0;
        long MOD = 1000000007;

        int[][] arrSmaller = new int[n][];
        int[][] arrLarger = new int[n][];
        for (int i = 0; i < n; i++) {
            arrSmaller[i] = new int[] { instructions[i], i };
            arrLarger[i] = new int[] { instructions[i], i };
        }

        sortSmaller(arrSmaller, 0, n - 1);
        sortLarger(arrLarger, 0, n - 1);

        for (int i = 0; i < n; i++) {
            cost += Math.min(smaller[i], larger[i]);
        }
        return (int) (cost % MOD);
    }

    private void sortSmaller(int[][] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        sortSmaller(arr, left, mid);
        sortSmaller(arr, mid + 1, right);
        mergeSmaller(arr, left, right, mid);
    }

    private void mergeSmaller(int[][] arr, int left, int right, int mid) {
        // merge [left, mid] and [mid+1, right]
        int i = left;
        int j = mid + 1;
        int k = left;
        // use temp[left...right] to temporarily store sorted array
        while (i <= mid && j <= right) {
            if (arr[i][0] < arr[j][0]) {
                temp[k++] = arr[i];
                i++;
            } else {
                temp[k++] = arr[j];
                smaller[arr[j][1]] += i - left;
                j++;
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i];
            i++;
        }
        while (j <= right) {
            temp[k++] = arr[j];
            smaller[arr[j][1]] += i - left;
            j++;
        }
        // restore from temp
        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
    }

    private void sortLarger(int[][] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        sortLarger(arr, left, mid);
        sortLarger(arr, mid + 1, right);
        mergeLarger(arr, left, right, mid);
    }

    private void mergeLarger(int[][] arr, int left, int right, int mid) {
        // merge [left, mid] and [mid+1, right]
        int i = left;
        int j = mid + 1;
        int k = left;
        // use temp[left...right] to temporarily store sorted array
        while (i <= mid && j <= right) {
            if (arr[i][0] <= arr[j][0]) {
                temp[k++] = arr[i];
                i++;
            } else {
                temp[k++] = arr[j];
                larger[arr[j][1]] += mid - i + 1;
                j++;
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i];
            i++;
        }
        while (j <= right) {
            temp[k++] = arr[j];
            larger[arr[j][1]] += mid - i + 1;
            j++;
        }
        // restore from temp
        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
    }
}