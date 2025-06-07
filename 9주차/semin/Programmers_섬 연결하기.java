import java.util.*;

class Solution {
    private static int find(int[] parents, int x){
        if(parents[x] != x){
            parents[x] = find(parents, parents[x]);
        }
        return parents[x];
    }
    private static void union(int[] parents, int x, int y){
        x = find(parents, x);
        y = find(parents, y);
        if(x>y){
            parents[y] = x;
        }else if(x<y){
            parents[x] = y;
        }
    }
    public int solution(int n, int[][] costs) {
        int ans = 0;
        int[] parents = new int[n];
        for(int i=0; i<n; i++)
            parents[i] = i;
        
        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));
        
        for(int i=0; i<costs.length; i++){
            //사이클 없으면 union & ans+=cost
            if(find(parents, costs[i][0]) != find(parents, costs[i][1])){
                union(parents, costs[i][0], costs[i][1]);
                ans += costs[i][2];
            }
            //사이클 있으면 pass
        }
        return ans;
    }
}
