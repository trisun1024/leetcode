import java.util.*;

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */
class Celebrity {

    public class CelebritySolution extends Relation {

        private int numberOfPeople;

        public int findCelebrity(int n) {
            numberOfPeople = n;
            int candidate = 0;
            for (int i = 0; i < n; i++) {
                if (knows(candidate, i)) {
                    candidate = i;
                }
            }
            return checkIfCelebrity(candidate);
        }

        private int checkIfCelebrity(int i) {
            for (int j = 0; j < numberOfPeople; j++) {
                if (i == j) {
                    continue;
                }
                if (knows(i, j) || !knows(j, i)) {
                    return -1;
                }
            }
            return i;
        }
    }

    public class SolutionI extends Relation {

        private int numberOfPeople;
        private Map<Pair<Integer, Integer>, Boolean> cache = new HashMap<>();

        @Override
        public boolean knows(int a, int b) {
            if (!cache.containsKey(new Pair(a, b))) {
                cache.put(new Pair(a, b), super.knows(a, b));
            }
            return cache.get(new Pair(a, b));
        }

        public int findCelebrity(int n) {
            numberOfPeople = n;
            int celebrityCandidate = 0;
            for (int i = 0; i < n; i++) {
                if (knows(celebrityCandidate, i)) {
                    celebrityCandidate = i;
                }
            }
            return checkIfCelebrity(celebrityCandidate);
        }

        private int checkIfCelebrity(int i) {
            for (int j = 0; j < numberOfPeople; j++) {
                if (i == j) {
                    continue;
                }
                if (knows(i, j) || !knows(j, i)) {
                    return -1;
                }
            }
            return i;
        }
    }

}

class Relation {
    public boolean knows(int i, int j) {
        return true;
    }
}