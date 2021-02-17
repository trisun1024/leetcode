import java.util.*;

class LetterCasePermutation {

    // DFS.
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(S.toCharArray(), 0, sb, res);
        return res;
    }

    private void helper(char[] arr, int index, StringBuilder cur, List<String> res) {
        if (index == arr.length) {
            res.add(new String(cur));
            return;
        }
        char c = arr[index];
        if (c >= '0' && c <= '9') {
            cur.append(c);
            helper(arr, index + 1, cur, res);
            cur.deleteCharAt(cur.length() - 1);
        } else {
            cur.append(Character.toUpperCase(c));
            helper(arr, index + 1, cur, res);
            cur.deleteCharAt(cur.length() - 1);
            cur.append(Character.toLowerCase(c));
            helper(arr, index + 1, cur, res);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}