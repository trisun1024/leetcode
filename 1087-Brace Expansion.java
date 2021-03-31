import java.util.*;

class BraceExpansion {

    // DFS.
    public String[] expand(String s) {
        List<String> res = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        helper(s.toCharArray(), 0, cur, res);
        Collections.sort(res);

        String[] ans = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    private void helper(char[] arr, int index, StringBuilder cur, List<String> res) {
        if (index == arr.length) {
            res.add(cur.toString());
            return;
        }
        // System.out.println(index);
        if (arr[index] == '{') {
            int next = index + 1;
            for (int i = index + 1; i < arr.length; i++) {
                if (arr[i] == '}') {
                    next = i;
                    break;
                }
            }
            for (int i = index + 1; i < arr.length; i++) {
                if (arr[i] == '}') {
                    break;
                }
                if (arr[i] >= 'a' && arr[i] <= 'z') {
                    cur.append(arr[i]);
                    helper(arr, next + 1, cur, res);
                    cur.deleteCharAt(cur.length() - 1);
                }
            }
        } else {
            cur.append(arr[index]);
            helper(arr, index + 1, cur, res);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}