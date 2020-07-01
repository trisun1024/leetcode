import java.util.*;

class GrayCode {

    // Bit Operator
    // The operator >> is shift right. The operator ^ is exclusive or.
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            result.add(i ^ i >> 1);
        }
        return result;
    }

    public List<Integer> grayCodeII(int n) {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < Math.pow(2, n); i++)
            result.add(i ^ i / 2);
        return result;
    }
}