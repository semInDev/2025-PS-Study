class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        long dd = 1L * d * d;

        for (long x = 0; x <= d; x += k) {
            long maxY = (long) Math.sqrt(dd - x * x);
            answer += (maxY / k) + 1;
        }

        return answer;
    }
}
