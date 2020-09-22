class RearrangeStringKDistanceApart {

    //
    public String rearrangeString(String s, int k) {
        if (k <= 1 || s == null || s.length() <= 1) {
            return s;
        }
        // find character frequence
        int[] counts = new int[26];
        int max = 0;
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
            max = Math.max(max, counts[c - 'a']);
        }
        // count
        int maxCount = 0;
        StringBuilder repeat = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (counts[i] == max) {
                maxCount++;
                repeat.append((char) (i + 'a'));
                counts[i] = 0;
            }
        }
        // set partition
        int needPartition = (max - 1) * (k - maxCount);
        int available = s.length() - max * maxCount;
        if (needPartition > available)
            return "";

        StringBuilder[] arr = new StringBuilder[max - 1];
        for (int i = 0; i < max - 1; i++) {
            arr[i] = new StringBuilder(repeat);
        }

        int j = 0;
        for (int i = 0; i < available; i++) {
            while (counts[j] == 0)
                j++;
            int index = i % (max - 1);
            arr[index].append((char) (j + 'a'));
            counts[j]--;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : arr) {
            res.append(sb);
        }
        res.append(repeat);
        return res.toString();
    }
}