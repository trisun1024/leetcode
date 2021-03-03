import java.util.*;

class ValidateStackSequences {

    // Stack & Greedy T = O(N) S = O(N)
    public boolean validateStackSequencesI(int[] pushed, int[] popped) {
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
        for (int x : pushed) {
            pushed[i++] = x;
            while (i > 0 && pushed[i - 1] == popped[j]) {
                i--;
                j++;
            }
        }
        return j == n;
    }

    //
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length == 0 && popped.length == 0) {
            return true;
        }
        if (pushed.length == 0 || popped.length == 0) {
            return false;
        }
        int i = 0;
        int j = 0;
        while (i < pushed.length && j < popped.length) {
            if (i == -1) {
                i = 0;
            }
            if (pushed[i] != popped[j]) {
                i++;
            } else {
                pushed[i] = -1;
                j++;
                i--;
                while (i >= 0 && pushed[i] == -1) {
                    i--;
                }
            }
            System.out.println(i + "," + j);
        }
        return i == -1 && j == popped.length;
    }
}