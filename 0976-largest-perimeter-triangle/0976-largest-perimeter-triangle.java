class Solution {
    public int largestPerimeter(int[] nums) {
        // int maxAns=0;
        // for(int i=0; i<nums.length; i++) {
        //     for(int j=i+1; j<nums.length; j++) {
        //         for(int k=j+1; k<nums.length; k++) {
        //             int perimeter = perimeter(nums[i], nums[j], nums[k]);
        //             maxAns=Math.max(perimeter, maxAns);
        //         }
        //     }
        // }
        // return maxAns;
        return slidingWindow(nums);
    }
    public int perimeter(int a, int b, int c) {
        if(a+b>c &&
                        a+c>b &&
                        b+c>a)
                        {
        int perimeter = a+b+c;
        return perimeter;
        }
        return 0;
    }
    public int slidingWindow(int[] nums) {
        Arrays.sort(nums);
        int ans=0;
        int n=nums.length, i=0, j=0, k=3, sum=0;
        while(j<n) {
            sum+=nums[j];
            if(j-i+1==k) {
                if(sum-nums[j]>nums[j])
                    ans=Math.max(sum, ans);
                sum-=nums[i];
                i++;
            }
            j++;
        }
        return ans;
    }
    
}