class Solution {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < indexes.length; i++) {
            if (S.startsWith(sources[i], indexes[i]))
                indexMap.put(indexes[i], i);
        }
        for (int i = 0; i < S.length();) {
            if (indexMap.containsKey(i)) {
                sb.append(targets[indexMap.get(i)]);
                i += sources[indexMap.get(i)].length();
            } else {
                sb.append(S.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }
}