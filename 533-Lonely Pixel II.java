import java.util.*;

class LonelyPixelII {

    public int findBlackPixel(char[][] picture, int N) {
        int m = picture.length;
        int n = picture[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B') {
                    rows[i]++;
                    cols[j]++;
                }
            }
        }

        int count = 0;
        for (int j = 0; j < n; j++) {
            // check if exists N numbers in column j;
            if (cols[j] != N) {
                continue;
            }
            boolean valid = true;
            String mark = "";
            // check each row
            for (int i = 0; i < m; i++) {
                if (picture[i][j] == 'B') {
                    if (rows[i] != N || (mark.length() != 0 && !serialize(picture[i]).equals(mark))) {
                        valid = false;
                        break;
                    }
                    mark = serialize(picture[i]);
                }
            }
            if (valid) {
                count += cols[j];
            }
        }
        return count;
    }

    private String serialize(char[] temp) {
        return new String(temp);
    }

    // HashMap.
    public int findBlackPixelI(char[][] picture, int N) {
        int m = picture.length;
        if (m == 0)
            return 0;
        int n = picture[0].length;
        if (n == 0)
            return 0;

        Map<String, Integer> map = new HashMap<>();
        int[] colCount = new int[n];

        for (int i = 0; i < m; i++) {
            String key = scanRow(picture, i, N, colCount);
            if (key.length() != 0) {
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }

        int result = 0;
        for (String key : map.keySet()) {
            if (map.get(key) == N) {
                for (int j = 0; j < n; j++) {
                    if (key.charAt(j) == 'B' && colCount[j] == N) {
                        result += N;
                    }
                }
            }
        }

        return result;
    }

    private String scanRow(char[][] picture, int row, int target, int[] colCount) {
        int n = picture[0].length;
        int rowCount = 0;
        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < n; j++) {
            if (picture[row][j] == 'B') {
                rowCount++;
                colCount[j]++;
            }
            sb.append(picture[row][j]);
        }

        if (rowCount == target) {
            return sb.toString();
        }
        return "";
    }
}