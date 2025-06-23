import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] count = new int[N+2];
        for(int stage : stages){
            count[stage]++;
        }
      
        double[] fails = new double[N+1];
        fails[0] = -1.;
        int total = stages.length;
        for(int i=1;i<N+1;i++){
            total -= count[i-1];
            if(total == 0 || count[i] == 0){
                fails[i] = 0.;
            }else{
                fails[i] = (double)count[i]/total;
            }
        }
      
        int[] answer = new int[N];
        int index = 0;
        while(index<N){
            double max_fail = Arrays.stream(fails).max().getAsDouble();
            for(int j=1; j<N+1; j++){
                if(max_fail == fails[j]){
                    answer[index++] = j;
                    fails[j] = -1;
                }
            }
        }
        return answer;
    }
}
