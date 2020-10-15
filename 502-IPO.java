import java.util.*;

class IPO {

    // Heap. Time = O(N*log(K)); Space = O(N);
    public int findMaximizedCapitalI(int k, int W, int[] Profits, int[] Capital) {
        boolean speedUp = true;
        for (int c : Capital) {
            if (W < c) {
                speedUp = false;
            }
        }
        if (speedUp) {
            PriorityQueue<Integer> heap = new PriorityQueue<>();
            for (int p : Profits) {
                heap.offer(p);
                if (heap.size() > k) {
                    heap.poll();
                }
            }
            for (int h : heap) {
                W += h;
            }
            return W;
        }
        int n = Profits.length;
        PriorityQueue<int[]> projects = new PriorityQueue<>((x, y) -> (x[0] - y[0]));
        for (int i = 0; i < n; i++) {
            projects.add(new int[] { Capital[i], Profits[i] });
        }
        // max Heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> (y - x));
        while (k > 0) {
            while (!projects.isEmpty() && projects.peek()[0] <= W) {
                maxHeap.offer(projects.poll()[1]);
            }
            if (!maxHeap.isEmpty()) {
                W += maxHeap.poll();
            } else {
                break;
            }
            k--;
        }
        return W;
    }

    // Heap. Time = O(N*log(K)); Space = O(1);
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        // to speed up: if all projects are available
        boolean speedUp = true;
        for (int c : Capital)
            if (W < c) {
                speedUp = false;
            }
        if (speedUp) {
            PriorityQueue<Integer> heap = new PriorityQueue<>();
            for (int p : Profits) {
                heap.offer(p);
                if (heap.size() > k) {
                    heap.poll();
                }
            }
            for (int h : heap) {
                W += h;
            }
            return W;
        }

        int idx;
        int n = Profits.length;
        for (int i = 0; i < Math.min(k, n); ++i) {
            idx = -1;
            // if there are available projects,
            // pick the most profitable one
            for (int j = 0; j < n; ++j) {
                if (W >= Capital[j]) {
                    if (idx == -1)
                        idx = j;
                    else if (Profits[idx] < Profits[j])
                        idx = j;
                }
            }
            // not enough capital to start any project
            if (idx == -1)
                break;

            // add the profit from chosen project
            // and remove the project from further consideration
            W += Profits[idx];
            Capital[idx] = Integer.MAX_VALUE;
        }
        return W;
    }
}