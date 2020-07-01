class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int totTank = 0;
        int curTank = 0;
        int startingPos = 0;
        for (int i = 0; i < n; i++) {
            totTank += gas[i] - cost[i];
            curTank += gas[i] - cost[i];
            if (curTank < 0) {
                startingPos = i + 1;
                curTank = 0;
            }
        }
        return totTank >= 0 ? startingPos : -1;
    }
}