import java.util.*;

class PhoneDirectory {

    private boolean[] flag = null;
    private int[] recycle = null;
    private int num, idx, maxNum;

    /**
     * Initialize your data structure here
     * 
     * @param maxNumbers - The maximum numbers that can be stored in the phone
     *                   directory.
     */
    public PhoneDirectory(int maxNumbers) {
        num = idx = 0;
        maxNum = maxNumbers;
        flag = new boolean[maxNumbers];
        Arrays.fill(flag, true);
        recycle = new int[maxNumbers];
    }

    /**
     * Provide a number which is not assigned to anyone.
     * 
     * @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
        if (num >= maxNum && idx <= 0)
            return -1;
        if (idx > 0) {
            int k = recycle[--idx];
            flag[k] = false;
            return k;
        }
        flag[num] = false;
        return num++;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return flag[number];
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (number >= 0 && number < maxNum && !flag[number]) {
            recycle[idx++] = number;
            flag[number] = true;
        }

    }
}