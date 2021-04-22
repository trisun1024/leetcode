
class MissingNumberInArithmeticProgression {

    // Linear Search.
    public int missingNumber(int[] arr) {
        int n = arr.length;
        int diff = (arr[n - 1] - arr[0]) / n;
        int expect = arr[0];
        for (int num : arr) {
            if (num != expect) {
                return expect;
            }
            expect += diff;
        }
        return expect;
    }

    // Binary Search.
    public int missingNumberI(int[] arr) {
        int n = arr.length;
        int diff = (arr[n - 1] - arr[0]) / n;
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == arr[0] + mid * diff) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return arr[0] + diff * left;
    }
}