 import java.util.*;
class TaskScheduler{

// Greedy. Time = O(N);
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for(int t: tasks) {
            freq[t-'A']++;
        }
        Arrays.sort(freq);
        
        int freqMax = freq[25];
        int idleTime = (freqMax-1) *n;
        
        for(int i = freq.length-2; i >= 0 && idleTime > 0; i--) {
            idleTime -= Math.min(freqMax - 1, freq[i]);
        }
        idleTime = Math.max(0, idleTime);
        return idleTime + tasks.length;
    }

    // Math
    public int leastIntervalI(char[] tasks, int n) {
        // frequencies of the tasks
        int[] frequencies = new int[26];
        for (int t : tasks) {
            frequencies[t - 'A']++;
        }

        // max frequency
        int f_max = 0;
        for (int f : frequencies) {
            f_max = Math.max(f_max, f);
        }

        // count the most frequent tasks
        int n_max = 0;
        for (int f : frequencies) {
            if (f == f_max)
                n_max++;
        }

        return Math.max(tasks.length, (f_max - 1) * (n + 1) + n_max);
    }
}
