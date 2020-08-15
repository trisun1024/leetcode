class RemoveDuplicateLetters {

    // two pointers
    public String removeDuplicateLetters(String s) {
        // base case 
        if (s == null || s.length() == 0) {
            return "";
        }
        int[] count = new int[26];
        boolean[] added = new boolean[26];
        // count characters 
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        // res 
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            count[c - 'a']--;
            // if we found this character is already found then skip 
            if (added[c - 'a']) {
                continue;
            }
            while (sb.length() > 0) {
                int top = (int) sb.charAt(sb.length() - 1);
                if (count[top - 'a'] > 0 && top > c) {
                    sb.setLength(sb.length() - 1);
                    added[top - 'a'] = false;
                } else {
                    break;
                }
            }
            sb.append(c);
            added[c - 'a'] = true;
        }
        return sb.toString();
    }
}