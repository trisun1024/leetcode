import java.util.*;

class NextGreaterElementIII {

    // Linear Solution. Time = O(N); Space = O(N);
    public int nextGreaterElementI(int n) {
        char[] arr = String.valueOf(n).toCharArray();
        int i = arr.length - 2;
        while (i >= 0 && arr[i + 1] <= arr[i]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }
        int j = arr.length - 1;
        while (j >= 0 && arr[j] <= arr[i]) {
            j--;
        }
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

    // Linear Solution.
    public int nextGreaterElementII(int n) {
        char[] number = String.valueOf(n).toCharArray();

        int i, j;
        // I) Start from the right most digit and
        // find the first digit that is
        // smaller than the digit next to it.
        for (i = number.length - 1; i > 0; i--)
            if (number[i - 1] < number[i])
                break;

        // If no such digit is found, its the edge case 1.
        if (i == 0)
            return -1;

        // II) Find the smallest digit on right side of (i-1)'th
        // digit that is greater than number[i-1]
        int x = number[i - 1], smallest = i;
        for (j = i + 1; j < number.length; j++)
            if (number[j] > x && number[j] <= number[smallest])
                smallest = j;

        // III) Swap the above found smallest digit with
        // number[i-1]
        char temp = number[i - 1];
        number[i - 1] = number[smallest];
        number[smallest] = temp;

        // IV) Sort the digits after (i-1) in ascending order
        Arrays.sort(number, i, number.length);

        long val = Long.parseLong(new String(number));
        return (val <= Integer.MAX_VALUE) ? (int) val : -1;
    }

    //
    public int NextGreaterElementIIi(int n) {
        char[] numArr = String.valueOf(n).toCharArray();
        int index = numArr.length - 1;
        while (index >= 1 && numArr[index - 1] >= numArr[index]) {
            index--;
        }
        if (index == 0) {
            return -1;
        } 

        int smallest = numArr[index - 1];
        int i = numArr.length - 1;
        for (i = numArr.length - 1; i >= index; i--) {
            if (numArr[i] > smallest) {
                break;
            }
        }

        swap(numArr, index - 1, i);
        Arrays.sort(numArr, index, numArr.length);
        String num = new String(numArr);
        long val = Long.parseLong(num);
        return (val <= Integer.MAX_VALUE) ? (int) val : -1;
    }

    // Better Optimized. 
    public int nextGreaterElement(int n) {
        long result = -1;
        int largest = 10, last_digit = 10;
        List<Integer> lst = new ArrayList<Integer>();
        while (n > 0) {
            last_digit = n % 10;
            if (largest == 10) {
                largest = last_digit;
                lst.add(last_digit);
            } else if (largest > last_digit) {
                lst.add(last_digit);
                break;
            } else {
                largest = last_digit;
                lst.add(last_digit);
            }
            n /= 10;
        }
        if (n == 0) {
            return -1;
        }
        Collections.sort(lst);
        n = n / 10;
        int i = 0;
        for (i = 0; i < lst.size(); i++) {
            if (lst.get(i) > last_digit) {
                break;
            }
        }
        result = (n * 10) + lst.get(i);
        lst.remove(i);
        for (i = 0; i < lst.size(); i++) {
            result = (result * 10) + lst.get(i);
            if (result > Integer.MAX_VALUE) {
                return -1;
            }
        }

        return (int) result;
    }
}