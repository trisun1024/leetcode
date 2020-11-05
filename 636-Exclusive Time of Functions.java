import java.util.*;

class ExclusiveTimeOfFunctions {

    // Stack. Time = O(N); Space = O(N);
    public int[] exclusiveTime(int n, List<String> logs) {
        // stack to store current timestamp
        Deque<Integer> stack = new ArrayDeque<>();
        // store each function id
        int[] res = new int[n];
        int prev = 0;
        for (int i = 0; i < logs.size(); i++) {
            String[] s = logs.get(i).split(":");
            if (i == 0 || s[1].equals("start")) {
                if (!stack.isEmpty()) {
                    res[stack.peekFirst()] += Integer.parseInt(s[2]) - prev;
                }
                stack.offerFirst(Integer.parseInt(s[0]));
                prev = Integer.parseInt(s[2]);
            } else {
                res[stack.peekFirst()] += Integer.parseInt(s[2]) - prev + 1;
                stack.pollFirst();
                prev = Integer.parseInt(s[2]) + 1;
            }
        }
        return res;
    }
}