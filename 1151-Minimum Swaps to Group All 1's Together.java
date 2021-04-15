import java.util.*;

class MinimumSwapsToGroupAllOnesTogether {

    // Sliding Window + Two Pointers. Time = O(N); Space = O(1);
    public int minSwaps(int[] data) {
        int ones = 0;
        for (int x : data) {
            ones += x;
        }
        int curOnes = 0;
        int maxOnes = 0;
        int left = 0, right = 0;
        while (right < data.length) {
            curOnes += data[right++];
            if (right - left > ones) {
                curOnes -= data[left++];
            }
            maxOnes = Math.max(maxOnes, curOnes);
        }
        return ones - maxOnes;
    }

    // Sliding Winde + Deque.
    public int minSwapsI(int[] data) {
        int ones = Arrays.stream(data).sum();
        int curOnes = 0, maxOnes = 0;
        // maintain a deque with the size = ones
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < data.length; i++) {
            // we would always add the new element into the deque
            deque.addLast(data[i]);
            curOnes += data[i];
            // when there are more than ones elements in the deque,
            // remove the leftmost one
            if (deque.size() > ones) {
                curOnes -= deque.removeFirst();
            }
            maxOnes = Math.max(maxOnes, curOnes);
        }
        return ones - maxOnes;
    }
}