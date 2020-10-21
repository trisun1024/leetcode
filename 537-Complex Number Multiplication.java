
class ComplexNumberMultiplication {

    public String complexNumberMultiply(String a, String b) {
        int[] as = getPartNumber(a);
        int[] bs = getPartNumber(b);
        return (as[0] * bs[0] - as[1] * bs[1]) + "+" + (as[0] * bs[1] + as[1] * bs[0]) + "i";
    }

    private int[] getPartNumber(String s) {
        int[] res = new int[2];
        res = getNum(s, 0);
        res[1] = getNum(s, res[1] + 1)[0];
        return res;
    }

    private int[] getNum(String a, int start) {
        int[] res = new int[2];
        boolean flag = true;
        int temp = 0;
        if (a.charAt(start) == '-') {
            flag = false;
            start++;
        }
        while (a.charAt(start) != '+' && a.charAt(start) != 'i') {
            temp = temp * 10 + a.charAt(start) - '0';
            start++;
        }
        res[0] = temp * (flag ? 1 : -1);
        res[1] = start;
        return res;
    }

    // String Split.
    public String complexNumberMultiplyI(String a, String b) {
        String operation = "\\+";
        String[] aa = a.split(operation);
        String[] bb = b.split(operation);
        int c = -1;
        if (aa[0].charAt(0) != '-') {
            c = Integer.parseInt(aa[0]);
        } else {
            c = Integer.parseInt(aa[0].substring(1)) * -1;
        }
        int d = -1;
        if (aa[1].charAt(0) != '-') {
            d = Integer.parseInt(aa[1].substring(0, aa[1].length() - 1));
        } else {
            d = Integer.parseInt(aa[1].substring(1, aa[1].length() - 1)) * -1;
        }
        int e = -1;
        if (bb[0].charAt(0) != '-') {
            e = Integer.parseInt(bb[0]);
        } else {
            e = Integer.parseInt(bb[0].substring(1)) * -1;
        }
        int f = -1;
        if (bb[1].charAt(0) != '-') {
            f = Integer.parseInt(bb[1].substring(0, bb[1].length() - 1));
        } else {
            f = Integer.parseInt(bb[1].substring(1, bb[1].length() - 1)) * -1;
        }
        int p = c * e - d * f;
        int q = c * f + d * e;
        return String.valueOf(p) + "+" + String.valueOf(q) + "i";
    }

}