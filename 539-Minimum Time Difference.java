import java.util.*;

class MinimumTimeDifference {

    // Sort. Time = O(N*log(N));
    public int findMinDifference(List<String> timePoints) {
        int min = Integer.MAX_VALUE;
        List<Integer> time = new ArrayList<>();

        for (int i = 0; i < timePoints.size(); i++) {
            Integer h = Integer.valueOf(timePoints.get(i).substring(0, 2));
            time.add(60 * h + Integer.valueOf(timePoints.get(i).substring(3, 5)));
        }

        Collections.sort(time, (Integer a, Integer b) -> a - b);

        for (int i = 1; i < time.size(); i++) {
            System.out.println(time.get(i));
            min = Math.min(min, time.get(i) - time.get(i - 1));
        }

        int corner = time.get(0) + (1440 - time.get(time.size() - 1));
        return Math.min(min, corner);
    }

    // Array.
    public int findMinDifferenceI(List<String> timePoints) {
        boolean[] timeframe = new boolean[24 * 60];
        for (String t : timePoints) {
            String[] tp = t.split(":");
            int hours = Integer.parseInt(tp[0]);
            int seconds = Integer.parseInt(tp[1]);
            // early stop when find a match
            if (timeframe[hours * 60 + seconds]) {
                return 0;
            }
            timeframe[hours * 60 + seconds] = true;
        }
        int min = Integer.MAX_VALUE;
        int fast = Integer.MAX_VALUE;
        int slow = Integer.MIN_VALUE;
        int p = 0;
        for (int i = 0; i < timeframe.length; i++) {
            if (timeframe[i]) {
                if (fast != Integer.MAX_VALUE) {
                    min = Math.min(min, i - p);
                }
                fast = Math.min(fast, i);
                slow = Math.max(slow, i);
                p = i;
            }
        }
        return Math.min(min, (24 * 60 - slow + fast));
    }
}