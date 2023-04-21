package ag.binarytree;

public class BinaryTree {

    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            root.preOrder();
        } else {
            System.out.println("当前二叉树为空,无法遍历");
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            root.infixOrder();
        } else {
            System.out.println("当前二叉树为空,无法遍历");
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            root.postOrder();
        } else {
            System.out.println("当前二叉树为空,无法遍历");
        }
    }

    // 前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return root.preOrderSearch(no);
        } else {
            System.out.println("当前二叉树为空,无法遍历");
            return null;
        }
    }

    // 中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return root.infixOrderSearch(no);
        } else {
            System.out.println("当前二叉树为空,无法遍历");
            return null;
        }
    }

    // 后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return root.postOrderSearch(no);
        } else {
            System.out.println("当前二叉树为空,无法遍历");
            return null;
        }
    }

    public void delNode(int no) {
        if (root != null) {
            // 这里需要判断root是不是需要删除的
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空树无法删除");
        }
    }

    /**
     * @param args
     */
    /**
     * @param args
     */
    public static void main(String[] args) {
        // 创建一个二叉树
        BinaryTree tree = new BinaryTree();
        // 创建节点
        HeroNode n1 = new HeroNode(1, "宋江");
        HeroNode n2 = new HeroNode(2, "卢俊义");
        HeroNode n3 = new HeroNode(3, "吴用");
        HeroNode n4 = new HeroNode(4, "林冲");
        HeroNode n5 = new HeroNode(5, "关胜");
        n1.setLeft(n2);
        n1.setRight(n3);
        n3.setLeft(n5);
        n3.setRight(n4);

        tree.setRoot(n1);

        // // 测试 前序
        // System.out.println("前序遍历");
        // tree.preOrder();
        // // 测试 中序
        // System.out.println("中序遍历");
        // tree.infixOrder();
        // // 测试后序
        // System.out.println("后序遍历");
        // tree.postOrder();
        //
        // // 前序遍历查找
        // HeroNode preOrderSearch = tree.preOrderSearch(5);
        // if (preOrderSearch != null) {
        // System.out.println("前序遍历找到: " + preOrderSearch.toString());
        // } else {
        // System.out.println("没有找到");
        // }
        //
        // // 中序遍历查找
        // HeroNode infixOrderSearch = tree.infixOrderSearch(5);
        // if (infixOrderSearch != null) {
        // System.out.println("中序遍历找到: " + infixOrderSearch.toString());
        // } else {
        // System.out.println("没有找到");
        // }
        //
        // // 后序遍历查找
        // HeroNode postOrderSearch = tree.postOrderSearch(5);
        // if (postOrderSearch != null) {
        // System.out.println("后序遍历找到: " + postOrderSearch.toString());
        // } else {
        // System.out.println("没有找到");
        // }

        System.out.println("测试删除前");
        tree.preOrder();
        // tree.delNode(5);
        tree.delNode(3);
        System.out.println("测试删除后");
        tree.preOrder();
    }

}
