class Solution {

    public String alienOrder(String[] words) {
        int n = 26;
        boolean[][] graph = new boolean[n][n];
        int[] visited = new int[n];
        buildGraph(words, graph, visited, n);
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n ;i++) {
            if(visited[i] == 0 && !dfs(graph, visited, sb, i , n)) {
                return "";
            }
        }
        return sb.reverse().toString();
    }

    private void buildGraph(String[] words, boolean[][] graph, int[] visited, int n) {

    }

    private boolean dfs(boolean[][] graph, int[] visited, StringBuilder sb , int index, int n) {
        return true;
    }
}