class Solution {
    public int[] solution(int[][] users, int[] emoticons) {
        int[] discountRates = {10, 20, 30, 40}; 
        int[] answer = new int[2];

        int total = (int) Math.pow(4, emoticons.length);

        for (int i = 0; i < total; i++) {
            int[] discounts = new int[emoticons.length];

            for (int j = 0; j < emoticons.length; j++) {
                discounts[j] = discountRates[(i / (int) Math.pow(4, j)) % 4];
            }

            evaluateCombination(answer, discounts, users, emoticons);
        }

        return answer;
    }
    private void evaluateCombination(int[] answer, int[] discounts, int[][] users, int[] emoticons) {
        int subscriber = 0; 
        int total = 0; 

        for (int[] user : users) {
            int dt = user[0]; 
            int sl = user[1]; 
            int use = 0; 

            for (int i = 0; i < emoticons.length; i++) {
                if (discounts[i] >= dt) {
                    int temp = emoticons[i] - (emoticons[i] * discounts[i] / 100);
                    use += temp; 
                }
                
                if(use >= sl){
                	subscriber++;
                    break;
                }
            }

            if(use < sl)
            	total += use; 
        }

        if (subscriber > answer[0]
        		|| (subscriber == answer[0] && total > answer[1])) {
            answer[0] = subscriber; 
            answer[1] = total;
        }
    }
}
