
class LongestMountainInArray {

    // Two Pointeers. Time = O(N);
    public int longestMountain(int[] A) {
        int N = A.length;
        int longest = 0;
        int start = 0;
        while (start < N) {
            // set end to the start point
            int end = start;
            if (end + 1 < N && A[end] < A[end + 1]) {
                // move end to the peak
                while (end + 1 < N && A[end] < A[end + 1]) {
                    end++;
                }
                // if end is a peak
                if (end + 1 < N && A[end] > A[end + 1]) {
                    // find the right-boundary, move end to there
                    while (end + 1 < N && A[end] > A[end + 1]) {
                        end++;
                    }
                    longest = Math.max(longest, end - start + 1);
                }
            }
            start = Math.max(end, start + 1);
        }
        return longest;
    }
}