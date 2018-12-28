public class Test {

    public static void printArray(int[] arr) {
        for (Object item : arr) {
            System.out.print(item + " \t");
        }
    }

    public static void main(String[] args) {
//       int[] nums = {3, 5, 2, 8, 10, 4};
       int[] nums = {11, 3, 5, 8, 9, 10};
       int temp = 0;
       int count = 0;
       boolean hasSwap = false;
       for (int i = nums.length - 1; i > 0; i--) {
           System.out.println("i --->" + i);
           hasSwap = false;
           for (int j = 0; j < i; j++) {
               System.out.println("j --->" + j);
               count++;
               if (nums[j] > nums[j+1]) {
                   temp = nums[j];
                   nums[j] = nums[j+1];
                   nums[j+1] = temp;
                   hasSwap = true;
               }
               System.out.print("当前结果：");
               printArray(nums);
               System.out.println();
           }
           System.out.println("\n=============================================");
           if (!hasSwap) {
               break;
           }
       }

       System.out.println("经过"+count+"次交换，排序后结果：");
        Test.printArray(nums);
        System.out.println();
    }
}
