import java.util.*;

class StrobogrammaticNumber {

    // HashMap
    public boolean isStrobogrammatic(String num) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(9, 6);
        map.put(8, 8);
        StringBuilder sb = new StringBuilder();
        for (int i = num.length() - 1; i >= 0; i--) {
            int c = num.charAt(i) - '0';
            if (map.containsKey(c)) {
                sb.append(String.valueOf(map.get(c)));
            } else {
                return false;
            }
        }
        return sb.toString().equals(num);
    }

    // HashMap + Two Pointers.
    public boolean isStrobogrammaticI(String num) {
        Map<Character, Character> map = new HashMap<>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('8', '8');
        map.put('1', '1');
        map.put('0', '0');

        int i = 0;
        int j = num.length() - 1;
        while (i <= j) {
            if (!map.containsKey(num.charAt(i)) || num.charAt(j) != map.get(num.charAt(i))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}