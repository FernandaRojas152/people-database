package avlTree;

import binarySearchTree.Node;

public class AVLNode<K extends Comparable<K>, V> extends Node<K,V> {
	
	private int height;
	
	public AVLNode(K key, V value) {
		super(key, value);
		height = 1;
	}
	
	@Override
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}