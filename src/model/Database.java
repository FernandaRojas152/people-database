package model;

import java.io.Serializable;
import avlTree.AVLTree;
import redBlackBST.RedBlackBST;

public class Database implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private AVLTree<String, Person> aVLTree1;
	private AVLTree<String, Person> aVLTree2;
	private RedBlackBST<String, Person> redBlackBST1;
	private RedBlackBST<String, Person> redBlackBST2;
	
	public Database() {
		// TODO Auto-generated constructor stub
	}

	public AVLTree<String, Person> getaVLTree1() {
		return aVLTree1;
	}

	public AVLTree<String, Person> getaVLTree2() {
		return aVLTree2;
	}

	public RedBlackBST<String, Person> getRedBlackBST1() {
		return redBlackBST1;
	}

	public RedBlackBST<String, Person> getRedBlackBST2() {
		return redBlackBST2;
	}
}
