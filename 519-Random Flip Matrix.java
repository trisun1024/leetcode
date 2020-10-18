import java.util.*;

class RandomFlipMatrix {

    class Solution {

        private Random rand;
        private Map<Integer, Integer> map;
        private int rows;
        private int cols;
        private int rem;

        public Solution(int n_rows, int n_cols) {
            this.rand = new Random();
            this.rows = n_rows;
            this.cols = n_cols;
            this.rem = rows * cols;
            this.map = new HashMap<>();
        }

        public int[] flip() {
            int r = this.rand.nextInt(rem--);
            int x = map.getOrDefault(r, r);
            map.put(r, map.getOrDefault(rem, rem));
            return new int[] { x / cols, x % cols };
        }

        public void reset() {
            map.clear();
            rem = rows * cols;
        }
    }
}