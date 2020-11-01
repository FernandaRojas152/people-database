package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import avlTree.AVLTree;
import redBlackBST.RedBlackBST;

public class Database implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private AVLTree<String, Person> nameAVLTree;
	private AVLTree<String, Person> lastNameAVLTree;
	private RedBlackBST<String, Person> fullNameRBTree;
	private RedBlackBST<String, Person> codeRBTree;
	private List<Person> persons;
	
	public Database() {
		nameAVLTree = new AVLTree<String, Person>();
		lastNameAVLTree = new AVLTree<String, Person>();
		fullNameRBTree = new RedBlackBST<String, Person>();
		codeRBTree = new RedBlackBST<String, Person>();
		persons = new ArrayList<Person>();
	}

	public void createPerson(String name, String lastName, String sex, LocalDate birthDate, Double height, 
			String nationality) throws IllegalArgumentException {
		String code = UUID.randomUUID().toString();
		Person person = new Person(code, name, lastName, sex, birthDate, height, nationality);
		nameAVLTree.addNode(name, person);
		lastNameAVLTree.addNode(lastName, person);
		fullNameRBTree.insertRB(name+" "+lastName, person);
		codeRBTree.insertRB(code, person);
	}
	
	public void deletePerson(String name, String lastName, String code) {
		nameAVLTree.deleteNode(name);
		lastNameAVLTree.deleteNode(lastName);
		fullNameRBTree.deleteRB(name+" "+lastName);
		codeRBTree.deleteRB(code);
	}
	
	public void updatePerson(String code, String name, String lastName, String gender, LocalDate birthDate, Double height, 
			String nationality) {
		Person person = codeRBTree.search(code).getV();
		person.setName(name);
		person.setLastName(lastName);
		person.setGender(gender);
		person.setBirthDate(birthDate);
		person.setHeight(height);
		person.setNationality(nationality);
	}
	
	public List<Person> getPersonsByName() {
		persons.clear();
		nameAVLTree.inOrder(persons);
		return persons;
	}
	
	public List<Person> getPersonsByLastName() {
		persons.clear();
		lastNameAVLTree.inOrder(persons);
		return persons;
	}
	
	public List<Person> getPersonsByFullName() {
		persons.clear();
		fullNameRBTree.inOrder(persons);
		return persons;
	}
	
	public List<Person> getPersonsByCode() {
		persons.clear();
		codeRBTree.inOrder(persons);
		return persons;
	}
}
