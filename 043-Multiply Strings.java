class Solution {
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int p : pos)
            if (!(sb.length() == 0 && p == 0))
                sb.append(p);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
// 1ms 
class Solution1 {
    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        int[] a = new int[m];
        int[] c = new int[m + n];
        int zeroth_pl = 0;
        for (int i = 0, k = m; i < m; i++) {
            a[--k] = num1.charAt(i) - '0';
        }
        for (int j = n - 1; j >= 0; j--) {
            add(c, a, num2.charAt(j) - '0', zeroth_pl++);
        }

        int temp, d = 0;
        for (int i = 0; i < c.length; i++) {
            temp = c[i] + d;
            c[i] = temp % 10;
            d = temp / 10;
        }
        int i = m + n;
        while (i > 0 && c[--i] == 0)
            ;
        i++;
        StringBuilder sb = new StringBuilder(i);
        while (i > 0) {
            sb.append((char) (c[--i] + '0'));
        }
        return sb.toString();
    }

    public void add(int[] c, int[] a, int b, int index) {
        for (int i = index, j = 0; j < a.length; i++, j++) {
            c[i] += (a[j] * b);
        }
    }

}