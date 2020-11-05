class ConsecutiveCharacters {

    public int maxPower(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        char prev = s.charAt(0);
        int count = 0;
        int max = 0;
        for (char c : s.toCharArray()) {
            if (c == prev) {
                count++;
            } else {
                prev = c;
                count = 1;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}