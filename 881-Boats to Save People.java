import java.util.*;
class Solution {

    // greedy 
    // Time O(N*log(N)) Space O(N)
    public int numRescueBoats(int[] people, int limit) {
        // sorting cost maximum time complexity
        Arrays.sort(people);
        int i = 0;
        int j = people.length - 1;
        int min = 0;
        while (i <= j) {
            min++;
            if (people[i] + people[j] <= limit) {
                i++;
            }
            j--;
        }
        return min;
    }
}