class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length == 2)
            return true;

        int[] first = coordinates[0];
        int[] second = coordinates[1];

        int a = first[0], b = first[1];
        int c = second[0], d = second[1];

        if (c == a) {
            return false;
        }

        int m = (d - b) / (c - a);
        int k = b - (m * a);

        for (int i = 2; i < coordinates.length; i++) {
            if (coordinates[i][1] != (m * coordinates[i][0] + k)) {
                return false;
            }
        }

        return true;
    }
}