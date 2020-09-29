

class TeemoAttacking {
    
    // One Pass.
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int n = timeSeries.length;
        int sum = 0;
        if(n == 0) {
            return sum;
        }
        for(int i = 0; i < n-1; i++) {
            sum += Math.min(timeSeries[i+1] - timeSeries[i], duration);
        }
        return sum + duration;
    }

}