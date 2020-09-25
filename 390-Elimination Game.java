
class EliminationGame {
    public int lastRemaining(int n) {
        if(n <= 0) {
            return n;
        }
        int len = n; int head = 1; int step = 1;
        boolean left = true;
        while(len > 1) {
            if(left || len % 2!= 0) {
                head += step;
            }
            len /= 2;
            step *= 2;
            left = !left;
        }
        return head;
    }
}