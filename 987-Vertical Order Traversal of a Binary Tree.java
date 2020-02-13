import java.util.*;

class Solution {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int v) {
            this.val = v;
        }
    }

    // use hashmap + bfs
    static class Node {
        TreeNode root;
        int horDistance;
        int verDistance;

        Node(TreeNode node, int hor, int ver) {
            this.root = node;
            this.horDistance = hor;
            this.verDistance = ver;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Integer, List<Node>> map = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(root, 0, 0));
        int minHorDist = 0;
        int maxHorDist = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            map.putIfAbsent(cur.horDistance, new ArrayList<>());
            minHorDist = Math.min(minHorDist, cur.horDistance);
            maxHorDist = Math.max(maxHorDist, cur.horDistance);
            map.get(cur.horDistance).add(cur);
            if (cur.root.left != null) {
                queue.offer(new Node(cur.root.left, cur.horDistance - 1, cur.verDistance - 1));
            }
            if (cur.root.right != null) {
                queue.offer(new Node(cur.root.right, cur.horDistance + 1, cur.verDistance - 1));
            }
        }

        int index = 0;
        for (int i = minHorDist; i <= maxHorDist; i++) {
            Collections.sort(map.get(i), new Comparator<Node>() {
                @Override
                public int compare(Node a, Node b) {
                    if (a.verDistance == b.verDistance) {
                        return a.root.val < b.root.val ? -1 : a.root.val == b.root.val ? 0 : 1;
                    }
                    return a.verDistance < b.verDistance ? 1 : -1;
                }
            });
            res.add(new ArrayList<>());
            for (Node node : map.get(i)) {
                res.get(index).add(node.root.val);
            }
            index++;
        }
        return res;
    }

    // dfs
    TreeMap<Integer, List<int[]>> map = new TreeMap<>();

    public List<List<Integer>> verticalTraversalII(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, 0);
        for (Map.Entry<Integer, List<int[]>> entry : map.entrySet()) {
            List<int[]> curr = entry.getValue();
            curr.sort(new Comparator<int[]>() {
                @Override
                public int compare(int[] a, int[] b) {
                    if (a[0] == b[0]) {
                        return a[1] - b[1];
                    }
                    return b[0] - a[0];
                }
            });
            List<Integer> c = new ArrayList<>();
            for (int i = 0; i < curr.size(); i++) {
                c.add(curr.get(i)[1]);
            }
            res.add(c);
        }
        return res;
    }

    private void dfs(TreeNode node, int x, int y) {
        if (node == null)
            return;
        if (!map.containsKey(x)) {
            map.put(x, new ArrayList<int[]>());
        }
        List<int[]> tmp = map.get(x);
        tmp.add(new int[] { y, node.val });
        map.put(x, tmp);
        dfs(node.left, x - 1, y - 1);
        dfs(node.right, x + 1, y - 1);
    }
}