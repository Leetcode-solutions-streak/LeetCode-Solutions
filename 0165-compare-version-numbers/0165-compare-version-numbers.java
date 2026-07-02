class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1=version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int maxx=Math.max(v1.length,v2.length);
        for(int i=0;i<maxx;i++){

        float n1 = i < v1.length ? Float.parseFloat(v1[i]) : 0.0f;

        float n2 = i < v2.length ? Float.parseFloat(v2[i]) : 0.0f;

        if(n1<n2){
            return -1;
        } else if(n1>n2){
            return 1;
        }
        }
        
            return 0;
        }
        
    }
