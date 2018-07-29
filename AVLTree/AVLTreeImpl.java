import java.util.*;
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
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
		if(Math.abs(height(root.left) - height(root.right)) > 1) {
			if(height(root.right) > height(root.left) && height(root.right.right) > height(root.right.left)) return leftRotate(root);
			if(height(root.left) > height(root.right) && height(root.left.right) > height(root.left.left)) return leftRightRotate(root);
			if(height(root.left) > height(root.right) && height(root.left.left) > height(root.left.right)) return rightRotate(root);
			if(height(root.right) > height(root.left) && height(root.right.left) > height(root.right.right)) return rightLeftRotate(root);
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
		return Math.max(height(root.left), height(root.right)) + 1;
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
		avl.add(10);
		avl.add(5);
		avl.add(12);
		avl.add(3);
		avl.add(8);
		System.out.println(avl.levelOrder(avl.root));
		avl.add(7);
		System.out.println(avl.levelOrder(avl.root));
	}
}


/*



             10
            /  \ 
           5   12
          / \    \
         3   8   15
            / 
           7 


*/

