import java.util.*;

class RecentCounter {

    List<Integer> times;

    public RecentCounter() {
        this.times = new ArrayList<>();
    }

    public int ping(int t) {
        this.times.add(t);
        int index = closestIndex(this.times, t - 3000);
        if (index == -1) {
            return 0;
        } else {
            return this.times.size() - index;
        }
    }

    private int closestIndex(List<Integer> array, int target) {
        int left = 0;
        int right = array.size() - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array.get(mid) == target) {
                return mid;
            } else if (array.get(mid) < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (array.get(left) >= target) {
            return left;
        } else if (array.get(right) >= target) {
            return right;
        } else {
            return -1;
        }
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter(); int param_1 = obj.ping(t);
 */