import java.util.*;

class SingleRowKeyboard {

    // HashMap.
    public int calculateTime(String keyboard, String word) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < keyboard.length(); i++) {
            map.put(keyboard.charAt(i), i);
        }
        int time = 0;
        int prev = 0;
        for (char c : word.toCharArray()) {
            int index = map.get(c);
            time += Math.abs(index - prev);
            prev = index;
        }
        return time;
    }

    // Index Array.
    public int calculateTimeI(String keyboard, String word) {
        int[] map = new int[26];
        for (int i = 0; i < keyboard.length(); i++) {
            map[keyboard.charAt(i) - 'a'] = i;
        }
        int time = 0;
        int prev = 0;
        for (char c : word.toCharArray()) {
            int index = map[c - 'a'];
            time += Math.abs(index - prev);
            prev = index;
        }
        return time;
    }
}