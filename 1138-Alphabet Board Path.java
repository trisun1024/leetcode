class AlphabetBoardPath {

    // Traverse.
    public String alphabetBoardPath(String target) {
        StringBuilder res = new StringBuilder();
        int[] prev = new int[] { 0, 0 };
        for (char c : target.toCharArray()) {
            int num = c - 'a';
            move(new int[] { num / 5, num % 5 }, prev, res);
        }
        return res.toString();
    }

    private void move(int[] cur, int[] prev, StringBuilder res) {
        if (cur[0] == prev[0] && cur[1] == prev[1]) {
            res.append("!");
            return;
        }
        String end = null;
        if (cur[0] == 5 && cur[1] == 0) {
            cur[0]--;
            end = "D";
        }
        while (prev[0] > cur[0]) {
            res.append("U");
            prev[0]--;
        }
        while (prev[0] < cur[0]) {
            res.append("D");
            prev[0]++;
        }
        while (prev[1] > cur[1]) {
            res.append("L");
            prev[1]--;
        }
        while (prev[1] < cur[1]) {
            res.append("R");
            prev[1]++;
        }
        if (end != null) {
            prev[0]++;
            res.append(end);
        }
        // System.out.println(Arrays.toString(cur));
        res.append("!");
    }
}