import java.util.*;
class Solution {
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
       
        for (int i=0; i<Math.pow(4, emoticons.length); i++) {
            int[] sale = new int[emoticons.length];
            int temp = i;
            for (int j = 0; j < emoticons.length; j++) {
                sale[j] = 10 * (temp % 4 + 1);
                temp /= 4;
            }
            System.out.println(Arrays.toString(sale));
            
            int plus = 0;
            int money = 0;
            
            for (int j=0; j<users.length; j++) {
                int sum = 0;
                for (int k=0; k<sale.length; k++) {
                    if(sale[k] >= users[j][0]) {
                        sum += emoticons[k] * (100-sale[k]) / 100;
                    }
                }
                if(sum >= users[j][1]) {
                    plus++;
                } else {
                    money += sum;
                }
            }
            if(answer[0] == plus) {
                answer[1] = Math.max(answer[1], money);
            } else if(answer[0] < plus) {
                answer[0] = plus;
                answer[1] = money;
            }
        }
        return answer;
    }
}
