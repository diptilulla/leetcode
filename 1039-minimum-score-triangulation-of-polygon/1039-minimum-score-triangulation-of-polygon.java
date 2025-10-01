class Solution {
    int[][] t = new int[50][50];
    public int minScoreTriangulation(int[] values) {
        int n=values.length, i=0, j=n-1;
        for(int[] arr:t) {
            Arrays.fill(arr, -1);
        }
        return topDown(values, i, j);
    }
    public int topDown(int[] values, int i, int j) {
        if(j-i+1<3)
            return 0;
        if(t[i][j]!=-1)
            return t[i][j];
        int min=Integer.MAX_VALUE;
        for(int k=i+1; k<j; k++) {
            int temp = values[i]*values[k]*values[j] + topDown(values, i, k)+topDown(values, k, j);
            min=Math.min(min, temp);
        }
        return t[i][j]=min;
    }
}