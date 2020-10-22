import java.util.*;

class AsteroidCollision {

    // Stack. Time = O(N); Space = O(N);
    public int[] asteroidCollisionI(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i : asteroids) {
            collision: {
                while (!stack.isEmpty() && i < 0 && stack.peekFirst() > 0) {
                    if (stack.peekFirst() < -i) {
                        stack.pollFirst();
                        continue;
                    } else if (stack.peekFirst() == -i) {
                        stack.pollFirst();
                    }
                    break collision;

                }
                stack.offerFirst(i);
            }
        }

        int[] ans = new int[stack.size()];
        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] = stack.pollFirst();
        }
        return ans;
    }

    // Two Pointers + Inplace. Time = O(N); Space = O(1);
    public int[] asteroidCollision(int[] asteroids) {
        int i = 0, j = 0;
        for (; i < asteroids.length; i++) {
            for (; j > 0 && asteroids[j - 1] > 0 && asteroids[j - 1] < -asteroids[i]; j--)
                ;
            if (j == 0 || asteroids[i] > 0 || asteroids[j - 1] < 0) {
                asteroids[j++] = asteroids[i];
            } else if (asteroids[i] == -asteroids[j - 1]) {
                j--;
            }
        }
        return Arrays.copyOf(asteroids, j);
    }
}