import java.util.*;

class FlipGame {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        char[] array = s.toCharArray();
        for (int i = 0; i < s.length() - 1; i++) {
            if (array[i] == '+' && array[i + 1] == '+') {
                array[i] = '-';
                array[i + 1] = '-';
                res.add(new String(array));
                array[i] = '+';
                array[i + 1] = '+';
            }
        }
        return res;
    }
}