import java.util.*;

class OptimalAccountBalancing {

    public int minTransfers(int[][] transactions) {
        if (transactions == null || transactions.length == 0) {
            return 0;
        }
        Map<Integer, Integer> debts = new HashMap<>();
        for (int[] trans : transactions) {
            int x = trans[0];
            int y = trans[1];
            int z = trans[2];
            if (!debts.containsKey(x)) {
                debts.put(x, 0);
            }
            if (!debts.containsKey(y)) {
                debts.put(y, 0);
            }
            debts.put(x, debts.get(x) - z);
            debts.put(y, debts.get(y) + z);
        }
        // loop debts
        List<Integer> neg = new ArrayList<>();
        List<Integer> pos = new ArrayList<>();
        for (Integer key : debts.keySet()) {
            int d = debts.get(key);
            if (d < 0) {
                neg.add(-d);
            } else if (d > 0) {
                pos.add(d);
            }
        }
        int res = Integer.MAX_VALUE;
        Deque<Integer> negStack = new ArrayDeque<>();
        Deque<Integer> posStack = new ArrayDeque<>();
        // run 1000 times to with shuffle arrays to check the correctness
        for (int i = 0; i < 1000; i++) {
            for (Integer num : pos) {
                posStack.offerFirst(num);
            }
            for (Integer num : neg) {
                negStack.offerFirst(num);
            }
            int cur = 0;
            while (!negStack.isEmpty()) {
                int n = negStack.pollFirst();
                int p = posStack.pollFirst();
                cur++;
                if (n > p) {
                    negStack.offerFirst(n - p);
                } else if (n < p) {
                    posStack.offerFirst(p - n);
                }
            }
            res = Math.min(res, cur);
            Collections.shuffle(pos);
            Collections.shuffle(neg);
        }
        return res;
    }

    // DFS.
    public int minTransfersI(int[][] transactions) {
        // debt hashmap <user, debt>
        Map<Integer, Integer> balances = new HashMap<>();
        for (int[] t : transactions) {
            balances.put(t[0], balances.getOrDefault(t[0], 0) + t[2]); // He should be paid (positive).
            balances.put(t[1], balances.getOrDefault(t[1], 0) - t[2]); // He should pay back (negative).
        }
        List<Integer> nonZeroBalances = new ArrayList<>();
        for (int balance : balances.values()) {
            if (balance != 0)
                nonZeroBalances.add(balance);
        }
        return dfs(0, nonZeroBalances, nonZeroBalances.size());
    }

    private int dfs(int index, List<Integer> nonZeroBalances, int n) {
        if (index == n) {
            return 0; // If we are at the end of the list, no more to settle.
        }
        int curr = nonZeroBalances.get(index);
        if (curr == 0) {
            return dfs(index + 1, nonZeroBalances, n); // If balance is zero, skip it.
        }
        int min = Integer.MAX_VALUE; // Find the minimum.
        for (int i = index + 1; i < n; i++) { // Consider all the options.
            int elementI = nonZeroBalances.get(i);
            if (curr * elementI < 0) { // Check to find a negative positive pair: negative X positive<0.
                nonZeroBalances.set(i, curr + elementI);
                min = Math.min(min, 1 + dfs(index + 1, nonZeroBalances, n)); // 1+other transactions.
                nonZeroBalances.set(i, elementI);
                if (curr + elementI == 0) {
                    break; // This is the optimal solution a-a=0 -> 1 transaction.
                }
            }
        }
        return min;
    }
}
