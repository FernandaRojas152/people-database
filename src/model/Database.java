package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

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
	
	public void createPerson(String name, String lastName, String nationality) throws IllegalArgumentException {
		String code = UUID.randomUUID().toString();
		Person person = new Person(code, name, lastName, generateGender(), generateBirthDate(),
				Double.parseDouble(String.format("%.2f", ThreadLocalRandom.current().nextDouble(0.50, 2.0))), nationality);
		nameAVLTree.addNode(name, person);
		lastNameAVLTree.addNode(lastName, person);
		fullNameRBTree.insertRB(name+" "+lastName, person);
		codeRBTree.insertRB(code, person);
	}
	
	private String generateGender() {
		Random r = new Random();
		int randomValue = 1+(2-1)*r.nextInt();
		String gender = "";
		if(randomValue==1) {
			gender = "Male";
		}else
			gender = "Female";
		return gender;
	}
	
	private LocalDate generateBirthDate() {
		
		Random r = new Random();
		double randomValue = 99.99*r.nextDouble();
		int age = 0;
		
		if(randomValue > 0 && randomValue <= 18.62) 
			age = (int) (14*r.nextDouble()); 
		else if(randomValue > 18.62 && randomValue <= 31.74) 
			age = (int) (15+(24-15)*r.nextDouble());	
		else if(randomValue > 31.74 && randomValue <= 71.03) 
			age = (int) (25+(54-25)*r.nextDouble());
		else if(randomValue > 73.03 && randomValue <= 83.87) 
			age = (int) (55+(64-55)*r.nextDouble());	
		else if(randomValue > 83.87 && randomValue <= 99.99) 
			age = (int) (65+(90-65)*r.nextDouble()); 
		
		LocalDate localDate = LocalDate.now().minusYears(age);
		localDate = localDate.minusDays((long) ThreadLocalRandom.current().nextDouble(1, 31));
		localDate = localDate.minusMonths((long) ThreadLocalRandom.current().nextDouble(1, 12));
		return localDate;
	}
	
	public void createPerson(String name, String lastName, String gender, LocalDate birthDate, double height, 
			String nationality) throws IllegalArgumentException {
		String code = UUID.randomUUID().toString();
		Person person = new Person(code, name, lastName, gender, birthDate, height, nationality);
		if(lastNameAVLTree.searchNode(lastName)==null)
			nameAVLTree.addNode(name, person);
		lastNameAVLTree.addNode(lastName, person);
		fullNameRBTree.insertRB(name+" "+lastName, person);
		codeRBTree.insertRB(code, person);
	}
	
	public void deletePerson(String name, String lastName, String code) {
		Person personByCode = codeRBTree.search(code).getV();
		nameAVLTree.deleteNode(personByCode.getName());
		lastNameAVLTree.deleteNode(personByCode.getLastName());
		fullNameRBTree.deleteRB(personByCode.getName()+" "+personByCode.getLastName());
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
	
	public Person searchByName(String name) {
		return nameAVLTree.searchNode(name).getV();
	}
	
	public Person searchByLastName(String lastName) {
		return lastNameAVLTree.searchNode(lastName).getV();
	}
	
	public Person searchByFullName(String fullName) {
		return fullNameRBTree.search(fullName).getV();
	}
	
	public Person searchByCode(String code) {
		return codeRBTree.search(code).getV();
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
