class SegmentTree:
    def __init__(self, s: str):
        self.n = len(s)
        self.s = list(s)
        self.tree_max = [0] * (4 * self.n)
        self.tree_pref = [0] * (4 * self.n)
        self.tree_suff = [0] * (4 * self.n)
        self.tree_lc = [''] * (4 * self.n)
        self.tree_rc = [''] * (4 * self.n)
        self._build(1, 0, self.n - 1)

    def _merge(self, node: int, l_len: int, r_len: int):
        left_child, right_child = 2 * node, 2 * node + 1
        
        self.tree_lc[node] = self.tree_lc[left_child]
        self.tree_rc[node] = self.tree_rc[right_child]

        # Calculate prefix length
        if self.tree_pref[left_child] == l_len and self.tree_lc[left_child] == self.tree_lc[right_child]:
            self.tree_pref[node] = l_len + self.tree_pref[right_child]
        else:
            self.tree_pref[node] = self.tree_pref[left_child]

        # Calculate suffix length
        if self.tree_suff[right_child] == r_len and self.tree_rc[right_child] == self.tree_rc[left_child]:
            self.tree_suff[node] = r_len + self.tree_suff[left_child]
        else:
            self.tree_suff[node] = self.tree_suff[right_child]

        # Calculate max length
        cross = 0
        if self.tree_rc[left_child] == self.tree_lc[right_child]:
            cross = self.tree_suff[left_child] + self.tree_pref[right_child]

        self.tree_max[node] = max(self.tree_max[left_child], self.tree_max[right_child], cross)

    def _build(self, node: int, start: int, end: int):
        if start == end:
            ch = self.s[start]
            self.tree_max[node] = 1
            self.tree_pref[node] = 1
            self.tree_suff[node] = 1
            self.tree_lc[node] = ch
            self.tree_rc[node] = ch
            return

        mid = (start + end) // 2
        self._build(2 * node, start, mid)
        self._build(2 * node + 1, mid + 1, end)
        self._merge(node, mid - start + 1, end - mid)

    def update(self, node: int, start: int, end: int, idx: int, ch: str):
        if start == end:
            self.s[idx] = ch
            self.tree_lc[node] = ch
            self.tree_rc[node] = ch
            return

        mid = (start + end) // 2
        if idx <= mid:
            self.update(2 * node, start, mid, idx, ch)
        else:
            self.update(2 * node + 1, mid + 1, end, idx, ch)

        self._merge(node, mid - start + 1, end - mid)


class Solution:
    def longestRepeating(self, s: str, queryCharacters: str, queryIndices: list[int]) -> list[int]:
        st = SegmentTree(s)
        ans = []
        n = len(s)

        for ch, idx in zip(queryCharacters, queryIndices):
            st.update(1, 0, n - 1, idx, ch)
            ans.append(st.tree_max[1])

        return ans