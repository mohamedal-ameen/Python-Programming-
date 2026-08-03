#include <vector>
#include <string>
#include <numeric>
#include <algorithm>

class Solution {
public:
    std::string stoneGameIII(std::vector<int>& stoneValue) {
        int n = stoneValue.size();
        std::vector<int> dp(n + 1, 0);

        // Process from right to left (bottom-up DP)
        for (int i = n - 1; i >= 0; --i) {
            int max_diff = -1e9; // Initialize with a very small number
            int take_sum = 0;

            // Current player can take 1, 2, or 3 stones
            for (int k = 0; k < 3 && (i + k) < n; ++k) {
                take_sum += stoneValue[i + k];
                max_diff = std::max(max_diff, take_sum - dp[i + k + 1]);
            }

            dp[i] = max_diff;
        }

        if (dp[0] > 0) return "Alice";
        if (dp[0] < 0) return "Bob";
        return "Tie";
    }
};