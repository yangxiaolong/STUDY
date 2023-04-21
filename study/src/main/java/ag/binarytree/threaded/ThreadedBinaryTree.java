package ag.binarytree.threaded;

// https://blog.csdn.net/jisuanjiguoba/article/details/81092812
// https://blog.csdn.net/UncleMing5371/article/details/54291221
public class ThreadedBinaryTree {

    private ThreadedHeroNode root;

    // 为了实现线索化,需要创建指向当前节点的前驱节点的指针
    // 在递归线索化树时,pre总是保留前一个节点
    private ThreadedHeroNode pre;

    public void setRoot(ThreadedHeroNode root) {
        this.root = root;
    }

    private void doLink(ThreadedHeroNode node) {
        // 左指针为空,将左指针指向前驱节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        // 前一个节点的后继节点指向当前节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
    }

    // 1.1 node 就是当前需要线索化的节点
    public void preThreadedNodes(ThreadedHeroNode node) {
        if (node == null) {
            return;
        }
        // 1.先处理当前节点
        doLink(node);
        // 2.处理左子树
        preThreadedNodes(node.getLeft());
        // 3.处理右子树
        preThreadedNodes(node.getRight());
    }

    // 1.2 前序线索化
    public void preThreadedNodes() {
        pre = null;
        preThreadedNodes(root);
    }

    // 1.3 前序遍历线索二叉树（按照后继线索遍历）
    public void preThreadList(ThreadedHeroNode node) {
        while (node != null) {
            while (node.getLeftType() == 0) {
                System.out.print(node);
                node = node.getLeft();
            }
            System.out.print(node);
            node = node.getRight();
        }
    }

    // 2.1 node 就是当前需要线索化的节点
    public void infixThreadedNodes(ThreadedHeroNode node) {
        if (node == null) {
            return;
        }
        // 1.先线索化左子树
        infixThreadedNodes(node.getLeft());
        // 2.线索化当前节点[有难度]
        doLink(node);
        // 3.最后线索化右子树
        infixThreadedNodes(node.getRight());
    }

    // 2.2 中序线索化
    public void infixThreadedNodes() {
        pre = null;
        infixThreadedNodes(root);
    }

    // 2.3 中序遍历线索化二叉树,按照后继方式遍历（思路：找到最左子节点开始）
    public void infixThreadedList() {
        // 定义一个变量,存储当前遍历的节点,从root开始
        ThreadedHeroNode node = root;
        while (node != null) {
            // 循环找到leftType == 1的节点,
            // 后面随着遍历而变化,因为leftType == 1,说明该节点是按照线索化处理之后的节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            // 打印当前节点
            System.out.println(node);
            // 如果当前节点的右指针指向的是后继节点,就一直输出
            while (node.getRightType() == 1) {
                // 获取到当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            // 替换这个遍历的节点
            node = node.getRight();
        }
    }

    // 3.1 node 就是当前需要线索化的节点
    public void postThreadedNodes(ThreadedHeroNode node) {
        if (node == null) {
            return;
        }
        // 1.先线索化左子树
        postThreadedNodes(node.getLeft());
        // 2.最后线索化右子树
        postThreadedNodes(node.getRight());
        // 3.线索化当前节点[有难度]
        doLink(node);
    }

    // 3.2 后序线索化
    public void postThreadedNodes() {
        pre = null;
        postThreadedNodes(root);
    }

    // 3.3 后续遍历线索二叉树，按照后继方式遍历（思路：后序遍历开始节点是最左节点）
    public void postThreadList(ThreadedHeroNode root) {

    }

    public static void main(String[] args) {
        // 创建节点
        ThreadedHeroNode root = new ThreadedHeroNode(1, "tom");
        ThreadedHeroNode n2 = new ThreadedHeroNode(3, "jetty");
        ThreadedHeroNode n3 = new ThreadedHeroNode(6, "smith");
        ThreadedHeroNode n4 = new ThreadedHeroNode(8, "marry");
        ThreadedHeroNode n5 = new ThreadedHeroNode(10, "king");
        ThreadedHeroNode n6 = new ThreadedHeroNode(14, "dim");
        // 二叉树,后面我们要递归创建,现在简单手动创建
        root.setLeft(n2);
        root.setRight(n3);
        n2.setLeft(n4);
        n2.setRight(n5);
        n3.setLeft(n6);

        // 创建一个二叉树
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(root);
        tree.infixThreadedNodes();

        // 测试10号节点
        System.out.println(n5.getLeft());
        System.out.println(n5.getRight());

        // 当线索化二叉树之后,不能使用原来的遍历方法
        // tree.infixOrder();
        System.out.println("测试线索化二叉树的遍历");
        tree.infixThreadedList();// 8,3,10,1,14,6
    }

}