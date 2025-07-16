class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);

            int length = binary.length();
            int full = 1;
            while (full < length) {
                full = full * 2 + 1;
            }
            binary = String.format("%" + full + "s", binary).replace(' ', '0');
            answer[i] = valid(binary) ? 1 : 0;
        }

        return answer;
    }
    
    private static boolean valid(String tree) {
        return check(tree, 0, tree.length() - 1);
    }

    private static boolean check(String tree, int left, int right) {
        if (left > right) return true;

        int mid = (left + right) / 2;
        char root = tree.charAt(mid);

        if (root == '0') {
            for (int i = left; i <= right; i++) {
                if (i != mid && tree.charAt(i) == '1') {
                    return false;
                }
            }
        }

        return check(tree, left, mid - 1) && check(tree, mid + 1, right);
    }
}
