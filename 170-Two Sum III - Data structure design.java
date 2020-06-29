import java.util.*;

// add() Time = O(N); find() Time = O(1)
class TwoSum {

    List<Integer> data; // store numbers
    Set<Integer> sum; // store pairs of two sum.

    /** Initialize your data structure here. */
    public TwoSum() {
        data = new ArrayList<>();
        sum = new HashSet<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                int s = data.get(i) + number;
                sum.add(s);
            }
        }
        data.add(number);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        return sum.contains(value);
    }
}

// Sorted List add() Time = O(1); find() Time = O(N*log(N));
class TwoSumI {

    private List<Integer> nums;
    private boolean isSorted;

    /** Initialize your data structure here. */
    public TwoSumI() {
        this.nums = new ArrayList<>();
        this.isSorted = false;
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        this.nums.add(number);
        this.isSorted = false;
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        if (!isSorted) {
            Collections.sort(nums);
        }
        int l = 0;
        int r = nums.size() - 1;
        while (l < r) {
            int sum = nums.get(l) + nums.get(r);
            if (sum == value) {
                return true;
            } else if (sum < value) {
                l++;
            } else {
                r--;
            }
        }
        return false;
    }

}
