
class ReverseStringII {

    // Two Pointers.
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        int l = 0, h = 0;
        while (h < s.length() && l < s.length()) {
            if (h - l + 1 == k || h == s.length() - 1) {
                reverse(arr, l, h);
                l += 2 * k;
            }
            h++;
        }
        return new String(arr);
    }

    private void reverse(char[] arr, int i, int j) {
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    // One Loop.
    public String reverseStrI(String s, int k) {
        char[] a = s.toCharArray();
        for (int st = 0; st < a.length; st += 2 * k) {
            int i = st;
            int j = Math.min(st + k - 1, a.length - 1);
            while (i < j) {
                char tmp = a[i];
                a[i++] = a[j];
                a[j--] = tmp;
            }
        }
        return new String(a);
    }

    // Bit Operations.
    public String reverseStrII(String s, int k) {
        if (k <= 1) {
            return s;
        }
        int idx = 0;
        char[] output = s.toCharArray();
        while (idx < s.length()) {
            reverse(output, idx, Math.min(idx + k - 1, output.length - 1));
            idx += (k << 1);
        }
        return new String(output);
    }
}
