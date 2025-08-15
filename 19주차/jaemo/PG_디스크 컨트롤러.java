import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        Queue<Job> queue = new PriorityQueue<>();
        int jobsSize = jobs.length;
        
        int[] turnaroundTimes = new int[jobsSize];

        int time = 0;
        int execCount = 0;
        int jobIdx = 0;
        
        while (execCount < jobsSize) {
            while (jobIdx < jobsSize && jobs[jobIdx][0] <= time) {
                int[] job = jobs[jobIdx];
                queue.offer(new Job(jobIdx, job[0], job[1]));
                jobIdx++;
            }
            
            if (queue.isEmpty()) {
                time = jobs[jobIdx][0];
            } else {
                Job job = queue.poll();
                time += job.duration;
                execCount++;
                turnaroundTimes[job.number] = time - job.reqTime;
            }
        }
        
        int sum = 0;
        for (int i=0;i<turnaroundTimes.length;i++) {
            sum += turnaroundTimes[i];
        }
        
        return sum / turnaroundTimes.length;
    }
}

class Job implements Comparable<Job> {
    int number;
    int reqTime;
    int duration;
    
    public Job(int n, int r, int d) {
        number = n;
        reqTime = r;
        duration = d;
    }
    
    @Override
    public int compareTo(Job o) {
        if (this.duration == o.duration) {
            if (this.reqTime == o.reqTime) {
                return this.number - o.number;
            }
            return this.reqTime - o.reqTime;
        }
        return this.duration - o.duration;
    }
}
