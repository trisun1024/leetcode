import java.util.*;
class PascalTriangleII {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new LinkedList<Integer>();
        long coeff = 1;
        for (int j = 0; j <= rowIndex; j++) {
            res.add((int) coeff);
            coeff = coeff * (rowIndex - j) / (j + 1); // calculating nCr using nCr-1
        }
        return res;
    }
}

/* 
class Solution {
    public List<Integer> getRow(int rowIndex) {
         List<Integer> res = new LinkedList<Integer>();
    for (int row = 0; row <= rowIndex; row++) {
        res.add(0, 1);
        for (int i = 1; i < row; i++)
            res.set(i, res.get(i) + res.get(i + 1));
    }
    return res;
    }
}
*/