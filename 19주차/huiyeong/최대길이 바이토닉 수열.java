class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];

        up[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = up[i - 1] + 1;
            } else {
                up[i] = 1;
            }
        }

        down[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                down[i] = down[i + 1] + 1;
            } else {
                down[i] = 1;
            }
        }

        down[0] = 1;
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] > nums[i+1]) {
                down[i+1] = down[i] +1;
            }
            else {
                down[i] = 1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (up[i] > 1 && down[i] > 1) {
                answer = Math.max(answer, up[i] + down[i] - 1);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution T = new Solution();
        System.out.println(T.solution(new int[]{1, 3, 2, 5, 7, 4, 2, 5, 1}));
        System.out.println(T.solution(new int[]{1, 1, 2, 3, 5, 7, 4, 3, 1, 2}));
        System.out.println(T.solution(new int[]{3, 2, 1, 3, 2, 4, 6, 7, 3, 1}));
        System.out.println(T.solution(new int[]{1, 3, 1, 2, 1, 5, 3, 2, 1, 1}));
    }
}
