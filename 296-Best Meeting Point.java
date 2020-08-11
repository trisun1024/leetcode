import java.util.*;

class BestMeetingPoint {
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = collectRows(grid);
        List<Integer> cols = collectCols(grid);
        return minDistance1D(rows) + minDistance1D(cols);
    }

    private List<Integer> collectRows(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rows.add(row);
                }
            }
        }
        return rows;
    }

    private List<Integer> collectCols(int[][] grid) {
        List<Integer> cols = new ArrayList<>();
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = 0; row < grid.length; row++) {
                if (grid[row][col] == 1) {
                    cols.add(col);
                }
            }
        }
        return cols;
    }

    private int minDistance1D(List<Integer> points) {
        int dist = 0;
        int i = 0;
        int j = points.size() - 1;
        while (i < j) {
            dist += points.get(j) - points.get(i);
            i++;
            j--;
        }
        return dist;
    }
}