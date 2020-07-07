class PaintHouse {

    // DP 
    public int minCostI(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        for (int n = costs.length - 2; n >= 0; n--) {
            // Total cost of painting the nth house red.
            costs[n][0] += Math.min(costs[n + 1][1], costs[n + 1][2]);
            // Total cost of painting the nth house green.
            costs[n][1] += Math.min(costs[n + 1][0], costs[n + 1][2]);
            // Total cost of painting the nth house blue.
            costs[n][2] += Math.min(costs[n + 1][0], costs[n + 1][1]);
        }
        return Math.min(Math.min(costs[0][0], costs[0][1]), costs[0][2]);
    }

    // DP with Optimize space 
    public int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int[] previousRow = costs[costs.length - 1];
        for (int n = costs.length - 2; n >= 0; n--) {
            /*
             * PROBLEMATIC CODE IS HERE This line here is NOT making a copy of the original,
             * it's simply making a reference to it Therefore, any writes into currentRow
             * will also be written into "costs". This is not what we wanted!
             */
            int[] currentRow = costs[n];

            // Total cost of painting the nth house red.
            currentRow[0] += Math.min(previousRow[1], previousRow[2]);
            // Total cost of painting the nth house green.
            currentRow[1] += Math.min(previousRow[0], previousRow[2]);
            // Total cost of painting the nth house blue.
            currentRow[2] += Math.min(previousRow[0], previousRow[1]);
            previousRow = currentRow;
        }

        return Math.min(Math.min(previousRow[0], previousRow[1]), previousRow[2]);
    }
}