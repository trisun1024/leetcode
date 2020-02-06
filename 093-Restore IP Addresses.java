import java.util.*;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(s.toCharArray(), 0, 0, sb, res);
        return res;
    }

    private void helper(char[] ip, int level, int i, StringBuilder sb, List<String> res) {
        // termination condition
        if (level == 4) {
            // exact ip address
            if (sb.length() == ip.length + 4) {
                res.add(sb.substring(0, sb.length() - 1));
            }
            return;
        }
        // with 1 number
        if (i < ip.length) {
            helper(ip, level + 1, i + 1, sb.append(ip[i]).append("."), res);
            sb.delete(sb.length() - 2, sb.length());
        }
        // with 2 numbers
        if (i + 1 < ip.length) {
            char a = ip[i];
            char b = ip[i + 1];
            if (a != '0') {
                helper(ip, level + 1, i + 2, sb.append(a).append(b).append("."), res);
                sb.delete(sb.length() - 3, sb.length());
            }
        }
        // with 3 numbers
        if (i + 2 < ip.length) {
            char a = ip[i];
            char b = ip[i + 1];
            char c = ip[i + 2];
            if (a == '1' || (a == '2' && b >= '0' && b <= '4') || (a == '2' && b == '5' && c >= '0' && c <= '5')) {
                helper(ip, level + 1, i + 3, sb.append(a).append(b).append(c).append("."), res);
                sb.delete(sb.length() - 4, sb.length());
            }
        }
    }
}