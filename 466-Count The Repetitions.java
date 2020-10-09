class CountRepetitions {

    // Brute Force.
    public int getMaxRepetitionsI(String s1, int n1, String s2, int n2) {
        int count = 0;
        // i points to cur char in s1, j points to cur char in s2
        for (int i = 0, j = 0; i < s1.length() * n1; i++) {
            if (s1.charAt(i % s1.length()) == s2.charAt(j)) { // if s1 current
                j = (j + 1) % s2.length(); // j++ mod s2.len
                if (j == 0)
                    count++; // each time j goes around to s1.charAt(0), we increase count
            }
        }
        return count / n2;
    }

    // Two Pointers.
    public int getMaxRepetitionsII(String s1, int n1, String s2, int n2) {

        char[] array1 = s1.toCharArray();
        char[] array2 = s2.toCharArray();
        int count1 = 0, count2 = 0, i = 0, j = 0;

        while (count1 < n1) {
            if (array1[i] == array2[j]) {
                j++;
                if (j == array2.length) {
                    j = 0;
                    count2++;
                }
            }
            i++;
            if (i == array1.length) {
                i = 0;
                count1++;
            }
        }

        return count2 / n2;
    }

    //
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int len1 = s1.length(), len2 = s2.length();
        // for detecting cycle
        boolean[] endAt = new boolean[len1];
        char[] chs1 = s1.toCharArray(), chs2 = s2.toCharArray();
        // how many s1 we use and how many s2 we collect when we endAt specific
        int[] nums1 = new int[len1], nums2 = new int[len1];

        int tltS1Len = len1 * n1;

        int startIdx = s1.indexOf(chs2[0]);
        if (startIdx == -1) {
            return 0;
        }

        int s2n = 0, s1n = 0, curIdx = 0, prevIdx = -1;
        while (s1n * len1 + curIdx < tltS1Len) {
            for (char c : chs2) {
                curIdx = s1.indexOf(c, prevIdx + 1);

                if (curIdx == -1 && prevIdx == -1) {
                    return 0;
                }
                prevIdx = curIdx;
                if (curIdx == -1) {
                    ++s1n;
                    curIdx = s1.indexOf(c, prevIdx + 1);
                }
                prevIdx = curIdx;
            }
            // cycle found
            if (endAt[curIdx]) {
                break;
            }

            prevIdx = curIdx;
            ++s2n;
            endAt[curIdx] = true;
            nums1[curIdx] = s1n;
            nums2[curIdx] = s2n;
        }

        int cycleLen = (s1n - nums1[curIdx]) * len1;
        if (cycleLen == 0) {
            return 0;
        }
        int nS2 = (tltS1Len - startIdx - 1) / cycleLen * s2n;
        int remain = (tltS1Len - startIdx - 1) % cycleLen;

        int extraNum = 0;
        for (int i = 0; i < len1; ++i) {
            if (!endAt[i]) {
                continue;
            }

            if (remain >= i + nums1[i] * len1) {
                extraNum = Math.max(nums2[i], extraNum);
            }
        }

        return (nS2 + extraNum) / n2;
    }
}