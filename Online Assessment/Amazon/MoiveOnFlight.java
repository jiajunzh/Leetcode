import java.util.Arrays;

public class MoiveOnFlight {
    public int[] twoSumClosest(int duration, int[] movieDuration) {
        Arrays.sort(movieDuration);
        int[] moviePair = new int[]{-1, -1};
        int d = 30;
        int maxTotal = -1;
        int i = 0, j = movieDuration.length - 1;
        
        while (i < j) {
            int sum = movieDuration[i] + movieDuration[j];
            if (sum > duration - d) {
                j--;
            } else {
                if (sum > maxTotal || (sum == maxTotal && Math.max(moviePair[0], moviePair[1]) < Math.max(movieDuration[i],movieDuration[j]))) {
                    maxTotal = sum;
                    moviePair[0] = movieDuration[i];
                    moviePair[1] = movieDuration[j];
                }
                i++;
            }
        }
        
        return moviePair;
    }

    public static void main(String[] args) {
        MoiveOnFlight test = new MoiveOnFlight();
        int[] moiveDuration = new int[]{95, 91, 75, 60, 126, 150, 125};
        int duration = 250;
        System.out.println(test.twoSumClosest(duration, moiveDuration)[0] + "-" + test.twoSumClosest(duration, moiveDuration)[1]);
    }
}
