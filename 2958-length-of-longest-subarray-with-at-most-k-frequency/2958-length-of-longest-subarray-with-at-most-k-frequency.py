class Solution:
    def maxSubarrayLength(self, nums: list[int], k: int) -> int:
        freq = {}
        left = 0
        max_len = 0
        
        for right in range(len(nums)):
            num = nums[right]
            freq[num] = freq.get(num, 0) + 1
            
            # Shrink window if frequency of nums[right] exceeds k
            while freq[num] > k:
                freq[nums[left]] -= 1
                left += 1
            
            # Update max length of valid window
            max_len = max(max_len, right - left + 1)
            
        return max_len