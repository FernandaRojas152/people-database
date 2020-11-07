package test;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import model.Database;

class DatabaseTest {
	private Database database;
	
	/** STAGES */
	public void setUpStage1() {
		database= new Database();
		database.createPerson("Fernanda", "Rojas", "Colombian");
	}
	
	public void setUpStage2() {
		database= new Database();
		database.createPerson("Amanda", "Rojas", "Female", LocalDate.now(), 1.62, "Colombian");
	}
	
	public void setUpStage3() {
		database= new Database();
	}
	
	@Test
	void testsearchByFullName() {
		setUpStage1();
		assertEquals("Fernanda",database.searchByFullName("Fernanda Rojas").getName(), "should be found");
	}
	
	@Test
	void testsearchByName() {
		setUpStage1();
		assertEquals("Fernanda",database.searchByName("Fernanda Rojas").getName(), "should be found");
	}
	
	@Test
	void testsearchByCode() {
		setUpStage2();
		assertEquals("Rojas",database.searchByLastName("Rojas").getLastName(), "should be found");
	}
	
	@Test
	void testDelete() {
		setUpStage2();
		database.createPerson("Carlos", "Perez", "Male", LocalDate.now(), 1.82, "Canadian");
		database.searchByName("Carlos").setCode("abcde");
		database.deletePerson("Carlos", "Perez", "abcde");
		assertEquals(null,database.searchByName("Carlos"), "shouldn't be found");
	}
}
