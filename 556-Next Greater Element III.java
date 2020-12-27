import java.util.*;

class NextGreaterElementIII {

    // Linear Solution. Time = O(N); Space = O(N);
    public int nextGreaterElement(int n) {
        char[] arr = String.valueOf(n).toCharArray();
        // I) Start from the right most digit and
        // find the first digit that is
        // smaller than the digit next to it.
        int i = arr.length - 2;
        while (i >= 0 && arr[i + 1] <= arr[i]) {
            i--;
        }
        // If no such digit is found, its the edge case 1.
        if (i < 0) {
            return -1;
        }
        // II) Find the smallest digit on right side of (i-1)'th
        // digit that is greater than number[i-1]
        int j = arr.length - 1;
        while (j >= 0 && arr[j] <= arr[i]) {
            j--;
        }
        // III) Swap the above found smallest digit with number[i-1]
        swap(arr, i, j);
        reverse(arr, i + 1);
        try {
            return Integer.parseInt(new String(arr));
        } catch (Exception e) {
            return -1;
        }
    }

    private void reverse(char[] arr, int start) {
        int i = start, j = arr.length - 1;
        while (i < j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Brute Force.
    public int nextGreaterElementI(int n) {
        char[] arr = String.valueOf(n).toCharArray();
        List<String> res = new ArrayList<>();
        permutate(arr, 0, arr.length - 1, res);
        Collections.sort(res);
        int i;
        for (i = res.size() - 1; i >= 0; i--) {
            if (res.get(i).equals("" + n)) {
                break;
            }
        }
        return i == res.size() - 1 ? -1 : Integer.parseInt(res.get(i + 1));
    }

    private void permutate(char[] arr, int left, int right, List<String> res) {
        if (left == right) {
            res.add(new String(arr));
            return;
        }
        for (int i = left; i <= right; i++) {
            swap(arr, left, i);
            permutate(arr, left + 1, right, res);
            swap(arr, left, i);
        }
    }
}