import java.util.*;

 class ZigzagIterator {

    boolean first = true;
    List<Integer> v1;
    List<Integer> v2;
    int ptr1 = 0;
    int ptr2 = 0;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.first = true;
        this.v1 = v1;
        this.v2 = v2;
    }

    public int next() {
        int result = -1;
        if (ptr1 < v1.size() && ptr2 < v2.size()) {
            if (first) {
                result = v1.get(ptr1++);
                first = false;
                return result;
            } else {
                result = v2.get(ptr2++);
                first = true;
                return result;
            }
        }

        // if only v1 exists
        if (ptr1 < v1.size()) {
            result = v1.get(ptr1++);
        }

        // if only v2 exists
        if (ptr2 < v2.size()) {
            result = v2.get(ptr2++);
        }

        return result;
    }

    public boolean hasNext() {
        return ptr1 < v1.size() || ptr2 < v2.size();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2); while (i.hasNext()) v[f()] =
 * i.next();
 */