import java.util.*;

class ShoppingOffers {

    // Recursion.
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return helper(price, special, needs, 0);
    }

    private int helper(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int pos) {
        int min = directPurchase(price, needs);
        for (int i = pos; i < special.size(); i++) {
            List<Integer> offer = special.get(i);
            List<Integer> temp = new ArrayList<Integer>();
            for (int j = 0; j < needs.size(); j++) {
                if (needs.get(j) < offer.get(j)) { // check if the current offer is valid
                    temp = null;
                    break;
                }
                temp.add(needs.get(j) - offer.get(j));
            }

            if (temp != null) { // use the current offer and try next
                min = Math.min(min, offer.get(offer.size() - 1) + helper(price, special, temp, i));
            }
        }

        return min;
    }

    private int directPurchase(List<Integer> price, List<Integer> needs) {
        int total = 0;
        for (int i = 0; i < needs.size(); i++) {
            total += price.get(i) * needs.get(i);
        }
        return total;
    }

    // Recursion + Memorization.
    public int shoppingOffersI(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        Map<List<Integer>, Integer> map = new HashMap<>();
        return shopping(price, special, needs, map);
    }

    public int shopping(List<Integer> price, List<List<Integer>> special, List<Integer> needs,
            Map<List<Integer>, Integer> map) {
        if (map.containsKey(needs))
            return map.get(needs);
        int j = 0, res = dot(needs, price);
        for (List<Integer> s : special) {
            ArrayList<Integer> clone = new ArrayList<>(needs);
            for (j = 0; j < needs.size(); j++) {
                int diff = clone.get(j) - s.get(j);
                if (diff < 0)
                    break;
                clone.set(j, diff);
            }
            if (j == needs.size())
                res = Math.min(res, s.get(j) + shopping(price, special, clone, map));
        }
        map.put(needs, res);
        return res;
    }

    public int dot(List<Integer> a, List<Integer> b) {
        int sum = 0;
        for (int i = 0; i < a.size(); i++) {
            sum += a.get(i) * b.get(i);
        }
        return sum;
    }
}
