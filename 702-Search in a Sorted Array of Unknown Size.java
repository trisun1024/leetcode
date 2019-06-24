// Binary Search
// 2^n up, and binary search down
class Solution {
    public int search(ArrayReader reader, int target) {
        int left = 0;
        int right = 2;
        while (reader.get(right) != 2147483647) {
            right *= 2;
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (reader.get(mid) == target) {
                return mid;
            } else if (reader.get(mid) > target || reader.get(mid) == 2147483647) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}