package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import trie.Trie;

class TrieTest {
	
	Trie trie;
	
	public void setUpStage1() {
		trie = new Trie();
		trie.insert("Edgar Allan Poe");
		trie.insert("Edges");
		trie.insert("Post");
		trie.insert("Post");
		trie.insert("Post");
		trie.insert("Post");
		trie.insert("Post");
		trie.insert("Post");
		trie.insert("Post");
		trie.insert("Post");
		
	}
	
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
