
class CanPlaceFlowers {

    // Linear Scan. Time = O(N);
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0)
                    && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                count++;
            }
            if (count >= n) {
                return true;
            }
        }
        return count >= n;
    }

    public boolean canPlaceFlowersI(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                if ((i - 1 >= 0 && flowerbed[i - 1] == 1) || (i + 1 < flowerbed.length && flowerbed[i + 1] == 1)) {
                    continue;
                }
                flowerbed[i] = 1;
                n--;
            }
            if (n <= 0) {
                return true;
            }
        }
        return n <= 0;
    }
}