import java.util.*;

class HappyNumber {

    // Time = O(log(N)); Space = O(log(N));
    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    private int getNext(int n) {
        int totSum = 0;
        while (n > 0) {
            int rem = n % 10;
            n = n / 10;
            totSum += rem * rem;
        }
        return totSum;
    }

    // Floyd's Time = O(log(N)); Space = O(1);
    public boolean isHappyI(int n) {
        int s = n;
        int f = getNext(n);
        while (f != 1 && s != f) {
            s = getNext(s);
            f = getNext(getNext(f));
        }
        return f == 1;
    }
}