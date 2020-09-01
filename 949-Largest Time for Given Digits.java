
class LargestTimeForGivenDigits {

    public String largestTimeFromDigits(int[] A) {
        int[] maxTime = new int[] { -1 };
        permutate(A, 0, maxTime);
        if (maxTime[0] == -1) {
            return "";
        } else {
            return String.format("%02d:%02d", maxTime[0] / 60, maxTime[0] % 60);
        }
    }

    private void permutate(int[] array, int start, int[] maxTime) {
        if (start == array.length) {
            buildTime(array, maxTime);
            return;
        }
        for (int i = start; i < array.length; i++) {
            swap(array, i, start);
            permutate(array, start + 1, maxTime);
            swap(array, i, start);
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void buildTime(int[] array, int[] maxTime) {
        int hour = array[0] * 10 + array[1];
        int minute = array[2] * 10 + array[3];
        if (hour < 24 && minute < 60) {
            maxTime[0] = Math.max(maxTime[0], hour * 60 + minute);
        }
    }
}