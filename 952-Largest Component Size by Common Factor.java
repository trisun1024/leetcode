import java.util.*;

class LargestComponentSizeByCommonFactor {

    public int largestComponentSize(int[] A) {
        int maxValue = Arrays.stream(A).reduce(A[0], (x, y) -> x > y ? x : y);
        DisjointSetUnion dsu = new DisjointSetUnion(maxValue);
        Map<Integer, Integer> numFactorMap = new HashMap<>();
        for (int num : A) {
            List<Integer> primeFactors = new ArrayList<>(new HashSet<>(primeDecompose(num)));
            numFactorMap.put(num, primeFactors.get(0));
            for (int i = 0; i < primeFactors.size() - 1; i++) {
                dsu.union(primeFactors.get(i), primeFactors.get(i + 1));
            }
        }
        int maxGroupSize = 0;
        Map<Integer, Integer> groupCount = new HashMap<>();
        for (int num : A) {
            Integer groupId = dsu.find(numFactorMap.get(num));
            Integer count = groupCount.getOrDefault(groupId, 0);
            groupCount.put(groupId, count + 1);
            maxGroupSize = Math.max(maxGroupSize, count + 1);
        }
        return maxGroupSize;
    }

    private List<Integer> primeDecompose(int num) {
        List<Integer> primeFactors = new ArrayList<>();
        int factor = 2;
        while (num >= factor * factor) {
            if (num % factor == 0) {
                primeFactors.add(factor);
                num = num / factor;
            } else {
                factor += 1;
            }
        }
        primeFactors.add(num);
        return primeFactors;
    }
}

class DisjointSetUnion {
    private int[] parent;
    private int[] size;

    public DisjointSetUnion(int size) {
        this.parent = new int[size + 1];
        this.size = new int[size + 1];
        for (int i = 0; i < size + 1; i++) {
            this.parent[i] = i;
            this.size[i] = i;
        }
    }

    public int find(int x) {
        if (this.parent[x] != x) {
            this.parent[x] = this.find(this.parent[x]);
        }
        return this.parent[x];
    }

    public int union(int x, int y) {
        int px = this.find(x);
        int py = this.find(y);
        // if two nodes share the same group
        if (px == py) {
            return px;
        }
        // otherwise, connect two sets
        if (this.size[px] > this.size[py]) {
            int temp = px;
            px = py;
            py = temp;
        }
        // add the smaller component to the larger one
        this.parent[px] = py;
        this.size[py] += this.size[px];
        return py;
    }
}