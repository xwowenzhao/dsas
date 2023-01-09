/*
Problem:
Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

Example 1:
Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6

Example 2:
Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
 
Constraints:
1 <= nums.length <= 105
nums[i] is either 0 or 1.
0 <= k <= nums.length
*/

class Solution {
    public int longestOnes(int[] nums, int k) {
        int zeros = 0;
        int left = 0;
        int right = 0;
        for (right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeros++;
            }
        
            if (zeros > k) {
                if (nums[left] == 0) {
                    zeros--;
                }
                
                left++;
            }
        }
        return right - left;
    }
}