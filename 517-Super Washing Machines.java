
class SuperWashingMachines {

    // Greedy.
    public int findMinMoves(int[] machines) {
        int len = machines.length;
        int total = 0;
        for (int m : machines) {
            total += m;
        }
        // base case if can't split even
        if (total % len != 0) {
            return -1;
        }
        int goal = total / len;
        int cur = 0;
        int res = 0;
        for (int m : machines) {
            cur += m - goal;
            res = Math.max(res, Math.max(Math.abs(cur), m - goal));
        }
        return res;
    }

    // DP.
    public int findMinMovesI(int[] machines) {
        int sum = 0;

        for (int machine : machines) {
            sum += machine;
        }

        if (sum % machines.length != 0) {
            return -1;
        }

        int target = sum / machines.length;
        int times = 0;
        int[] moves = new int[machines.length];

        for (int idx = 0; idx < machines.length - 1; idx++) {

            if (machines[idx] > target) {
                moves[idx] += machines[idx] - target;
                machines[idx + 1] += machines[idx] - target;
                machines[idx] = target;
                times = Math.max(times, moves[idx]);
            } else {
                moves[idx + 1] = target - machines[idx];
                machines[idx + 1] -= target - machines[idx];
                machines[idx] = target;
                times = Math.max(times, moves[idx + 1]);
            }
        }

        return times;
    }
}
