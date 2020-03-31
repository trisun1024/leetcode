import java.util.*;

class Solution {

    // Stack & Greedy T = O(N) S = O(N)
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int j = 0;
        for (int i : pushed) {
            stack.offerFirst(i);
            while (!stack.isEmpty() && j < n && stack.peek() == popped[j]) {
                stack.pollFirst();
                j++;
            }
        }
        return j == n;
    }

    // S = O(1)
    public boolean validateStackSequencesII(int[] pushed, int[] popped) {
        int n = pushed.length;
        int i = 0;
        int j = 0;
        for(int x :pushed) {
            pushed[i++] = x; 
            while( i > 0 && pushed[i-1]==popped[j]) {
                i--;
                j++;
            }
        }
        return j == n;
    }
}