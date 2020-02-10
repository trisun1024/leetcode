import java.util.*;

class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {

        Map<String, Integer> map = new HashMap<>();
        String cellsStr = Arrays.toString(cells);

        for (int i = 0; i < N; i++) {

            map.put(cellsStr, i); // keep track of the state and day

            cells = nextDay(cells); // advance the state
            cellsStr = Arrays.toString(cells); // serialize it

            // if we've seen this state before, fast-forward
            if (map.containsKey(cellsStr)) {
                int daysAgo = i + 1 - map.get(cellsStr); // how many days ago was it when we saw this state?
                int daysLeft = N - (i + 1); // how many days do we have left (if we don't fast-forward)?
                return doLastNDays(cells, daysLeft % daysAgo);
            }

        }

        // if we never get a cycle, the for-loop will exit on its own after N days.
        return cells;
    }

    // do N days of advancement
    private int[] doLastNDays(int[] cells, int N) {
        for (int i = 0; i < N; i++) {
            cells = nextDay(cells);
        }

        return cells;
    }

    // advance the state by one day
    private int[] nextDay(int[] cells) {
        int[] newCells = new int[8];

        for (int i = 1; i < cells.length - 1; i++) {
            if (cells[i - 1] == cells[i + 1])
                newCells[i] = 1;
            else
                newCells[i] = 0;
        }

        return newCells;
    }

}