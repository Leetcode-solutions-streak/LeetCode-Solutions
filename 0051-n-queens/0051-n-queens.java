class Solution {
    int n=0;
    List<List<String>> ans=new ArrayList<>();
    boolean [] col;
    boolean [] dail1;
    boolean [] dail2;
    char[][] board;
    public List<List<String>> solveNQueens(int n) {
        this.n=n;
        col=new boolean[n];
        dail1=new boolean[2*n-1];
        dail2=new boolean[2*n-1];
        board=new char[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(board[i],'.');
        }
        solve(0);
        return ans;
    }
    public void solve(int r){
        if(r==n) {
            List<String> list = new ArrayList<>();

            for(int i=0; i<n; i++){
                list.add(new String(board[i]));
            }

            ans.add(list);
            return;
        }

        for(int c=0;c<n;c++){
            int d1=r-c+n-1;
            int d2=r+c;
            if(col[c] || dail1[d1] || dail2[d2]){
                continue;
            }
            place(r,c);
            solve(r+1);
            remove(r,c);
        }
            
    }
    public void place (int r,int c){
        col[c]=true;
        dail1[r-c+n-1]=true;
        dail2[r+c]=true;
        board[r][c]='Q';
    }
    public void remove(int r,int c){
        col[c]=false;
        dail1[r-c+n-1]=false;
        dail2[r+c]=false;
        board[r][c]='.';
    }
}