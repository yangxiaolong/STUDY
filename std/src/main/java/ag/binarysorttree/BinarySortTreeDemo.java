package ag.binarysorttree;

public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = { 7, 3, 10, 12, 5, 1, 9, 2 };
        BinarySortTree tree = new BinarySortTree();
        // 循环的添加二叉排序树
        for (int i = 0; i < arr.length; i++) {
            tree.add(new Node(arr[i]));
        }
        // 中序遍历二叉树
        System.out.println("中序遍历二叉树~");// 1,3,5,7,9,10,12
        tree.infixOrder();

        // 测试一下删除叶子结点
        // tree.delNode(2);
        // tree.delNode(5);
        // tree.delNode(9);
        // tree.delNode(12);
        // System.out.println();
        // System.out.println("删除之后中序遍历二叉树~");
        // tree.infixOrder();

        // 测试一下删除只有一颗子树的节点
        // tree.delNode(1);
        // System.out.println();
        // System.out.println("删除之后中序遍历二叉树~");
        // tree.infixOrder();

        // 测试一下删除有两颗子树的节点
        // tree.delNode(7);
        // System.out.println();
        // System.out.println("删除之后中序遍历二叉树~");
        // tree.infixOrder();

        // 加上判空逻辑之后的处理
        tree.delNode(2);
        tree.delNode(5);
        tree.delNode(9);
        tree.delNode(12);
        tree.delNode(7);
        tree.delNode(3);
        tree.delNode(10);
        tree.delNode(1);
        // tree.delNode(1);
        // tree.delNode(10);
        System.out.println();
        System.out.println("删除之后中序遍历二叉树~");
        tree.infixOrder();
    }

}

// 创建二叉排序树
class BinarySortTree {

    private Node root;

    // 添加节点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        }
    }

    // 查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    // 查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }

    // 删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        }
        // 1.先去查找需要删除的节点 targetNode
        Node targetNode = search(value);
        // 没有找到要删除的节点
        if (targetNode == null) {
            return;
        }
        // 如果我们发现当前这颗二叉排序树只有一个节点
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        // 找到targetNode的父节点
        Node parent = searchParent(value);
        // 如果要删除的是叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            // 判断targetNode是父节点的左子节点还是右子节点
            if (parent.left != null && parent.left.value == value) {
                parent.left = null;
            } else if (parent.right != null && parent.right.value == value) {
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {// 删除有两颗子树的节点
            int minValue = delRightMin(targetNode.right);
            targetNode.value = minValue;
        } else {// 删除只有一颗子树的节点
            // 如果要删除的节点有左子节点
            if (targetNode.left != null) {
                if (parent != null) {
                    // 如果targetNode是parent的左子节点
                    if (parent.left.value == value) {
                        parent.left = targetNode.left;
                    } else { // 如果targetNode是parent的右子节点
                        parent.right = targetNode.left;
                    }
                } else {
                    root = targetNode.left;
                }
            } else if (targetNode.right != null) {// 如果要删除的节点有右子节点
                if (parent != null) {
                    // 如果targetNode是parent的左子节点
                    if (parent.left.value == value) {
                        parent.left = targetNode.right;
                    } else { // 如果targetNode是parent的右子节点
                        parent.right = targetNode.right;
                    }
                } else {
                    root = targetNode.right;
                }
            }
        }
    }

    /**
     * 1.返回以node为根节点的二叉排序树的最小节点的值 2.删除node为根节点的二叉排序树的最小节点的值
     *
     * @param node
     *            node 当做二叉排序树的根节点
     * @return 返回以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightMin(Node node) {
        Node target = node;
        // 循环的查找左节点,找到最小值
        while (target.left != null) {
            target = target.left;
        }
        // 这是target就指向最小节点
        // 删除最小节点
        delNode(target.value);
        return target.value;
    }

}

// 创建节点
class Node {

    int value;
    Node left;
    Node right;

    public Node(int value) {
        super();
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }

    // 查找要删除的节点
    public Node search(int value) {
        if (value == this.value) {
            return this;
        }
        if (value < this.value) {
            if (this.left != null) {
                return this.left.search(value);
            }
        } else {
            if (this.right != null) {
                return this.right.search(value);
            }
        }
        return null;
    }

    // 查找要删除节点的父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || this.right != null && this.right.value == value) {
            return this;
        }
        // 如果查找的值小于当前节点,并且当前节点的左子节点不为空
        if (value < this.value && this.left != null) {
            return this.left.searchParent(value);
        }
        // 如果查找的值大于当前节点,并且当前节点的左子节点不为空
        else if (value >= this.value && this.right != null) { // >= 是因为添加的时候相同值放在了右边
            return this.right.searchParent(value);
        }
        return null;// 没有找到父节点
    }

    // 添加节点(递归的形式添加 需要满足二叉排序树的规则)
    /**
     * @param node
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        // 判断当前输入节点的值和当前子树根节点的值的关系
        if (node.value < this.value) {// 放到左边
            if (this.left == null) {
                this.left = node;
            } else {
                // 递归的向左子树添加节点
                this.left.add(node);
            }
        } else {// 放到右边
            if (this.right == null) {
                this.right = node;
            } else {
                // 递归的向右子树添加节点
                this.right.add(node);
            }
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.print(this.value + " ");
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

}
