import java.util.*;

class LineReflection {

    /*
     * for each y value, save unique x coordinates of every point that has this
     * y-value, then convert each set to a list and sort the list.
     */
    public boolean isReflected(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int[] point : points) {
            map.putIfAbsent(point[1], new HashSet<>());
            map.get(point[1]).add(point[0]);
        }
        Integer mid = null;
        for(Set<Integer> set: map.values()) {
            List<Integer> list = new ArrayList<>(set);
            Collections.sort(list);
            int left = 0;int right = list.size()-1;
            while(left <= right) {
                int temp = list.get(left) + list.get(right);
                if(mid == null) {
                    mid = temp;
                } else {
                    if(mid != temp) {
                        return false;
                    }
                }
                left++;
                right--;
            }
        }
        return true;
    }
}