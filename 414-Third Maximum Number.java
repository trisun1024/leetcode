import java.util.*;

class ThirdMaximumNumber {

    // HashSet & PriorityQueue.
    public int thirdMax(int[] nums) {
        Set<Integer> set = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                if (i1.equals(i2)) {
                    return 0;
                }
                return i1 < i2 ? -1 : 1;
            }
        });
        for (int i : nums) {
            if (set.contains(i)) {
                continue;
            }
            pq.offer(i);
            set.add(i);
            if (pq.size() > 3) {
                pq.poll();
            }
        }
        if (pq.size() != 3) {
            while (pq.size() != 1) {
                pq.poll();
            }
            return pq.peek() == null ? -1 : pq.peek();
        } else {
            return pq.peek();
        }
    }

    // Basic
    public int thirdMaxI(int[] nums) {
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;

        for (int num : nums) {
            if (num > first) {
                third = second;
                second = first;
                first = num;
            } else if (num > second && num != first) {
                third = second;
                second = num;
            } else if (num > third && num != second && num != first) {
                third = num;
            }
        }
        if (third != Long.MIN_VALUE) {
            return (int) third;
        }
        return (int) first;

    }
}