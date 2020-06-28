import java.util.*;

class Solution {

    // use array to store
    public int twoCitySchedCostII(int[][] costs) {
        int sumA = 0;
        int[] diffB = new int[costs.length];
        for (int i = 0; i < costs.length; i++) {
            sumA += costs[i][0];
            diffB[i] = costs[i][1] - costs[i][0];
        }
        Arrays.sort(diffB);
        for (int i = 0; i < costs.length / 2; i++)
            sumA += diffB[i];

        return sumA;
    }

    // sort array Time = O(N*log(N));
    public int twoCitySchedCost(int[][] costs) {
        // sort costs on |priceA-priceB| ascending order
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o1[1] - (o2[0] - o2[1]);
            }
        });
        int tot = 0;
        int n = costs.length / 2;
        for (int i = 0; i < n; i++) {
            tot += costs[i][0] + costs[i + n][1];
        }
        return tot;
    }
}