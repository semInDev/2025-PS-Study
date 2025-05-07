import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        
        long total = 0;
        long sum1 = 0;
        long sum2 = 0;
        for(int i=0; i<queue1.length; i++){
            sum1 += queue1[i];
            q1.offer(queue1[i]);
            sum2 += queue2[i];
            q2.offer(queue2[i]);
        }
        
        total = (sum1+sum2)/2;
        
        int cnt = 0;
        while(cnt < 600000){
            if(sum1 < total){
                int val2 = q2.poll();
                sum1 += val2;
                q1.offer(val2);
                sum2 -= val2;
                cnt++;
            }
            else if(sum1 > total){
                int val1 = q1.poll();
                sum1 -= val1;
                sum2 += val1;
                q2.offer(val1);
                cnt++;
            }
            else{
                break;
            }
        }
        
        if(sum1==total){
            answer=cnt;
        }
        else{
            answer=-1;
        }
        
        return answer;
    }
}