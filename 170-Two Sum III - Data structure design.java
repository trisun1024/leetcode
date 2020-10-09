import java.util.*;

class TwoSumIII {



    // add() Time = O(1); find() Time = O(N)
    class TwoSum {

        Map<Integer, Integer> numCount;

        /** Initialize your data structure here. */
        public TwoSum() {
            numCount = new HashMap<>();
        }

        /** Add the number to an internal data structure.. */
        public void add(int number) {
            numCount.put(number, numCount.getOrDefault(number, 0) + 1);
        }

        /** Find if there exists any pair of numbers which sum is equal to the value. */
        public boolean find(int value) {
            for (Map.Entry<Integer, Integer> entry : this.numCount.entrySet()) {
                int complement = value - entry.getKey();
                if (complement != entry.getKey()) {
                    if (this.numCount.containsKey(complement))
                        return true;
                } else {
                    if (entry.getValue() > 1)
                        return true;
                }
            }
            return false;
        }

    }
}
