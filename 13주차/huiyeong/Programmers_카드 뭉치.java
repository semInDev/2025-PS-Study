class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        int a = 0;
        int b = 0;
        for (int i=0; i<goal.length; i++) {
            if (a < cards1.length && cards1[a].equals(goal[i])) a++;
            else if (b < cards2.length && cards2[b].equals(goal[i])) b++;
            else return "No";
        }
        return answer;
    }
}
