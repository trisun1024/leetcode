
class ReachNumber {

    // Math.
    public int reachNumber(int target) {
        target = Math.abs(target);
        int k = 0;
        while (target > 0) {
            target -= ++k;
        }
        return target % 2 == 0 ? k : k + 1 + k % 2;
    }

    public int reachNumberI(int target) {
        target = Math.abs(target);
        double n = target;
        double ceil = Math.ceil((-1 + Math.sqrt(1 + 8 * n)) / 2);
        double sum = (ceil * (ceil + 1)) / 2;
        while ((sum - target) % 2 == 1) {
            sum += ++ceil;
        }
        return (int) ceil;
    }
}