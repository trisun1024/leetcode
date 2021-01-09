import java.util.*;

class KthSmallestNumberInMultiplicationTable {

    // Heap. Time = O(K*M*log(M)); Space = O(M);
    public int findKthNumberI(int m, int n, int k) {
        PriorityQueue<Node> heap = new PriorityQueue<>(m, new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                return a.val - b.val;
            }
        });
        for (int i = 1; i <= m; i++) {
            heap.offer(new Node(i, i));
        }
        Node node = null;
        for (int i = 0; i < k; i++) {
            node = heap.poll();
            int next = node.val + node.root;
            if (next <= node.root * n) {
                heap.offer(new Node(next, node.root));
            }
        }
        return node.val;
    }

    static class Node {
        int val;
        int root;

        public Node(int v, int r) {
            val = v;
            root = r;
        }
    }

    // Binary Search. Time = O(M*log(M*N)); Space = O(1);
    public int findKthNumber(int m, int n, int k) {
        int left = 1;
        int right = m * n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (enough(mid, m, n, k)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean enough(int x, int m, int n, int k) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            while (i * n > x)
                n--;
            count += n;
        }
        return count >= k;
    }
}