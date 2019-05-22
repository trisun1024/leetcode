class Solution {
    public int lengthOfLastWord(String s) {
        if (s.equals("") || s.trim().equals(""))
            return 0;
        String[] str = s.split(" ");
        return str[str.length - 1].length();
    }
}

/*
// The shortest
class Solution {
    public int lengthOfLastWord(String s) {
        return s.trim().length() - s.trim().lastIndexOf(" ") - 1;
    }
}
*/