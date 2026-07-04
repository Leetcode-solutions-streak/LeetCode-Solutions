class Solution {
    public int myAtoi(String s) {
        if(s==null || s.length()==0) return 0;

        int i=0;
        int n=s.length();

        while(i<n && s.charAt(i)==' '){
            i++;
        }
        if(i==n) return 0;

        int sign=1;
        if(s.charAt(i) == '+' || s.charAt(i)=='-'){
            sign =(s.charAt(i) == '-') ? -1 : 1;
            i++;
        }
        long res=0;
        while(i < n && Character.isDigit(s.charAt(i))){
            int digit= s.charAt(i) - '0';
            res =(res*10)+digit;
            if (sign == 1 && res > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && (-res) < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            
            i++;

        }
        return (int) (res *sign);
    }
}