import java.util.*;

class FurthestBuildingYouCanReach {

    // Min Heap. Time = O(N*log(N));
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> ladderAllocations = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < heights.length - 1; i++) {
            int climb = heights[i + 1] - heights[i];
            // If this is actually a "jump down", skip it.
            if (climb <= 0) {
                continue;
            }
            // Otherwise, allocate a ladder for this climb.
            ladderAllocations.add(climb);
            // If we haven't gone over the number of ladders, nothing else to do.
            if (ladderAllocations.size() <= ladders) {
                continue;
            }
            // Otherwise, we will need to take a climb out of ladder_allocations
            bricks -= ladderAllocations.remove();
            // If this caused bricks to go negative, we can't get to i + 1
            if (bricks < 0) {
                return i;
            }
        }
        // If we got to here, this means we had enough materials to cover every climb.
        return heights.length - 1;
    }

    // Max Heap.
    public int furthestBuildingI(int[] heights, int bricks, int ladders) {
        // Create a priority queue with a comparator that makes it behave as a max-heap.
        Queue<Integer> brickAllocations = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < heights.length - 1; i++) {
            int climb = heights[i + 1] - heights[i];
            // If this is actually a "jump down", skip it.
            if (climb <= 0) {
                continue;
            }
            // Otherwise, allocate a ladder for this climb.
            brickAllocations.add(climb);
            bricks -= climb;
            // If we've used all the bricks, and have no ladders remaining, then
            // we can't go any further.
            if (bricks < 0 && ladders == 0) {
                return i;
            }
            // Otherwise, if we've run out of bricks, we should replace the largest
            // brick allocation with a ladder.
            if (bricks < 0) {
                bricks += brickAllocations.remove();
                ladders--;
            }
        }
        // If we got to here, this means we had enough materials to cover every climb.
        return heights.length - 1;
    }

    // Binary Search.
    public int furthestBuildingII(int[] heights, int bricks, int ladders) {
        // find out lowest and highest bricks
        int lo = Integer.MAX_VALUE;
        int hi = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length - 1; i++) {
            int climb = heights[i + 1] - heights[i];
            if (climb <= 0) {
                continue;
            }
            lo = Math.min(lo, climb);
            hi = Math.max(hi, climb);
        }
        // no lowest climb means the graph is decreasing, then return last index
        if (lo == Integer.MAX_VALUE) {
            return heights.length - 1;
        }
        // binary search for index
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int[] result = solveWithGivenThreshold(heights, bricks, ladders, mid);
            int indexReached = result[0];
            int laddersRemaining = result[1];
            int bricksRemaining = result[2];
            if (indexReached == heights.length - 1) {
                return heights.length - 1;
            }
            if (laddersRemaining > 0) {
                hi = mid - 1;
                continue;
            }
            // Otherwise, check whether this is the "too low" or "just right" case.
            int nextClimb = heights[indexReached + 1] - heights[indexReached];
            if (nextClimb > bricksRemaining && mid > bricksRemaining) {
                return indexReached;
            } else {
                lo = mid + 1;
            }
        }
        return -1; // It always returns before here. But gotta keep Java happy.
    }

    public int[] solveWithGivenThreshold(int[] heights, int bricks, int ladders, int K) {
        int laddersUsedOnThreshold = 0;
        for (int i = 0; i < heights.length - 1; i++) {
            int climb = heights[i + 1] - heights[i];
            if (climb <= 0) {
                continue;
            }
            // Make resource allocations
            if (climb == K) {
                laddersUsedOnThreshold++;
                ladders--;
            } else if (climb > K) {
                ladders--;
            } else {
                bricks -= climb;
            }
            // Handle negative resources
            if (ladders < 0) {
                if (laddersUsedOnThreshold >= 1) {
                    laddersUsedOnThreshold--;
                    ladders++;
                    bricks -= K;
                } else {
                    return new int[] { i, ladders, bricks };
                }
            }
            if (bricks < 0) {
                return new int[] { i, ladders, bricks };
            }
        }
        return new int[] { heights.length - 1, ladders, bricks };
    }

}