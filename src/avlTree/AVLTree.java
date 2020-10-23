package avlTree;

import binarySearchTree.BinarySearchTree;

public class AVLTree<K extends Comparable<K>, T> extends BinarySearchTree<K,T> {
	
	public AVLTree() {
		
	}
	
	public void add(K key, T value) {
		AVLNode<K,T> child= new AVLNode<>(key, value);
		AVLNode<K,T> parent= (AVLNode<K, T>) child.getParent();
		while(parent!=null) {
			if(parent== parent.getLeft()) {
				if(parent.getBalanceFactor()== 1) {
					if(child.getBalanceFactor()== -1) {
						rotateLeft(child);
					}
					rotateRight(parent);
					break;
				}
				if(parent.getBalanceFactor()== -1) {
					parent.setBalanceFactor(0);
					break;
				}
				parent.setBalanceFactor(1);
			}else {
				if(parent.getBalanceFactor()==-1) {
					if(child.getBalanceFactor()== 1) {
						rotateRight(child);
					}
					rotateLeft(parent);
					break;
				}
				if(parent.getBalanceFactor()== 1) {
					parent.setBalanceFactor(0);
					break;
				}
				parent.setBalanceFactor(-1);
			}
			child= parent;
			parent= (AVLNode<K, T>) child.getParent();
		}
	}
	
	public void delete() {
		
	}
	
	public void search() {
		
	}
	
	public static void main(String[] args) {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		tree.add("c", "4");
		tree.add("b", "6");
		tree.add("a", "2");
		tree.add("d", "1");
		System.out.println(tree.getWeight());
	}
}
