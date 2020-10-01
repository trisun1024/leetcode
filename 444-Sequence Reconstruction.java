import java.util.*;

class SequenceReconstruction {

    // Array.
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        // base case 
        if(seqs == null || seqs.size() == 0) {
            return false;
        }
        int len = org.length;
        int[] pos = new int[len+1];
        boolean[] state= new boolean[len+1];
        for(int i = 0; i < len; i++) {
            pos[org[i]] = i;
        }
        for(List<Integer> seq: seqs) {
            for(int i = 0; i < seq.size(); i++) {
                int cur = seq.get(i);
                // base case 
                if(cur <= 0 || cur > org.length) {
                    return false;
                }
                if( i== 0) {
                    continue;
                }
                int prev = seq.get(i-1);
                if(pos[prev] >= pos[cur]) {
                    return false;
                }
                if(!state[prev] && pos[prev]+ 1 == pos[cur]) {
                    state[prev]  = true;
                }
            }
        }
        // loop the state
        for (int i = 0; i < len - 1; i++) {
            if (state[org[i]] == false) {
                return false;
            }
        }

        return true;
    }

    // BFS
    public boolean sequenceReconstructionI(int[] org, List<List<Integer>> seqs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size(); i++) {
                graph.putIfAbsent(seq.get(i), new ArrayList<Integer>());
                indegree.putIfAbsent(seq.get(i), 0);
                if (i > 0) {
                    graph.get(seq.get(i - 1)).add(seq.get(i));
                    indegree.put(seq.get(i), indegree.get(seq.get(i)) + 1);
                }
            }
        }
        if (org.length != indegree.size()) {
            return false;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                q.offer(entry.getKey());
            }
        }

        int index = 0;
        while (!q.isEmpty()) {
            if (q.size() > 1) {
                return false;
            }
            int curr = q.poll();
            if (org[index++] != curr) {
                return false;
            }
            for (int nb : graph.get(curr)) {
                indegree.put(nb, indegree.get(nb) - 1);
                if (indegree.get(nb) == 0) {
                    q.offer(nb);
                }
            }
        }
        return index == org.length;
    }
}