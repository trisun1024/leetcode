class Solution {

    // Comparsion. Time = O(min(M,N));
    public int compareVersion(String version1, String version2) {
        // version split by "\\."
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int n1 = v1.length;
        int n2 = v2.length;
        // compare each section until reach the shorter length
        for (int i = 0; i < Math.max(n1, n2); i++) {
            int i1 = i < n1 ? Integer.parseInt(v1[i]) : 0;
            int i2 = i < n2 ? Integer.parseInt(v2[i]) : 0;
            if (i1 != i2) {
                return i1 < i2 ? -1 : 1;
            }
        }
        return 0;
    }
}