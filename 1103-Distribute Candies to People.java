class DistributeCandiesToPeople {

    // Simulation
    public int[] distributeCandiesI(int candies, int num_people) {
        int[] arr = new int[num_people];
        int i = 0;
        int c = 1;
        while (candies > 0) {
            if (c > candies) {
                arr[i] += candies;
            } else {
                arr[i] += c;
            }
            candies -= c;
            c++;
            i++;
            if (i >= arr.length) {
                i = 0;
            }
        }
        return arr;
    }

    // Math solution
    public int[] distributeCandies(int candies, int num_people) {
        int[] arr = new int[num_people];
        int p = (int) (Math.sqrt(2 * candies + 0.25) - 0.5);
        int remain = (int) (candies - (p + 1) * p * 0.5);
        int rows = p / num_people;
        int cols = p % num_people;
        for (int i = 0; i < num_people; i++) {
            arr[i] = (i + 1) * rows + (int) (rows * (rows - 1) * 0.5) * num_people;
            if (i < cols) {
                arr[i] += i + 1 + rows * num_people;
            }
        }
        arr[cols] += remain;
        return arr;
    }
}