class Solution {
    public boolean isNumber(String s) {
        s = s.trim();

        boolean point = false;
        boolean exp = false;
        boolean numberSeen = false;
        boolean numberAfterE = true;
        for (int i = 0; i < s.length(); i++) {
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
                numberAfterE = true;
            } else if (s.charAt(i) == '.') {
                if (exp || point) {
                    return false;
                }
                point = true;
            } else if (s.charAt(i) == 'e') {
                if (exp || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                exp = true;
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                if (i != 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }

        return numberSeen && numberAfterE;
    }
}