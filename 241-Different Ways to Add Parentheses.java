import java.util.*;

class DiffWaysToCompute {

    // Recursion.
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        if (input.length() == 0) {
            return res;
        }
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                String first = input.substring(0, i);
                String second = input.substring(i + 1);
                List<Integer> partFirst = diffWaysToCompute(first);
                List<Integer> partSecond = diffWaysToCompute(second);
                for (Integer p1 : partFirst) {
                    for (Integer p2 : partSecond) {
                        int c = 0;
                        switch (input.charAt(i)) {
                            case '+':
                                c = p1 + p2;
                                break;
                            case '-':
                                c = p1 - p2;
                                break;
                            case '*':
                                c = p1 * p2;
                                break;
                        }
                        res.add(c);
                    }
                }
            }
        }
        if(res.size()==0) {
            res.add(Integer.valueOf(input));
        }
        return res;
    }

 
}