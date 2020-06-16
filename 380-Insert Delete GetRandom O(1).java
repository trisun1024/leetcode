import java.util.*;

class RandomizedSet {

    Random rand; // random number generator
    List<Integer> list; // store the list of input values
    Map<Integer, Integer> set; // store the relation of [input value, index of list];

    /** Initialize your data structure here. */
    public RandomizedSet() {
        set = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain
     * the specified element.
     */
    public boolean insert(int val) {
        if (set.containsKey(val)) {
            return false;
        }
        list.add(val);
        set.put(val, list.size() - 1);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified
     * element.
     */
    public boolean remove(int val) {
        if (!set.containsKey(val)) {
            return false;
        }
        // get remove val's index
        int index = set.get(val);
        // update set last element's index to the val's index
        int lastElement = list.get(list.size() - 1);
        list.set(index, lastElement);
        set.put(lastElement, index);
        // remove the last element of list, and remove set contains val
        list.remove(list.size() - 1);
        set.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet(); boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val); int param_3 = obj.getRandom();
 */