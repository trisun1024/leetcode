class ReverseVowelsOfString {

    public String reverseVowels(String s) {
        boolean[] init = new boolean[256];
        init['a'] = true;
        init['e'] = true;
        init['i'] = true;
        init['o'] = true;
        init['u'] = true;
        init['A'] = true;
        init['E'] = true;
        init['I'] = true;
        init['O'] = true;
        init['U'] = true;
        char[] array = s.toCharArray();
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            while (left < array.length && !init[array[left]]) {
                left++;
            }
            while (right >= 0 && !init[array[right]]) {
                right--;
            }
            if (left < right) {
                swap(array, left++, right--);
            }
        }
        return new String(array);
    }

    private void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}