class Solution {

    int isValid;

    public int[] solution(long[] numbers) {
        int[] result = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            String bin = Long.toBinaryString(numbers[i]);

            int treeLen = 0;
            int h = 1;
            while (treeLen < bin.length()) {
                treeLen = (1 << h++) - 1;
            }

            boolean[] tree = new boolean[treeLen];
            int startIdx = treeLen - bin.length();
            for (int j = 0; j < bin.length(); j++) {
                tree[startIdx + j] = bin.charAt(j) == '1';
            }

            isValid = 1;
            check(0, treeLen - 1, false, tree);
            result[i] = isValid;
        }

        return result;
    }

    private void check(int left, int right, boolean parentZero, boolean[] tree) {
        if (isValid == 0) return;

        int mid = (left + right) / 2;

        if (parentZero && tree[mid]) {
            isValid = 0;
            return;
        }

        if (left != right) {
            check(left, mid - 1, !tree[mid], tree);
            check(mid + 1, right, !tree[mid], tree);
        }
    }
}