
class SquirrelSimulation {

    private int distance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    // Time = O(N); Space = O(1);
    public int minDistanceI(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int totDist = 0;
        int minDist = Integer.MIN_VALUE;
        for (int[] nut : nuts) {
            totDist += (distance(nut, tree) * 2);
            minDist = Math.max(minDist, distance(nut, tree) - distance(nut, squirrel));
        }
        return totDist - minDist;
    }

    // DP. Time = O(N); Space = O(N^2);
    public int minDistanceII(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int[][] dis = new int[nuts.length][2];
        int min = Integer.MAX_VALUE;
        int sum = 0;

        for (int i = 0; i < dis.length; i++) {
            dis[i][0] = distance(nuts[i], tree);
            dis[i][1] = distance(nuts[i], squirrel);

            sum += 2 * dis[i][0];
            min = Math.min(min, dis[i][1] - dis[i][0]);
        }

        return sum + min;
    }

    //
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int distanceToTree = 0, distanceToSquirrel = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;

        for (int i = 0; i < nuts.length; i++) {
            distanceToTree = distance(nuts[i], tree);
            distanceToSquirrel = distance(nuts[i], squirrel);
            sum += 2 * distanceToTree;
            min = Math.min(min, distanceToSquirrel - distanceToTree);
        }

        return sum + min;
    }

}