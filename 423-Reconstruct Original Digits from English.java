import java.util.*;

class ReconstructOriginalDigitsfromEnglish {

    // Array
    public String originalDigits(String s) {
        int[] count = new int[128];
        for (char c : s.toCharArray()) {
            count[c]++;
        }
        int[] nums = new int[10];
        nums[0] = count['z'];
        nums[2] = count['w'];
        nums[4] = count['u'];
        nums[6] = count['x'];
        nums[8] = count['g'];
        nums[1] = count['o'] - nums[0] - nums[2] - nums[4];
        nums[3] = count['h'] - nums[8];
        nums[5] = count['f'] - nums[4];
        nums[7] = count['v'] - nums[5];
        nums[9] = count['i'] - nums[5] - nums[6] - nums[8];

        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            if (nums[i] > 0) {
                char[] arr = new char[nums[i]];
                Arrays.fill(arr, (char) ('0' + i));
                ret.append(String.valueOf(arr));
            }
        }

        return ret.toString();
    }
}