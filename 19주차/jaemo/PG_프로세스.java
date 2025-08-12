import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Process> queue = new LinkedList<>();
        Queue<Process> pq = new PriorityQueue<>();
        for (int i=0;i<priorities.length;i++) {
            queue.offer(new Process(priorities[i], i));
            pq.offer(new Process(priorities[i], i));
        }
        
        int executionOrder = 0;
        while (!queue.isEmpty()) {
            Process now = queue.poll();
            Process top = pq.peek();
            if (now.priority == top.priority) {
                pq.poll();
                executionOrder++;
                if (now.location == location) {
                   return executionOrder;
                }
            } else {
                queue.offer(now);
            }
        }
    
        return executionOrder;
    }
}

class Process implements Comparable<Process> {
    int priority, location;
    
    public Process(int p, int l) {
        priority = p;
        location = l;
    }
    
    @Override
    public int compareTo(Process o) {
        return o.priority - this.priority;
    }
}
