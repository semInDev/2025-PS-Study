class Solution {
    static int answer = 0;
    public int solution(int[] numbers, int target) {
        dfs(target, -1*numbers[0], numbers, 1);
        dfs(target, 1*numbers[0], numbers, 1);
        return answer;
    }
    
    public void dfs(int target, int num, int[] numbers, int idx) {
        if (idx == numbers.length) {
            if (num == target) {
                answer++;
            }
            return;
        }
        
        dfs(target, num+numbers[idx]*-1, numbers, idx+1);
        dfs(target, num+numbers[idx]*1, numbers, idx+1);
    }
}
