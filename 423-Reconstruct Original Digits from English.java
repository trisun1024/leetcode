import java.util.*;

class ReconstructOriginalDigitsfromEnglish {

    // Array
    // zero, one, two, three, four, five, six, seven, eight, nine
    public String originalDigits(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        int[] nums = new int[10];
        // unique character mapping with numbers
        nums[0] = count['z' - 'a'];
        nums[2] = count['w' - 'a'];
        nums[4] = count['u' - 'a'];
        nums[6] = count['x' - 'a'];
        nums[8] = count['g' - 'a'];
        // characters appear in both numbers, then know the number of others
        nums[3] = count['h' - 'a'] - nums[8];
        nums[5] = count['f' - 'a'] - nums[4];
        nums[7] = count['v' - 'a'] - nums[5];
        nums[1] = count['o' - 'a'] - nums[0] - nums[2] - nums[4];
        nums[9] = count['i' - 'a'] - nums[5] - nums[6] - nums[8];

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            while (nums[i] > 0) {
                res.append(String.valueOf(i));
                nums[i]--;
            }
        }

        return res.toString();
    }

    public String originalDigitsI(String s) {
        int[] alphabets = new int[26];
        for (char c : s.toCharArray()) {
            alphabets[c - 'a']++;
        }
        int[] digits = new int[10];
        // the character only appear in mapping numbers
        digits[0] = alphabets['z' - 'a'];
        digits[2] = alphabets['w' - 'a'];
        digits[6] = alphabets['x' - 'a'];
        digits[8] = alphabets['g' - 'a'];
        // the character appear
        digits[7] = alphabets['s' - 'a'] - digits[6];
        digits[5] = alphabets['v' - 'a'] - digits[7];
        digits[3] = alphabets['h' - 'a'] - digits[8];
        digits[4] = alphabets['f' - 'a'] - digits[5];
        // missing
        digits[9] = alphabets['i' - 'a'] - digits[6] - digits[8] - digits[5];
        digits[1] = alphabets['o' - 'a'] - digits[0] - digits[2] - digits[4];

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            for (int count = 0; count < digits[i]; count++) {
                res.append(i);
            }
        }

        return res.toString();
    }
}