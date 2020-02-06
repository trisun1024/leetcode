import java.util.*;

class Solution {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        helper(res, S);
        return res;
    }

    private boolean helper(List<Integer> res, String S) {
        // termination condition
        if (res.size() > 2 && S.length() == 0) {
            return true;
        }
        for (int i = 0; i < S.length(); i++) {
            // case 1: 01
            if (S.charAt(0) == '0' && i > 0)
                break;
            long val = Long.valueOf(S.substring(0, i + 1));
            int size = res.size();
            // current val greater than max int
            if (val > Integer.MAX_VALUE) {
                break;
            }
            // next value is not the
            if (size >= 2 && val > res.get(size - 1) + res.get(size - 2)) {
                break;
            }
            if (size < 2 || val == res.get(size - 1) + res.get(size - 2)) {
                res.add((int) val);
                if (helper(res, S.substring(i + 1))) {
                    return true;
                }
                res.remove(res.size() - 1);
            }
        }
        return false;
    }

    // substring require extra time, use index to reduce the time complexity
    public List<Integer> splitIntoFibonacciII(String S) {
        List<Integer> ans = new ArrayList<>();
        helper(ans, S, 0);
        return ans;
    }

    private boolean helper(List<Integer> res, String S, int start) {
        if (start == S.length() && res.size() >= 3) {
            return true;
        }
        long num = 0;
        for (int i = start; i < S.length(); i++) {
            if (S.charAt(start) == '0' && i > start) {
                break;
            }
            num = num * 10 + (S.charAt(i) - '0');
            if (num > Integer.MAX_VALUE) {
                break;
            }
            if (res.size() >= 2 && res.get(res.size() - 1) + res.get(res.size() - 2) < num) {
                break;
            }
            if (res.size() <= 1 || res.get(res.size() - 1) + res.get(res.size() - 2) == num) {
                res.add((int) num);
                if (helper(res, S, i + 1)) {
                    return true;
                }
                res.remove(res.size() - 1);
            }
        }
        return false;
    }
}