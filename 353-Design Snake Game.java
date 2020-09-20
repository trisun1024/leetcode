import java.util.*;

class SnakeGame {

    /**
     * Initialize your data structure here.
     * 
     * @param width  - screen width
     * @param height - screen height
     * @param food   - A list of food positions E.g food = [[1,1], [1,0]] means the
     *               first food is positioned at [1,1], the second is at [1,0].
     */

    int index = 0;
    int width;
    int height;
    List<int[]> list = new ArrayList<>();
    int[][] food;
    int currRow = 0;
    int currCol = 0;
    int points = 0;

    public SnakeGame(int width, int height, int[][] food) {
        this.height = height;
        this.width = width;
        this.food = food;
        list.add(new int[] { 0, 0 });
    }

    /**
     * Moves the snake.
     * 
     * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     * @return The game's score after the move. Return -1 if game over. Game over
     *         when snake crosses the screen boundary or bites its body.
     */

    public int move(String direction) {
        if (direction.equals("U")) {
            currRow--;
        } else if (direction.equals("D")) {
            currRow++;
        } else if (direction.equals("L")) {
            currCol--;
        } else {
            currCol++;
        }

        if (currRow < 0 || currCol < 0 || currRow >= height || currCol >= width) {
            return -1;
        }

        if (index < food.length && food[index][0] == currRow && food[index][1] == currCol) {
            points++;
            index++;
        } else {
            list.remove(0);
        }

        for (int[] x : list) {
            if (x[0] == currRow && x[1] == currCol) {
                return -1;
            }
        }
        list.add(new int[] { currRow, currCol });
        return points;
    }

}

/**
 * Your SnakeGame object will be instantiated and called as such: SnakeGame obj
 * = new SnakeGame(width, height, food); int param_1 = obj.move(direction);
 */