class Solution {
    public int numUniqueEmails(String[] emails) {
        if (emails.length == 0)
            return 0;
        HashSet<String> res = new HashSet<String>();
        for (String email : emails) {
            String[] s = email.split("@");
            if (s.length > 0) {
                StringBuilder sb = new StringBuilder();
                for (char c : s[0].toCharArray()) {
                    if (c == '+') {
                        break;
                    } else if (c != '.') {
                        sb.append(c);
                    }
                }
                sb.append("@" + s[1]);
                System.out.println(sb.toString());
                if (!res.contains(sb.toString()))
                    res.add(sb.toString());
            }
        }
        return res.size();
    }
}

// 4ms HashSet
class Solution2 {
    public int numUniqueEmails(String[] emails) {
        HashSet<String> set = new HashSet<>();
        for (String email : emails) {
            String s = find(email);
            set.add(s);
        }
        return set.size();
    }

    private String find(String s) {
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        int i = 0;
        while (i < s.length()) {
            if (!flag) {
                if (s.charAt(i) == '.') {
                    i++;
                } else if (s.charAt(i) == '+') {
                    while (s.charAt(i) != '@') {
                        i++;
                    }
                }
            }
            if (s.charAt(i) == '@') {
                flag = true;
            }
            sb.append(s.charAt(i));
            i++;
        }
        return sb.toString();
    }
}