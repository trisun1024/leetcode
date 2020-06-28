import java.util.*;

class Solution {

    // Stack Time = O(N); Space = O(N) 
    public boolean isValid(String s) {
        // use stack to store the parentheses;
        Deque<Character> stack = new ArrayDeque<>();
        // if we see the left side, we push the right side in, if we see right side, we check the stack if stack is empty or stack.pop != current character, we return false;
        // return the stack is empty or not 
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.offerFirst(')');
            } else if (c == '[') {
                stack.offerFirst(']');
            } else if (c == '{') {
                stack.offerFirst('}');
            } else if (stack.isEmpty() || stack.pollFirst() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}