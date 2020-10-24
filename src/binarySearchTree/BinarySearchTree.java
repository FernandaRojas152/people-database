package binarySearchTree;

import java.util.Collection;

/**
 * Binary Search Tree data structure
 * @author usuario
 * @param <K> Key
 * @param <V> Value
 */

public class BinarySearchTree<K extends Comparable<K>, V> implements BinarySearchTreeOperations<K, V>{
	
	private Node<K, V> current;
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
		
		if(current==null)
			current = node;
		else
			current.addNode(node);
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
		return current==null ? null : current.searchNode(k);
	}
	
	/**
	 * Deletes a node from the binary search tree
	 * @param k Key
	 */
	
	public void deleteNode(K k) {
		current.deleteNode(k);
	}
	
	/**
	 * Orders the binary search tree inorder
	 * @param collection
	 */
	
	public void inOrder(Collection<V> collection) {
		current.inOrder(collection);
	}
	
	/**
	 * Orders the binary search tree preorder
	 * @param collection
	 */
	
	public void preOrder(Collection<V> collection) {
		current.preOrder(collection);
	}
	
	/**
	 * Orders the binary search tree postorder
	 * @param collection
	 */
	
	public void postOrder(Collection<V> collection) {
		current.postOrder(collection);
	}
	
	/**
	 * Calculates the weight of the binary search tree
	 * @return Binary search tree's weight
	 */
	
	public int getWeight() {
		
		if(current==null)
			return 0;
		else
			weight = current.getWeight();
		
		return weight;
	}
	
	/**
	 * Calculates the height of the binary search tree
	 * @return Binary search tree's height
	 */
	
	public int getHeight() {
		
		if(current==null)
			return 0;
		else
			height = current.getHeight();
		
		return height;
	}

	public Node<K, V> getRoot() {
		return current;
	}

	public void setRoot(Node<K, V> root) {
		this.current = root;
	}
}
