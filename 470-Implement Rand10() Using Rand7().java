import java.util.Random;

class Sol extends SolBase {
    public int rand10() {
        while (true) {
            int a = rand7() - 1;
            int b = rand7() - 1;
            int c = rand7() - 1;

            int k = 7 * (7 * a + b) + c;
            if (k < 340) {
                k = k % 10;
                if (k == 0)
                    k = 10;

                return k;
            }

        }
    }
}

class SolBase {
    Random rand = new Random();

    public int rand7() {
        return rand.nextInt(7) + 1;
    }
}