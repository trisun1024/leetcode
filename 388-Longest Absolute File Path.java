import java.util.*;

class LongestAbsoluteFilePath {

    // Stack. Time = O(N);
    public int lengthLongestPath(String input) {
        int maxLen = 0;
        if (input == null || input.length() == 0) {
            return maxLen;
        }
        // store the level of path
        Deque<Integer> stack = new ArrayDeque<>();
        stack.offerFirst(0);
        String[] inputArray = input.split("\n");
        for(String s: inputArray) {
            int level = s.lastIndexOf("\t") + 1;
            // if cur level is the same level or bigger than the previous, poll out to the same
            while(level +1 < stack.size()) {
                stack.pollFirst();
            }
            int len = stack.peekFirst() + s.length() -level + 1;
            stack.offerFirst(len);
            if(s.contains(".")) {
                maxLen = Math.max(maxLen, len-1);
            }
        }
        return maxLen;
    }
}