import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets,(o1,o2)-> o1[1]-o2[1]);

        int cur = -1;
        int ans = 0;

        for(int[] target : targets){
            if(cur == -1){
                ans++;
                cur = target[1];
                continue;
            }

            if(target[0] < cur) continue;

            ans++;
            cur = target[1];
        }

        return ans;

    }
}