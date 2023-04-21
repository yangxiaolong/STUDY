package ag.binarytree;

public class HeroNode extends BaseNode {

    public HeroNode(int no, String name) {
        super(no, name);
    }

    private HeroNode left;

    private HeroNode right;

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);// 输出父节点
        // 递归向左子树前序遍历
        if (this.left != null) {
            left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            right.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        // 递归向左子树前序遍历
        if (this.left != null) {
            left.infixOrder();
        }
        System.out.println(this);// 输出父节点
        // 递归向右子树前序遍历
        if (this.right != null) {
            right.infixOrder();
        }
    }

    // 后序遍历
    public void postOrder() {
        // 递归向左子树前序遍历
        if (this.left != null) {
            left.postOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            right.postOrder();
        }
        System.out.println(this);// 输出父节点
    }

    // 前序遍历查找
    public HeroNode preOrderSearch(int no) {
        System.out.println("前序遍历次数");
        // 比较当前节点是不是
        if (this.getNo() == no) {
            return this;
        }
        // 1.判断当前节点的左子节点是否为空,如果不为空,则递归前序查找
        // 2.如果左子节点前序查找找到,则返回
        HeroNode node = null;
        if (this.left != null) {
            node = this.left.preOrderSearch(no);
            if (node != null) {// 找到则返回
                return node;
            }
        }
        if (this.right != null) {
            node = this.right.preOrderSearch(no);
            if (node != null) {
                return node;
            }
        }
        return null;
    }

    // 中序遍历
    public HeroNode infixOrderSearch(int no) {
        // 1.判断当前节点的左子节点是否为空,如果不为空,则递归前序查找
        // 2.如果左子节点前序查找找到,则返回
        HeroNode node = null;
        if (this.left != null) {
            node = this.left.infixOrderSearch(no);
            if (node != null) {// 找到则返回
                return node;
            }
        }
        System.out.println("中序遍历次数");
        // 比较当前节点是不是
        if (this.getNo() == no) {
            return this;
        }
        if (this.right != null) {
            node = this.right.infixOrderSearch(no);
            if (node != null) {
                return node;
            }
        }
        return null;
    }

    // 后序遍历
    public HeroNode postOrderSearch(int no) {
        // 1.判断当前节点的左子节点是否为空,如果不为空,则递归前序查找
        // 2.如果左子节点前序查找找到,则返回
        HeroNode node = null;
        if (this.left != null) {
            node = this.left.postOrderSearch(no);
            if (node != null) {// 找到则返回
                return node;
            }
        }
        if (this.right != null) {
            node = this.right.postOrderSearch(no);
            if (node != null) {
                return node;
            }
        }
        System.out.println("后序遍历次数");
        // 比较当前节点是不是
        if (this.getNo() == no) {
            return this;
        }
        return null;
    }

    // 递归删除节点(此处约定的删除规则)
    // 1.如果删除的节点是叶子结点,则删除该节点
    // 2.如果删除的节点是非叶子结点,则删除该子树
    public void delNode(int no) {
        if (this.left != null && this.left.getNo() == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.getNo() == no) {
            this.right = null;
            return;
        }
        // 向左递归
        if (this.left != null) {
            this.left.delNode(no);
        }
        // 向右递归
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

}
