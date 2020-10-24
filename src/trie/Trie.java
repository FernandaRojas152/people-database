package trie;

import java.util.ArrayList;
import java.util.List;

public class Trie {
	private TrieNode root;
	
	public Trie() {
		root= new TrieNode(' ');
	}
	
	public void insert(String word) {
		if(search(word)== true) {
			return;
		}
		TrieNode aux= root;
		TrieNode temp;
		for (char c : word.toCharArray()) {
			temp= aux;
			TrieNode child= aux.getChild(c);
			if(child!=null) {
				aux= child;
				child.parent= temp;
			}else {
				aux.children.add(new TrieNode(c));
				aux= aux.getChild(c);
				aux.parent= temp;
			}
		}
		aux.end= true;
	}
	
	public boolean search(String word) {
		TrieNode aux= root;
		for (char c : word.toCharArray()) {
			if(aux.getChild(c)== null) {
				return false;
			}else aux= aux.getChild(c);
		}
		if(aux.end== true) {
			return true;
		}
		return false;
	}
	
	public List<String> autocomplete(String word){
		TrieNode aux= root;
		for (int i = 0; i < word.length(); i++) {
			aux= aux.getChild(word.charAt(i));
			if(aux== null) {
				return new ArrayList<>();
			}
		}
		return aux.getWords();
	}
}
