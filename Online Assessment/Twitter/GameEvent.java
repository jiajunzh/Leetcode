import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameEvent {
    /*
     * Complete the 'getEventsOrder' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING team1
     *  2. STRING team2
     *  3. STRING_ARRAY events1
     *  4. STRING_ARRAY events2
     */

    static class Event {
        private String teamName;
        private String playerName;
        private int time;
        private char type;
        private String subName;
        private String timeStr;
        private boolean isFirst;

        Event(String teamName, String playerName, int time, char type, String subName, String timeStr, boolean isFirst) {
            this.teamName = teamName;
            this.playerName = playerName;
            this.time = time;
            this.type = type;
            this.subName = subName;
            this.timeStr = timeStr;
            this.isFirst = isFirst;
        }

        public String toString() {
            return teamName + " " + playerName + " " + timeStr + " " + type + " " + subName;
        }
    }

    public static List<String> getEventsOrder(String team1, String team2, List<String> events1, List<String> events2) {
        // Write your code here
        int[] letters = new int[26];
        letters['G' - 'A'] = 1;
        letters['Y' - 'A'] = 2;
        letters['R' - 'A'] = 3;
        letters['S' - 'A'] = 4;

        List<Event> events = new ArrayList<>();
        for (int i = 0; i < events1.size(); i++) {
            events.add(getEvent(team1, events1.get(i)));
        }

        for (int i = 0; i < events2.size(); i++) {
            events.add(getEvent(team2, events2.get(i)));
        }

        Collections.sort(events, new Comparator<Event>() {
            @Override
            public int compare(Event e1, Event e2) {
                if (e1.isFirst && !e2.isFirst) return -1;
                if (!e1.isFirst && e2.isFirst) return 1;
                if (e1.time != e2.time) {
                    return e1.time - e2.time;
                }
                if (e1.type != e2.type) {
                    return letters[e1.type - 'A'] - letters[e2.type - 'A'];
                }

                if (!e1.teamName.equals(e2.teamName)) {
                    return e1.teamName.compareTo(e2.teamName);
                }

                if (!e1.playerName.equals(e2.playerName)) {
                    return e1.playerName.compareTo(e2.playerName);
                }

                return e1.subName.compareTo(e2.subName);
            }
        });

        List<String> result = new ArrayList<>();
        for (int i = 0; i < events.size(); i++) {
            result.add(events.get(i).toString());
        }
        return result;
    }

    private static Event getEvent(String teamName, String event) {
        String[] eventItems = event.split(" ");
        int timeIndex = getTimePos(eventItems);
        char type = eventItems[timeIndex + 1].charAt(0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < timeIndex; i++) {
            sb.append(eventItems[i]);
            sb.append(" ");
        }
        String playerName = sb.toString();
        playerName = playerName.trim();

        sb = new StringBuilder();
        if (type == 'S') {
            for (int i = timeIndex + 2; i < eventItems.length; i++) {
                sb.append(eventItems[i]);
                sb.append(" ");
            }
        }
        String subName = sb.toString();
        subName = subName.trim();

        String[] times = eventItems[timeIndex].split("\\+");
        int time = Integer.parseInt(times[0]);
        boolean isFirst = false;
        if (time <= 45) isFirst = true;

        if (times.length == 2) {
            time += Integer.parseInt(times[1]);
        }

        Event e = new Event(teamName, playerName, time, type, subName, eventItems[timeIndex], isFirst);
        return e;
    }

    private static int getTimePos(String[] events) {
        int i = 0;
        for (; i < events.length; i++) {
            if (Character.isDigit(events[i].charAt(0))) break;
        }
        return i;
    }
}
