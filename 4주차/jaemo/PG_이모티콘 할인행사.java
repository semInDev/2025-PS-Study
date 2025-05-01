class Solution {
    private int[] DISCOUNT = {40,30,20,10};
    private int[] answer = new int[2];
    
    public int[] solution(int[][] users, int[] emoticons) {
        dfs(users, emoticons, new int[emoticons.length], 0);
        return answer;
    }
    
    public void dfs(int[][] users, int[] emoticons, int[] discountRates, int depth) {
        if (depth == discountRates.length) {
            int[] result = new int[2];
            for (int i=0;i<users.length;i++) {
                int totalPrice = 0;
                for (int j=0;j<emoticons.length;j++) {
                    // user 할인율이 현재 할인율보다 작거나 같으면 totalPrice 계산
                    if (users[i][0] <= discountRates[j]) {
                        totalPrice += emoticons[j] * (100-discountRates[j]) / 100;
                    }
                }
                
                // 유저별 총 구매금액이 일정 가격 이상이면 플러스 가입
                // 아니면 총 금액 ++
                if (totalPrice >= users[i][1]) {
                    result[0]++;
                } else {
                    result[1] += totalPrice;
                }
            }
            
            if (result[0] > answer[0]) {
                answer[0] = result[0];
                answer[1] = result[1];
            }
            
            if (result[0] == answer[0] && result[1] > answer[1]) {
                answer[0] = result[0];
                answer[1] = result[1];
            }
            
            return;
        }
        
        for (int i=0;i<DISCOUNT.length;i++) {
            discountRates[depth] = DISCOUNT[i];
            dfs(users, emoticons, discountRates, depth+1);
        }
    }
}
