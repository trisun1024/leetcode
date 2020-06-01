class Solution {

    // think of increasing from oct to 25
    public int titleToNumber(String s) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            num += num * 25 + (s.charAt(i) - 'A' + 1);
        }
        return num;
    }
}