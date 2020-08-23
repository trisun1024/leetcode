import java.util.*;

class RandomPoint {

    int[][] rects;
    List<Integer> prefix;
    int tot;
    Random rand;

    public RandomPoint(int[][] rects) {
        this.prefix = new ArrayList<>();
        this.tot = 0;
        this.rand = new Random();
        this.rects = rects;
        for (int[] rect : rects) {
            tot += (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
            prefix.add(tot);
        }
    }

    public int[] pick() {
        int target = rand.nextInt(tot);
        int low = 0;
        int high = rects.length - 1;
        while (low != high) {
            int mid = (low + high) / 2;
            if (target >= prefix.get(mid)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        int[] rect = rects[low];
        int width = rect[2] - rect[0] + 1;
        int height = rect[3] - rect[1] + 1;
        int base = prefix.get(low) - width * height;
        return new int[] { rect[0] + (target - base) % width, rect[1] + (target - base) / width };
    }
}