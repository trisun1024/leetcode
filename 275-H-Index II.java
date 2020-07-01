class HIndexII {

    // linear scan Time = O(N)
    // generally we increment the index until we find the value that is greater than size-cur_index
    public int hIndex(int[] citations) {
        int i = 0;
        int n = citations.length;
        for (int cit : citations) {
            if (cit > n - i) {
                return n - i;
            } else {
                i++;
            }
        }
        return 0;
    }

    // binary search Time = O(N*log(N))
    public int hIndexII(int[] citations) {
        int n = citations.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] == n - mid) {
                return n - mid;
            } else if (citations[mid] < n - mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return n - left;
    }
}