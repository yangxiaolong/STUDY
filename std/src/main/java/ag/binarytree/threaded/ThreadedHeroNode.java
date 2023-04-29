package ag.binarytree.threaded;

import ag.binarytree.BaseNode;

public class ThreadedHeroNode extends BaseNode {

    public ThreadedHeroNode(int no, String name) {
        super(no, name);
    }

    private ThreadedHeroNode left;

    private ThreadedHeroNode right;

    // 0:指向的是左子树 1:表示指向前驱节点
    private int leftType;

    // 0:指向的是右子树 1:表示指向后继节点
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public ThreadedHeroNode getLeft() {
        return left;
    }

    public void setLeft(ThreadedHeroNode left) {
        this.left = left;
    }

    public ThreadedHeroNode getRight() {
        return right;
    }

    public void setRight(ThreadedHeroNode right) {
        this.right = right;
    }

    // 前序遍历查找
    public ThreadedHeroNode preOrderSearch(int no) {
        if (this.getNo() == no) {
            return this;
        }
        ThreadedHeroNode node = null;
        if (this.left != null) {
            node = this.left.preOrderSearch(no);
            if (node != null) {
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

    // 中序遍历查找
    public ThreadedHeroNode infixOrderSearch(int no) {
        ThreadedHeroNode node = null;
        if (this.left != null) {
            node = this.left.infixOrderSearch(no);
            if (node != null) {
                return node;
            }
        }
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

    // 后序遍历查找
    public ThreadedHeroNode postOrderSearch(int no) {
        ThreadedHeroNode node = null;
        if (this.left != null) {
            node = this.left.postOrderSearch(no);
            if (node != null) {
                return node;
            }
        }
        if (this.right != null) {
            node = this.right.postOrderSearch(no);
            if (node != null) {
                return node;
            }
        }
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
