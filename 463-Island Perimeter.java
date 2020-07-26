class IslandPerimeter {

    // simple calculation
    public int islandPerimeter(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    sum += calculate(grid, i, j);
                }
            }
        }
        return sum;
    }

    private int calculate(int[][] grid, int i, int j) {
        int sum = 0;
        sum += surround(grid, i + 1, j);
        sum += surround(grid, i - 1, j);
        sum += surround(grid, i, j + 1);
        sum += surround(grid, i, j - 1);
        return sum;
    }

    private int surround(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}