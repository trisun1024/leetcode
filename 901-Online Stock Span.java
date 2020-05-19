import java.util.*;

class StockSpanner {

    Deque<Integer> prices;
    Deque<Integer> weights;

    public StockSpanner() {
        prices = new ArrayDeque<>();
        weights = new ArrayDeque<>();
    }

    public int next(int price) {
        int w = 1;
        while (!prices.isEmpty() && prices.peek() <= price) {
            prices.pop();
            w += weights.pop();
        }

        prices.push(price);
        weights.push(w);
        return w;
    }
}