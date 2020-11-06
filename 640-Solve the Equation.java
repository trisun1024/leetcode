import java.util.*;

class SolveEquation {

    // Partioning Coefficients.
    public String solveEquation(String equation) {
        String[] str = equation.split("=");
        int left = 0, right = 0;
        for (String l : breakIt(str[0])) {
            if (l.indexOf("x") >= 0) {
                left += Integer.parseInt(coeff(l));
            } else {
                right -= Integer.parseInt(l);
            }
        }

        for (String r : breakIt(str[1])) {
            if (r.indexOf("x") >= 0) {
                left -= Integer.parseInt(coeff(r));
            } else {
                right += Integer.parseInt(r);
            }
        }

        if (left == 0) {
            if (right == 0) {
                return "Infinite solutions";
            } else {
                return "No solution";
            }
        }
        return "x=" + right / left;
    }

    private List<String> breakIt(String s) {
        List<String> res = new ArrayList<>();
        String r = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (r.length() > 0) {
                    res.add(r);
                }
                r = "" + s.charAt(i);
            } else {
                r += s.charAt(i);
            }
        }
        res.add(r);
        return res;
    }

    private String coeff(String x) {
        if (x.length() > 1 && x.charAt(x.length() - 2) >= '0' && x.charAt(x.length() - 2) <= '9') {
            return x.replace("x", "");
        }
        return x.replace("x", "1");
    }

    // Using regex for spliting.
    public String solveEquationI(String equation) {
        String[] lr = equation.split("=");
        int lhs = 0, rhs = 0;
        for (String x : lr[0].split("(?=\\+)|(?=-)")) {
            if (x.indexOf("x") >= 0) {
                lhs += Integer.parseInt(coeff(x));
            } else {
                rhs -= Integer.parseInt(x);
            }
        }
        for (String x : lr[1].split("(?=\\+)|(?=-)")) {
            if (x.indexOf("x") >= 0) {
                lhs -= Integer.parseInt(coeff(x));
            } else {
                rhs += Integer.parseInt(x);
            }
        }
        if (lhs == 0) {
            if (rhs == 0) {
                return "Infinite solutions";
            } else {
                return "No solution";
            }
        }
        return "x=" + rhs / lhs;
    }

}