import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        Queue<Integer> queueA = new LinkedList<>();
        Queue<Integer> queueB = new LinkedList<>();
        long sumA = 0;
        long sumB = 0;
        for (int i=0;i<queue1.length;i++) {
            int nowA = queue1[i];
            sumA += nowA;
            queueA.add(nowA);
            int nowB = queue2[i];
            sumB += nowB;
            queueB.add(nowB);
        }
        
        if (sumA == sumB) {
            return 0;
        }
        
        int size = queue1.length + queue2.length + 1;
        
        while (answer <= size) {
            if (sumA > sumB) {
                int polled = queueA.poll();
                sumA -= polled;
                sumB += polled;
                queueB.add(polled);
            } else if (sumA < sumB) {
                int polled = queueB.poll();
                sumB -= polled;
                sumA += polled;
                queueA.add(polled);
            }
            
            answer++;
            
            if (sumA == sumB) {
                return answer;
            }
        }
        
        return -1;
    }
}

// 각 큐의 합을 같게 만드는 최소 작업 횟수 구하기
