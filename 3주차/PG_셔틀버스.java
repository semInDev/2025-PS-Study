import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        // 크루 도착 시간을 분 단위로 변환하여 정렬
        List<Integer> crewTimes = new ArrayList<>();
        for (String time : timetable) {
            crewTimes.add(timeToMinutes(time));
        }
        Collections.sort(crewTimes);

        int shuttleTime = timeToMinutes("09:00");
        int tmp = 0;

        // 각 셔틀 운행에 대해 탑승할 크루 결정
        for (int i = 0; i < n; i++) {
            int passengers = 0;
            while (tmp < crewTimes.size() && crewTimes.get(tmp) <= shuttleTime && passengers < m) {
                tmp++;
                passengers++;
            }
            if (i == n - 1) { // 마지막 셔틀
                if (passengers < m) {
                    return minutesToTime(shuttleTime);
                } else {
                    return minutesToTime(crewTimes.get(tmp - 1) - 1);
                }
            }
            shuttleTime += t;
        }
        return "";
    }

    private int timeToMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    private String minutesToTime(int minutes) {
        int hour = minutes / 60;
        int minute = minutes % 60;
        return String.format("%02d:%02d", hour, minute);
    }


}