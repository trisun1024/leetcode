class StringCompression {

    // three pointers 
    public int compress(char[] chars) {
        int slow = 0;
        int anchor = 0;
        for (int fast = 0; fast < chars.length; fast++) {
            if (fast + 1 == chars.length || chars[fast + 1] != chars[fast]) {
                chars[slow++] = chars[anchor];
                if (fast > anchor) {
                    for (char c : ("" + (fast - anchor + 1)).toCharArray()) {
                        chars[slow++] = c;
                    }
                }
                anchor = fast + 1;
            }
        }
        return slow;
    }

    // two pointers 
    public int compressII(char[] chars) {
        int n = chars.length;
        int slow = 0, fast = 0;
        while (fast < n) {
            int begin = fast;
            while (fast + 1 < n && chars[fast] == chars[fast + 1]) {
                fast++;
            }
            int count = fast - begin + 1;
            if (count == 1) {
                chars[slow++] = chars[fast++];
            } else {
                chars[slow++] = chars[begin];
                slow = addDigit(chars, slow, count);
                fast++;
            }
        }
        return slow;
    }

    private int addDigit(char[] chars, int slow, int count) {
        int begin = slow;
        while (count != 0) {
            chars[slow++] = (char) (count % 10 + '0');
            count /= 10;
        }
        reverse(chars, begin, slow - 1);
        return slow;
    }

    private void reverse(char[] chars, int start, int end) {
        while (start < end) {
            swap(chars, start++, end--);
        }
    }

    private void swap(char[] chars, int a, int b) {
        char tmp = chars[a];
        chars[a] = chars[b];
        chars[b] = tmp;

    }
}