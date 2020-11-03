import java.util.*;

class MaximumDistanceInArrays {

    // Brute Force
    public int maxDistanceI(List<List<Integer>> arrays) {
        int maxDistance = 0;
        for (int i = 0; i < arrays.size() - 1; i++) {
            for (int j = i + 1; j < arrays.size(); j++) {
                maxDistance = Math.max(maxDistance,
                        Math.abs(arrays.get(i).get(0) - arrays.get(j).get(arrays.get(j).size() - 1)));
                maxDistance = Math.max(maxDistance,
                        Math.abs(arrays.get(j).get(0) - arrays.get(i).get(arrays.get(i).size() - 1)));
            }
        }
        return maxDistance;
    }

    // Single Scan. Time = O(N); Space = O(1);
    // because the array are all presorted. So the minimum value will be the first
    // one, the maximum value will be the last one.
    public int maxDistance(List<List<Integer>> arrays) {
        int maxDistance = 0;
        int minVal = arrays.get(0).get(0);
        int maxVal = arrays.get(0).get(arrays.get(0).size() - 1);
        for (int i = 1; i < arrays.size(); i++) {
            maxDistance = Math.max(maxDistance, Math.max(Math.abs(arrays.get(i).get(arrays.get(i).size() - 1) - minVal),
                    Math.abs(maxVal - arrays.get(i).get(0))));
            minVal = Math.min(minVal, arrays.get(i).get(0));
            maxVal = Math.max(maxVal, arrays.get(i).get(arrays.get(i).size() - 1));
        }
        return maxDistance;
    }
}