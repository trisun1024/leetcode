import java.util.*;

class PartitionLabels {

    // the whole idea of solving this is to find the max index of each characters. 
    // because the partition include the characters which will all have less or equal index 

    public List<Integer> partitionLabels(String S) {
        // build a index array to store the latest index of array in this char
        int[] maxs = new int[26];
        for (int i = 0; i < S.length(); i++) {
            maxs[S.charAt(i) - 'a'] = i;
        }
        // loop the string again to check
        // if current index == maximum index then insert the result
        int j = 0;
        int offset = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            j = Math.max(j, maxs[S.charAt(i) - 'a']);
            if (i == j) {
                res.add(i - offset + 1);
                offset = i + 1;
            }
        }
        return res;
    }
}