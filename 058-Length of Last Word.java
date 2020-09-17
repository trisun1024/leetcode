class LengthOfLastWord {

    // string split
    public int lengthOfLastWord(String s) {
        if (s.equals("") || s.trim().equals(""))
            return 0;
        String[] str = s.split(" ");
        return str[str.length - 1].length();
    }

    // two pointers
    public int lengthOfLastWordI(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }
        int slow = -1;
        int fast = 0;
        while (fast < s.length()) {
            if (s.charAt(fast) == ' ') {
                slow = fast;
            }
            fast++;
        }
        return fast - slow - 1;
    }
}
