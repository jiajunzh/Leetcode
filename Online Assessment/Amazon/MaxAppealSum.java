public class MaxAppealSum {
    public int[] maximumAppealValue(int[] A){
        if (A == null || A.length == 0) return new int[]{-1, -1};
        int max1 = 0, max2 = 0;
        int smallerIndex = -1;
        int[] res = new int[2];
        for (int i = 0; i < A.length; i++) {
            int tmp1 = A[i] - i;
            
            // A[i] - i  max until index i
            if (max1 < tmp1) {
                max1 = tmp1;
                smallerIndex = i;
            }
            
            int tmp2 = A[smallerIndex] + A[i] + i;
            if (tmp2 > max2) {
                res[0] = smallerIndex;
                res[1] = i;
                max2 = tmp2;
            }
            
        }
        return res;
    }

    public int[] maxAppealPair(int[] arr){
        if(arr==null || arr.length==0) return new int[] {-1,-1};
    
        int max1= Integer.MIN_VALUE, max2= Integer.MIN_VALUE;
        int m1=-1, m2=-1;
        for(int i=0; i<arr.length; i++) {
            int curr1 = arr[i] + i;
            int curr2 = arr[i] - i;

            if (curr1 > max1) {
                max1 = curr1;
                m1 = i;
            }
            if (curr2 > max2) {
                max2 = curr2;
                m2 = i;
            }
        }
        return new int[] {m1, m2};
    }

    public static void main(String[] args) {
        MaxAppealSum test = new MaxAppealSum();
        int[] nums = new int[]{10, 2, 30, 4, 6, 6, 70};
        System.out.println(test.maximumAppealValue(nums)[0] + "-" + test.maximumAppealValue(nums)[1]);
        System.out.println(test.maxAppealPair(nums)[0] + "-" + test.maxAppealPair(nums)[1]);
    }
}
