import java.util.*;
class FindKClosestElements {

    // middle out, Time = O(K*log(N));
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        if(arr==null || arr.length==0) {
            return res;
        }
        int l = 0;
        int r = arr.length-1;
        while(l < r-1) {
            int m = l + (r-l)/2;
            if(arr[m] > x) {
                r = m;
            } else {
                l = m;
            }
        }
        // post-processing
        while(l >= 0 && r < arr.length && k > 0) {
            if(Math.abs(arr[l]-x) <= Math.abs(arr[r]-x)) {
                res.add(arr[l--]);
            } else {
                res.add(arr[r++]);
            }
            k--;
        }
        while(l >= 0 && k > 0) {
            res.add(arr[l--]);
            k--;
        }
        while(r < arr.length && k > 0) {
            res.add(arr[r++]);
            k--;
        }
        Collections.sort(res);
        return res;
    }

    // better solution
    public List<Integer> findClosestElementsI(int[] arr, int k , int x) {
        int l = 0, r = k;
        List<Integer> ans = new ArrayList<>();
        while(r<arr.length && (x-arr[l] > arr[r]-x)){
            l++;
            r++;
        }
        for(int i = l; i < r; i++){
            ans.add(arr[i]);
        }
        return ans;
    }
}