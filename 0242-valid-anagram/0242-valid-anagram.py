from collections import Counter

class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        # If lengths are different, they cannot be anagrams
        if len(s) != len(t):
            return False
            
        # Compare character frequency counts
        return Counter(s) == Counter(t)