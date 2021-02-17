import java.util.*;

class KillProcess {

    // DFS. Time = O(N^N); Space = O(N);
    public List<Integer> killProcessI(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> list = new ArrayList<>();
        if (kill == 0) {
            return list;
        }
        list.add(kill);
        for (int i = 0; i < ppid.size(); i++) {
            if (ppid.get(i) == kill) {
                list.addAll(killProcessI(pid, ppid, ppid.get(i)));
            }
        }
        return list;
    }

    // HashMap + DFS. Time = O(N); Space = O(N);
    public List<Integer> killProcessII(List<Integer> pid, List<Integer> ppid, int kill) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < ppid.size(); i++) {
            if (ppid.get(i) > 0) {
                List<Integer> l = map.getOrDefault(ppid.get(i), new ArrayList<Integer>());
                l.add(pid.get(i));
                map.put(ppid.get(i), l);
            }
        }
        List<Integer> list = new ArrayList<>();
        list.add(kill);
        getAllChildren(map, list, kill);
        return list;
    }

    public void getAllChildren(HashMap<Integer, List<Integer>> map, List<Integer> l, int kill) {
        if (map.containsKey(kill))
            for (int id : map.get(kill)) {
                l.add(id);
                getAllChildren(map, l, id);
            }
    }

    // HashMap + BFS. Time = O(N); Space = O(N);
    public List<Integer> killProcessIII(List<Integer> pid, List<Integer> ppid, int kill) {
        // build graph
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < ppid.size(); i++) {
            if (ppid.get(i) > 0) {
                List<Integer> list = map.getOrDefault(ppid.get(i), new ArrayList<Integer>());
                list.add(pid.get(i));
                map.put(ppid.get(i), list);
            }
        }
        // traverse using queue
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        queue.offer(kill);
        while (!queue.isEmpty()) {
            int r = queue.poll();
            list.add(r);
            if (map.containsKey(r)) {
                for (int id : map.get(r)) {
                    queue.offer(id);
                }
            }
        }
        return list;
    }

}