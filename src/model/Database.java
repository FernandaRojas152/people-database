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
}
