import java.util.Arrays;

public class MergeSort {
    public static int[] merge_sort_top_down(int[] input){
        if(input.length<=1)
            return input;

        int pivot = input.length /2;
        int[] left_list = merge_sort_top_down(Arrays.copyOfRange(input,0,pivot));
        int[] right_list = merge_sort_top_down(Arrays.copyOfRange(input,pivot,input.length));
        return merge(left_list, right_list);
    }
    public static int[] merge(int[] left_list, int[] right_list){
        int[] ret = new int[left_list.length + right_list.length];
        int left_cursor = 0, right_cursor = 0, ret_cursor = 0;

        while(left_cursor < left_list.length &&
            right_cursor < right_list.length){
            if(left_list[left_cursor] < right_list[right_cursor])
                ret[ret_cursor++] = left_list[left_cursor++];
            else
                ret[ret_cursor++] = right_list[right_cursor++];
        }

        // append what remains from above lists
        while(left_cursor < left_list.length){
            ret[ret_cursor++] = left_list[left_cursor++];
        }
        while(right_cursor < right_list.length){
            ret[ret_cursor++] = right_list[right_cursor++];
        }
        return ret;
    }

    public static void main(String[] args){
        example1();
    }

    private static void example1(){
        System.out.println("Divide and Conquer Top-Down");
        int[] unsorted = new int[]{1,5,3,2,8,7,6,4};
        int[] sorted = merge_sort_top_down(unsorted);
        System.out.print("Unsorted:\t");
        for(int i = 0; i<unsorted.length; i++){
            String s = i>0?", ":"";
            System.out.print(s+unsorted[i]);
        }
        System.out.println("");
        System.out.print("Sorted:\t\t");
        for(int i = 0; i<sorted.length; i++){
            String s = i>0?", ":"";
            System.out.print(s+sorted[i]);
        }
        System.out.println("");
    }
}
