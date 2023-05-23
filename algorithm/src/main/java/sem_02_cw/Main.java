package sem_02_cw;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        int[] testArr = new int[]{435, 3267, 346, 80, 464579, 35, 909};
        quickSort(testArr, 0, testArr.length - 1);
        for (int i = 0; i < testArr.length; i++) {
            System.out.print(testArr[i] + " ");
        }

//        Написать тесты для сравнения производительности сортировки больших
//        массивов.
//        Для наглядного результата стоит сравнивать массивы до 100 000 элементов.
//        При таком подходе будет явно видно, какая из сортировок окажется быстрее

//        for (int i = 10000; i <= 100000; i = i + 10000) {
//            int[] array = new int[i];
//            for (int j = 0; j < array.length; j++) {
//                array[j] = (int) (Math.random() * 10000);
//            }
//            Date startDate = new Date();
//            BubbleSort.sort(array);
//            Date endDate = new Date();
//            long bubbleSortDuration = endDate.getTime() - startDate.getTime();
//
//            for (int j = 0; j < array.length; j++) {
//                array[j] = (int) (Math.random() * 10000);
//            }
//            startDate = new Date();
//            QuickSort.sort(array);
//            endDate = new Date();
//            long quickSortDuration = endDate.getTime() - startDate.getTime();
//            System.out.printf("i: %s, bubble sort duration: %s, quick sort duration: %s%n", i, bubbleSortDuration,
//                    quickSortDuration);
//        }

        System.out.println(binarySearch(testArr, 909, 0, testArr.length - 1));
    }

    public static void quickSort(int[] array, int start, int end) {
        int pivot = (end - start) / 2 + start;
        int left_pos = start;
        int right_pos = end;
        while (left_pos <= right_pos) {
            while (array[left_pos] < array[pivot]) {
                left_pos++;
            }
            while (array[right_pos] > array[pivot]) {
                right_pos--;
            }
            if (left_pos <= right_pos) {
                if (left_pos < right_pos) {
                    int temp = array[left_pos];
                    array[left_pos] = array[right_pos];
                    array[right_pos] = temp;
                }
                left_pos++;
                right_pos--;
            }
        }
        if (start < right_pos) {
            quickSort(array, start, right_pos);
        }
        if (start < left_pos) {
            quickSort(array, left_pos, end);
        }
    }

    public static int binarySearch(int[] array, int value, int min, int max) {
        int mid;
        if (max < min) {
            return -1;
        }
        mid = (max - min) / 2 + min;
        if (array[mid] < value) {
            return binarySearch(array, value, mid + 1, max);
        } else if (array[mid] > value) {
            return binarySearch(array, value, min, mid - 1);
        } else {
            return mid;
        }
    }
}
