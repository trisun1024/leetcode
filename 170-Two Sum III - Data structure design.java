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
