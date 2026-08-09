class Solution:
    def stoneGameII(self, piles: list[int]) -> int:
        n = len(piles)
        
        # Calculate suffix sums to quickly query total remaining stones from index i to end
        suffix_sum = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
            suffix_sum[i] = suffix_sum[i + 1] + piles[i]
            
        memo = {}

        def dp(i: int, m: int) -> int:
            # Base case: If current player can take all remaining piles
            if i + 2 * m >= n:
                return suffix_sum[i]
            
            if (i, m) in memo:
                return memo[(i, m)]
            
            max_stones = 0
            # Try taking X piles where 1 <= X <= 2 * M
            for x in range(1, 2 * m + 1):
                # Max stones current player can get = Total remaining stones - best opponent response
                max_stones = max(max_stones, suffix_sum[i] - dp(i + x, max(m, x)))
            
            memo[(i, m)] = max_stones
            return max_stones

        return dp(0, 1)