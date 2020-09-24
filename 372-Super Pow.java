class SuperPow {

    //
    final int MOD = 1337; // prime factorization 7 * 191

    public int superPow(int a, int[] b) {
        int r1 = superPow(a, b, 7);
        int r2 = superPow(a, b, 191);
        for (int i = r2; i < MOD; i += 191) {
            if (i % 7 == r1) {
                return i;
            }
        }
        return -1;
    }

    public int superPow(int a, int[] b, int p) {
        a = a % p;
        int c = 0;
        // Fermat's little theorem: a^(p-1) == 1 mod p
        for (int i : b) {
            c = (c * 10 + i) % (p - 1);
        }
        return powmod(a, c, p);
    }

    // compute a^c % mod
    int powmod(int a, int c, int mod) {
        if (a == 0) {
            return 0;
        }
        int ans = 1;
        int m = a;
        while (c > 0) {
            if (c % 2 == 1) {
                ans = (ans * m) % mod;
            }
            m = (m * m) % mod;
            c >>= 1;
        }
        return ans;
    }
}