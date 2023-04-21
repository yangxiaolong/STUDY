package ag.avl;

public class AVLTreeDemo {

	public static void main(String[] args) {
		// int[] arr = { 4, 3, 6, 5, 7, 8 };// 测试左旋转
		// int[] arr = { 10, 12, 8, 9, 7, 6 };// 测试右旋转
		int[] arr = { 10, 11, 7, 6, 8, 9 };// 双旋转不能解决问题

		// 创建一个AVLTree
		AVLTree avlTree = new AVLTree();
		for (int value : arr) {
			avlTree.add(new Node(value));
		}

		// 遍历
		System.out.println("中序遍历");
		avlTree.infixOrder();

		System.out.println("在没有做平衡处理之前~");
		System.out.println("高度" + avlTree.getRoot().height());
		System.out.println("左子树的高度" + avlTree.getRoot().leftHeight());
		System.out.println("右子树的高度" + avlTree.getRoot().rightHeight());
		System.out.println(avlTree.getRoot());
	}
}

class AVLTree {

	private Node root;

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

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
            targetNode.value = delRightMin(targetNode.right);
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

	// 返回左子树的高度
	public int leftHeight() {
		if (left == null) {
			return 0;
		}
		return left.height();
	}

	// 返回右子树的高度
	public int rightHeight() {
		if (right == null) {
			return 0;
		}
		return right.height();
	}

	// 返回当前节点的高度,当前节点为根节点的子树的高度
	public int height() {
		return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
	}

	// 左旋转方法
	public void leftRotate() {
		// 创建新的节点,复制当前根节点的值
		Node newNode = new Node(this.value);
		// 把新节点的左子树设置为当前节点的左子树
		newNode.left = this.left;
		// 把新节点的右子树设置为当前节点的右子树的左子树
		newNode.right = this.right.left;
		// 把当前节点的值替换成右子节点的值
		this.value = this.right.value;
		// 把当前节点的右子树设置成当前节点的右子树的右子树
		this.right = this.right.right;
		// 把当前节点的左子树设置成新的节点
		this.left = newNode;
	}

	// 右旋转方法
	public void rightRotate() {
		Node newNode = new Node(this.value);
		newNode.right = this.right;
		newNode.left = this.left.right;
		this.value = this.left.value;
		this.left = this.left.left;
		this.right = newNode;
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
		// 当添加完一个节点后,如果: (右子树的高度 - 左子树的高度) > 1, 就要左旋转
		if (this.rightHeight() - this.leftHeight() > 1) {
			// 它:[当前节点的右子树] 如果[它的左子树的高度] 大于 [它的右子树的高度]
			if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
				// 先对当前节点的右节点右旋转
				this.left.leftRotate();
			}
			this.leftRotate();
			return;// 必须要!!!
		}
		if (this.leftHeight() - this.rightHeight() > 1) {
			// 它:[当前节点的左子树] 如果[它的右子树的高度] 大于 [它的左子树的高度]
			if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
				// 先对当前节点的左节点左旋转
				this.left.leftRotate();
			}
			this.rightRotate();
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
