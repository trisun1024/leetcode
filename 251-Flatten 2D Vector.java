import java.util.*;
class Vector2D {

    private List<Integer> nums;
    private int pos;

    public Vector2D(int[][] v) {
        this.nums = new ArrayList<>();
        this.pos = 0;
        for (int[] vi : v) {
            for (int i : vi) {
                nums.add(i);
            }
        }
    }

    public int next() {
        return nums.get(this.pos++);
    }

    public boolean hasNext() {
        return this.pos < this.nums.size();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such: Vector2D obj =
 * new Vector2D(v); int param_1 = obj.next(); boolean param_2 = obj.hasNext();
 */


 