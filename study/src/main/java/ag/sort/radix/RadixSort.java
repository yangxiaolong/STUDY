package ag.sort.radix;

import java.util.Arrays;

public class RadixSort {

    public static void main(String[] args) {
        int[] arr = { 53, 3, 542, 748, 14, 214 };
        radixSort(arr);
    }

    private static void radixSort(int[] arr) {
        int len = arr.length;
        int[][] bucket = new int[10][len];
        // 每个桶放入的数量的个数
        int[] bucketElementCounts = new int[10];

        // 第1轮(针对每个元素的个位进行排序)
        for (int i : arr) {
            // 取出每个元素个位的值
            int digitOfElement = i % 10;
            // 放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = i;
            bucketElementCounts[digitOfElement]++;
        }
        // 按照这个桶的顺序(一维数组的下标依次取出数据,放入原来数组)
        int index = 0;
        // 遍历每一个桶,并将桶中的数据,放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            // 如果桶中有数据才放入到原数组
            if (bucketElementCounts[k] != 0) {
                // 循环该桶即第k个桶(即第k个一维数组),放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    // 取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
                // 每一轮处理后,需要将每个bucketElementCounts[k] = 0 !!!!
                bucketElementCounts[k] = 0;
            }
        }
        System.out.println("第一轮对个位数的排序处理" + Arrays.toString(arr));

        // =========================第二轮
        for (int i : arr) {
            // 取出每个元素个位的值
            int digitOfElement = i / 10 % 10;
            // 放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = i;
            bucketElementCounts[digitOfElement]++;
        }
        // 按照这个桶的顺序(一维数组的下标依次取出数据,放入原来数组)
        index = 0;
        // 遍历每一个桶,并将桶中的数据,放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            // 如果桶中有数据才放入到原数组
            if (bucketElementCounts[k] != 0) {
                // 循环该桶即第k个桶(即第k个一维数组),放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    // 取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
                bucketElementCounts[k] = 0;
            }
        }
        System.out.println("第二轮对十位数的排序处理" + Arrays.toString(arr));

        // =========================第三轮
        for (int i : arr) {
            // 取出每个元素个位的值
            int digitOfElement = i / 100 % 10;
            // 放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = i;
            bucketElementCounts[digitOfElement]++;
        }
        // 按照这个桶的顺序(一维数组的下标依次取出数据,放入原来数组)
        index = 0;
        // 遍历每一个桶,并将桶中的数据,放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            // 如果桶中有数据才放入到原数组
            if (bucketElementCounts[k] != 0) {
                // 循环该桶即第k个桶(即第k个一维数组),放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    // 取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
                bucketElementCounts[k] = 0;
            }
        }
        System.out.println("第三轮对百位数的排序处理" + Arrays.toString(arr));
    }

}
