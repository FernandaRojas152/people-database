package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Database;

class DatabaseTest {
	private Database database;
	
	/** STAGES */
	public void setUpStage1() {
		database= new Database();
		database.createPerson("Fernanda", "Rojas", "Colombian");
	}
	
	@Test
	void test() {
		setUpStage1();
		assertEquals("Fernanda Rojas",database.searchByFullName("Fernanda Rojas"), "should be found");
	}

}
