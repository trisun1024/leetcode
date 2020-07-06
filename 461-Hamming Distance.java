class HammingDistance {

    public int hammingDistanceI(int x, int y) {
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            // bits different so count
            if ((x & 1) != (y & 1)) {
                sum++;
            }
            x = x >> 1;
            y = y >> 1;
        }
        return sum;
    }

    // Bit Operator. Time = O(1); Space = O(1);
    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int dist = 0;
        while (xor != 0) {
            if (xor % 2 == 1) {
                dist++;
            }
            xor = xor >> 1;
        }
        return dist;
    }
}