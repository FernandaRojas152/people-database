package model;

import java.io.Serializable;
import java.time.LocalDate;

import avlTree.AVLTree;
import redBlackBST.RedBlackBST;

public class Database implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private AVLTree<String, Person> name;
	private AVLTree<String, Person> lastName;
	private RedBlackBST<String, Person> fullName;
	private RedBlackBST<String, Person> id;
	
	public Database() {
		name = new AVLTree<String, Person>();
		lastName = new AVLTree<String, Person>();
		fullName = new RedBlackBST<String, Person>();
		id = new RedBlackBST<String, Person>();
	}
	
	public AVLTree<String, Person> getName() {
		return name;
	}

	public AVLTree<String, Person> getLastName() {
		return lastName;
	}

	public RedBlackBST<String, Person> getFullName() {
		return fullName;
	}

	public RedBlackBST<String, Person> getId() {
		return id;
	}
	
	public void createPerson(String name, String lastName, String sex, LocalDate birthDate, Double height,
			String nationality, String photo) {
		
	}
}
