import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int[] times = new int[timetable.length];
        for (int i = 0; i < timetable.length; i++) {
            String[] timeSplit = timetable[i].split(":");
            int hour = Integer.parseInt(timeSplit[0]);
            int minute = Integer.parseInt(timeSplit[1]);
            times[i] = hour * 60 + minute;
        }

        Arrays.sort(times);

        int currentTime = 540; // 9:00 = 540분
        int index = 0; // 현재 탐색 중인 사람 인덱스
        int lastPossibleTime = 0;

        while (n-- > 0) {
            int busCount = 0;

            while (index < times.length && times[index] <= currentTime && busCount < m) {
                index++;
                busCount++;
            }

            // 버스에 자리가 남은 경우 -> 콘은 버스 도착 시간에 타면 됨
            if (busCount < m) {
                lastPossibleTime = currentTime;
            }
            // 버스가 가득 찬 경우 -> 가장 마지막 사람보다 1분 일찍 도착해야 함
            else {
                lastPossibleTime = times[index - 1] - 1;
            }

            currentTime += t; // 다음 버스 시간 갱신
        }

        int hour = lastPossibleTime / 60;
        int minute = lastPossibleTime % 60;
        String answer = String.format("%02d:%02d", hour, minute);
		return answer;
    }
}