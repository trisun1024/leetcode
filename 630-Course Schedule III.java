import java.util.Arrays;
import java.util.PriorityQueue;

class CourseScheduleIII {

    // Recursion + Memorization. Time = O(N*D);
    public int scheduleCourseI(int[][] courses) {
        // sort array by ascending end time
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        int m = courses.length;
        int n = courses[0].length;
        Integer[][] memo = new Integer[m][courses[m - 1][1] + 1];
        return helper(courses, 0, 0, memo);
    }

    private int helper(int[][] courses, int i, int time, Integer[][] memo) {
        if (i == courses.length) {
            return 0;
        }
        if (memo[i][time] != null) {
            return memo[i][time];
        }
        int taken = 0;
        if (time + courses[i][0] <= courses[i][1]) {
            taken = 1 + helper(courses, i + 1, time + courses[i][0], memo);
        }
        int notTaken = helper(courses, i + 1, time, memo);
        memo[i][time] = Math.max(taken, notTaken);
        return memo[i][time];
    }

    // Iteration. Time = O(N^2);
    public int scheduleCourseII(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        int time = 0, count = 0;
        for (int i = 0; i < courses.length; i++) {
            if (time + courses[i][0] <= courses[i][1]) {
                time += courses[i][0];
                count++;
            } else {
                int max_i = i;
                for (int j = 0; j < i; j++) {
                    if (courses[j][0] > courses[max_i][0]) {
                        max_i = j;
                    }
                }
                if (courses[max_i][0] > courses[i][0]) {
                    time += courses[i][0] - courses[max_i][0];
                }
                courses[max_i][0] = -1;
            }
        }
        return count;
    }

    // Optimized Iteration.
    public int scheduleCourseIIII(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        int time = 0, count = 0;
        for (int i = 0; i < courses.length; i++) {
            if (time + courses[i][0] <= courses[i][1]) {
                time += courses[i][0];
                courses[count++] = courses[i];
            } else {
                int max_i = i;
                for (int j = 0; j < count; j++) {
                    if (courses[j][0] > courses[max_i][0]) {
                        max_i = j;
                    }
                }
                if (courses[max_i][0] > courses[i][0]) {
                    time += courses[i][0] - courses[max_i][0];
                    courses[max_i] = courses[i];
                }
            }
        }
        return count;
    }

    // PriorityQueue. Time = O(N*log(N)); Space = O(N);
    public int scheduleCourse(int[][] courses) {
        // sort array by ascending order of end time
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        // build max heap to store end time
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int time = 0;
        for (int[] c : courses) {
            if (time + c[0] <= c[1]) {
                maxHeap.offer(c[0]);
                time += c[0];
            } else if (!maxHeap.isEmpty() && maxHeap.peek() > c[0]) {
                time += c[0] - maxHeap.poll();
                maxHeap.offer(c[0]);
            }
        }
        return maxHeap.size();
    }

}
