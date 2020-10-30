package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import avlTree.AVLTree;
import redBlackBST.RedBlackBST;

public class Database implements Serializable {

	private static final long serialVersionUID = 1L;

	private static AVLTree<String, Person> nameP;
	private static AVLTree<String, Person> lastNameP;
	private static RedBlackBST<String, Person> fullNameP;
	private static RedBlackBST<String, Person> id;

	public Database() {
		nameP = new AVLTree<String, Person>();
		lastNameP = new AVLTree<String, Person>();
		fullNameP = new RedBlackBST<String, Person>();
		id = new RedBlackBST<String, Person>();
	}

	public AVLTree<String, Person> getName() {
		return nameP;
	}

	public AVLTree<String, Person> getLastName() {
		return lastNameP;
	}

	public RedBlackBST<String, Person> getFullName() {
		return fullNameP;
	}

	public RedBlackBST<String, Person> getId() {
		return id;
	}

	public static void createPerson(String name, String lastName, String sex, LocalDate birthDate, Double height,
			String nationality, String photo) {
		String uniqueID = UUID.randomUUID().toString();
		String fullName= name+" "+lastName;
		Person person= new Person(uniqueID, name, lastName, sex, birthDate, height, nationality);
		nameP.addNode(name, person);
		lastNameP.addNode(lastName, person);
		fullNameP.insertRB(fullName, person);
		id.insertRB(uniqueID, person);
	}
}
