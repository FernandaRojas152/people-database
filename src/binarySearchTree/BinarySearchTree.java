package binarySearchTree;

import java.util.Collection;

/**
 * Binary Search Tree data structure
 * @author usuario
 * @param <K> Key
 * @param <V> Value
 */

public class BinarySearchTree<K extends Comparable<K>, V> implements BinarySearchTreeOperations<K, V>{
	
	private Node<K, V> root;
	private int weight;
	private int height;
	
	/**
	 * BinarySearchTree constructor
	 */
	
	public BinarySearchTree() {
	}
	
	/**
	 * Adds a new node to the binary search tree
	 * <b>post:</b> A new node has been added<br>
	 * @param k Key
	 * @param v Value
	 * @throws Exception
	 */
	
	public void addNode(K k, V v) throws Exception {
		
		Node<K, V> node = new Node<>(k, v);
		
		if(root==null)
			root = node;
		else
			root.addNode(node);
	}
	
	/**
	 * Updates the Value v of the node with Key k
	 * @param k Key
	 * @param v Value
	 */
	
	public void updateNode(K k, V v) {
		searchNode(k).setV(v);
	}
	
	/**
	 * Searches a node in the binary search tree
	 * @param k Key
	 * @return Node
	 */
	
	public Node<K, V> searchNode(K k) {
		return root==null ? null : root.searchNode(k);
	}
	
	/**
	 * Deletes a node from the binary search tree
	 * @param k Key
	 */
	
	public void deleteNode(K k) {
		root.deleteNode(k);
	}
	
	/**
	 * Orders the binary search tree inorder
	 * @param collection
	 */
	
	public void inOrder(Collection<V> collection) {
		root.inOrder(collection);
	}
	
	/**
	 * Orders the binary search tree preorder
	 * @param collection
	 */
	
	public void preOrder(Collection<V> collection) {
		root.preOrder(collection);
	}
	
	/**
	 * Orders the binary search tree postorder
	 * @param collection
	 */
	
	public void postOrder(Collection<V> collection) {
		root.postOrder(collection);
	}
	
	/**
	 * Calculates the weight of the binary search tree
	 * @return Binary search tree's weight
	 */
	
	public int getWeight() {
		
		if(root==null)
			return 0;
		else
			weight = root.getWeight();
		
		return weight;
	}
	
	/**
	 * Calculates the height of the binary search tree
	 * @return Binary search tree's height
	 */
	
	public int getHeight() {
		
		if(root==null)
			return 0;
		else
			height = root.getHeight();
		
		return height;
	}
}
