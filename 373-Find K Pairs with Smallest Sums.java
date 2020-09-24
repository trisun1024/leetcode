import java.util.*;

class FindKPairsWithSmallestSums {

    // the two arrays are sorted. So there is at lease one element smallest element
    // used from the index 0 on both
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return res;
        }
        // int[] 0 store the element from nums1; 1 store the element from nums2; 2 store
        // the index of nums2
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] + a[1] - b[0] - b[1];
            }
        });
        for (int i = 0; i < nums1.length && i < k; i++) {
            pq.offer(new int[] { nums1[i], nums2[0], 0 });
        }
        while (k > 0 && !pq.isEmpty()) {
            int[] cur = pq.poll();
            List<Integer> curList = new ArrayList<>();
            curList.add(cur[0]);
            curList.add(cur[1]);
            res.add(curList);
            if (cur[2] != nums2.length - 1) {
                pq.offer(new int[] { cur[0], nums2[cur[2] + 1], cur[2] + 1 });
            }
            k--;
        }
        return res;
    }
}