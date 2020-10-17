import java.util.*;

class BeautifulArrangement {

    // Time O(K) K is the number of valid permutations
    // Space O(N) because visited size is N+1
    public int countArrangement(int N) {
        boolean[] visited = new boolean[N + 1];
        int[] count = new int[1];
        helper(N, 1, visited, count);
        return count[0];
    }

    private void helper(int N, int pos, boolean[] visited, int[] count) {
        if (pos > N) {
            count[0]++;
        }
        for (int i = 1; i <= N; i++) {
            if (!visited[i] && (isDivisble(pos, i))) {
                visited[i] = true;
                helper(N, pos + 1, visited, count);
                visited[i] = false;
            }
        }
    }

    private boolean isDivisble(int a, int b) {
        return a % b == 0 || b % a == 0;
    }
}