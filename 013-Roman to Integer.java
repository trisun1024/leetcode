public class Solution {
    public int romanToInt(String s) {
        char[] charArray = s.toCharArray();
        int res = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (i > 0 && matchup(charArray[i]) > matchup(charArray[i - 1])) {
                res += matchup(charArray[i]) - 2 * matchup(charArray[i - 1]);
            } else {
                res += matchup(charArray[i]);
            }
        }
        return res;
    }

    public int matchup(char c) {
        if (c == 'I')
            return 1;
        if (c == 'V')
            return 5;
        if (c == 'X')
            return 10;
        if (c == 'L')
            return 50;
        if (c == 'C')
            return 100;
        if (c == 'D')
            return 500;
        if (c == 'M')
            return 1000;
        return 0;
    }
}