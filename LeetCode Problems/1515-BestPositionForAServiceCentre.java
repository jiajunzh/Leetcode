package problem;

/**
 * 1. Problem
 * A delivery company wants to build a new service center in a new city. The company knows the positions of all the
 * customers in this city on a 2D-Map and wants to build the new center in a position such that the sum of the euclidean
 * distances to all customers is minimum.
 *
 * Given an array positions where positions[i] = [xi, yi] is the position of the ith customer on the map, return the
 * minimum sum of the euclidean distances to all customers.
 *
 * In other words, you need to choose the position of the service center [xcentre, ycentre] such that the following
 * formula is minimized:
 *
 *
 * Answers within 10-5 of the actual value will be accepted.
 *
 * 2. Examples
 * Example 1
 * Input: positions = [[0,1],[1,0],[1,2],[2,1]]
 * Output: 4.00000
 * Explanation: As shown, you can see that choosing [xcentre, ycentre] = [1, 1] will make the distance to each
 * customer = 1, the sum of all distances is 4 which is the minimum possible we can achieve.
 *
 * Example 2
 * Input: positions = [[1,1],[3,3]]
 * Output: 2.82843
 * Explanation: The minimum possible sum of distances = sqrt(2) + sqrt(2) = 2.82843
 *
 * 3. Constraints
 * 1 <= positions.length <= 50
 * positions[i].length == 2
 * 0 <= xi, yi <= 100
 */
public class BestPositionForAServiceCentre {

    /**
     * 1. Approach
     * Gradient Descent + Adaptive Learning
     *
     * 2. Complexity
     * - Time O(Unknown)
     * - Space O(1)
     *
     * @param positions
     * @return
     */
    public double getMinDistSum(int[][] positions) {
        double step = 50, x = 0, y = 0, deltaX = 0, deltaY = 0;
        double MIN_DELTA = 1e-9;
        do {
            double newDeltaX = 0, newDeltaY = 0;
            for (int[] position : positions) {
                if (x == position[0] && y == position[1]) continue; // Avoid NAN double
                newDeltaX += (x - position[0]) * step / getDistance(x, y, position[0], position[1]);
                newDeltaY += (y - position[1]) * step / getDistance(x, y, position[0], position[1]);
            }
            if (deltaX * newDeltaX < 0 || deltaY * newDeltaY < 0) {
                step /= 2;
            }
            deltaX = newDeltaX;
            deltaY = newDeltaY;
            x = x - deltaX;
            y = y - deltaY;
        } while (Math.abs(deltaX) > MIN_DELTA || Math.abs(deltaY) > MIN_DELTA);
        return getTotalDistance(positions, x, y);
    }

    private double getTotalDistance(int[][] positions, double x, double y) {
        double dist = 0;
        for (int[] position : positions) {
            dist += getDistance(x, y, position[0], position[1]);
        }
        return dist;
    }

    private double getDistance(double x, double y, double x0, double y0) {
        double deltaX = x0 - x;
        double deltaY = y0 - y;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final double MIN_DELTA = 1e-9;

    /**
     * 1. Approach
     * Search with Decreasing Step
     *
     * 2. Complexity
     * - Time O(Unknown)
     * - Space O(1)
     *
     * @param positions
     * @return
     */
    public double getMinDistSum2(int[][] positions) {
        double step = 50, x = 0, y = 0, minDist = getTotalDistance(positions, x, y);
        do {
            double tmp = minDist;
            for (int[] dir : DIRS) {
                double newX = x + step * dir[0];
                double newY = y + step * dir[1];
                double newDist = getTotalDistance(positions, newX, newY);
                if (newDist < minDist) {
                    minDist = newDist;
                    x = newX;
                    y = newY;
                }
            }
            if (minDist == tmp) {
                step /= 2;
            }
        } while (step > MIN_DELTA);
        return getTotalDistance(positions, x, y);
    }

}
