class RobotBoundedInCircle {

    // One Pass. Time = O(N); Space = O(1);
    public boolean isRobotBounded(String instructions) {
        // north = 0, east = 1, south = 2, west = 3
        int[][] dirs = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };  
        // initial positions
        int x = 0;
        int y = 0;
        // facing north
        int index = 0;
        for (char c : instructions.toCharArray()) {
            if (c == 'L') {
                index = (index + 3) % 4;
            } else if (c == 'R') {
                index = (index + 1) % 4;
            } else {
                x += dirs[index][0];
                y += dirs[index][1];
            }
        }
        return (x == 0 && y == 0) || (index != 0);
    }
}