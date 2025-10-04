class Solution {
    public int maxArea(int[] height) {
        int n=height.length,i=0, j=n-1;
        int max=-1;
        while(i<j) {
            int h=height[j];
            if(height[i]<height[j]) {
                h=height[i];
            }
            max=Math.max(max, h*(j-i));
            if(height[i]<height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
        

    }
}