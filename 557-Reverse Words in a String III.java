
class ReverseWordsInString {

    // Two Pointers.
    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int i = 0;
        int j = 0;
        while (i < arr.length && j < arr.length) {
            while (i < arr.length && arr[i] == ' ') {
                i++;
            }
            j = i;
            while (j < arr.length && arr[j] != ' ') {
                j++;
            }
            reverse(arr, i, j - 1);
            i = j;
        }
        return new String(arr);
    }

    private void reverse(char[] arr, int l, int r) {
        while (l < r) {
            char temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }
}