class Solution {
public:
    bool predictTheWinner(vector<int>& nums) {
        int n = nums.size();
        // Dynamic programming array to store max score differences
        vector<int> dp(nums.begin(), nums.end());

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[j] = max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }

        return dp[n - 1] >= 0;
    }
};