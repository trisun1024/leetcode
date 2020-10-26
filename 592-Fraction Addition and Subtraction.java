import java.util.*;

class FractionAdditionAndSubtraction {

    // GCD.
    public String fractionAddition(String expression) {
        expression = expression.replaceAll("-", "\\+-");
        String[] fractions = expression.split("\\+");
        List<int[]> list = new ArrayList<>();
        int commonDenom = 1;
        for (String s : fractions) {
            if (s.length() == 0) {
                continue;
            }

            String[] a = s.split("/");
            int n = Integer.parseInt(a[0]);
            int d = Integer.parseInt(a[1]);

            if (commonDenom % d != 0)
                commonDenom *= d;
            list.add(new int[] { n, d });
        }

        int sum = 0;
        for (int[] a : list) {
            sum += (a[0] * commonDenom / a[1]);
        }

        int gcdSumDenom = gcd(Math.abs(sum), commonDenom);
        sum /= gcdSumDenom;
        commonDenom /= gcdSumDenom;

        return "" + sum + "/" + (commonDenom == 0 ? 1 : commonDenom);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    // LCM.
    public String fractionAdditionI(String expression) {
        String res = "+0/1";
        int i = 0;
        int n = expression.length();

        while (i < n) {
            int next = -1;
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
                next = iterateToNextOperator(i + 1, expression);
            } else {
                next = iterateToNextOperator(i, expression);
            }
            res = extractCurrentFunction(res, expression.substring(i, next));
            i = next;
        }
        if (res.charAt(0) == '0')
            return "0/1";
        if (res.charAt(0) == '-') {
            int a = Integer.valueOf(res.substring(1).split("/")[0]);
            int b = Integer.valueOf(res.substring(1).split("/")[1]);
            for (int j = Math.min(a, b); j >= 1; j--) {
                if (a % j == 0 && b % j == 0) {
                    return "-".concat(String.valueOf(a / j).concat("/").concat(String.valueOf(b / j)));
                }
            }
        }
        int a = Integer.valueOf(res.split("/")[0]);
        int b = Integer.valueOf(res.split("/")[1]);
        for (int j = Math.min(a, b); j >= 1; j--) {
            if (a % j == 0 && b % j == 0) {
                return String.valueOf(a / j).concat("/").concat(String.valueOf(b / j));
            }
        }
        return res;
    }

    public int iterateToNextOperator(int i, String s) {
        while (i < s.length() && s.charAt(i) != '+' && s.charAt(i) != '-') {
            i++;
        }
        return i;
    }

    public String extractCurrentFunction(String curr, String in) {
        int up1 = Integer.valueOf(curr.split("/")[0]);
        int down1 = Integer.valueOf(curr.split("/")[1]);
        int up2 = Integer.valueOf(in.split("/")[0]);
        int down2 = Integer.valueOf(in.split("/")[1]);
        int common = findLCM(down1, down2);
        int up3 = up1 * (common / down1) + up2 * (common / down2);
        return String.valueOf(up3).concat("/").concat(String.valueOf(common));
    }

    public int findLCM(int a, int b) {
        int tmp = Math.min(a, b);
        for (int i = tmp; i >= 1; i--) {
            if (a % i == 0 && b % i == 0)
                return i * (a / i) * (b / i);
        }
        return 1;
    }
}