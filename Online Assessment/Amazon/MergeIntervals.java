package others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * group of friends decide to meetup on a chosen day
 * free time slots 
 */
public class MergeIntervals {
    
    public List<String[]> meetUp(String[][] schedule) {
        List<String[]> nonAvailable = new ArrayList<>();
        
        Arrays.sort(schedule, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int time1 = computeIntCode(o1[0]);
                int time2 = computeIntCode(o2[0]);
                
                return time1 - time2;
            }
        });
        
        for (int i = 0; i < schedule.length; i++) {
            if (i + 1 != schedule.length && computeIntCode(schedule[i][1]) >= computeIntCode(schedule[i + 1][0])) {
                schedule[i + 1][0] = schedule[i][0];
            
                if (computeIntCode(schedule[i + 1][1]) < computeIntCode(schedule[i][1])) {
                    schedule[i + 1][1] = schedule[i][1];
                }
            } else {
                nonAvailable.add(schedule[i]);
            }
        }
        
        List<String[]> available = new ArrayList<>();
        String[] curr = new String[]{"7:00", "18:00"};
        for (int i = 0; i < nonAvailable.size(); i++) {
            int timeStart = computeIntCode(nonAvailable.get(i)[0]);
            int timeEnd = computeIntCode(nonAvailable.get(i)[1]);
            int currStart = computeIntCode(curr[0]);
            int currEnd = computeIntCode(curr[1]);
            
            if (currStart >= currEnd) {
                break;
            }
            
            if (timeStart > currStart) {
                available.add(new String[]{curr[0], nonAvailable.get(i)[0]});
            }
            curr[0] = nonAvailable.get(i)[1];
        }

        int currStart = computeIntCode(curr[0]);
        int currEnd = computeIntCode(curr[1]);

        if (currStart < currEnd) {
            available.add(curr);
        }
        return available;
    }
    
    public int computeIntCode(String time) {
        String[] hm = time.split(":");
        return Integer.parseInt(hm[0]) * 100 + Integer.parseInt(hm[1]);
    }

    public static void main(String[] args) {
        MergeIntervals test = new MergeIntervals();
        
        //String[][] schedule = new String[][]{{"16:00","16:30"}, {"6:00", "7:30"}, {"8:00", "9:00"}, {"8:00", "9:20"}, {"17:30", "19:20"}};
        //String[][] schedule = new String[][]{{"12:00","17:30"}, {"8:00", "10:00"}, {"10:00", "11:30"}};
        String[][] schedule = new String[][]{{"12:00","18:30"}, {"8:00", "10:00"}, {"10:00", "11:30"}};

        List<String[]> results = test.meetUp(schedule);
        
        for (String[] result: results) {
            System.out.println(result[0] + " to " + result[1]);
        }
    }
}
