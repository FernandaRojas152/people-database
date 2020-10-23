package binarySearchTree;

import java.util.Collection;

/**
 * Binary Search Tree data structure
 * @author usuario
 * @param <K> Key
 * @param <V> Value
 */

public class BinarySearchTree<K extends Comparable<K>, V>{
	
	private Node<K, V> root;
	private int weight;
	private int height;
	
	public Node<K, V> getRoot() {
		return root;
	}

	public void setRoot(Node<K, V> root) {
		this.root = root;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * BinarySearchTree constructor
	 */
	
	public BinarySearchTree() {
		root= null;
	}
	
	public boolean add(K k, V v) {
		Node<K, V> tA = new Node<>(k,v);
		return addNode(tA);
	}
	
	
	/**
	 * Adds a node in the tree, provided that it's key hasn't been added yet.
	 * @param ta The given node to be added in the tree.
	 */
	protected boolean addNode(Node<K, V> tA) {
		if(searchNode(tA.getK()) != null) {
			return false;
		}		
		
		if(root == null) {
			root = tA;
			return true;
		}
		
		Node<K, V> current = root;
		boolean added = false;
		
		while(!added){
			if(tA.getK().compareTo(current.getK())<0) {
				if(current.getLeft() != null) {
					current = current.getLeft();
				}else {
					current.setLeft(tA);
					tA.setParent(current);
					added = true;
				}
			}else {
				if(current.getRight() != null){
					current = current.getRight();
				}else {
					current.setRight(tA);
					tA.setParent(current);
					added = true;
				}
			}
		}
		return added;
		
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
	
	public void rotateLeft(K key) {
		Node<K, V> node= searchNode(key);
		rotateLeft(node);
	}
	
	protected void rotateLeft(Node<K, V> node) {
		Node<K, V> aux= node.getRight();
		node.setRight(aux.getLeft());
		if(aux.getLeft()!=null) {
			aux.getLeft().setParent(node);
		}
		aux.setParent(node.getParent());
		if(node.getParent()== null) {
			root= aux;
		}else if(node== node.getParent().getLeft()) {
			node.getParent().setLeft(aux);
		}else {
			node.getParent().setRight(aux);
		}
		aux.setLeft(node);
		node.setParent(aux);
	}
	
	public void rotateRight(K key) {
		Node<K, V> node= searchNode(key);
		rotateRight(node);
	}
	
	protected void rotateRight(Node<K, V> node) {
		Node<K, V> aux= node.getLeft();
		node.setLeft(aux.getRight());
		if(aux.getRight()!=null) {
			aux.getRight().setParent(node);
		}
		aux.setParent(node.getParent());
		if(node.getParent()== null) {
			root= aux;
		}else if(node== node.getParent().getRight()) {
			node.getParent().setRight(aux);
		}else {
			node.getParent().setLeft(aux);
		}
		aux.setRight(node);
		node.setParent(aux);
	}
}
