package avlTree;

import java.io.Serializable;

import binarySearchTree.Node;

public class AVLNode<K extends Comparable<K>, V> extends Node<K,V> implements Serializable {
	
	private static final long serialVersionUID = 1L;
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