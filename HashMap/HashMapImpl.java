
// HashMap implementation using BST in case of collision

import java.util.List;
import java.util.ArrayList;

class MapNode {
	Integer key;
	String value;
	MapNode left;
	MapNode right;
	public MapNode(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
	public String toString() {
		return "{" + key + ", " + value + "}";
	}
}

class KeyInsertionException extends Exception{
	public KeyInsertionException() {
		super("Key already present");	
	}
}

class KeyNotFoundException extends Exception{
	public KeyNotFoundException(Integer key) {
		super("key '" + key + "' is not present");
	}
}

class HashMap {
	int capacity;
	MapNode map[]; 
	public HashMap(int capacity) {
		this.capacity = capacity;
		map = new MapNode[capacity];
	}

	public void put(Integer key, String value) throws KeyInsertionException {
		int index = Math.abs(key.hashCode() % capacity);
		map[index] = add(map[index], key, value);
	}

	public MapNode add(MapNode root, Integer key, String value) throws KeyInsertionException {
		if(root == null) return new MapNode(key, value);
		if(key < root.key) root.left = add(root.left, key, value);
		else if(key > root.key) root.right = add(root.right, key, value);
		else throw new KeyInsertionException();
		return root;
	}

	public String get(Integer key) throws KeyNotFoundException {
		int index = Math.abs(key.hashCode() % capacity);
		return search(map[index], key);
	}

	public String search(MapNode root, Integer key) throws KeyNotFoundException {
		if(root == null) throw new KeyNotFoundException(key);
		if(root.key.equals(key)) return root.value;
		if(key < root.key) return search(root.left, key);
		if(key > root.key) return search(root.right, key); 
		return null;
	}

	public void remove(Integer key) {

	}

	public String toString() {
		List<List<MapNode>> mainList = new ArrayList<>();
		for(int i = 0; i < map.length; i++) {
			List<MapNode> temp = new ArrayList<>();
			if(map[i] != null) inorder(map[i], temp);
			mainList.add(temp);
		}
		return mainList.toString();
	}

	public void inorder(MapNode root, List<MapNode> res) {
		if(root.left != null) inorder(root.left, res);
		res.add(root);
		if(root.right != null) inorder(root.right, res);
	}
}
public class HashMapImpl {
	public static void main(String args[]) throws KeyInsertionException, KeyNotFoundException {
		HashMap map = new HashMap(10);
		map.put(10, "ten");
		map.put(5, "five");
		map.put(3, "three");
		map.put(12, "twelve");
		map.put(8, "eight");
		map.put(15, "fifteen");
		//map.put(5, "xyz");
		map.put(7, "seven");
		System.out.println(map);

		System.out.println(map.get(10));
		System.out.println(map.get(7));
		System.out.println(map.get(1));


	}
}