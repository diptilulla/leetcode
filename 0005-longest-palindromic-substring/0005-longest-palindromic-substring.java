class Solution {
    boolean t[][] = new boolean[1000][1000];
    public String longestPalindrome(String s) {  //n^2
        int n=s.length();
        int maxLength=1;
        for(int i=0;i<n;i++)
        {
            t[i][i]=true;//all substring of length 1 true
        }
        int start=0,end=0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<i; j++) {
                if(s.charAt(i)==s.charAt(j)) {
                    if(i-j<=2 || t[i-1][j+1]) { //first condition - length=1,2, 3 but last 2 chars are same
                        t[i][j]=true;
                        if(maxLength<i-j+1) {
                            maxLength=i-j+1;
                            start=j;
                            end=i;
                        }
                    }
                        
                }
                
            }
        }
        return s.substring(start,end+1);
    }
    public String longestPalindrome1(String s) { //n^3
        int n=s.length();
        int maxLength=1;
        String ans=s.charAt(0)+"";
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                String subStr=s.substring(i,j+1);
                if(isPalindrome(subStr)) {
                    if(maxLength<j-i+1) {
                        maxLength=j-i+1;
                        ans=subStr;
                    }
                }
            }
        }
        return ans;
    }
    public String bruteForce(String s, int l, int r) { //n*2^n fine only for small inputs of n like 20
        if(l>r)
            return "";
        String subStr = s.substring(l, r+1);
        if(isPalindrome(subStr)) {
            return subStr;
        }
        String left=bruteForce(s, l+1, r);
        String right=bruteForce(s, l, r-1);
        return left.length()>right.length()?left:right;
    }
    public boolean isPalindrome(String s) {
        int i=0, j=s.length()-1;
        while(i<j) {
            if(s.charAt(i)!=s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
    
}