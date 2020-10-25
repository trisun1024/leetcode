import java.util.*;

class DistributeCandies {

    // Brute Force. Time = O(N!); Space = O(1);
    public int distributeCandiesI(int[] candies) {
        int[] max = new int[] { 0 };
        permute(candies, 0, max);
        return max[0];
    }

    private void permute(int[] candies, int i, int[] max) {
        if (i == candies.length - 1) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < candies.length / 2; j++) {
                set.add(candies[j]);
            }
            max[0] = Math.max(max[0], set.size());
        }
        for (int j = i; i < candies.length; i++) {
            swap(candies, j, i);
            permute(candies, i + 1, max);
            swap(candies, j, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Better Brute Force. Time = O(N^2);
    public int distributeCandiesII(int[] candies) {
        int count = 0;
        for (int i = 0; i < candies.length && count < candies.length / 2; i++) {
            if (candies[i] != Integer.MIN_VALUE) {
                count++;
                for (int j = i + 1; j < candies.length; j++) {
                    if (candies[j] == candies[i]) {
                        candies[j] = Integer.MIN_VALUE;
                    }
                }
            }
        }
        return count;
    }

    // Sorting. Time = O(N*log(N)); Space = O(1);
    public int distributeCandiesIII(int[] candies) {
        Arrays.sort(candies);
        int count = 1;
        for (int i = 1; i < candies.length && count < candies.length / 2; i++)
            if (candies[i] > candies[i - 1]) {
                count++;
            }
        return count;
    }

    // HashSet. Time = O(N); Space = O(N);
    public int distributeCandies(int[] candies) {
        // Keep a set of different candy types
        Set<Integer> candySet = new HashSet<Integer>(candies.length);
        // Add all the candies to the set
        for (int i = 0; i < candies.length; i++) {
            candySet.add(candies[i]);
        }
        // Return the min of the size of the candy set and half the total candies
        return Math.min(candySet.size(), candies.length / 2);
    }



    
}