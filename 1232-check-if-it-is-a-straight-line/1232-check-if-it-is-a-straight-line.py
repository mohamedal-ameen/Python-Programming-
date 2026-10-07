class Solution:
    def checkStraightLine(self, coordinates: list[list[int]]) -> bool:
        # Extract the first two points to define the baseline slope/direction
        x0, y0 = coordinates[0]
        x1, y1 = coordinates[1]
        
        dx = x1 - x0
        dy = y1 - y0
        
        # Check every subsequent point against the first two points
        for i in range(2, len(coordinates)):
            x, y = coordinates[i]
            
            # Using cross-multiplication to avoid division by zero:
            # (y1 - y0) / (x1 - x0) == (y - y1) / (x - x1)
            # which rearranges to: dx * (y - y1) == dy * (x - x1)
            if dx * (y - y1) != dy * (x - x1):
                return False
                
        return True
        