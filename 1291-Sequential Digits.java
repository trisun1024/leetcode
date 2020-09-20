import java.util.*;

class SequentialDigits {

    // Sliding Window. Time = O(N^2); Space = O(1);
    public List<Integer> sequentialDigitsI(int low, int high) {
        List<Integer> res = new ArrayList<>();
        String sample = "123456789";
        int n = 10;
        int lowLen = String.valueOf(low).length();
        int highLen = String.valueOf(high).length();
        for (int len = lowLen; len <= highLen; len++) {
            for (int start = 0; start < n - len; start++) {
                int num = Integer.parseInt(sample.substring(start, start + len));
                if (num >= low && num <= high) {
                    res.add(num);
                }
            }
        }
        return res;
    }

    // Queue.
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < 10; i++)
            q.add(i); // add 1 - 9 all digits in queue
        while (q.size() > 0) {
            int curr = q.poll(); // poll curr from queue
            if (curr >= low && curr <= high)
                result.add(curr); // check if current >= low and <= high add
            int lastDigit = curr % 10;
            int next = curr * 10 + lastDigit + 1; // generate next num
            if (lastDigit < 9 && next <= high)
                q.add(next); // check if next num <= high add in queue
        }
        return result;
    }
}