import java.util.List;
import java.util.ArrayList;
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int val) {
		this.val = val;
	}
}

class NodeNotFoundException extends Exception {
	public NodeNotFoundException() {
		super("Node not found");
	}
}

class BST {
	TreeNode root;
	public BST() {
		root = null;
	}

	public void add(int val) {
		root = recursiveAdd(root, val);
	}

	public TreeNode recursiveAdd(TreeNode root, int val) {
		if(root == null) return new TreeNode(val);
		if(val < root.val) root.left = recursiveAdd(root.left, val);
		else root.right = recursiveAdd(root.right, val);
		return root;
	}

	public boolean search(int val) {
		return recursiveSearch(root, val);
	}

	public boolean recursiveSearch(TreeNode root, int val) {
		if(root == null) return false;
		if(root.val == val) return true;
		if(val < root.val) return recursiveSearch(root.left, val);
		if(val > root.val) return recursiveSearch(root.right, val);
		return false;
	}

	public void remove(int val) throws NodeNotFoundException {
		root = recursiveRemove(root, val);
	}

	public TreeNode recursiveRemove(TreeNode root, int val) throws NodeNotFoundException {
		if(root == null) throw new NodeNotFoundException();
		if(root.val == val) {
			if(root.left == null && root.right == null) return null;
			if(root.left ==null) return root.right;
			if(root.right == null) return root.left;
			TreeNode prev = root;
			TreeNode current = root.right;
			while(current != null) {
				prev = current;
				current = current.left;
			}
			prev.left = root.left;
			return root.right;
		}
		if(val < root.val) root.left = recursiveRemove(root.left, val);
		if(val > root.val) root.right = recursiveRemove(root.right, val);
		return root;
	}

	public List<Integer> inorder() {
		List<Integer> result = new ArrayList<>();
		if(root != null) helperInorder(root, result);
		return result;
	}
	public void helperInorder(TreeNode root, List<Integer> result) {
		if(root.left != null) helperInorder(root.left, result);
		result.add(root.val);
		if(root.right != null) helperInorder(root.right, result);
	}
}	
public class BSTImpl {
	public static void main(String args[]) throws NodeNotFoundException {
		BST bst = new BST();
		bst.add(10);
		bst.add(5);
		bst.add(3);
		bst.add(12);
		bst.add(8);
		bst.add(15);
		bst.add(7);
		bst.remove(10);
		System.out.println(bst.search(10));
		System.out.println(bst.search(7));
		System.out.println(bst.inorder());
	}
}

/*
remove - 7

             10
            /  \ 
           5   12
          / \    \
         3   8   15
            / 
           7 

inorder - 3 5 7 8 10 12 15

*/