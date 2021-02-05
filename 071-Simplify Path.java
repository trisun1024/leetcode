import java.util.*;

class SimplifyPath {

    // Stack Time = O(N);
    public String simplifyPath(String path) {
        if (path.length() == 0) {
            return path;
        }
        Deque<String> stack = new ArrayDeque<>();
        String[] comp = path.split("/");
        for (String c : comp) {
            if (c.equals(".") || c.length() == 0) {
                continue;
            } else if (c.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pollFirst();
                }
            } else {
                stack.offerFirst(c);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append("/");
            res.append(stack.pollLast());
        }
        return res.length() > 0 ? res.toString() : "/";
    }

    // Recursion.
    public String simplifyPathI(String path) {
        String[] arr = path.split("/");
        List<String> res = new ArrayList<>();
        helper(arr, 0, res);
        if (res.size() == 0) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : res) {
            sb.append("/").append(s);
        }
        return sb.toString();
    }

    private void helper(String[] arr, int index, List<String> res) {
        if (index == arr.length) {
            return;
        }
        String cur = arr[index];
        if (cur.equals(".") || cur.length() == 0) {
            // skip
        } else if (cur.equals("..")) {
            if (res.size() > 0) {
                res.remove(res.size() - 1);
            }
        } else {
            res.add(arr[index]);
        }
        helper(arr, index + 1, res);
    }

}