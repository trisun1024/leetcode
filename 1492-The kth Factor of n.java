import java.util.*;

class KthFactorOfN {

    // Brute Force. Time = O(N);
    public int kthFactor(int n, int k) {
        // base case
        if (n <= 1) {
            return n;
        }
        for (int x = 1; x < n / 2 + 1; ++x) {
            if (n % x == 0) {
                --k;
                if (k == 0) {
                    return x;
                }
            }
        }

        return k == 1 ? n : -1;
    }

    // Heap.
    Queue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);

    // push into heap
    // by limiting size of heap to k
    public void heappushK(int x, int k) {
        heap.add(x);
        if (heap.size() > k) {
            heap.remove();
        }
    }

    public int kthFactorI(int n, int k) {
        int sqrtN = (int) Math.sqrt(n);
        for (int x = 1; x < sqrtN + 1; ++x) {
            if (n % x == 0) {
                heappushK(x, k);
                if (x != n / x) {
                    heappushK(n / x, k);
                }
            }
        }

        return k == heap.size() ? heap.poll() : -1;
    }

}