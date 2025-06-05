class Solution {

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            boolean[] tree = toBinaryTree(numbers[i]);
            answer[i] = isValid(tree, 0, tree.length - 1, false) ? 1 : 0;
        }
        return answer;
    }

    private boolean[] toBinaryTree(long num) {
        String binary = Long.toBinaryString(num);
        int length = binary.length();

        int treeLength = 1;
        while (treeLength < length) {
            treeLength = treeLength * 2 + 1;
        }

        boolean[] result = new boolean[treeLength];
        int padding = treeLength - length;

        for (int i = 0; i < binary.length(); i++) {
            result[padding + i] = binary.charAt(i) == '1';
        }

        return result;
    }

    private boolean isValid(boolean[] tree, int start, int end, boolean parentZero) {
        int mid = (start + end) / 2;
        boolean current = tree[mid];

        if (parentZero && current) {
            return false;
        }

        if (start == end) {
            return true;
        }

        return isValid(tree, start, mid - 1, !current)
            && isValid(tree, mid + 1, end, !current);
    }
}
