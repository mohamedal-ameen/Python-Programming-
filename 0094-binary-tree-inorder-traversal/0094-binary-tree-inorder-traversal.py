# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def inorderTraversal(self, root: Optional[TreeNode]) -> list[int]:
        res = []

        def dfs(node):
            if not node:
                return
            dfs(node.left)      # 1. Left
            res.append(node.val) # 2. Node
            dfs(node.right)     # 3. Right

        dfs(root)
        return res