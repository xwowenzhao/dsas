/*
Problem:
You are given an m x n integer matrix grid​​​.
A rhombus sum is the sum of the elements that form the border of a regular rhombus shape in grid​​​. The rhombus must have the shape of a square rotated 45 degrees with each of the corners centered in a grid cell. 
Note that the rhombus can have an area of 0.
Return the biggest three distinct rhombus sums in the grid in descending order. If there are less than three distinct values, return all of them.
*/
class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int[] sums = {0, 0, 0};        
        for (int side = 1; side <= (Math.min(grid.length, grid[0].length) + 1) / 2; side++) {
            for (int v = 0; v <= grid.length - 2 * side + 1; v++) {
                for (int h = side - 1; h <= grid[0].length - side; h++) {
                    int sum = 0;
                    if (side == 1) {
                        sum = grid[v][h];
                    } else {                        
                        for (int i = 0; i <= side - 2; i++) {
                            sum += grid[v + i][h - i];
                            sum += grid[v + side - 1 + i][h - side + 1 + i];
                            sum += grid[v + 2 * (side - 1) - i][h + i];
                            sum += grid[v + side - 1 - i][h + side - 1 - i];
                        }
                    }
                    
                    boolean doesExist = false;
                    for (int i = 0; i < sums.length; i++) {
                        doesExist = doesExist || sum == sums[i];
                        if (!doesExist && sum > sums[i]) {
                            for (int j = sums.length - 1; j >= i + 1; j--) {
                               sums[j] = sums[j - 1];
                            }
                            sums[i] = sum;
                            break;
                        }
                    }                           
                }               
            }
        }
        
        int length = 0;
        for (int i = 0; i < sums.length; i++) {
            if (sums[i] > 0) length++;
        }
        int[] ans = new int[length];
        for (int i = 0; i < length; i++) {
            ans[i] = sums[i];
        }        
        
        return ans;        
    }
}