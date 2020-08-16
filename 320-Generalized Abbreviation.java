import java.util.*;

class GeneralizedAbbreviation {

    // DFS. Leetcode is not match
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        helper(word.toCharArray(), new StringBuilder(), 0, res);
        return res;
    }

    private void helper(char[] array, StringBuilder cur, int index, List<String> res) {
        if (index == array.length) {
            res.add(cur.toString());
            return;
        }
        if (cur.length() == 0 || !Character.isDigit(cur.charAt(cur.length() - 1))) {
            for (int i = 1; i <= array.length - index; i++) {
                cur.append(i);
                helper(array, cur, index + i, res);
                cur.deleteCharAt(cur.length() - 1);
            }
        }
        cur.append(array[index]);
        helper(array, cur, index + 1, res);
        cur.deleteCharAt(cur.length() - 1);
    }
}