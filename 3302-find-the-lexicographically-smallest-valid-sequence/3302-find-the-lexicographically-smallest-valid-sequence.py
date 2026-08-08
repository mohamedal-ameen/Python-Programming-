class Solution:
    def validSequence(self, word1: str, word2: str) -> list[int]:
        n, m = len(word1), len(word2)
        
        # last_pos[j] stores the largest index in word1 to match word2[j:]
        last_pos = [-1] * m
        ptr1 = n - 1
        
        for j in range(m - 1, -1, -1):
            while ptr1 >= 0 and word1[ptr1] != word2[j]:
                ptr1 -= 1
            if ptr1 >= 0:
                last_pos[j] = ptr1
                ptr1 -= 1
            else:
                break

        ans = []
        j = 0
        modified = False

        for i in range(n):
            if j == m:
                break

            is_match = (word1[i] == word2[j])
            suffix_valid = (j == m - 1) or (last_pos[j + 1] > i)

            if is_match:
                ans.append(i)
                j += 1
            elif not modified and suffix_valid:
                ans.append(i)
                modified = True
                j += 1

        return ans if len(ans) == m else []