package avlTree;

import binarySearchTree.Node;

public class AVLNode<K extends Comparable<K>, T> extends Node<K,T> {
	private int balanceFactor;
	
	public AVLNode(K key, T value) {
		super(key, value);
		balanceFactor= 0;
	}

	public int getBalanceFactor() {
		return balanceFactor;
	}

	public void setBalanceFactor(int balanceFactor) {
		this.balanceFactor = balanceFactor;
	}

}