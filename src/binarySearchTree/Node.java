package binarySearchTree;

import java.util.Collection;

/**
 * Binary Search Tree node
 * @author usuario
 *
 * @param <K>
 * @param <V>
 */

public class Node<K extends Comparable<K>, V> {
	
	private K k;
	private V v;
	private Node<K, V> left;
	private Node<K, V> right;
	
	/**
	 * Node constructor
	 * @param k Key
	 * @param v Value
	 */
	
	public Node(K k, V v) {
		this.k = k;
		this.v = v;
	}
	
	/**
	 * Adds a new node to the node's subtree
	 * @param Kode
	 * @throws Exception
	 */

	public void addNode(Node<K, V> node) throws Exception {
		
		if(k.compareTo(node.k)==0)
			throw new Exception();
		
		if(k.compareTo(node.k)>0) {
			
			if(left==null) 
				left = node;
			else
				left.addNode(node);
		}else {
			
			if(right==null) 
				right = node;
			else
				right.addNode(node);
		}
	}
	
	/**
	 * Searches a node in the node's subtree
	 * @param k Key
	 * @return Node
	 */
	public Node<K, V> searchNode(K k){
		
		if(this.k.compareTo(k)==0) 
			return this;
		
		else if(this.k.compareTo(k)>0) 
			return left==null ? null : left.searchNode(k);
		
		else
			return right==null ? null : right.searchNode(k);
	}
	
	/**
	 * Deletes a node from the node's subtree
	 * <b>pre:</b> The node to be deleted is != null<br>
	 * <b>post:</b> The node has been deleted
	 * @param k Key
	 * @return Updated subtree
	 */
	
	public Node<K, V> deleteNode(K k) {		
		if(isLeaf()) 
			return null;
		
		if(this.k.compareTo(k)==0) {
			
			if(left==null)
				return right;
			
			if(right==null)
				return left;
			
			Node<K, V> successor = right.getMin();
			right = right.deleteNode(successor.k);
			successor.left = left;
			successor.right = right;
			return successor;
		}
		
		else if(this.k.compareTo(k)>0) 
			left = left.deleteNode(k);
		else
			right = right.deleteNode(k);
		
		return this;
	}
	
	/**
	 * Orders the node's subtree inorder
	 * @param collection
	 */
	
	public void inOrder(Collection<V> collection) {
		
		if(left!=null)
			left.inOrder(collection);
		
		collection.add(v);
		
		if(right!=null)
			right.inOrder(collection);
	}
	
	/**
	 * Orders the node's subtree preorder
	 * @param collection
	 */
	
	public void preOrder(Collection<V> collection) {
		
		collection.add(v);
		
		if(left!=null)
			left.preOrder(collection);
		
		if(right!=null)
			right.preOrder(collection);
	}
	
	/**
	 * Orders the node's subtree postorder
	 * @param collection
	 */
	
	public void postOrder(Collection<V> collection) {
		
		if(left!=null)
			left.postOrder(collection);
		
		if(right!=null)
			right.postOrder(collection);
		
		collection.add(v);
	}

	/**
	 * Calculates the weight of the node's subtree
	 * @return Subtree's weight
	 */

	public int getWeight() {		
		
		int lw = left==null ? 0 : left.getWeight();
		int rw = right==null ? 0 : right.getWeight();
		
		return 1+lw+rw;
	}
	
	/**
	 * Calculates the height of the node's subtree
	 * @return Subtree's height
	 */
	
	public int getHeight() {
		
		if(isLeaf())
			return 1;
		else {
			int hl = left==null ? 0 : left.getHeight();
			int hr = right==null ? 0 : right.getHeight();
			return 1+Math.max(hl, hr);
		}
	}
	
	/**
	 * Returns the minimum node in the node's subtree
	 * @return Minimum node
	 */
	
	public Node<K, V> getMin() {
		return left==null ? this : left.getMin();
	}
	
	/**
	 * Returns the maximum node in the node's subtree
	 * @return Maximum node
	 */
	
	public Node<K, V> getMax() {
		return right==null ? this : right.getMax();
	}
	
	/**
	 * Returns true if the node is a leaf, false if it's not
	 * @return Boolean statement
	 */
	
	public boolean isLeaf() {
		return left==null && right==null;
	}
	
	public K getK() {
		return k;
	}

	public void setK(K k) {
		this.k = k;
	}

	public V getV() {
		return v;
	}

	public void setV(V v) {
		this.v = v;
	}

	public Node<K, V> getLeft() {
		return left;
	}

	public void setLeft(Node<K, V> left) {
		this.left = left;
	}

	public Node<K, V> getRight() {
		return right;
	}

	public void setRight(Node<K, V> right) {
		this.right = right;
	}
}

