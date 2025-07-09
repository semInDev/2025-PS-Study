import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, String> referrals = new HashMap<>();
        for (int i=0;i<enroll.length;i++) {
            referrals.put(enroll[i], referral[i]);
        }
        
        for (int i=0;i<amount.length;i++) {
            amount[i] *= 100;
        }
        
        Map<String, Integer> benefits = new HashMap<>();
        for (int i=0;i<seller.length;i++) {
            int distribution = amount[i] / 10;
            int benefit = amount[i] - distribution;
            benefits.put(seller[i], benefits.getOrDefault(seller[i], 0) + benefit);
            
            if (referrals.get(seller[i]).equals("-")) {
                continue;
            }
            
            dfs(referrals, benefits, referrals.get(seller[i]), distribution);
        }
        
        int[] answer = new int[enroll.length];
        for (int i=0;i<enroll.length;i++) {
            answer[i] = benefits.getOrDefault(enroll[i], 0);
        }
        return answer;
    }
    
    public void dfs(Map<String, String> referrals, Map<String, Integer> benefits, String now, int amount) {
        int distribution = amount / 10;
        int benefit = amount - distribution;
        benefits.put(now, benefits.getOrDefault(now, 0) + benefit);
        
        if (referrals.get(now).equals("-") || distribution < 1) {
            return;
        } else {
            dfs(referrals, benefits, referrals.get(now), distribution);
        }
    }
}
