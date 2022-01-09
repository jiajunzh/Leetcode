public class RollDice {
    public int missingNumber(int[] nums) {
        int minMoves = Integer.MAX_VALUE;
        int[] count = new int[7];
        for (int face: nums) {
            count[face]++;
        }
        
        for (int i = 1; i <= 6; i++) {
            int moves = nums.length - count[i] + count[7 - i];
            minMoves = Math.min(minMoves, moves);
        }
        
        return minMoves;
    }

    public static void main(String[] args) {
        RollDice test = new RollDice();
        int[] nums = new int[]{1, 2, 6, 3};
        System.out.println(test.missingNumber(nums));
    }
}
