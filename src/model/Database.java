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
	private RedBlackBST<String, Person> code;
	
	public Database() {
		// TODO Auto-generated constructor stub
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

	public RedBlackBST<String, Person> getCode() {
		return code;
	}
	
	public void createPerson(String name, String lastName, String sex, LocalDate birthDate, Double height,
			String nationality, String photo) {
		
	}
}
