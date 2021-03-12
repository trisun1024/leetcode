import java.util.*;

class LetterCombinationsOfPhoneNumbers {

    // Backtracking.
    public List<String> letterCombinationsI(String digits) {
        Map<String, String> phone = new HashMap<>();
        phone.put("2", "abc");
        phone.put("3", "def");
        phone.put("4", "ghi");
        phone.put("5", "jkl");
        phone.put("6", "mno");
        phone.put("7", "pqrs");
        phone.put("8", "tuv");
        phone.put("9", "wxyz");
        List<String> result = new ArrayList<String>();
        if (digits.length() != 0) {
            backtrack("", digits, phone, result);
        }
        return result;
    }

    private void backtrack(String comb, String next, Map<String, String> phone, List<String> result) {
        if (next.length() == 0)
            result.add(comb);
        else {
            String digit = next.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1);
                backtrack(comb + letter, next.substring(1), phone, result);
            }
        }
    }

    // DFS + Array Memory. Time = O(4^N);
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        String[] phone = new String[] { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        StringBuilder cur = new StringBuilder();
        dfs(digits.toCharArray(), 0, cur, res, phone);
        return res;
    }

    private void dfs(char[] digits, int index, StringBuilder cur, List<String> res, String[] phone) {
        if (index == digits.length) {
            res.add(new String(cur));
            return;
        }
        String s = phone[digits[index] - '0'];
        for (char c : s.toCharArray()) {
            cur.append(c);
            dfs(digits, index + 1, cur, res, phone);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}