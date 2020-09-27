
class IntegerReplacement {

    // Recursion.
    public int integerReplacement(int n) {
        return (int) helper((long) n);
    }

    private long helper(long n) {
        if ((n & n - 1) == 0) {
            return (long) (Math.log(n) / Math.log(2));
        }
        return (n % 2 == 0) ? helper(n / 2) + 1 : Math.min(helper(n + 1), helper(n - 1)) + 1;
    }

    // Iteration.
    /*
     * 1. The bits of n end with 01. Here we should always subtract one and then
     * right-shift twice (3 steps) instead of adding one, right-shifting,
     * adding/subtracting one, and right-shifting (4 steps).
     * 
     * 2. The bits of n end with 11, and n > 3. Here we should add one, because each
     * carry after the first will save a step in the future (at least one step
     * saved); and the final carry will add one step, for a net benefit of at worst
     * 0 steps.
     * 
     * 3. n = 3. In this special case, the second carry after adding one does not
     * save a step, because we are not trying to eliminate the 1 in the
     * second-to-lowest-order bit. So we should subtract.
     */
    public int integerReplacementI(int n) {
        if (n == Integer.MAX_VALUE) {
            return 32;
        }
        int steps = 0;
        while (n > 1) {
            if (n % 2 == 0) {
                n >>>= 1;
            } else {
                if ((n & 2) == 0 || n == 3) {
                    n--;
                } else {
                    n++;
                }
            }
            steps++;
        }
        return steps;
    }
}