
class ReverseWordsInStringII {
    public void reverseWords(char[] arr) {
        reverse(arr, 0, arr.length - 1);
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
}