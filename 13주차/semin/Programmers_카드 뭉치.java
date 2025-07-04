import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        ArrayDeque<String> q1 = new ArrayDeque<>();
        ArrayDeque<String> q2 = new ArrayDeque<>();
        for(int i=0; i<cards1.length; i++){
            q1.addLast(cards1[i]);
        }
        for(int i=0; i<cards2.length; i++){
            q2.addLast(cards2[i]);
        }
        
        for(String current_need : goal){
            if(!q1.isEmpty() && q1.getFirst().equals(current_need)){
                q1.pollFirst();
            }else if(!q2.isEmpty() && q2.getFirst().equals(current_need)){
                q2.pollFirst();
            }else{
                return "No";
            }
        }
        return "Yes";
    }
}
