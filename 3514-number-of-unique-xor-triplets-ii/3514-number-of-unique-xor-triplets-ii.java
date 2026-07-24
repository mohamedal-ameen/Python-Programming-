import java.util.HashSet;
import java.util.Set;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // Step 1: Extract unique values from nums
        Set<Integer> uniqueNums = new HashSet<>();
        for (int x : nums) {
            uniqueNums.add(x);
        }

        // Step 2: Start with {0} and expand 3 times
        boolean[] curr = new boolean[2048];
        curr[0] = true;

        for (int round = 0; round < 3; round++) {
            boolean[] next = new boolean[2048];
            for (int val = 0; val < 2048; val++) {
                if (curr[val]) {
                    for (int x : uniqueNums) {
                        next[val ^ x] = true;
                    }
                }
            }
            curr = next;
        }

        // Step 3: Count unique XOR results
        int ans = 0;
        for (boolean b : curr) {
            if (b) ans++;
        }

        return ans;
    }
}