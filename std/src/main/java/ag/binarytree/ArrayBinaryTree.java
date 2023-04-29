package ag.binarytree;

public class ArrayBinaryTree {

    private int[] arr;// 存储数据节点的数组

    public ArrayBinaryTree(int[] arr) {
        super();
        this.arr = arr;
    }

    // 编写一个方法,完成顺序存储二叉树的前序遍历
    /**
     * @param index
     *            数组的下标
     */
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,无法遍历");
        }
        // 输出当前元素
        System.out.println(arr[index]);
        // 向左递归遍历
        if ((2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        // 向右递归
        if ((2 * index + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    public void preOrder() {
        preOrder(0);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
        System.out.println("前序遍历");
        tree.preOrder();
    }

}
