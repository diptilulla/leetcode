class Solution {
    public int maxFrequencyElements(int[] nums) {
        int[] freq = new int[100];
        int n=nums.length;
        for(int i=0; i<n; i++) {
            freq[nums[i]-1]++;
        }
        int maxF=0;
        for(int x:freq) {
            maxF=Math.max(maxF, x);
        }
        // System.out.println(Arrays.toString(freq));
        int ans=0;
        for(int x:freq) {
            if(x==maxF) {
                ans++;
            }
        }
        return ans*maxF;
    }
}