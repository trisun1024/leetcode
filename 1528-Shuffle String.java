
class ShuffleString {

    // One-Pass. Time = O(N);
    public String restoreString(String s, int[] indices) {
        char[] arr = new char[s.length()];
        for (int i = 0; i < indices.length; i++) {
            int index = indices[i];
            arr[index] = s.charAt(i);
        }
        return String.valueOf(arr);
    }
}