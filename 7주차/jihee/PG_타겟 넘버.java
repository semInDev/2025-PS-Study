class Solution {
    static int count =0;

    public int solution(int[] numbers, int target) {

        recur(0, 0, numbers, target);
        return count;
    }

    private static void recur(int cur, int sum, int[] numbers, int target) {
        if (cur == numbers.length) {
            if (sum == target) {
                count++;
            }

            return;
        }

        recur(cur + 1, sum + numbers[cur], numbers, target);
        recur(cur + 1, sum - numbers[cur], numbers, target);
    }
}