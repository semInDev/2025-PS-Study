import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<stages.length;i++) {
            int stage = stages[i];
            if (map.containsKey(stage)) {
                map.put(stage, map.get(stage)+1);
            } else {
                map.put(stage, 1);
            }
        }
        
        int users = stages.length;
        Queue<Stage> pq = new PriorityQueue<>();
        for (int i=1;i<=N;i++) {
            if (!map.containsKey(i)) {
                pq.offer(new Stage(i, 0));
                continue;
            }
            pq.offer(new Stage(i, (double) map.get(i) / users));
            users -= map.get(i);
        }
        
        int[] answer = new int[N];
        for (int i=0;i<N;i++) {
            answer[i] = pq.poll().num;
        }
        
        return answer;
    }
}

class Stage implements Comparable<Stage> {
    int num;
    double failRatio;
    
    public Stage(int num, double failRatio) {
        this.num = num;
        this.failRatio = failRatio;
    }
    
    @Override
    public int compareTo(Stage o) {
        if (this.failRatio == o.failRatio) {
            return this.num - o.num;
        }
        return Double.compare(o.failRatio, this.failRatio);
    }
}
