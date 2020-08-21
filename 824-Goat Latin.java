import java.util.*;

class GoatLatin {

    public String toGoatLatin(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }
        String[] str = S.split(" ");
        StringBuilder res = new StringBuilder();
        // set
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        for (int i = 0; i < str.length; i++) {
            helper(str[i], i, res, set);
            if (i != str.length - 1) {
                res.append(" ");
            }
        }
        return res.toString();
    }

    private void helper(String s, int index, StringBuilder res, Set<Character> set) {
        boolean addEnd = false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && !set.contains(s.charAt(i))) {
                addEnd = true;
                continue;
            }
            res.append(s.charAt(i));
        }
        if (addEnd) {
            res.append(s.charAt(0));
        }
        res.append("ma");
        while (index >= 0) {
            res.append("a");
            index--;
        }
    }
}