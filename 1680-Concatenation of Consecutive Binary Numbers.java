
class ConcatenationOfConsecutiveBinaryNumbers {

    public int concatenatedBinary(int n) {
        final int MOD = 1000000007;
        int res = 0;
        for (int i = 1; i <= n; i++) {
            String binary = Integer.toBinaryString(i);
            for (int j = 0; j < binary.length(); j++) {
                res = (res * 2 + (binary.charAt(j) - '0')) % MOD;
            }
        }
        return res;
    }

    // Math.
    public int concatenatedBinaryI(int n) {
        final int MOD = 1000000007;
        int length = 0; // bit length of addends
        long result = 0; // long accumulator
        for (int i = 1; i <= n; i++) {
            // when meets power of 2, increase the bit length
            if (Math.pow(2, (int) (Math.log(i) / Math.log(2))) == i) {
                length++;
            }
            result = ((result * (int) Math.pow(2, length)) + i) % MOD;
        }
        return (int) result;
    }

    // Math Bitwise.
    public int concatenatedBinaryII(int n) {
        final int MOD = 1000000007;
        int length = 0; // bit length of addends
        long result = 0; // long accumulator
        for (int i = 1; i <= n; i++) {
            // when meets power of 2, increase the bit length
            if ((i & (i - 1)) == 0) {
                length++;
            }
            result = ((result << length) | i) % MOD;
        }
        return (int) result;
    }

}