package model;

import java.io.Serializable;

import redBlackBST.RedBlackBST;

public class Database implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private RedBlackBST<String, Person> redBlackBST1;
	private RedBlackBST<String, Person> redBlackBST2;
}
