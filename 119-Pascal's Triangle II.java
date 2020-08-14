import java.util.*;

class PascalTriangleII {

    // Brute Force
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0)
            return Arrays.asList(1);
        if (rowIndex == 1)
            return Arrays.asList(1, 1);

        List<Integer> prev = getRow(rowIndex - 1);
        List<Integer> curr = new ArrayList<>();

        curr.add(1);
        for (int i = 1; i < prev.size(); i++) {
            curr.add(prev.get(i) + prev.get(i - 1));
        }
        curr.add(1);
        return curr;
    }

    // DP
    public List<Integer> getRowI(int rowIndex) {
        List<Integer> res = new LinkedList<Integer>();
        for (int row = 0; row <= rowIndex; row++) {
            res.add(0, 1);
            for (int i = 1; i < row; i++)
                res.set(i, res.get(i) + res.get(i + 1));
        }
        return res;
    }

    // Math
    public List<Integer> getRowII(int rowIndex) {
        List<Integer> row = new ArrayList<>() {
            {
                add(1);
            }
        };

        for (int k = 1; k <= rowIndex; k++) {
            row.add((int) ((row.get(row.size() - 1) * (long) (n - k + 1)) / k));
        }

        return row;
    }
}
