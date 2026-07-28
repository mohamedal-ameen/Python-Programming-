class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        // Find initial differences using the first two points
        int deltaX = coordinates[1][0] - coordinates[0][0];
        int deltaY = coordinates[1][1] - coordinates[0][1];
        
        // Check all other points against the initial slope using cross-multiplication
        for (int i = 2; i < coordinates.length; i++) {
            int currentDeltaX = coordinates[i][0] - coordinates[0][0];
            int currentDeltaY = coordinates[i][1] - coordinates[0][1];
            
            // If deltaY * currentDeltaX != deltaX * currentDeltaY, it's not a straight line
            if (deltaY * currentDeltaX != deltaX * currentDeltaY) {
                return false;
            }
        }
        
        return true;
    }
}