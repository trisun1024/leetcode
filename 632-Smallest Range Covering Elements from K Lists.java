import java.util.*;

class SmallestRangeCoveringElementsFromKLists {

    // PriorityQueue. Time = O(N*log(M)); Space = O(M);
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Element> pq = new PriorityQueue<Element>(new Comparator<Element>() {
            public int compare(Element a, Element b) {
                return a.val - b.val;
            }
        });
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            Element e = new Element(i, 0, nums.get(i).get(0));
            pq.offer(e);
            max = Math.max(max, nums.get(i).get(0));
        }
        int range = Integer.MAX_VALUE;
        int start = -1, end = -1;
        while (pq.size() == nums.size()) {

            Element curr = pq.poll();
            if (max - curr.val < range) {
                range = max - curr.val;
                start = curr.val;
                end = max;
            }
            if (curr.idx + 1 < nums.get(curr.row).size()) {
                curr.idx = curr.idx + 1;
                curr.val = nums.get(curr.row).get(curr.idx);
                pq.offer(curr);
                if (curr.val > max) {
                    max = curr.val;
                }
            }
        }

        return new int[] { start, end };
    }

    class Element {
        int val;
        int idx;
        int row;

        public Element(int r, int i, int v) {
            val = v;
            idx = i;
            row = r;
        }
    }

    // Sorting.
    public int[] smallestRangeI(List<List<Integer>> nums) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            for (int num : nums.get(i)) {
                list.add(new int[] { num, i });
            }
        }

        Collections.sort(list, (a, b) -> a[0] - b[0]);

        // like minimum window substring where we find a window to cover at
        // least 1 number from all lists

        int[] res = new int[2];
        int count = 0, minLen = Integer.MAX_VALUE, left = 0;
        int[] map = new int[nums.size()];

        for (int right = 0; right < list.size(); right++) {
            if (map[list.get(right)[1]]++ == 0)
                count++;
            while (count == nums.size() && left <= right) {
                if (minLen > list.get(right)[0] - list.get(left)[0]) {
                    minLen = list.get(right)[0] - list.get(left)[0];
                    res = new int[] { list.get(left)[0], list.get(right)[0] };
                }
                if (--map[list.get(left++)[1]] == 0)
                    count--;
            }
        }
        return res;
    }
}