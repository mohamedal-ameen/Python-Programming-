class Solution:
    def missingInteger(self, nums: list[int]) -> int:
        # Step 1: Find the sum of the longest sequential prefix
        prefix_sum = nums[0]
        i = 1
        while i < len(nums) and nums[i] == nums[i - 1] + 1:
            prefix_sum += nums[i]
            i += 1
            
        # Step 2: Store elements in a set for O(1) lookup
        num_set = set(nums)
        
        # Step 3: Find the smallest integer >= prefix_sum missing from nums
        x = prefix_sum
        while x in num_set:
            x += 1
            
        return x