import java.util.*;

class BrickWall {

    // Brute Force. Time = O(N*M);
    public int leastBricksI(List<List<Integer>> wall) {
        int[] pos = new int[wall.size()];
        int sum = 0, res = Integer.MAX_VALUE;
        for (int el : wall.get(0)) {
            sum += el;
        }
        while (sum != 0) {
            int count = 0, mini = Integer.MAX_VALUE;
            for (int i = 0; i < wall.size(); i++) {
                List<Integer> row = wall.get(i);
                if (row.get(pos[i]) != 0) {
                    count++;
                } else {
                    pos[i]++;
                }
                mini = Math.min(mini, row.get(pos[i]));
            }
            for (int i = 0; i < wall.size(); i++) {
                List<Integer> row = wall.get(i);
                row.set(pos[i], row.get(pos[i]) - mini);
            }
            sum -= mini;
            res = Math.min(res, count);
        }
        return res;
    }

    // HashMap. Time = O(N); Space = O(M);
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> row : wall) {
            int sum = 0;
            for (int i = 0; i < row.size() - 1; i++) {
                sum += row.get(i);
                if (map.containsKey(sum)) {
                    map.put(sum, map.get(sum) + 1);
                } else {
                    map.put(sum, 1);
                }
            }
        }
        int res = wall.size();
        for (int key : map.keySet()) {
            res = Math.min(res, wall.size() - map.get(key));
        }
        return res;
    }
}