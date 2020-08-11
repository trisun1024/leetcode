import java.util.*;

class FlipGameII {

    // Method 1. O(N!)
    public boolean canWin(String s) {
        return helper(s.toCharArray());
    }

    private boolean helper(char[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == '+' && array[i + 1] == '+') {
                array[i] = '-';
                array[i + 1] = '-';
                boolean win = !helper(array);
                array[i] = '+';
                array[i + 1] = '+';
                if (win) {
                    return true;
                }
            }
        }
        return false;
    }

    // Method 2. O(N^2)
    public boolean canWinI(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        Map<String, Boolean> map = new HashMap<>();
        return helperI(s, map);
    }

    private boolean helperI(String s, Map<String, Boolean> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                String opp = s.substring(0, i) + "--" + s.substring(i + 2);
                if (!helperI(opp, map)) {
                    map.put(s, true);
                    return true;
                }
            }
        }
        map.put(s, false);
        return false;
    }

    // Method 3.
    public boolean canWinII(String s) {
        s = s.replace('-', ' ');
        int num = 0;
        List<Integer> list = new ArrayList<>();
        for(String sub: s.split(" ")) {
            int subLen = sub.length();
            if(subLen == 0) {
                continue;
            }
            while(list.size() <= subLen) {
                char[] array = sub.toCharArray();
                int i = 0; int j = list.size()-2;
                while(i <= j) {
                    array[list.get(i) ^ list.get(j)] = '-';
                    i++;
                    j--;
                }
                list.add(new String(array).indexOf('+'));
            }
            num ^= list.get(subLen);
        }
        return num != 0;
    }
}