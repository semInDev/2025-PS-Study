import java.util.*;

class Solution {
    List<Integer> infoList;
    static int[] peach;
    static int[] lion = {0,0,0,0,0,0,0,0,0,0,0};
    static int maxScore = 0;
    static int[] answer = {0,0,0,0,0,0,0,0,0,0,0};

    void backtracking(int start, int cnt, int size){
        if(cnt >= size){
            int peach_score = 0;
            int lion_score = 0;

            for(int i = 0; i < peach.length; i++){
                if(peach[i] == 0 && lion[i] == 0) continue;
                if(lion[i] > peach[i]) lion_score += 10-i;
                else peach_score += 10-i;
            }

            int score_gap = lion_score - peach_score;

            if(lion_score > peach_score){
                if(score_gap > maxScore){
                    maxScore = score_gap;
                    answer = lion.clone();
                } else if(score_gap == maxScore){
                    for(int i = 10; i >= 0; i--){
                        if(lion[i] > answer[i]){
                            answer = lion.clone();
                        } else if(lion[i] < answer[i]) return;
                    }
                }
            }
            return;
        }

        for(int i = start; i <= 10; i++){
            if(lion[i] > peach[i]) continue;
            lion[i]++;
            backtracking(i, cnt+1, size);
            lion[i]--;
        }
    }

    public int[] solution(int n, int[] info) {
        peach = info;
        backtracking(0, 0, n);
        if(Arrays.stream(answer).filter(e -> e == 0).count() == 11) 
            answer = new int[]{-1};
        return answer;
    }
}
