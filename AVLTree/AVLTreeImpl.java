import java.util.*;
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	int height;
	public TreeNode(int val) {
		this.val = val;
	}
}

class AVLTree {
	TreeNode root;
	public AVLTree() {
		root = null;
	}

	public void add(int val) {
		root = addRecursive(this.root, val);
	}

	public TreeNode addRecursive(TreeNode root, int val) {
		if(root == null) return new TreeNode(val);
		if(val < root.val) root.left = addRecursive(root.left, val);
		else root.right = addRecursive(root.right, val);
		root = balance(root);
		return root;
	}

	public TreeNode balance(TreeNode root) {
		height(this.root);
		if(Math.abs(height(root.left) - height(root.right)) > 1) {
			/*
			if(height(root.right) > height(root.left) && height(root.right.right) > height(root.right.left)) return leftRotate(root);
			if(height(root.left) > height(root.right) && height(root.left.right) > height(root.left.left)) return leftRightRotate(root);
			if(height(root.left) > height(root.right) && height(root.left.left) > height(root.left.right)) return rightRotate(root);
			if(height(root.right) > height(root.left) && height(root.right.left) > height(root.right.right)) return rightLeftRotate(root);
			*/

			if((root.right == null ? 0 : root.right.height) > (root.left == null ? 0 : root.left.height) && (root.right.right == null ? 0 : root.right.right.height) > (root.right.left == null ? 0 : root.right.left.height)) return leftRotate(root);
			if((root.left == null ? 0 : root.left.height) > (root.right == null ? 0 : root.right.height) && (root.left.right == null ? 0 : root.left.right.height) > (root.left.left == null ? 0 : root.left.left.height)) return leftRightRotate(root);
			if((root.left == null ? 0 : root.left.height) > (root.right == null ? 0 : root.right.height) && (root.left.left == null ? 0 : root.left.left.height) > (root.left.right == null ? 0 : root.left.right.height)) return rightRotate(root);
			if((root.right == null ? 0 : root.right.height) > (root.left == null ? 0 : root.left.height) && (root.right.left == null ? 0 : root.right.left.height) > (root.right.right == null ? 0 : root.right.right.height)) return rightLeftRotate(root);
		}
		return root;
	}

	public TreeNode leftRotate(TreeNode root) {
		TreeNode temp = root.right;
		root.right = temp.left;
		temp.left = root;
		return temp;
	}

	public TreeNode rightRotate(TreeNode root) {
		TreeNode temp = root.left;
		root.left = temp.right;
		temp.right = root;
		return temp;
	}

	public TreeNode leftRightRotate(TreeNode root) {
		root.left = leftRotate(root.left);
		return rightRotate(root);
	}

	public TreeNode rightLeftRotate(TreeNode root) {
		root.right = rightRotate(root.right);
		return leftRotate(root);
	}

	public int height(TreeNode root) {
		if(root == null) return 0;
		root.height = Math.max(height(root.left), height(root.right)) + 1;
		return root.height;
	}

	public List<List<Integer>> levelOrder(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		List<List<Integer>> mainList = new ArrayList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> tempList = new ArrayList<>();
			for(int i = 0; i < size; i++) {
				TreeNode current = queue.remove();
				tempList.add(current.val);
				if(current.left != null) queue.add(current.left);
				if(current.right != null) queue.add(current.right);
			}
			mainList.add(tempList);
		}
		return mainList;
	}
}

public class AVLTreeImpl {
	public static void main(String args[]) {
		AVLTree avl = new AVLTree();
		avl.add(15);
		avl.add(10);
		avl.add(20);
		avl.add(18);
		avl.add(25);
		System.out.println(avl.levelOrder(avl.root));


		avl.add(17);
		System.out.println(avl.levelOrder(avl.root));
	}
}


/*

rightLeftRotate();



             15                                        15                                            18
            /  \           rightRotate(20)            /  \            leftRotate(15)                /  \
           10   20        ----------------->         10  18         ----------------->             15  20
               /  \                                     /  \                                      /  \   \
              18  25                                   17  20                                    10  17  25
            /                                                \ 
           17                                                25


*/

