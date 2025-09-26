class Solution {
    public int triangleNumber(int[] nums) {
        //brute force
        // int ans=0;
        // for(int i=0; i<nums.length; i++) {
        //     for(int j=i+1; j<nums.length; j++) {
        //         for(int k=j+1; k<nums.length; k++) {
        //             if(nums[i]+nums[j]>nums[k] &&
        //                 nums[i]+nums[k]>nums[j] &&
        //                 nums[j]+nums[k]>nums[i])
        //                 ans++;
        //         }
        //     }
        // }
        // return ans;
        return twoPointer(nums);
    }
    public int twoPointer(int[] nums) {
        Arrays.sort(nums);
        int ans=0;
        for(int i=nums.length-1; i>=2; i--) {
            //2-pointer
            int left=0, right=i-1;
            while(left<right) {
                if(nums[left]+nums[right]>nums[i]) {
                    ans+=(right-left); // all pairs with this 'right' are valid
                    right--;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }
}