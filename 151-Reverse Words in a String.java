class Solution {

    // general idea reverse twice, first time reverse whole, second time reverse
    // partial
    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        reverse(arr, 0, s.length() - 1);
        int i = 0;
        int j = 0;
        while (j <= arr.length) {
            if (j == arr.length || arr[j] == ' ') {
                reverse(arr, i, j - 1);
                while (j + 1 < arr.length && arr[j + 1] == ' ') {
                    j++;
                }
                j++;
                i = j;
            }
            j++;
        }
        // remove the extra space in the string
        return removeExtraSpace(arr);
    }

    private void reverse(char[] arr, int i, int j) {
        while (i < j) {
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }

    private String removeExtraSpace(char[] arr) {
        int i = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] == ' ' && (i == 0 || arr[j - 1] == ' ')) {
                continue;
            }
            arr[i++] = arr[j];
        }
        if (i > 0 && arr[i - 1] == ' ') {
            i--;
        }
        return new String(arr, 0, i);
    }
}