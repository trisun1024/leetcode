import java.util.*;

class RemoveInterval {

    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            int[] curr = intervals[i];
            if (curr[1] < toBeRemoved[0] || curr[0] > toBeRemoved[1]) {
                res.add(toList(curr));
            } else {
                // overlap or curr is within/larger toBeRemoved
                if (curr[0] < toBeRemoved[0]) {
                    res.add(toList(new int[] { curr[0], toBeRemoved[0] }));
                    if (curr[1] > toBeRemoved[1]) {
                        // no more to remove after this
                        res.add(toList(new int[] { toBeRemoved[1], curr[1] }));
                    }
                } else if (curr[0] >= toBeRemoved[0] && curr[1] <= toBeRemoved[1]) {
                    // do nothing

                } else {
                    // curr[1] > toBeRemoved[1]
                    curr[0] = toBeRemoved[1];
                    res.add(toList(curr));
                    // nothing else to remove anymore
                }
            }
        }
        return res;
    }

    private List<Integer> toList(int[] a) {
        List<Integer> res = new ArrayList<>();
        for (int i : a) {
            res.add(i);
        }
        return res;
    }
}
