class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int d = 0; // 남은 배달량
        int p = 0; // 남은 수거량

        for (int i = n - 1; i >= 0; i--) {
            d += deliveries[i];
            p += pickups[i];

            // 처리해야 할 배달/수거가 있으면 이동
            while (d > 0 || p > 0) {
                d -= cap;
                p -= cap;
                answer += (i + 1) * 2L; // 왕복 거리
            }
        }

        return answer;
    }
}