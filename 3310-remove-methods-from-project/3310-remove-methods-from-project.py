from collections import defaultdict, deque

class Solution:
    def remainingMethods(self, n: int, k: int, invocations: list[list[int]]) -> list[int]:
        # 1. Build adjacency list for forward invocations
        adj = defaultdict(list)
        for u, v in invocations:
            adj[u].append(v)
            
        # 2. Find all suspicious methods using BFS starting from k
        suspicious = set([k])
        queue = deque([k])
        
        while queue:
            curr = queue.popleft()
            for neighbor in adj[curr]:
                if neighbor not in suspicious:
                    suspicious.add(neighbor)
                    queue.append(neighbor)
                    
        # 3. Check if any non-suspicious method invokes a suspicious method
        for u, v in invocations:
            if u not in suspicious and v in suspicious:
                # Removal invalid: return all original methods
                return list(range(n))
                
        # 4. Return remaining methods
        return [i for i in range(n) if i not in suspicious]