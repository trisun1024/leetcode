import java.util.*;

class OutputContestMatches {

    // Recursion + Bit Operation. Time = O(N); Space = O(N);
    public String findContestMatch(int n) {
        StringBuilder sb = new StringBuilder();
        helper(sb, 3, n, 1);
        return sb.toString();
    }

    void helper(StringBuilder sb, int sum, int n, int val) {
        if (sum > n + 1) {
            sb.append(val);
            return;
        }
        sb.append('(');
        helper(sb, (sum << 1) - 1, n, val);
        sb.append(',');
        helper(sb, (sum << 1) - 1, n, sum - val);
        sb.append(')');
    }

    // Simulation. Time = O(N*log(N)); Space = O(N*log(N));
    public String findContestMatchI(int n) {
        String[] team = new String[n];
        for (int i = 1; i <= n; ++i) {
            team[i - 1] = "" + i;
        }

        for (; n > 1; n /= 2) {
            for (int i = 0; i < n / 2; ++i) {
                team[i] = "(" + team[i] + "," + team[n - 1 - i] + ")";
            }
        }
        return team[0];
    }

    // Deque.
    public String findContestMatchII(int n) {
        if (n < 1)
           { return "";}
        Deque<String> d = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            d.offerLast(Integer.toString(i));
        }

        Deque<String> d2 = new ArrayDeque<>();
        while (d.size() > 1) {
            String left = d.pollFirst();
            String right = d.pollLast();
            String merged = merge(left, right);
            d2.offerLast(merged);
            if (d.size() <= 1) {
                d = d2;
                d2 = new ArrayDeque<>();
            }
        }

        return d.pollFirst();
    }

    private String merge(String left, String right) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(left);
        sb.append(",");
        sb.append(right);
        sb.append(")");
        return sb.toString();
    }
}