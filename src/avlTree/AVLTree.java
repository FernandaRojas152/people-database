package avlTree;
import binarySearchTree.BinarySearchTree;

public class AVLTree<K extends Comparable<K>, T> extends BinarySearchTree<K,T> {
	public AVLTree() {
	}
	
	public boolean add(K key, T value) {
		AVLNode<K,T> child= new AVLNode<>(key, value);
		boolean added= addNode(child);
		if(added) {
			AVLNode<K,T> parent= (AVLNode<K, T>) child.getParent();
			while(parent!=null) {
				if(child== parent.getLeft()) {
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
		return added;
	}

	public void delete(K key, T value) {
		AVLNode<K,T> child= new AVLNode<>(key, value);
		deleteNode(key);
			AVLNode<K,T> parent= (AVLNode<K, T>) child.getParent();
			while(parent!=null) {
				if(child== parent.getLeft()) {
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
	
	/**
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void delete(AVLNode<K,T> child) {
		AVLNode<K,T> aux= null;
		for(AVLNode x = (AVLNode) child.getParent(); x != null; x = aux) { 
			aux = (AVLNode) x.getParent(); 
			if (child == x.getLeft()) {
				if (x.getBalanceFactor() < 0) { 
					AVLNode Z = (AVLNode) x.getRight();
					int b = Z.getBalanceFactor();
					if (b > 0) {
						rotateRight(Z);
//						Z.updateFactorBalance();
						rotateLeft(x);
//						x.updateFactorBalance();
					}else {
						rotateLeft(x);
//						x.updateFactorBalance();
					}
				} else {
					if (x.getBalanceFactor() == 0) {
						x.setBalanceFactor(-1);
						break; 
					}
					child = x;
					child.setBalanceFactor(0);
					continue;
				}
			} else { 
				if (x.getBalanceFactor() > 0) {
					AVLNode Z = (AVLNode) x.getLeft();
					int b = Z.getBalanceFactor();
					if (b < 0) {
						rotateLeft(Z);
//						Z.updateFactorBalance();
						rotateRight(x);
//						x.updateFactorBalance();
					}else {
						rotateRight(x);
//						x.updateFactorBalance();
					}
				} else {
					if (x.getBalanceFactor() == 0) {
						x.setBalanceFactor(1);
						break; 
					}
					child = x;
					child.setBalanceFactor(0);
					continue;
				}
			}
		}
		
	}
	*/
	public static void main(String[] args) {
		AVLTree<Integer, Integer> prueba= new AVLTree<>();
		prueba.add(3, 5);
		System.out.println(prueba.getRoot().getV());
		prueba.add(2, 15);
		System.out.println(prueba.getRoot().getV());
		prueba.add(1, 10);
		System.out.println(prueba.getRoot().getV());
		prueba.delete(2,15);
		System.out.println(prueba.getRoot().getV());
	}
}