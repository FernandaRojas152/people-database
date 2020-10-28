package trie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TrieNode {
	boolean end;
	private char data;
	LinkedList<TrieNode> children;
	TrieNode parent;

	public TrieNode(char c) {
		end= false;
		children= new LinkedList<>();
		data= c;
	}

	public TrieNode getChild(char c) {
		if(children!=null)
			for (TrieNode trieNode : children)
				if(trieNode.data== c)
					return trieNode;
		return null;
	}
	public List<String> getWords(){
		List<String>words = new ArrayList<>();
		if(end) {
			words.add(toString());
		}
		if(children!=null) {
			for (int i = 0; i < children.size(); i++) {
				if(children.get(i)!=null) {
					words.addAll(children.get(i).getWords());
				}
			}
		}
		return words;
	}
	
	public String toString() {
		if(parent==null) {
			return "";
		}else {
			return parent.toString() + new String(new char[] {data});
		}
	}
}
