import java.util.*;

class Solution {
    public boolean confusingNumber(int N) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);
        int newNum = 0;
        int old = N;
        while (old != 0) {
            int num = old % 10;
            old = old / 10;
            if (!map.containsKey(num)) {
                return false;
            } else {
                newNum = newNum * 10 + map.get(num);
            }
        }
        // System.out.println(newNum);
        return newNum != N;
    }
}