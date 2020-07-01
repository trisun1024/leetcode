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
    
}