package avlTree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import binarySearchTree.Node;

public class AVLNode<K extends Comparable<K>, V> extends Node<K,V> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int height;
	private List<AVLNode<K, V>> nodes;
	
	public AVLNode(K key, V value) {
		super(key, value);
		nodes = new ArrayList<AVLNode<K,V>>();
		height = 1;
	}
	
	@Override
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void addNode(AVLNode<K,V> node) {
		nodes.add(node);
	}
	
	public List<AVLNode<K, V>> getNodes() {
		return nodes;
	}
}