import java.util.*;

class Heaters {

    // Two Pointers.
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int radius = 0;
        int heaterPos = 0;
        for (int housePos : houses) {
            while (heaterPos < heaters.length - 1 && heaters[heaterPos] + heaters[heaterPos + 1] <= housePos * 2) {
                heaterPos++;
            }
            radius = Math.max(radius, Math.abs(heaters[heaterPos] - housePos));
        }
        return radius;
    }

    // Two Pointers Finding.
    public int findRadiusI(int[] houses, int[] heaters) {
        if (houses == null || heaters == null)
            return Integer.MAX_VALUE;
        Arrays.sort(heaters);
        int result = Integer.MIN_VALUE;
        for (int house : houses) {
            int rad = findRad(house, heaters);
            result = Math.max(rad, result);
        }
        return result;
    }

    private int findRad(int house, int[] heaters) {
        int start = 0, end = heaters.length - 1;
        int left = Integer.MAX_VALUE, right = Integer.MAX_VALUE;
        while (start <= end) {
            int mid = (start + end) / 2;
            int heater = heaters[mid];
            if (heater == house)
                return 0;
            else if (heater > house) {
                right = heater - house;
                end = mid - 1;
            } else {
                left = house - heater;
                start = mid + 1;
            }
        }

        return Math.min(left, right);
    }
}