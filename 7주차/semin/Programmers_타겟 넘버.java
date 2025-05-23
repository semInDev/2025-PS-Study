class Solution {
    static int answer = 0;
    
    public static int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answer;
    }
    
    private static void dfs(int[] numbers, int target, int index, int sum){
        if(idx == numbers.length){
            if(target == sum)
              answer++;
            return;
        }
        dfs(numbers, target, index + 1, sum + numbers[index]);
        dfs(numbers, target, index + 1, sum - numbers[index]);        
    }
}
