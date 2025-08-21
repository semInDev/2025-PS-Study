class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        int row = (num-1) /w + 1;
        int result = num;
        int temp = 0;
        while(result <= n) {
            temp = w * row - result;
            result += temp * 2 + 1;
            row++;
            answer++;
        }
        return answer;
    }
}
