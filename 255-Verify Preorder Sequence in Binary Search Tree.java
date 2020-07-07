import java.util.*;

class VerifyPreorderSequenceInBST {

    public boolean verifyPreorder(int[] preorder) {
        int low = Integer.MIN_VALUE;
        int lowPos = -1;
        for (int i : preorder) {
            if (i < low) {
                return false;
            }
            while (lowPos >= 0 && i > preorder[lowPos]) {
                low = preorder[lowPos--];
            }
            preorder[++lowPos] = i;
        }
        return true;
    }

    // Stack
    public boolean verifyPreorderI(int[] preorder) {
        if (preorder == null || preorder.length == 0)
            return true;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(preorder[0]);
        int lastRm = Integer.MIN_VALUE;

        for (int i = 1; i < preorder.length; i++) {
            if (preorder[i] < lastRm)
                return false;
            if (preorder[i] > preorder[i - 1]) {
                while (!stack.isEmpty() && preorder[i] > stack.peek())
                    lastRm = stack.pop();
            }
            stack.push(preorder[i]);
        }
        return true;
    }

    // Divide and Conquor. Time = O(N*log(N));
    public boolean verifyPreorderII(int[] preorder) {
        return verify(preorder, 0, preorder.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean verify(int[] preorder, int start, int end, int min, int max) {
        if (start > end) {
            return true;
        }
        int root = preorder[start];
        if (root > max || root < min) {
            return false;
        }

        int rightIndex = start;
        while (rightIndex <= end && preorder[rightIndex] <= root) {
            rightIndex++;
        }
        return verify(preorder, start + 1, rightIndex - 1, min, root) && verify(preorder, rightIndex, end, root, max);
    }
}