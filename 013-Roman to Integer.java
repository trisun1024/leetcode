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

    // use hashmap
    public int romanToIntII(String s) {
        Map<String, Integer> letterValues = new HashMap<>();
        letterValues.put("I", 1);
        letterValues.put("V", 5);
        letterValues.put("X", 10);
        letterValues.put("L", 50);
        letterValues.put("C", 100);
        letterValues.put("D", 500);
        letterValues.put("M", 1000);
        letterValues.put("IV", 4);
        letterValues.put("IX", 9);
        letterValues.put("XL", 40);
        letterValues.put("XC", 90);
        letterValues.put("CD", 400);
        letterValues.put("CM", 900);

        int res = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            char nextC = i < len - 1 ? s.charAt(i + 1) : ' ';
            String secChar = "" + c + nextC;
            if (letterValues.containsKey(secChar)) {
                res += letterValues.get(secChar);
                i++;
            } else {
                res += letterValues.get("" + c);
            }
        }
        return res;
    }
}