import java.util.*;

class ZumaGame {

    // DFS.
    public int findMinStep(String board, String hand) {
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        int len = hand.length();
        for (int i = 0; i < len; i++) {
            int val = hand.charAt(i) - 65;
            if (hm.containsKey(val) == true) {
                hm.put(val, hm.get(val) + 1);
            } else {
                hm.put(val, 1);
            }
        }
        int[] ans = new int[] { Integer.MAX_VALUE };
        helper(board, hm, 0, ans);
        if (ans[0] == Integer.MAX_VALUE) {
            return -1;
        }
        return ans[0];
    }

    public void helper(String board, HashMap<Integer, Integer> hm, int tot, int[] ans) {
        int i = 0;
        String temp = board;
        int len = temp.length();
        if (len == 0) {
            ans[0] = Math.min(ans[0], tot);
            return;
        }
        // System.out.println(temp);
        while (i < len) {
            char ch = temp.charAt(i);
            int count = 1;
            int start = i;
            i++;
            while (i < len && temp.charAt(i) == ch) {
                count += 1;
                i++;
            }
            if (count >= 3) {
                temp = temp.substring(0, start) + temp.substring(i, len);
                helper(temp, hm, tot, ans);
                temp = board;
            } else {
                int val = ch - 65;
                if (hm.containsKey(val) == true && hm.get(val) >= 3 - count) {
                    hm.put(val, hm.get(val) - (3 - count));
                    temp = temp.substring(0, start) + temp.substring(i, len);
                    helper(temp, hm, tot + (3 - count), ans);
                    hm.put(val, hm.get(val) + (3 - count));
                    temp = board;
                }
            }
        }
    }

}