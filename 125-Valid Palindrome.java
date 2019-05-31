class Solution {
    public boolean isPalindrome(String s) {
        String cur = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(cur).reverse().toString();
        return cur.equals(rev);
    }
}