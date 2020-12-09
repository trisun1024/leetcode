
class PairsOfSongsWithTotalDurationsDivisibleBy60 {

    // Brute Force.
    public int numPairsDivisibleBy60I(int[] time) {
        int count = 0;
        int n = time.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((time[i] + time[j]) % 60 == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    // HashMap.
    public int numPairsDivisibleBy60(int[] time) {
        int[] rem = new int[60];
        int c = 0;
        for (int t : time) {
            if (t % 60 == 0) {
                c += rem[0];
            } else {
                c += rem[60 - t % 60];
            }
            rem[t % 60]++;
        }
        return c;
    }

}