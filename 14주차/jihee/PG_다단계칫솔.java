import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        HashMap<String, Integer> profitMap = new HashMap<>();
        HashMap<String, String> referralMap = new HashMap<>();

        for (int i = 0; i < enroll.length; i++) {
            profitMap.put(enroll[i], 0);
            referralMap.put(enroll[i], referral[i]);
        }

        for (int i = 0; i < seller.length; i++) {
            profit(seller[i], amount[i] * 100, profitMap, referralMap);
        }

        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = profitMap.get(enroll[i]);
        }
        return answer;
    }

    private void profit(String seller, int profit, HashMap<String, Integer> profitMap, HashMap<String, String> referralMap) {
        String cur = seller;
        int currentProfit = profit;

        while (!cur.equals("-") && currentProfit > 0) {
            int commission = currentProfit / 10;
            profitMap.put(cur, profitMap.get(cur) + (currentProfit - commission));
            cur = referralMap.get(cur);
            currentProfit = commission;
        }
    }
}