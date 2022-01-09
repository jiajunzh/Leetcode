import java.util.HashMap;
import java.util.Map;

public class PrisonCellsAfterNDays {
    private static final int NUM_OF_PRISON = 8;
    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<Integer, Integer> map = new HashMap<>();
        
        int state = 0;
        for (int i = 0; i < NUM_OF_PRISON; i++) {
            state |= cells[i] << i;
        }
        while (N > 0) {
            if (map.containsKey(state)) {
                N %= map.get(state) - N;
            }
            map.put(state, N);
            if (N > 0) {
                N--;
                state = getNewState(state);
            }
        }
        
        int[] res = new int[NUM_OF_PRISON];
        for (int i = 0; i < NUM_OF_PRISON; i++) {
            res[i] = (state >> i) & 1;
        }
        return res;
    }
    
    public int getNewState(int state) {
        int newState = 0;
        if (((state >> 1) & 1) == 1) newState |= 1;
        if (((state >> 6) & 1) == 1) newState |= 1 << 7;
        for (int i = 1; i < NUM_OF_PRISON - 1; i++) {
            newState |= (((state >> (i - 1)) & 1) ^ (state >> (i + 1) & 1)) << i ;
        }
        return newState;
    }

    public static void main(String[] args) {
        PrisonCellsAfterNDays test = new PrisonCellsAfterNDays();
        int[] prisons = new int[]{1, 1, 1, 0, 1, 1, 1, 1};
        int[] res = test.prisonAfterNDays(prisons, 1);
        for (int i = 1; i <= 1; i++) {
            res = test.prisonAfterNDays(prisons, 100000);
            for (int r : res) {
                System.out.print(r + " ");
            }
            System.out.println();
        }
    }
    
}
