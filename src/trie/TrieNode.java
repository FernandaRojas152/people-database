package trie;

public class TrieNode {
	public static final int ALPHABET_SIZE= 26;
	TrieNode[] children= new TrieNode[ALPHABET_SIZE];
	boolean end;
	
	public TrieNode() {
		
	}
}
