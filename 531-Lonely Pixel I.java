
class LonelyPixelI {

    public int findLonelyPixel(char[][] picture) {
        int[] rowSum = new int[picture.length];
        int[] colSum = new int[picture[0].length];
        int count = 0;

        // compute rowSum
        for (int i = 0; i < picture.length; ++i) {
            int temp = 0;
            for (int j = 0; j < picture[i].length; ++j) {
                if (picture[i][j] == 'B')
                    temp += 1;
            }
            rowSum[i] = temp;
        }

        // compute colSum
        for (int i = 0; i < picture[0].length; ++i) {
            int temp = 0;
            for (int j = 0; j < picture.length; ++j) {
                if (picture[j][i] == 'B')
                    temp += 1;
            }
            colSum[i] = temp;
        }

        // compute lonely pixel
        for (int i = 0; i < picture.length; ++i) {
            for (int j = 0; j < picture[i].length; ++j) {
                if (picture[i][j] == 'B' && rowSum[i] + colSum[j] == 2)
                    count++;
            }
        }
        return count;
    }

    public int findLonelyPixelI(char[][] picture) {
        int n = picture.length, m = picture[0].length;

        int[] rowCount = new int[n], colCount = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (picture[i][j] == 'B') {
                    rowCount[i]++;
                    colCount[j]++;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (picture[i][j] == 'B' && rowCount[i] == 1 && colCount[j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }
}