

class PerfectNumber {

// Brute Force. Time = O(N); Space = O(1);
    public boolean checkPerfectNumberI(int num) {
        if (num <= 0) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                sum += i;
            }

        }
        return sum == num;
    }
    // Brute Force with early stop.
    public boolean checkPerfectNumberII(int num) {
        if (num <= 0) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                sum += i;
            }
            if(sum>num) {
                return false;
            }
        }
        return sum == num;
    }

    // Optimal. Time = O(sqrt(N)); Space = O(1);
    public boolean checkPerfectNumber(int num) {
        if (num <= 0) {
            return false;
        }
        int sum = 0;
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i != num) {
                    sum += num / i;
                }

            }
        }
        return sum - num == num;
    }

}