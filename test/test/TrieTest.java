package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import trie.Trie;

class TrieTest {
	Trie trie;

	/** STAGES */
	public void setUpStage1() {
		trie = new Trie();
		trie.insert("Edgar Allan Poe");
		trie.insert("James Barrie");
		trie.insert("Emily Bronte");
		trie.insert("Euripides");
		trie.insert("Ernest Hemingway");
		trie.insert("Arthur Conan Doyle");
		trie.insert("Lewis Carroll");
		trie.insert("JRR Tolkien");
		trie.insert("Elvira Sastre");
		trie.insert("Alejandra Pizarnik");
	}

	/** TESTS */
	@Test
	void testAutoCompleteWithSize() {
		setUpStage1();
		List<String> data= trie.autocomplete("E");
		assertEquals(5, data.size(), "Should be 5");
	}

	@Test
	void testAutoComplete() {
		setUpStage1();
		List<String> data= trie.autocomplete("E");
		for (int i = 0; i < data.size(); i++) {
			assertEquals('E', data.get(i).charAt(0));
			System.out.println(data.get(i));
		}
	}
	
	@Test
	void testNoAutoComplete() {
		setUpStage1();
		List<String> data= trie.autocomplete("Y");
		assertEquals(0, data.size(), "Should be 0");
	}
}
