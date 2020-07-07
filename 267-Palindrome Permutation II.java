import java.util.*;

class PalindromePermutationII {

    // Backtracking.
    public List<String> generatePalindromes(String s) {
        List<String> ans = new ArrayList<String>();
        int[] map = new int[128];
        if (!canHavePalindromes(s, map)) {
            return ans;
        }
        char[] arr = new char[s.length() / 2];
        char ch = 0;
        int j = 0;
        for (int i = 0; i < 128; i++) {
            if (map[i] > 0) {
                for (int k = 0; k < map[i] / 2; k++) {
                    arr[j++] = (char) i;
                }
                if (map[i] % 2 == 1) {
                    ch = (char) i;
                }
            }
        }
        backtracking(arr, 0, ch, ans);
        return ans;
    }

    private boolean canHavePalindromes(String s, int[] map) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i)]++;
            if (map[s.charAt(i)] % 2 == 0) {
                count--;
            } else {
                count++;
            }
        }
        return count <= 1;
    }
    

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

 

    private void backtracking(char[] arr, int index, char ch, List<String> list) {
        if (index == arr.length) {
            StringBuilder sb = new StringBuilder();
            sb.append(arr);
            sb.reverse();
            if (ch != 0)
                sb.append(ch);
            sb.append(arr);
            list.add(sb.toString());
            return;
        }

        backtracking(arr, index + 1, ch, list);
        for (int i = index + 1; i < arr.length; i++) {
            if (arr[i] != arr[index]) {
                swap(arr, index, i);
                backtracking(arr, index + 1, ch, list);
            }
        }
        char c = arr[index];
        System.arraycopy(arr, index + 1, arr, index, arr.length - 1 - index);
        arr[arr.length - 1] = c;
    }

}