import java.util.*;

class LargestNumber {

    // Time = O(N*log(N)); Space = O(N);
    private class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        }
    }

    public String largestNumber(int[] nums) {
        // Get input integers as strings.
        String[] numStrings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStrings[i] = String.valueOf(nums[i]);
        }

        // Sort strings according to custom comparator.
        Arrays.sort(numStrings, new LargerNumberComparator());

        // If, after being sorted, the largest number is `0`, the entire number
        // is zero.
        if (numStrings[0].equals("0")) {
            return "0";
        }

        // Build largest number from sorted array.
        StringBuilder res = new StringBuilder();
        for (String s : numStrings) {
            res.append(s);
        }

        return res.toString();
    }
}