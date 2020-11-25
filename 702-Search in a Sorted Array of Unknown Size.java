
class SearchInSortedArrayOfUnknownSize {

    // Binary Search. Time = O(2^N);
    public int search(ArrayReader reader, int target) {
        if (reader == null) {
            return -1;
        }
        int left = 0;
        int right = 1;
        while (reader.get(right) < target) {
            left = right;
            right = 2 * right;
        }
        return bs(reader, target, left, right);
    }

    private int bs(ArrayReader reader, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (reader.get(mid) == target) {
                return mid;
            } else if (reader.get(mid) > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}

class ArrayReader {
    public int get(int index) {
        return 0;
    }
}