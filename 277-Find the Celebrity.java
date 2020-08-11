/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */
class Celebrity {
    class Relation {
        public boolean knows(int i, int j) {
            return true;
        }
    }

    public class CelebritySolution extends Relation {

        private int people;

        public int findCelebrity(int n) {
            people = n;
            int candidate = 0;
            for (int i = 0; i < n; i++) {
                if (knows(candidate, i)) {
                    candidate = i;
                }
            }
            if (isCelebrity(candidate)) {
                return candidate;
            }
            return -1;
        }

        private boolean isCelebrity(int i) {
            for (int j = 0; j < people; j++) {
                if (i == j) {
                    continue;
                }
                if (knows(i, j) || !knows(j, i)) {
                    return false;
                }
            }
            return true;
        }
    }
}
