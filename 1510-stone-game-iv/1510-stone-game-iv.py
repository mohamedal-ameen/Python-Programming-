class Solution:
    def winnerSquareGame(self, n: int) -> bool:
        # dp[i] represents if the player whose turn it is can win with i stones
        dp = [False] * (n + 1)
        
        for i in range(1, n + 1):
            k = 1
            while k * k <= i:
                # If removing k*k stones leaves the opponent in a losing state,
                # the current player can win.
                if not dp[i - k * k]:
                    dp[i] = True
                    break  # Found a winning move, no need to check other square numbers
                k += 1
                
        return dp[n]