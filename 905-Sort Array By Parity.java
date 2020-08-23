class SortArrayByParity {

    // Two Pointers, In-place. 
    public int[] sortArrayByParity(int[] A) {
        int i = 0;
        int j = 0;
        while (j < A.length) {
            if (A[j] % 2 == 0) {
                swap(A, i++, j++);
            } else {
                j++;
            }
        }
        return A;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}